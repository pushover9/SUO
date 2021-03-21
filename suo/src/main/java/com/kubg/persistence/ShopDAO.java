package com.kubg.persistence;

import java.util.List;

import com.kubg.domain.CartVO;
import com.kubg.domain.GoodViewVO;
import com.kubg.domain.OrderDetailVO;
import com.kubg.domain.OrderListVO;
import com.kubg.domain.OrderVO;
import com.kubg.domain.ReplyListVO;
import com.kubg.domain.ReplyVO;

public interface ShopDAO {

	List<GoodViewVO> list(int cateCode, int level);
	
	List<GoodViewVO> list(int cateCode);

	GoodViewVO goodsView(int gdsNum);

	void registReply(ReplyVO reply);

	List<ReplyListVO> replyList(int gdsNum);

	String idCheck(int repNum);

	Object deleteReply(ReplyVO reply);

	void modifyReply(ReplyVO reply);

	void addCart(CartVO cart);

	List<CartVO> cartList(String userId);

	void deleteCart(CartVO cart);

	void orderInfo(OrderVO order);

	void orderInfo_Details(OrderDetailVO orderDetail);

	void cartALLDelete(String userId);

	List<OrderListVO> orderView(OrderVO order);

	List<OrderVO> orderList(OrderVO order);

	

}
