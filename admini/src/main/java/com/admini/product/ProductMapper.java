package com.admini.product;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ProductMapper {
	List<ProductVo> findAll();
	int create(ProductVo productVo);
	ProductVo findByName(String productName);
}
