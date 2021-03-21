package com.kubg.service;

import java.util.List;

import com.kubg.domain.CategoryVO;
import com.kubg.domain.GoodViewVO;
import com.kubg.domain.GoodsVO;
import com.kubg.domain.OrderListVO;
import com.kubg.domain.OrderVO;
import com.kubg.domain.ReplyListVO;

public interface AdminService {

	List<CategoryVO> category();

	void register(GoodsVO vo);

	List<GoodViewVO> goodslist();

	GoodViewVO goodsView(int gdsNum);

	void goodsModify(GoodsVO vo);

	void goodsDelete(int gdsNum);

	List<OrderVO> orderList();

	List<OrderListVO> orderView(OrderVO order);

	void delivery(OrderVO order);
	
	public void changeStock(GoodsVO goods);

	List<ReplyListVO> allReply();
	
	void deleteReply(int repNum);

	void deleteAllreply(int gdsNum);

}
