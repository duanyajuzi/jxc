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
import com.gesoft.model.OrderExcelModel;
import com.gesoft.model.OrderItemModel;
import com.gesoft.service.OrderItemService;
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

import com.gesoft.model.OrderModel;
import com.gesoft.model.MsgModel;
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
	public String getStringOrderNo(){
		String orderNo = StringOrderNoUtil.getOrderNo();
		return orderNo;
	}
	
	@Resource
	private OrderService orderService;
	
	@Resource
	private OrderItemService orderItemService;
	
	
	/**
	 * 描述信息：excel导出
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/exportExcel", method=RequestMethod.GET)
	public @ResponseBody MsgModel exportExcel(OrderModel model, HttpServletRequest request,HttpServletResponse response)
	{
		MsgModel msgModel = new MsgModel();
		try
		{
			Map<String, Object> map = new HashMap<String, Object>();
			List<OrderExcelModel> orderModels = new ArrayList<>();
			orderModels.add(new OrderExcelModel("123","abc"));
			orderModels.add(new OrderExcelModel("avsd","a测试bc"));
			map.put("orderList", orderModels);
			map.put("ordername", "订单名称");
			File file = null;
			InputStream inputStream = null;
			ServletOutputStream out = null;
			try {
				request.setCharacterEncoding("UTF-8");
				file = ExportExecl.createExcel(map, "myExcel","order.ftl");//调用创建excel帮助类
				inputStream = new FileInputStream(file);
				response.setCharacterEncoding("utf-8");
				response.setContentType("application/msexcel");
				response.setHeader("content-disposition", "attachment;filename="+ URLEncoder.encode("订单统计" + ".xls", "UTF-8"));
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
