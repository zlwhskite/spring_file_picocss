package com.admini.product;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.admini.file.FileService;
import com.admini.file.FileVo;
import com.admini.file.Filestore;

@Controller
public class ProductController {
	@Autowired
	ProductService productService;
	@Autowired
	Filestore filestore;
	@Autowired
	FileService fileService;
	
	@GetMapping("/")
	public String home(Model model) {
		List<ProductVo> productList = productService.findAll();
		List<FileVo> fileList = fileService.distinctFindAll();
		
		model.addAttribute("productList", productList);
		model.addAttribute("fileList", fileList);
		
		return "product/index";
	}
	
	@GetMapping("/create")
	public String index() {
		return "product/productCreate";
	}

	@PostMapping("/create")
	public String create(@ModelAttribute ProductVo productVo, @RequestParam("files") List<MultipartFile> multipartFiles, RedirectAttributes rttr) 
			throws IOException {
		ProductVo product = new ProductVo();
		FileVo fileVo = new FileVo();
		product.setProductName(productVo.getProductName());
		product.setQuantity(productVo.getQuantity());

		int result = productService.create(productVo);
		product = productService.findByName(product.getProductName());
		
		if(result == 1) {
			
			List<String> fileList = filestore.storeFiles(multipartFiles);
			
			if(!fileList.isEmpty()) {
				for(String src : fileList) {
					fileVo.setProductId(product.getId());		
					fileVo.setSrc(src);	
					
					fileService.create(fileVo);
				}
			}else {
				fileVo.setProductId(product.getId());		
				fileVo.setSrc("/products/noimage.png");
				
				fileService.create(fileVo);
			}
			
			rttr.addFlashAttribute("msg", "등록에 성공했습니다.");
			return "redirect:/create";
		}else {			
			rttr.addFlashAttribute("msg", "등록에 실패했습니다.");
			return "redirect:/create";
		}
	}
}
