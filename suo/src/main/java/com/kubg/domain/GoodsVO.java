package com.kubg.domain;

import java.util.Date;

import lombok.Data;

@Data
public class GoodsVO {
/*  
    gdsNum       number          not null,
    gdsName      varchar2(50)    not null,
    cateCode     varchar2(30)    not null,
    gdsPrice     number          not null,
    gdsStock     number          null,
    gdsDes       varchar(500)    null,
    gdsImg       varchar(200)    null,
    gdsDate      date            default sysdate,
*/
	private int gdsNum;
	private String gdsName;
	private String cateCode;	
	private int gdsPrice;
	private int gdsStock;
	private String gdsDes;
	private String gdsImg;
	private Date gdsDate;
	
	private String gdsThumbImg;
	
	
}