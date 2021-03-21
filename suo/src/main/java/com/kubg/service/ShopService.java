package com.kubg.service;

import java.util.List;

import com.kubg.domain.CartVO;
import com.kubg.domain.GoodViewVO;
import com.kubg.domain.OrderDetailVO;
import com.kubg.domain.OrderListVO;
import com.kubg.domain.OrderVO;
import com.kubg.domain.ReplyListVO;
import com.kubg.domain.ReplyVO;

public interface ShopService {

	List<GoodViewVO> list(int cateCode, int level);

	GoodViewVO goodsView(int gdsNum);

	void registReply(ReplyVO reply);

	List<ReplyListVO> replyList(int gdsNum);

	String idCheck(int repNum);

	void deleteReply(ReplyVO reply);

	void modifyReply(ReplyVO reply);

	void addCart(CartVO cart);

	List<CartVO> cartList(String userId);

	void deleteCart(CartVO cart);

	void orderInfo(OrderVO order);

	void orderInfo_Details(OrderDetailVO orderDetail);

	void cartAllDelete(String userId);

	List<OrderListVO> orderView(OrderVO order);

	List<OrderVO> orderList(OrderVO order);

}
