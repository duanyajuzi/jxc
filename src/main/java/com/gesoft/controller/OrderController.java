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
	 * 描述信息：分页查询
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
		model.setCuserid((int)getSessionUserId(request));//类型不匹配
		String id = Md5Util.UUID();
		model.setId(id);
		try
		{
			//保存采购订单
			if("1".equals(model.getZdsc())){
				model.setOrderStatus(1);
			}else{
				model.setOrderStatus(0);
			}
			orderService.save(model);
			//保存子项
			editItem(model);
			if("1".equals(model.getZdsc())){
				String id2 = Md5Util.UUID();
				model.setId(id2);
				model.setOrderType(1);
				model.setOrderStatus(0);
				model.setOrderNo("DD"+getStringOrderNo());
				orderService.save(model);
//				editItem(model);
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
	 * 描述信息：修改订单子项
	 * @return
	 */
	public void editItem(OrderModel model)
	{
		MsgModel msgModel = new MsgModel();
		try
		{
			String orderId = model.getId();
			String iskh = model.getIskh();
			OrderItemModel itemModel2 = new OrderItemModel();
			itemModel2.setOrderId(orderId);
			
			String data = model.getData();
			JSONArray jsArr = JSONArray.parseArray(data);
			OrderItemModel itemModel;
			for(Object obj : jsArr){
				itemModel = new OrderItemModel();
				JSONObject jsonObject = JSONObject.parseObject(obj.toString());
				
				itemModel.setOrderId(orderId);
				itemModel.setUnitPrice(Float.parseFloat(jsonObject.get("price").toString()));
				itemModel.setCustomerGoodId(Long.parseLong(jsonObject.get("customerGoodId").toString()));
				itemModel.setEsgouNum(Float.parseFloat(jsonObject.get("esgouNum").toString()));
				//是否控货处理
				if("1".equals(iskh)){
					itemModel.setTmpNum(Float.parseFloat(jsonObject.get("esgouNum").toString()));
				}
				String state = jsonObject.get("_state").toString();
				if("added".equals(state)){
					orderItemService.save(itemModel);
				}else if("modified".equals(state)){
					itemModel.setId(Long.parseLong(jsonObject.get("id").toString()));
					orderItemService.update(itemModel);
				}else if("removed".equals(state)){
					itemModel.setId(Long.parseLong(jsonObject.get("id").toString()));
					orderItemService.delete(itemModel);
				}
				
				//是否控货处理
				if("1".equals(iskh)){
				
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
				editItem(model);
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
			if (orderService.delete(model) > 0)
			{
				msgModel.setSuccess(GLOBAL_MSG_BOOL_SUCCESS);
			}
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
}
