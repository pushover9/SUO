package com.kubg.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.kubg.domain.CategoryVO;
import com.kubg.domain.GoodsVO;
import com.kubg.domain.OrderListVO;
import com.kubg.domain.OrderVO;
import com.kubg.domain.ReplyListVO;
import com.kubg.domain.GoodViewVO;
import com.kubg.persistence.AdminDAO;

@Service
public class AdminServiceImpl implements AdminService {
	
	@Autowired
	private AdminDAO adminDAO;

	@Override
	public List<CategoryVO> category() {
		return adminDAO.category();
	}

	@Override
	public void register(GoodsVO vo) {
		adminDAO.register(vo);
		
	}

	@Override
	public List<GoodViewVO> goodslist() {
		return adminDAO.goodslist();
	}

	@Override
	public GoodViewVO goodsView(int gdsNum) {
		return adminDAO.goodsView(gdsNum);
	}

	@Override
	public void goodsModify(GoodsVO vo) {
		adminDAO.goodsModify(vo);
		
	}

	@Override
	public void goodsDelete(int gdsNum) {
		adminDAO.goodsDelete(gdsNum);
		
	}

	@Override
	public List<OrderVO> orderList() {
		return adminDAO.orderList();
	}

	@Override
	public List<OrderListVO> orderView(OrderVO order) {
		return adminDAO.orderView(order);
	}

	@Override
	public void delivery(OrderVO order) {
		adminDAO.delivery(order);
		
	}

	@Override
	public void changeStock(GoodsVO goods) {
		adminDAO.changeStock(goods);
		
	}

	@Override
	public List<ReplyListVO> allReply() {
		return adminDAO.allReply();
	}

	@Override
	public void deleteReply(int repNum) {
		adminDAO.deleteReply(repNum);
		
	}

	@Override
	public void deleteAllreply(int gdsNum) {
		adminDAO.deleteAllrepy(gdsNum);
		
	}
	

	
}
