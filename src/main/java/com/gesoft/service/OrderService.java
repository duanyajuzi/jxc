 /**
 * 文件名称：
 * 版权所有：Copyright gesoft
 * 创建时间：2017-06-28 11:58:20
 * 创 建 人：WCL (ln_admin@yeah.net)
 * 功能描述：
 **/
package com.gesoft.service;

import javax.annotation.Resource;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.gesoft.dao.OrderItemDAO;
import com.gesoft.dao.UserDAO;
import com.gesoft.model.MsgModel;
import com.gesoft.model.OrderItemModel;
import com.gesoft.model.UserModel;
import com.gesoft.util.Md5Util;
import com.gesoft.util.StringOrderNoUtil;
import org.aspectj.weaver.ast.Or;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.gesoft.common.EntityDAO;
import com.gesoft.common.EntityService;
import com.gesoft.dao.OrderDAO;
import com.gesoft.model.OrderModel;

import java.util.List;

import static com.gesoft.util.Constants.GLOBAL_MSG_BOOL_FAIL;
import static com.gesoft.util.Constants.GLOBAL_MSG_BOOL_SUCCESS;

 @Service
 @Transactional
public class OrderService extends EntityService<OrderModel, String>
{

	@Resource
	private OrderDAO orderDAO;
	@Resource
	private OrderItemDAO orderItemDAO;
	@Resource
	private UserDAO userDAO;
	
	@Override
	protected EntityDAO<OrderModel, String> getEntityDao()
	{
		return this.orderDAO;
	}

	public int  updateOrderStatus(OrderModel orderModel){
		return orderDAO.updateOrderStatus(orderModel);
	}
	
	public List<OrderModel> getLadderPrice(OrderModel model){
		return orderDAO.getLadderPrice(model);
	}
	
	public void add(OrderModel model, MsgModel msgModel){
		
		if("1".equals(model.getZdsc())){
			//检查子项是否有客户方案
			if(!checkItem(model)){
				msgModel.setSuccess(GLOBAL_MSG_BOOL_FAIL);
				msgModel.setMsg("订单项中商品未添加客户方案，不能自动生成订单");
				return;
			}
		}
		
		model.setOrderStatus(1);
		orderDAO.save(model);
		//采购订单项保存
		if(model.getOrderType() == 0){
			//保存子项
			editItem(model);
			
			//自动生成销售订单
			if("1".equals(model.getZdsc())){
				String id2 = Md5Util.UUID();
				model.setId(id2);
				model.setOrderType(1);
				model.setOrderStatus(0);
				model.setIskh("0");
				model.setZdsc("0");
				long userid = model.getCuserid();
				UserModel userModel = (UserModel)userDAO.get(userid);
				String orderTitle = userModel.getOrderTitle();
				model.setOrderNo(orderTitle+ StringOrderNoUtil.getOrderNo());
				orderDAO.save(model);
				//复制子项
				editItem2(model);
			}
		}else{//销售订单项保存
			editSellItem(model);
		}
		msgModel.setSuccess(GLOBAL_MSG_BOOL_SUCCESS);
	}
	
	public void modify(OrderModel model, MsgModel msgModel){
		orderDAO.update(model);
		if(model.getOrderType() == 0){//采购订单项保存
			editItem(model);
		}else{//销售订单项保存
			editSellItem(model);
		}
		msgModel.setSuccess(GLOBAL_MSG_BOOL_SUCCESS);
	}
	
	public void del(OrderModel model, MsgModel msgModel){
		String orderId = model.getId();
		OrderItemModel itemModel = new OrderItemModel();
		itemModel.setOrderId(orderId);
		//根据订单id查询订单项id
		List<OrderItemModel> itemList2 = orderItemDAO.getItemIdByOrderId(itemModel);
		//根据已出库/入库数量恢复库存
		if(model.getOrderType() == 0) {//采购订单
			//查询子项列表
			List<OrderItemModel> itemList = orderItemDAO.findList2(itemModel);
			for(OrderItemModel item : itemList){
				item.setAfterNum(-item.getTmpNum());
				orderItemDAO.updateStorage(item);
			}
			for(OrderItemModel item : itemList2){
				//根据订单项id删除出入库记录
				orderItemDAO.deleteInStockByItemId(item);
			}
		}else{//销售订单
			List<OrderItemModel> itemList = orderItemDAO.findList(itemModel);
			for(OrderItemModel item : itemList){
				item.setAfterNum(item.getTmpNum());
				item.setCustomerGoodId(item.getGoodsId());
				orderItemDAO.updateStorage(item);
			}
			for(OrderItemModel item : itemList2){
				//根据订单项id删除出入库记录
				orderItemDAO.deleteOutStockByItemId(item);
			}
		}
		
		orderDAO.delete(model);
		msgModel.setSuccess(GLOBAL_MSG_BOOL_SUCCESS);
	}
	
	
	/**
	 * 描述信息：检查订单子项是否有客户方案
	 * @return
	 */
	public boolean checkItem(OrderModel model)
	{
		//客户id
		int customerId = model.getPcustomerId();
		
		String data = model.getData();
		JSONArray jsArr = JSONArray.parseArray(data);
		OrderItemModel itemModelTmp;
		for(Object obj : jsArr){
			itemModelTmp = new OrderItemModel();
			JSONObject jsonObject = JSONObject.parseObject(obj.toString());
			String customerGoodId = jsonObject.get("customerGoodId").toString();
			itemModelTmp.setCustomerGoodId(customerGoodId);
			itemModelTmp.setCustomerId(customerId);
			
			//根据customerGoodId和customerId查询blueprint主键
			OrderItemModel orderItemTemp = orderItemDAO.getBluePrintInfo(itemModelTmp);
			if(orderItemTemp == null){
				return false;
			}
		}
		return true;
	}
	
	/**
	 * 描述信息：修改采购订单子项
	 * @return
	 */
	public void editItem(OrderModel model)
	{
		try{
			String orderId = model.getId();
			String iskh = model.getIskh();
			String data = model.getData();
			long userid = model.getCuserid();
			JSONArray jsArr = JSONArray.parseArray(data);
			OrderItemModel itemModel;
			OrderItemModel inoutStockModel;
			for(Object obj : jsArr){
				itemModel = new OrderItemModel();
				JSONObject jsonObject = JSONObject.parseObject(obj.toString());
				double price = Double.parseDouble(jsonObject.get("price").toString());
				itemModel.setOrderId(orderId);
				itemModel.setUnitPrice(price);
				itemModel.setCustomerGoodId(jsonObject.get("customerGoodId").toString());
				itemModel.setDeliveryTime(jsonObject.get("deliveryTime").toString());
				itemModel.setSortIndex(jsonObject.get("sortIndex").toString());
				Long esgouNum = Long.parseLong(jsonObject.get("esgouNum").toString());
				itemModel.setEsgouNum(esgouNum);
				itemModel.setTmpNum(0L);
				
				String state = jsonObject.get("_state").toString();
				if("added".equals(state)){
					//是否控货处理
					if("0".equals(iskh)){//不控货
						itemModel.setTmpNum(esgouNum);
						//添加库存
						itemModel.setAfterNum(esgouNum);
						orderItemDAO.updateStorage(itemModel);
					}
					String id = Md5Util.UUID();
					itemModel.setId(id);
					orderItemDAO.save(itemModel);
					if(model.getOrderType() == 0 && "0".equals(model.getIskh())){
						inoutStockModel = new OrderItemModel();
						inoutStockModel.setOrderItemId(id);
						inoutStockModel.setGoodNum(esgouNum);
						inoutStockModel.setOrderType(0);
						inoutStockModel.setCreateUserId(userid);
						inoutStockModel.setModifyUserId(userid);
						orderItemDAO.insertInStockItem(inoutStockModel);
					}
					
				}else if("modified".equals(state)){
					itemModel.setId(jsonObject.get("id").toString());
					//不控货时，数目小于已入库存时，temNum=num,afterNum=num-temNum
					//控货时，num>temNum时，temNum不需要赋值，其他同不控货
					Long orderItemTepNum = orderItemDAO.getOrderItemTepNum(itemModel);
					if(orderItemTepNum >= esgouNum){//数目小于已入库存时
						Long afterNum = esgouNum - orderItemTepNum;
						itemModel.setAfterNum(afterNum);
						itemModel.setTmpNum(esgouNum);
					}else{//数目大于已入库存时
						if("0".equals(iskh)){//不控货
							itemModel.setTmpNum(esgouNum);
							//添加库存
							Long afterNum = esgouNum - orderItemTepNum;
							itemModel.setAfterNum(afterNum);
						}else{
							itemModel.setTmpNum(orderItemTepNum);
							itemModel.setAfterNum(0L);
						}
					}
					orderItemDAO.updateStorage(itemModel);
					orderItemDAO.update(itemModel);
				}else if("removed".equals(state)){
					itemModel.setId(jsonObject.get("id").toString());
					//删除时库存处理
					//查询已入库数量
					Long orderItemTepNum = orderItemDAO.getOrderItemTepNum(itemModel);
					itemModel.setAfterNum(-orderItemTepNum);
					orderItemDAO.updateStorage(itemModel);
					
					orderItemDAO.delete(itemModel);
				}
				//新增操作，不控货需要添加库存，控货不做任何操作
				//删除操作，修改订单时，需要根据temNum修改库存
				//修改操作，修改订单时，需要根据temNum和num修改库存
				// temNum=10 oldnum=10 num=8  =>temNum=8 库存-2
				// temNum=10 oldnum=15 num=5  =>temNum=5 库存-5
				// temNum=10 oldnum=15 num=13 +>不变
				
				//删除订单时，回归库存 加减temNum
				
			}
		}catch (Exception e){
			e.printStackTrace();
		}
		
	}
	
	/**
	 * 描述信息：修改销售订单子项
	 * @return
	 */
	public void editSellItem(OrderModel model)
	{
		try{
			String orderId = model.getId();
			
			String data = model.getData();
			JSONArray jsArr = JSONArray.parseArray(data);
			OrderItemModel itemModel;
			for(Object obj : jsArr){
				itemModel = new OrderItemModel();
				JSONObject jsonObject = JSONObject.parseObject(obj.toString());
				
				itemModel.setOrderId(orderId);
				itemModel.setUnitPrice(Double.parseDouble(jsonObject.get("price").toString()));
				itemModel.setCustomerGoodId(jsonObject.get("customerGoodId").toString());
				itemModel.setDeliveryTime(jsonObject.get("deliveryTime").toString());
				itemModel.setSortIndex(jsonObject.get("sortIndex").toString());
				itemModel.setTmpNum(0L);
				Long esgouNum = Long.parseLong(jsonObject.get("esgouNum").toString());
				itemModel.setEsgouNum(esgouNum);
				String state = jsonObject.get("_state").toString();
				if("added".equals(state)){
					itemModel.setId(Md5Util.UUID());
					orderItemDAO.save(itemModel);
				}else if("modified".equals(state)){
					itemModel.setId(jsonObject.get("id").toString());
					Long orderItemTepNum = orderItemDAO.getOrderItemTepNum(itemModel);
					if(orderItemTepNum >= esgouNum){//数目小于已出库存时
						Long afterNum = orderItemTepNum - esgouNum;
						itemModel.setAfterNum(afterNum);
						itemModel.setTmpNum(esgouNum);
					}else{//数目大于已出库存时
						itemModel.setTmpNum(orderItemTepNum);
						itemModel.setAfterNum(0L);
					}
					orderItemDAO.update(itemModel);
					itemModel.setCustomerGoodId(jsonObject.get("goodsId").toString());
					orderItemDAO.updateStorage(itemModel);
				}else if("removed".equals(state)){
					itemModel.setId(jsonObject.get("id").toString());
					//删除时库存处理
					//查询已出库数量
					Long orderItemTepNum = orderItemDAO.getOrderItemTepNum(itemModel);
					itemModel.setAfterNum(orderItemTepNum);
					orderItemDAO.delete(itemModel);
					itemModel.setCustomerGoodId(jsonObject.get("goodsId").toString());
					orderItemDAO.updateStorage(itemModel);
				}
			}
		}catch (Exception e){
			e.printStackTrace();
		}

	}
	
	/**
	 * 描述信息：复制订单子项
	 * @return
	 */
	public void editItem2(OrderModel model)
	{
		try{
			String orderId = model.getId();
			//客户id
			int customerId = model.getPcustomerId();
			
			String data = model.getData();
			JSONArray jsArr = JSONArray.parseArray(data);
			OrderItemModel itemModel;
			OrderItemModel itemModelTmp;
			for(Object obj : jsArr){
				itemModelTmp = new OrderItemModel();
				JSONObject jsonObject = JSONObject.parseObject(obj.toString());
				String customerGoodId = jsonObject.get("customerGoodId").toString();
				itemModelTmp.setCustomerGoodId(customerGoodId);
				itemModelTmp.setCustomerId(customerId);
				
				//根据customerGoodId和customerId查询blueprint主键
				OrderItemModel orderItemTemp = orderItemDAO.getBluePrintInfo(itemModelTmp);
				String blueprintId = orderItemTemp.getCustomerGoodId();
				String isHasLadder = orderItemTemp.getIsHasLadder();
				Double price;
				if("1".equals(isHasLadder)){
					model.setCustomerGoodId(blueprintId);
					model.setNum(jsonObject.get("esgouNum").toString());
					List<OrderModel> ladderPrice = orderDAO.getBluePrintLadderPrice(model);
					if(ladderPrice.size() > 0){
						price = ladderPrice.get(0).getPrice();
					}else{
						price = orderItemTemp.getPrice();
					}
				}else {
					price = orderItemTemp.getPrice();
				}
				itemModel = new OrderItemModel();
				itemModel.setOrderId(orderId);
				itemModel.setUnitPrice(price);
				itemModel.setDeliveryTime(jsonObject.get("deliveryTime").toString());
				itemModel.setSortIndex(jsonObject.get("sortIndex").toString());
				itemModel.setCustomerGoodId(blueprintId);
				itemModel.setTmpNum(0L);
				long num = Long.parseLong(jsonObject.get("esgouNum").toString());
				itemModel.setEsgouNum(num);
				String id = Md5Util.UUID();
				itemModel.setId(id);
				orderItemDAO.save(itemModel);
				
			}
		}catch (Exception e){
			e.printStackTrace();
		}
		
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
	
	public OrderModel getpMaterialNum(OrderModel model){
		return orderDAO.getpMaterialNum(model);
	}
	
}
