 /**
 * 文件名称：
 * 版权所有：Copyright gesoft
 * 创建时间：2017-06-28 11:58:20
 * 创 建 人：WCL (ln_admin@yeah.net)
 * 功能描述：
 **/
package com.gesoft.service;

import javax.annotation.Resource;

import com.gesoft.model.MsgModel;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.gesoft.common.EntityDAO;
import com.gesoft.common.EntityService;
import com.gesoft.dao.OrderDAO;
import com.gesoft.model.OrderModel;

import java.util.List;

 @Service
@Transactional
public class OrderService extends EntityService<OrderModel, Long>
{

	@Resource
	private OrderDAO orderDAO;
	
	@Override
	protected EntityDAO<OrderModel, Long> getEntityDao()
	{
		return this.orderDAO;
	}

	public int  updateOrderStatus(OrderModel orderModel){
		return orderDAO.updateOrderStatus(orderModel);
	}
	
	public List<OrderModel> getLadderPrice(OrderModel model){
		return orderDAO.getLadderPrice(model);
	}

	public void findPageOrderSell(OrderModel model, MsgModel msgModel) {
		//取总记录数
		long recordCount = model.getTotal();
		if (isSearchPageTotal(model)) {
			recordCount = orderDAO.findCntOrderSell(model);
		}
		//分页加载数据
		if (recordCount > 0) {
			setPageModel(recordCount, model);
			List <OrderModel> argArgs = orderDAO.findListOrderSell(model);
			if (argArgs != null) {
				msgModel.setData(argArgs);
				msgModel.setTotal(recordCount);
			}
		}
	}


	public List<OrderModel> getBluePrintLadderPrice(OrderModel model){
		return orderDAO.getBluePrintLadderPrice(model);
	}
	
	public List<OrderModel> queryMaterialNum(OrderModel model){
		return orderDAO.queryMaterialNum(model);
	}
	
	public List<OrderModel> queryMaterialNum2(OrderModel model){
		return orderDAO.queryMaterialNum2(model);
	}
	
}
