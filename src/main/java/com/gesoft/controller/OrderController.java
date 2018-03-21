 /**
 * 文件名称：
 * 版权所有：Copyright gesoft
 * 创建时间：2017-06-28 11:58:20
 * 创 建 人：WCL (ln_admin@yeah.net)
 * 功能描述：
 **/
package com.gesoft.controller;


import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.gesoft.model.*;
import com.gesoft.service.OrderItemService;
import com.gesoft.service.UserService;
import com.gesoft.util.ExportExecl;
import com.gesoft.util.Md5Util;
import com.gesoft.util.StringOrderNoUtil;
import org.omg.CORBA.Request;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gesoft.service.OrderService;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.*;


@Controller
@RequestMapping("/order")
public class OrderController extends BaseController
{	
	private static final Logger logger = LoggerFactory.getLogger(OrderController.class);


//获取String类型的订单编号
	@RequestMapping(value = "/queryOrderNo")
	@ResponseBody
	public MsgModel getStringOrderNo(HttpServletRequest request){
		MsgModel msgModel = new MsgModel();
		Long userid = getSessionUserId(request);
		UserModel userModel = userService.get(userid);
		String orderTitle = userModel.getOrderTitle();
		String orderNo = orderTitle + StringOrderNoUtil.getOrderNo();
		List<String> list = new ArrayList<>();
		list.add(orderNo);
		msgModel.setData(list);
		msgModel.setSuccess(GLOBAL_MSG_BOOL_SUCCESS);
		return msgModel;
	}
	
	@Resource
	private OrderService orderService;
	
	@Resource
	private UserService userService;
	
	@Resource
	private OrderItemService orderItemService;
	
	
	/**
	 * 描述信息：excel导出
	 * @param model2
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/exportExcel", method=RequestMethod.GET)
	public @ResponseBody MsgModel exportExcel(OrderModel model2, HttpServletRequest request,HttpServletResponse response)
	{
		MsgModel msgModel = new MsgModel();
		try
		{
			Map<String, Object> map = new HashMap<String, Object>();
			
			List<OrderExcelModel> orderItems = new ArrayList<>();
			
			OrderModel model = orderService.get(model2.getId());

			//订单信息
			map.put("deliveryAddress", model.getDeliveryAddress());//交货地址
			map.put("deliveryTime", model.getDeliveryTime());//交货期
			map.put("orderTime", model.getOrderTime());//订单时间
			map.put("orderNo", model.getOrderNo());//订单号
			String cust_po_no = model.getOrderName();
			//订单项列表
			OrderItemModel orderItemModel = new OrderItemModel();
			orderItemModel.setOrderId(model.getId());
			List<OrderItemModel> orderItemModels = orderItemService.findList2(orderItemModel);
			
			int size = orderItemModels.size();
			
			map.put("expandedRowCount", size);
			map.put("formula", size);
			map.put("index", size);
			
			OrderExcelModel orderExcelModel;
			OrderModel orderModelTmp;
			for(OrderItemModel orderItem : orderItemModels){
				orderExcelModel = new OrderExcelModel();
				orderExcelModel.setCust_po_no(cust_po_no);
				orderExcelModel.setPn(orderItem.getMaterialNum());
				orderExcelModel.setQty(orderItem.getEsgouNum());
				orderExcelModel.setUnit(orderItem.getSpecUnitName());
				orderExcelModel.setUnit_price_net(orderItem.getPrice());
				orderExcelModel.setTotal_price_net(orderItem.getTotalMoney());
				orderExcelModel.setDescription(orderItem.getMemo());
				orderModelTmp = new OrderModel();
				orderModelTmp.setPcustomerId(model.getPcustomerId());
				orderModelTmp.setCustomerGoodId(orderItem.getCustomerGoodId());
				
				//根据客户，customerGoodId查询客户料号
				OrderModel orderMModel =  orderService.getpMaterialNum(orderModelTmp);
				if(orderMModel != null){
					orderExcelModel.setPn2(orderMModel.getMaterialNum());
				}else {
					orderExcelModel.setPn2(orderItem.getMaterialNum());
				}
				orderItems.add(orderExcelModel);
			}
			map.put("orderItems", orderItems);
			
			//sysuser
			Long userid = getSessionUserId(request);
			UserModel userModel = userService.get(userid);
			int roleId = userModel.getRoleId();
			
			map.put("utel", userModel.getTel());//电话
			map.put("ucompany", userModel.getCompany());//公司抬头
			map.put("uposition", userModel.getPosition());//地址
			map.put("urealName", userModel.getRealName());//姓名
			
			//厂商信息
			map.put("customerName", model.getName1());//名称
			map.put("caddress", model.getCaddress());//地址
			map.put("ccontacts", model.getCcontacts());//联系人
			map.put("ctel", model.getCtel());//联系电话
			
			File file = null;
			InputStream inputStream = null;
			ServletOutputStream out = null;
			try {
				String valueName = null;
				if(roleId == 3){
					valueName = "order_ort.ftl";
				}else if(roleId == 4){
					valueName = "order_anke.ftl";
				}else{
					valueName = "order_tuoding.ftl";
				}
				request.setCharacterEncoding("UTF-8");
				file = ExportExecl.createExcel(map, "myExcel",valueName);//调用创建excel帮助类
				inputStream = new FileInputStream(file);
				response.setCharacterEncoding("utf-8");
				response.setContentType("application/msexcel");
				response.setHeader("content-disposition", "attachment;filename="+ URLEncoder.encode(model.getOrderNo() + "订单" + ".xls", "UTF-8"));
				out = response.getOutputStream();
				byte[] buffer = new byte[512]; // 缓冲区
				int bytesToRead = -1;
				// 通过循环将读入的Excel文件的内容输出到浏览器中
				while ((bytesToRead = inputStream.read(buffer)) != -1) {
					out.write(buffer, 0, bytesToRead);
				}
				out.flush();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (inputStream != null)
					inputStream.close();
				if (out != null)
					out.close();
				if (file != null)
					file.delete(); // 删除临时文件
			}
		}
		catch (Exception e)
		{
			logger.error("OrderController search error：", e);
		}
		return msgModel;
	}
	
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
			orderService.add(model,msgModel);
		}
		catch (Exception e)
		{
			logger.error("OrderController add error：", e);
		}
		return msgModel;
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
			orderService.modify(model,msgModel);
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
			orderService.del(model,msgModel);
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
