package com.kubg.persistence;

import java.util.List;

import com.kubg.domain.CategoryVO;
import com.kubg.domain.GoodsVO;
import com.kubg.domain.OrderListVO;
import com.kubg.domain.OrderVO;
import com.kubg.domain.ReplyListVO;
import com.kubg.domain.GoodViewVO;

public interface AdminDAO {

	List<CategoryVO> category();

	void register(GoodsVO vo);

	List<GoodViewVO> goodslist();

	GoodViewVO goodsView(int gdsNum);

	void goodsModify(GoodsVO vo);

	void goodsDelete(int gdsNum);

	List<OrderVO> orderList();

	List<OrderListVO> orderView(OrderVO order);

	void delivery(OrderVO order);

	void changeStock(GoodsVO goods);

	List<ReplyListVO> allReply();

	void deleteReply(int repNum);

	void deleteAllrepy(int gdsNum);

}
