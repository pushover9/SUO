package com.kubg.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kubg.domain.CartVO;
import com.kubg.domain.GoodViewVO;
import com.kubg.domain.OrderDetailVO;
import com.kubg.domain.OrderListVO;
import com.kubg.domain.OrderVO;
import com.kubg.domain.ReplyListVO;
import com.kubg.domain.ReplyVO;
import com.kubg.persistence.ShopDAO;

@Service
public class ShopServiceImpl implements ShopService {

	@Autowired
	private ShopDAO shopdao;
	
	@Override
	public List<GoodViewVO> list(int cateCode, int level) {
		return shopdao.list(cateCode, level);
	}

	@Override
	public GoodViewVO goodsView(int gdsNum) {
		return shopdao.goodsView(gdsNum);
	}

	@Override
	public void registReply(ReplyVO reply) {
		shopdao.registReply(reply);
		
	}

	@Override
	public List<ReplyListVO> replyList(int gdsNum) {
		return shopdao.replyList(gdsNum);
	}

	@Override
	public String idCheck(int repNum) {
		return shopdao.idCheck(repNum);
	}

	@Override
	public void deleteReply(ReplyVO reply) {
		shopdao.deleteReply(reply);
		
	}

	@Override
	public void modifyReply(ReplyVO reply) {
		shopdao.modifyReply(reply);
		
	}

	@Override
	public void addCart(CartVO cart) {
		shopdao.addCart(cart);
		
	}

	@Override
	public List<CartVO> cartList(String userId) {
		return shopdao.cartList(userId);
	}

	@Override
	public void deleteCart(CartVO cart) {
		shopdao.deleteCart(cart);
		
	}

	@Override
	public void orderInfo(OrderVO order) {
		shopdao.orderInfo(order);
		
	}

	@Override
	public void orderInfo_Details(OrderDetailVO orderDetail) {
		shopdao.orderInfo_Details(orderDetail);
		
	}

	@Override
	public void cartAllDelete(String userId) {
		shopdao.cartALLDelete(userId);
		
	}

	@Override
	public List<OrderListVO> orderView(OrderVO order) {
		return shopdao.orderView(order);
	}

	@Override
	public List<OrderVO> orderList(OrderVO order) {
		return shopdao.orderList(order);
		}

}
