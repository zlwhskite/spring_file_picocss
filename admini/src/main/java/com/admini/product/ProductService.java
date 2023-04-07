package com.admini.product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
	@Autowired
	ProductMapper productMapper;
	
	public List<ProductVo> findAll() {
		return productMapper.findAll();
	}
	
	public int create(ProductVo productVo) {
		int result = productMapper.create(productVo);
		
		return result;
	}
	
	public ProductVo findByName(String productName) {
		return productMapper.findByName(productName);
	}

}
