 /**
 * 文件名称：
 * 版权所有：Copyright gesoft
 * 创建时间：2017-06-28 11:56:39
 * 创 建 人：WCL (ln_admin@yeah.net)
 * 功能描述：
 **/
package com.gesoft.controller;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gesoft.model.CustomerModel;
import com.gesoft.model.MsgModel;
import com.gesoft.service.CustomerService;

import java.util.ArrayList;
import java.util.List;


 @Controller
@RequestMapping("/customer")
public class CustomerController extends BaseController
{	
	private static final Logger logger = LoggerFactory.getLogger(CustomerController.class);
	
	@Resource
	private CustomerService customerService;
	

	/**
	 * 描述信息：分页查询
	 * 创建时间：2017-06-28 11:56:39
	 * @author WCL (ln_admin@yeah.net)
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/query", method=RequestMethod.POST)
	public @ResponseBody MsgModel search(CustomerModel model)
	{
		MsgModel msgModel = new MsgModel();
		try
		{
			customerService.findPageList(model, msgModel);
		}
		catch (Exception e)
		{
			logger.error("CustomerController search error：", e);
		}
		return msgModel;
	}
	
	
	/**
	 * 描述信息：增加
	 * 创建时间：2017-06-28 11:56:39
	 * @author WCL (ln_admin@yeah.net)
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public @ResponseBody MsgModel add(CustomerModel model, HttpServletRequest request)
	{
		MsgModel msgModel = new MsgModel();
		try
		{
			setSessionUserId(model, request);
			if (customerService.save(model) > 0)
			{
				msgModel.setSuccess(GLOBAL_MSG_BOOL_SUCCESS);
			}
		}
		catch (Exception e)
		{
			logger.error("CustomerController add error：", e);
		}
		return msgModel;
	}

	
	/**
	 * 描述信息：修改
	 * 创建时间：2017-06-28 11:56:39
	 * @author WCL (ln_admin@yeah.net)
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/modify", method=RequestMethod.POST)
	public @ResponseBody MsgModel modify(CustomerModel model, HttpServletRequest request)
	{
		MsgModel msgModel = new MsgModel();
		try
		{
			setSessionUserId(model, request);
			if (customerService.update(model) > 0)
			{
				msgModel.setSuccess(GLOBAL_MSG_BOOL_SUCCESS);
			}
		}
		catch (Exception e)
		{
			logger.error("CustomerController modify error：", e);
		}
		return msgModel;
	}
	
	
	/**
	 * 描述信息：删除
	 * 创建时间：2017-06-28 11:56:39
	 * @author WCL (ln_admin@yeah.net)
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/del", method=RequestMethod.POST)
	public @ResponseBody MsgModel delete(CustomerModel model)
	{
		MsgModel msgModel = new MsgModel();
		try
		{
			if (customerService.delete(model) > 0)
			{
				msgModel.setSuccess(GLOBAL_MSG_BOOL_SUCCESS);
			}
		}
		catch (Exception e)
		{
			logger.error("CustomerController delete error：", e);
		}
		return msgModel;
	}

	@RequestMapping(value = "/queryCustomerList")
	@ResponseBody
	public List<CustomerModel> queryList(CustomerModel model) {
		List<CustomerModel> list=new ArrayList<>();
		try{
				list=customerService.queryCustomerList(model);
		}catch (Exception e){
			logger.error("CustomerController queryCustomerList error：", e);
		}
		return list;
	}

	@RequestMapping(value = "/queryOtherInfoList")
	@ResponseBody
	public List<CustomerModel> searchOtherInfo(CustomerModel model){
		List<CustomerModel> list=new ArrayList<>();
		try{
			list=customerService.queryOtherInfo(model);
		}catch (Exception e){
			logger.error("CustomerController queryOtherInfoList error：", e);
		}
		return list;
	}

}
