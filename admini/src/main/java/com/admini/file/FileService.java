package com.admini.file;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FileService {
	@Autowired
	FileMapper fileMapper;
	
	public List<FileVo> findAll() {
		return fileMapper.findAll();
	}
	
	public List<FileVo> distinctFindAll() {
		return fileMapper.distinctFindAll();
	}
	
	public int create(FileVo fileVo) {
		int result = fileMapper.create(fileVo);
		
		return result;
	}

}
