 /**
 * 文件名称：
 * 版权所有：Copyright gesoft
 * 创建时间：2017-06-28 11:57:13
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

import com.gesoft.model.BusinessModel;
import com.gesoft.model.MsgModel;
import com.gesoft.service.BusinessService;

import java.util.ArrayList;
import java.util.List;


 @Controller
@RequestMapping("/business")
public class BusinessController extends BaseController
{	
	private static final Logger logger = LoggerFactory.getLogger(BusinessController.class);
	
	@Resource
	private BusinessService businessService;
	

	/**
	 * 描述信息：分页查询
	 * 创建时间：2017-06-28 11:57:13
	 * @author WCL (ln_admin@yeah.net)
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/query", method=RequestMethod.POST)
	public @ResponseBody MsgModel search(BusinessModel model)
	{
		MsgModel msgModel = new MsgModel();
		try
		{
			businessService.findPageList(model, msgModel);
		}
		catch (Exception e)
		{
			logger.error("BusinessController search error：", e);
		}
		return msgModel;
	}
	
	
	/**
	 * 描述信息：增加
	 * 创建时间：2017-06-28 11:57:13
	 * @author WCL (ln_admin@yeah.net)
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public @ResponseBody MsgModel add(BusinessModel model, HttpServletRequest request)
	{
		MsgModel msgModel = new MsgModel();
		try
		{
			setSessionUserId(model, request);
			if (businessService.save(model) > 0)
			{
				msgModel.setSuccess(GLOBAL_MSG_BOOL_SUCCESS);
			}
		}
		catch (Exception e)
		{
			logger.error("BusinessController add error：", e);
		}
		return msgModel;
	}

	
	/**
	 * 描述信息：修改
	 * 创建时间：2017-06-28 11:57:13
	 * @author WCL (ln_admin@yeah.net)
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/modify", method=RequestMethod.POST)
	public @ResponseBody MsgModel modify(BusinessModel model, HttpServletRequest request)
	{
		MsgModel msgModel = new MsgModel();
		try
		{
			setSessionUserId(model, request);
			if (businessService.update(model) > 0)
			{
				msgModel.setSuccess(GLOBAL_MSG_BOOL_SUCCESS);
			}
		}
		catch (Exception e)
		{
			logger.error("BusinessController modify error：", e);
		}
		return msgModel;
	}
	
	
	/**
	 * 描述信息：删除
	 * 创建时间：2017-06-28 11:57:13
	 * @author WCL (ln_admin@yeah.net)
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/del", method=RequestMethod.POST)
	public @ResponseBody MsgModel delete(BusinessModel model)
	{
		MsgModel msgModel = new MsgModel();
		try
		{
			if (businessService.delete(model) > 0)
			{
				msgModel.setSuccess(GLOBAL_MSG_BOOL_SUCCESS);
			}
		}
		catch (Exception e)
		{
			logger.error("BusinessController delete error：", e);
		}
		return msgModel;
	}

	@RequestMapping(value = "/queryBusinessList")
	@ResponseBody
	public List<BusinessModel> queryBusinessList(BusinessModel model){
		List<BusinessModel> list=new ArrayList<>();
		try{
			list=businessService.queryBusiness(model);
		}catch (Exception e){
			logger.error("BusinessController queryBusinessList error：", e);
		}
		return list;
	}
}
