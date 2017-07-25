 /**
 * 文件名称：
 * 版权所有：Copyright gesoft
 * 创建时间：2017-07-19 13:24:33
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

import com.gesoft.model.BillModel;
import com.gesoft.model.MsgModel;
import com.gesoft.service.BillService;

import java.text.SimpleDateFormat;
import java.util.Date;


 @Controller
@RequestMapping("/bill")
public class BillController extends BaseController
{	
	private static final Logger logger = LoggerFactory.getLogger(BillController.class);
	
	@Resource
	private BillService billService;
	

	/**
	 * 描述信息：分页查询
	 * 创建时间：2017-07-19 13:24:33
	 * @author WCL (ln_admin@yeah.net)
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/query", method=RequestMethod.POST)
	public @ResponseBody MsgModel search(BillModel model)
	{
		MsgModel msgModel = new MsgModel();
		try
		{
			billService.findPageList(model, msgModel);
		}
		catch (Exception e)
		{
			logger.error("BillController search error：", e);
		}
		return msgModel;
	}
	
	
	/**
	 * 描述信息：增加
	 * 创建时间：2017-07-19 13:24:33
	 * @author WCL (ln_admin@yeah.net)
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public @ResponseBody MsgModel add(BillModel model, HttpServletRequest request)
	{
		MsgModel msgModel = new MsgModel();
		try
		{
//			setSessionUserId(model, request);
			model.setPayConfirmUser(getSessionUserId(request));
			model.setCuserId(getSessionUserId(request));
			model.setCtime((new SimpleDateFormat("yyyyMMddhhmmss")).format(new Date()));
			if (billService.save(model) > 0)
			{
				msgModel.setSuccess(GLOBAL_MSG_BOOL_SUCCESS);
			}
		}
		catch (Exception e)
		{
			logger.error("BillController add error：", e);
		}
		return msgModel;
	}

	
	/**
	 * 描述信息：修改
	 * 创建时间：2017-07-19 13:24:33
	 * @author WCL (ln_admin@yeah.net)
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/modify", method=RequestMethod.POST)
	public @ResponseBody MsgModel modify(BillModel model, HttpServletRequest request)
	{
		MsgModel msgModel = new MsgModel();
		try
		{
//			setSessionUserId(model, request);
			model.setCuserId(getSessionUserId(request));
			if (billService.update(model) > 0)
			{
				msgModel.setSuccess(GLOBAL_MSG_BOOL_SUCCESS);
			}
		}
		catch (Exception e)
		{
			logger.error("BillController modify error：", e);
		}
		return msgModel;
	}
	
	
	/**
	 * 描述信息：删除
	 * 创建时间：2017-07-19 13:24:33
	 * @author WCL (ln_admin@yeah.net)
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/del", method=RequestMethod.POST)
	public @ResponseBody MsgModel delete(BillModel model)
	{
		MsgModel msgModel = new MsgModel();
		try
		{
			if (billService.delete(model) > 0)
			{
				msgModel.setSuccess(GLOBAL_MSG_BOOL_SUCCESS);
			}
		}
		catch (Exception e)
		{
			logger.error("BillController delete error：", e);
		}
		return msgModel;
	}

}
