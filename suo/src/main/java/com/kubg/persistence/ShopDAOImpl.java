package com.kubg.persistence;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kubg.domain.CartVO;
import com.kubg.domain.GoodViewVO;
import com.kubg.domain.OrderDetailVO;
import com.kubg.domain.OrderListVO;
import com.kubg.domain.OrderVO;
import com.kubg.domain.ReplyListVO;
import com.kubg.domain.ReplyVO;

@Repository
public class ShopDAOImpl implements ShopDAO {
	
	@Autowired
	private SqlSession sqlsession;

	@Override
	public List<GoodViewVO> list(int cateCode, int cateCodeRef) {
		
		HashMap<String, Object> map = new HashMap<String, Object>();
		
		map.put("cateCode", cateCode);
		map.put("cateCodeRef", cateCodeRef);
		
		return sqlsession.selectList("list_1", map);
	}
	
	// 카테고리별 상품 리스트 : 2차 분류
	@Override
	public List<GoodViewVO> list(int cateCode){
		
		return sqlsession.selectList("list_2", cateCode);
	}

	@Override
	public GoodViewVO goodsView(int gdsNum) {
		return sqlsession.selectOne("goodsView", gdsNum);
	}

	@Override
	public void registReply(ReplyVO reply) {
			sqlsession.insert("registReply",reply);
		
	}

	@Override
	public List<ReplyListVO> replyList(int gdsNum) {
		return sqlsession.selectList("replyList",gdsNum);
	}

	@Override
	public String idCheck(int repNum) {
		return sqlsession.selectOne("replyUserIdCheck",repNum);
	}

	@Override
	public Object deleteReply(ReplyVO reply) {
		return sqlsession.delete("deleteReply1",reply);
	}

	@Override
	public void modifyReply(ReplyVO reply) {
		sqlsession.update("modifyReply",reply);
		
	}

	@Override
	public void addCart(CartVO cart) {
		sqlsession.insert("addCart",cart);
		
	}

	@Override
	public List<CartVO> cartList(String userId) {
		return sqlsession.selectList("cartList",userId);
	}

	@Override
	public void deleteCart(CartVO cart) {
		sqlsession.delete("deleteCart",cart);
		
	}

	@Override
	public void orderInfo(OrderVO order) {
		sqlsession.insert("orderInfo", order);
		
	}

	@Override
	public void orderInfo_Details(OrderDetailVO orderDetail) {
		sqlsession.insert("orderInfo_Details",orderDetail);
		
	}

	@Override
	public void cartALLDelete(String userId) {
		sqlsession.delete("cartAlldelete",userId);
		
	}

	@Override
	public List<OrderListVO> orderView(OrderVO order) {
		return sqlsession.selectList("orderView",order);
	}

	@Override
	public List<OrderVO> orderList(OrderVO order) {
		return sqlsession.selectList("orderList",order);
	}
	

	
	

}
