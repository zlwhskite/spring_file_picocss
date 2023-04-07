package com.admini.file;

public class FileVo {
	private int id;
	private int productId;
	private String src;
	
	public FileVo() {}

	public FileVo(int id, int productId, String src) {
		super();
		this.id = id;
		this.productId = productId;
		this.src = src;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getProductId() {
		return productId;
	}

	public void setProductId(int productId) {
		this.productId = productId;
	}

	public String getSrc() {
		return src;
	}

	public void setSrc(String src) {
		this.src = src;
	}

}
