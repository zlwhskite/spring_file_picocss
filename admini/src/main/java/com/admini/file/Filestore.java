package com.admini.file;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class Filestore {
	@Value("${file.dir}")
	private String fileDir;
	

	public List<String> storeFiles(List<MultipartFile> multipartFiles) throws IOException{
		List<String> storeFileResult = new ArrayList<>();
		
		for(MultipartFile multipartFile : multipartFiles) {
			if(!multipartFile.isEmpty()) {
				storeFileResult.add(storeFile(multipartFile));
			}
		}
		
		return storeFileResult;
	}
	
	public String storeFile(MultipartFile multipartFile) throws IOException {
		if (multipartFile.isEmpty()) {
			return null;
		}
		
		String originalFilename = multipartFile.getOriginalFilename();
		String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
		String savedFileName = UUID.randomUUID() + extension;
		File resultFile = new File(fileDir + savedFileName);
		
		try {
			multipartFile.transferTo(resultFile);
		}catch (IOException e) {
			resultFile.delete();
			e.printStackTrace();
		}
		
		String src = "/products/" + savedFileName;
		
		return src;
	}

}
