package com.kubg.domain;

import java.util.Date;

import lombok.Data;

@Data
public class CartVO {
	
	private int cartNum;
	private String userId;
	private int gdsId;
	private int gdsNum;
	private int cartStock;
	private Date addDate;
	
	private int num;
	private String gdsName;
	private int gdsPrice;
	private String gdsThumbImg;

}
