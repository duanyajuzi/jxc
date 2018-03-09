 /**
 * 文件名称：
 * 版权所有：Copyright gesoft
 * 创建时间：2017-06-28 11:58:20
 * 创 建 人：WCL (ln_admin@yeah.net)
 * 功能描述：
 **/
package com.gesoft.controller;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.gesoft.model.OrderItemModel;
import com.gesoft.service.OrderItemService;
import com.gesoft.util.Md5Util;
import org.omg.CORBA.Request;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gesoft.model.OrderModel;
import com.gesoft.model.MsgModel;
import com.gesoft.service.OrderService;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;


 @Controller
@RequestMapping("/order")
public class OrderController extends BaseController
{	
	private static final Logger logger = LoggerFactory.getLogger(OrderController.class);
	private static long tmpID = 0;

	private static boolean tmpIDlocked = false;

	public static long getOnlyOrderNo()
	{
		long ltime = 0;
		while (true)
		{
			if(tmpIDlocked == false)
			{
				tmpIDlocked = true;
				//当前：（年、月、日、时、分、秒、毫秒）*10000
				ltime = Long.valueOf(new SimpleDateFormat("yyMMddhhmmssSSS").format(new Date()).toString()) * 10000;
				if(tmpID < ltime)
				{
					tmpID = ltime;
				}
				else
				{
					tmpID = tmpID + 1;
					ltime = tmpID;
				}
				tmpIDlocked = false;
				return ltime;
			}
		}
	}
//获取String类型的订单编号
	@RequestMapping(value = "/queryOrderNo")
	@ResponseBody
	public String getStringOrderNo(){
		if(tmpID > 999999){
			tmpID=1;
		}
		return (new SimpleDateFormat("yyyyMMddhhmmss")).format(new Date())+tmpID;
	}
	
	@Resource
	private OrderService orderService;
	
	@Resource
	private OrderItemService orderItemService;
	
	/**
	 * 描述信息：采购订单分页查询
	 * 创建时间：2017-06-28 11:58:20
	 * @author WCL (ln_admin@yeah.net)
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/query", method=RequestMethod.GET)
	public @ResponseBody MsgModel search(OrderModel model)
	{
		MsgModel msgModel = new MsgModel();
		try
		{
			orderService.findPageList(model, msgModel);
		}
		catch (Exception e)
		{
			logger.error("OrderController search error：", e);
		}
		return msgModel;
	}

	/**
	 * 描述信息：销售订单分页查询
	 *  @param model
	 * @return
	 */
	@RequestMapping(value="/querySell", method=RequestMethod.GET)
	public @ResponseBody MsgModel querySell(OrderModel model)
	{
		MsgModel msgModel = new MsgModel();
		try
		{
			orderService.findPageOrderSell(model, msgModel);
		}
		catch (Exception e)
		{
			logger.error("OrderController querySell error：", e);
		}
		return msgModel;
	}
	/**
	 * 获取商品阶梯价格
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/getLadderPrice", method=RequestMethod.GET)
	public @ResponseBody MsgModel getLadderPrice(OrderModel model)
	{
		
		MsgModel msgModel = new MsgModel();
		try
		{
			msgModel.setData(orderService.getLadderPrice(model));
			msgModel.setSuccess(GLOBAL_MSG_BOOL_SUCCESS);
		}
		catch (Exception e)
		{
			logger.error("OrderItemController search error：", e);
		}
		return msgModel;
		
	}
	
	/**
	 * 获取客户方案阶梯价格
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/getBluePrintLadderPrice", method=RequestMethod.GET)
	public @ResponseBody MsgModel getBluePrintLadderPrice(OrderModel model)
	{
		
		MsgModel msgModel = new MsgModel();
		try
		{
			msgModel.setData(orderService.getBluePrintLadderPrice(model));
			msgModel.setSuccess(GLOBAL_MSG_BOOL_SUCCESS);
		}
		catch (Exception e)
		{
			logger.error("OrderItemController search error：", e);
		}
		return msgModel;
		
	}
	
	
	/**
	 * 描述信息：增加
	 * 创建时间：2017-06-28 11:58:20
	 * @author WCL (ln_admin@yeah.net)
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public @ResponseBody MsgModel add(OrderModel model, HttpServletRequest request)
	{

		MsgModel msgModel = new MsgModel();
		Long userid = getSessionUserId(request);
		model.setCuserid(userid);//类型不匹配
		String id = Md5Util.UUID();
		model.setId(id);
		try
		{
			if("1".equals(model.getZdsc())){
				//检查子项是否有客户方案
				if(!checkItem(model)){
					msgModel.setSuccess(GLOBAL_MSG_BOOL_FAIL);
					msgModel.setMsg("订单项中商品未添加客户方案，不能自动生成订单");
					return msgModel;
				}
			}
			
			model.setOrderStatus(1);
			orderService.save(model);
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
					model.setOrderNo("DD"+getStringOrderNo());
					orderService.save(model);
					//复制子项
					editItem2(model);
				}
			}else{//销售订单项保存
				editSellItem(model);
			}
			
			msgModel.setSuccess(GLOBAL_MSG_BOOL_SUCCESS);
		}
		catch (Exception e)
		{
			logger.error("OrderController add error：", e);
		}
		return msgModel;
	}
	
	/**
	 * 描述信息：检查订单子项是否有客户方案
	 * @return
	 */
	public boolean checkItem(OrderModel model)
	{
		try
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
				OrderItemModel orderItemTemp = orderItemService.getBluePrintInfo(itemModelTmp);
				if(orderItemTemp == null){
					return false;
				}
			}
			return true;
		}
		catch (Exception e)
		{
			logger.error("OrderItemController modify error：", e);
			return false;
		}
	}
	
	/**
	 * 描述信息：修改采购订单子项
	 * @return
	 */
	public void editItem(OrderModel model)
	{
		MsgModel msgModel = new MsgModel();
		try
		{
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
						orderItemService.updateStorage(itemModel);
					}
					String id = Md5Util.UUID();
					itemModel.setId(id);
					orderItemService.save(itemModel);
					if(model.getOrderType() == 0 && "1".equals(model.getZdsc())){
						inoutStockModel = new OrderItemModel();
						inoutStockModel.setOrderItemId(id);
						inoutStockModel.setPrice(price);
						inoutStockModel.setGoodNum(esgouNum);
						inoutStockModel.setOrderType(0);
						inoutStockModel.setCreateUserId(userid);
						inoutStockModel.setModifyUserId(userid);
						orderItemService.insertInoutStockItem(inoutStockModel);
					}

				}else if("modified".equals(state)){
					itemModel.setId(jsonObject.get("id").toString());
					//不控货时，数目小于已入库存时，temNum=num,afterNum=num-temNum
					//控货时，num>temNum时，temNum不需要赋值，其他同不控货
					Long orderItemTepNum = orderItemService.getOrderItemTepNum(itemModel);
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
					orderItemService.updateStorage(itemModel);
					orderItemService.update(itemModel);
				}else if("removed".equals(state)){
					itemModel.setId(jsonObject.get("id").toString());
					//删除时库存处理
					//查询已入库数量
					Long orderItemTepNum = orderItemService.getOrderItemTepNum(itemModel);
					itemModel.setAfterNum(-orderItemTepNum);
					orderItemService.updateStorage(itemModel);
					
					orderItemService.delete(itemModel);
				}
				//新增操作，不控货需要添加库存，控货不做任何操作
				//删除操作，修改订单时，需要根据temNum修改库存
				//修改操作，修改订单时，需要根据temNum和num修改库存
				// temNum=10 oldnum=10 num=8  =>temNum=8 库存-2
				// temNum=10 oldnum=15 num=5  =>temNum=5 库存-5
				// temNum=10 oldnum=15 num=13 +>不变
				
				//删除订单时，回归库存 加减temNum
				
			}
			msgModel.setSuccess(GLOBAL_MSG_BOOL_SUCCESS);
		}
		catch (Exception e)
		{
			logger.error("OrderItemController modify error：", e);
		}
	}
	
	/**
	 * 描述信息：修改销售订单子项
	 * @return
	 */
	public void editSellItem(OrderModel model)
	{
		MsgModel msgModel = new MsgModel();
		try
		{
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
				itemModel.setTmpNum(0L);
				Long esgouNum = Long.parseLong(jsonObject.get("esgouNum").toString());
				itemModel.setEsgouNum(esgouNum);
				String state = jsonObject.get("_state").toString();
				if("added".equals(state)){
					itemModel.setId(Md5Util.UUID());
					orderItemService.save(itemModel);
				}else if("modified".equals(state)){
					itemModel.setId(jsonObject.get("id").toString());
					Long orderItemTepNum = orderItemService.getOrderItemTepNum(itemModel);
					if(orderItemTepNum >= esgouNum){//数目小于已出库存时
						Long afterNum = orderItemTepNum - esgouNum;
						itemModel.setAfterNum(afterNum);
						itemModel.setTmpNum(esgouNum);
					}else{//数目大于已出库存时
						itemModel.setTmpNum(orderItemTepNum);
						itemModel.setAfterNum(0L);
					}
					orderItemService.update(itemModel);
					itemModel.setCustomerGoodId(jsonObject.get("goodsId").toString());
					orderItemService.updateStorage(itemModel);
				}else if("removed".equals(state)){
					itemModel.setId(jsonObject.get("id").toString());
					//删除时库存处理
					//查询已出库数量
					Long orderItemTepNum = orderItemService.getOrderItemTepNum(itemModel);
					itemModel.setAfterNum(orderItemTepNum);
					orderItemService.delete(itemModel);
					itemModel.setCustomerGoodId(jsonObject.get("goodsId").toString());
					orderItemService.updateStorage(itemModel);
				}
			}
			msgModel.setSuccess(GLOBAL_MSG_BOOL_SUCCESS);
		}
		catch (Exception e)
		{
			logger.error("OrderItemController modify error：", e);
		}
	}
	
	/**
	 * 描述信息：复制订单子项
	 * @return
	 */
	public void editItem2(OrderModel model)
	{
		MsgModel msgModel = new MsgModel();
		try
		{
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
				OrderItemModel orderItemTemp = orderItemService.getBluePrintInfo(itemModelTmp);
				String blueprintId = orderItemTemp.getCustomerGoodId();
				String isHasLadder = orderItemTemp.getIsHasLadder();
				Double price;
				if("1".equals(isHasLadder)){
					model.setCustomerGoodId(blueprintId);
					model.setNum(jsonObject.get("esgouNum").toString());
					List<OrderModel> ladderPrice = orderService.getBluePrintLadderPrice(model);
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
				itemModel.setCustomerGoodId(blueprintId);
				itemModel.setTmpNum(0L);
				long num = Long.parseLong(jsonObject.get("esgouNum").toString());
				itemModel.setEsgouNum(num);
				String id = Md5Util.UUID();
				itemModel.setId(id);
				orderItemService.save(itemModel);

			}
			msgModel.setSuccess(GLOBAL_MSG_BOOL_SUCCESS);
		}
		catch (Exception e)
		{
			logger.error("OrderItemController modify error：", e);
		}
	}

	
	/**
	 * 描述信息：修改
	 * 创建时间：2017-06-28 11:58:20
	 * @author WCL (ln_admin@yeah.net)
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/modify", method=RequestMethod.POST)
	public @ResponseBody MsgModel modify(OrderModel model, HttpServletRequest request)
	{
		MsgModel msgModel = new MsgModel();
		try
		{
			setSessionUserId(model, request);
			if (orderService.update(model) > 0)
			{
				if(model.getOrderType() == 0){//采购订单项保存
					editItem(model);
				}else{//销售订单项保存
					editSellItem(model);
				}
				
				msgModel.setSuccess(GLOBAL_MSG_BOOL_SUCCESS);
			}
		}
		catch (Exception e)
		{
			logger.error("OrderController modify error：", e);
		}
		return msgModel;
	}
	
	
	/**
	 * 描述信息：删除
	 * 创建时间：2017-06-28 11:58:20
	 * @author WCL (ln_admin@yeah.net)
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/del", method=RequestMethod.POST)
	public @ResponseBody MsgModel delete(OrderModel model)
	{
		MsgModel msgModel = new MsgModel();
		try
		{
			String orderId = model.getId();
			OrderItemModel itemModel = new OrderItemModel();
			itemModel.setOrderId(orderId);
			//根据已出库/入库数量恢复库存
			if(model.getOrderType() == 0) {//采购订单
				//查询子项列表
				List<OrderItemModel> itemList = orderItemService.findList2(itemModel);
				for(OrderItemModel item : itemList){
					item.setAfterNum(-item.getTmpNum());
					orderItemService.updateStorage(item);
				}
			}else{//销售订单
				List<OrderItemModel> itemList = orderItemService.findList(itemModel);
				for(OrderItemModel item : itemList){
					item.setAfterNum(item.getTmpNum());
					item.setCustomerGoodId(item.getGoodsId());
					orderItemService.updateStorage(item);
				}
			}
			orderService.delete(model);
			msgModel.setSuccess(GLOBAL_MSG_BOOL_SUCCESS);
		}
		catch (Exception e)
		{
			logger.error("OrderController delete error：", e);
		}
		return msgModel;
	}

	@RequestMapping(value = "/updateOrderStatus")
	@ResponseBody
	public void  updateOrderStatus(OrderModel model){
		try {
			orderService.updateOrderStatus(model);
		}catch (Exception e){
			logger.error("OrderController updateOrderStatus error：", e);
		}
	}
	
	/**
	 * 查询客户料号列表
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/queryMaterialNum")
	@ResponseBody
	public List<OrderModel> queryMaterialNumList(OrderModel model){
		List<OrderModel> list=null;
		try{
			list=orderService.queryMaterialNum(model);
			if(list==null){
				list=new ArrayList<>();
			}
		}catch (Exception e){
			logger.error("GoodsController queryMaterialNum error：", e);
		}
		return list;
	}
	
	/**
	 * 查询原厂物流号列表
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/queryMaterialNum2")
	@ResponseBody
	public List<OrderModel> queryMaterialNumList2(OrderModel model){
		List<OrderModel> list=null;
		try{
			list=orderService.queryMaterialNum2(model);
			if(list==null){
				list=new ArrayList<>();
			}
		}catch (Exception e){
			logger.error("GoodsController queryMaterialNum error：", e);
		}
		return list;
	}
	
}
