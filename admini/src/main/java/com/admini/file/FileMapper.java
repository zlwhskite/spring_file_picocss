package com.admini.file;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface FileMapper {
	List<FileVo> findAll();
	List<FileVo> distinctFindAll();
	int create(FileVo fileVo);
}
