package com.kubg.persistence;

import java.util.List;

import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.kubg.domain.CategoryVO;
import com.kubg.domain.GoodViewVO;
import com.kubg.domain.GoodsVO;
import com.kubg.domain.OrderListVO;
import com.kubg.domain.OrderVO;
import com.kubg.domain.ReplyListVO;

@Repository
public class AdminDAOImpl implements AdminDAO {

	@Autowired
	private SqlSession session;

	@Override
	public List<CategoryVO> category() {
		return session.selectList("category");
	}

	@Override
	public void register(GoodsVO vo) {
		session.insert("register",vo);
		
	}

	@Override
	public List<GoodViewVO> goodslist() {
		return session.selectList("goodslist");
	}

	@Override
	public GoodViewVO goodsView(int num) {
		return session.selectOne("goodsView",num);
	}

	@Override
	public void goodsModify(GoodsVO vo) {
		session.update("goodsModify",vo);
		
	}

	@Override
	public void goodsDelete(int gdsNum) {
		session.delete("goodsDelete",gdsNum);
		
	}

	@Override
	public List<OrderVO> orderList() {
		return session.selectList("orderList1");
	}

	@Override
	public List<OrderListVO> orderView(OrderVO order) {
		return session.selectList("orderView1",order);
	}

	@Override
	public void delivery(OrderVO order) {
		session.update("delivery",order);
		
	}

	@Override
	public void changeStock(GoodsVO goods) {
		session.update("changeStock",goods);
		
	}

	@Override
	public List<ReplyListVO> allReply() {
		return session.selectList("allReply");
	}

	@Override
	public void deleteReply(int repNum) {
		session.delete("deleteReply",repNum);
	}

	@Override
	public void deleteAllrepy(int gdsNum) {
		session.delete("deleteAllReply",gdsNum);
		
	}
	

	
}
