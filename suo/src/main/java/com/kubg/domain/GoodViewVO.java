package com.kubg.domain;

import java.util.Date;

import lombok.Data;

@Data
public class GoodViewVO {
	
	private int gdsNum;
	private String gdsName;
	private String cateCode;
	private int gdsPrice;
	private int gdsStock;
	private String gdsDes;
	private String gdsImg;
	private Date gdsDate;
	
	private String cateCodeRef;
	private String cateName;
	
	private String gdsThumbImg;
	
	

}
