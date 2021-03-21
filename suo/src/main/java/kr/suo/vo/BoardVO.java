package kr.suo.vo;

import lombok.Data;

@Data
public class BoardVO {
	private int bno;
	private String writer;
	private String title;
	private String content;
	private int viewcnt;
	private String regdate;
	private int reply_cnt;
	private String category;
	private String find_name;
	private String find_field;
	
	private int startrow;
	private int endrow;
	
	private int file_num;
	private String o_name;
	private String file_name;
	private int file_size;
	
	private String bno_seq;
	

	
}
