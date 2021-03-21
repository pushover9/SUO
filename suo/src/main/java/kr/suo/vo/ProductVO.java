package kr.suo.vo;

import lombok.Data;

@Data
public class ProductVO {
	private String name;
	private double price;
	
	public ProductVO(String name, double price) {
		this.name = name;
		this.price =price;
	}
}

