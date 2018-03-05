 /**
 * 文件名称：
 * 版权所有：Copyright gesoft
 * 创建时间：2017-07-19 13:24:49
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
import com.gesoft.model.*;
import com.gesoft.service.CustomerPriceService;
import com.gesoft.util.Md5Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gesoft.service.GoodCustomerService;


@Controller
@RequestMapping("/goodCustomer")
public class GoodCustomerController extends BaseController
{	
	private static final Logger logger = LoggerFactory.getLogger(GoodCustomerController.class);
	
	@Resource
	private GoodCustomerService goodCustomerService;


	@Resource
	private CustomerPriceService customerPriceService;

	/**
	 * 描述信息：分页查询
	 * 创建时间：2017-07-19 13:24:49
	 * @author WCL (ln_admin@yeah.net)
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/query", method=RequestMethod.POST)
	public @ResponseBody MsgModel search(GoodCustomerModel model)
	{
		MsgModel msgModel = new MsgModel();
		try
		{
			goodCustomerService.findPageList(model, msgModel);
		}
		catch (Exception e)
		{
			logger.error("GoodCustomerController search error：", e);
		}
		return msgModel;
	}
	
	
	/**
	 * 描述信息：增加
	 * 创建时间：2017-07-19 13:24:49
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public @ResponseBody MsgModel add(GoodCustomerModel model, HttpServletRequest request) {
		MsgModel msgModel = new MsgModel();
		String id = Md5Util.UUID();
		model.setId(id);
		try {
			setSessionUserId(model, request);
			if (goodCustomerService.save(model) > 0) {
				msgModel.setSuccess(GLOBAL_MSG_BOOL_SUCCESS);
				addItem(model);
			}
		}catch (Exception e) {
			logger.error("GoodCustomerController add error：", e);
		}
		return msgModel;
	}



	/**
	 * 描述信息：新增订单子项
	 * @return
	 */
	public void addItem(GoodCustomerModel model) {
		MsgModel msgModel = new MsgModel();
		try {
			String Id = model.getId();
			String data = model.getData();
			JSONArray jsArr = JSONArray.parseArray(data);
			for(Object obj : jsArr){
				CustomerPriceModel customerPriceModel = new CustomerPriceModel();
				customerPriceModel.setGood_customer_id(Id);
				JSONObject jsonObject = JSONObject.parseObject(obj.toString());
				customerPriceModel.setPrice(Float.parseFloat(jsonObject.get("price").toString()));
				customerPriceModel.setNum(Long.parseLong(jsonObject.get("num").toString()));
				customerPriceService.save(customerPriceModel);
			}
			msgModel.setSuccess(GLOBAL_MSG_BOOL_SUCCESS);
		}catch (Exception e) {
			logger.error("GoodCustomerController modify error：", e);
		}
	}



	/**
	 * 描述信息：修改
	 * 创建时间：2017-07-19 13:24:49
	 * @author WCL (ln_admin@yeah.net)
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/modify", method=RequestMethod.POST)
	public @ResponseBody MsgModel modify(GoodCustomerModel model, HttpServletRequest request) {
		MsgModel msgModel = new MsgModel();
		try{
			setSessionUserId(model, request);
			if (goodCustomerService.update(model) > 0) {
				msgModel.setSuccess(GLOBAL_MSG_BOOL_SUCCESS);
				editItem(model);
			}
		}catch (Exception e) {
			logger.error("GoodCustomerController modify error：", e);
		}
		return msgModel;
	}


	/**
	 * 描述信息：修改订单子项
	 * @return
	 */
	public void editItem(GoodCustomerModel model) {
		MsgModel msgModel = new MsgModel();
		try {
			String Id = model.getId();
			String data = model.getData();
			JSONArray jsArr = JSONArray.parseArray(data);
			for(Object obj : jsArr){
				CustomerPriceModel customerPriceModel = new CustomerPriceModel();
				customerPriceModel.setGood_customer_id(Id);
				JSONObject jsonObject = JSONObject.parseObject(obj.toString());
				customerPriceModel.setPrice(Float.parseFloat(jsonObject.get("price").toString()));
				customerPriceModel.setNum(Long.parseLong(jsonObject.get("num").toString()));
				String state = jsonObject.get("_state").toString();
				if("added".equals(state)){
					customerPriceService.save(customerPriceModel);
				}else if("modified".equals(state)){
					customerPriceModel.setId(Long.parseLong(jsonObject.get("id").toString()));
					customerPriceService.update(customerPriceModel);
				}else if("removed".equals(state)){
					customerPriceModel.setId(Long.parseLong(jsonObject.get("id").toString()));
					customerPriceService.delete(customerPriceModel);
				}
			}
			msgModel.setSuccess(GLOBAL_MSG_BOOL_SUCCESS);
		}catch (Exception e) {
			logger.error("GoodCustomerController modify error：", e);
		}
	}



	/**
	 * 描述信息：删除
	 * 创建时间：2017-07-19 13:24:49
	 * @author WCL (ln_admin@yeah.net)
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/del", method=RequestMethod.POST)
	public @ResponseBody MsgModel delete(GoodCustomerModel model)
	{
		MsgModel msgModel = new MsgModel();
		try
		{
			if (goodCustomerService.delete(model) > 0)
			{
				msgModel.setSuccess(GLOBAL_MSG_BOOL_SUCCESS);
			}
		}
		catch (Exception e)
		{
			logger.error("GoodCustomerController delete error：", e);
		}
		return msgModel;
	}

}
