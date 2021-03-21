package com.kubg.domain;

import java.util.Date;

import lombok.Data;

@Data
public class ReplyListVO {
		
	private int gdsNum;
	private String userId;
	
	private String repCon;
	private Date repDate;
	private int repNum;
	
	private String userName;
	
	
}
