 /**
 * 文件名称：
 * 版权所有：Copyright gesoft
 * 创建时间：2017-06-28 11:54:30
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
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gesoft.model.DictModel;
import com.gesoft.model.MsgModel;
import com.gesoft.service.DictService;

import java.util.ArrayList;
import java.util.List;


 @Controller
@RequestMapping("/dict")
public class DictController extends BaseController
{	
	private static final Logger logger = LoggerFactory.getLogger(DictController.class);
	
	@Resource
	private DictService dictService;
	

	/**
	 * 描述信息：分页查询
	 * 创建时间：2017-06-28 11:54:30
	 * @author WCL (ln_admin@yeah.net)
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/query", method=RequestMethod.POST)
	public @ResponseBody MsgModel search(DictModel model)
	{
		MsgModel msgModel = new MsgModel();
		try
		{
			dictService.findPageList(model, msgModel);
		}
		catch (Exception e)
		{
			logger.error("DictController search error：", e);
		}
		return msgModel;
	}
	
	
	/**
	 * 描述信息：增加
	 * 创建时间：2017-06-28 11:54:30
	 * @author WCL (ln_admin@yeah.net)
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public @ResponseBody MsgModel add(DictModel model, HttpServletRequest request)
	{
		MsgModel msgModel = new MsgModel();
		try
		{
			setSessionUserId(model, request);
			if (dictService.save(model) > 0)
			{
				msgModel.setSuccess(GLOBAL_MSG_BOOL_SUCCESS);
			}
		}
		catch (Exception e)
		{
			logger.error("DictController add error：", e);
		}
		return msgModel;
	}

	
	/**
	 * 描述信息：修改
	 * 创建时间：2017-06-28 11:54:30
	 * @author WCL (ln_admin@yeah.net)
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/modify", method=RequestMethod.POST)
	public @ResponseBody MsgModel modify(DictModel model, HttpServletRequest request)
	{
		MsgModel msgModel = new MsgModel();
		try
		{
			setSessionUserId(model, request);
			if (dictService.update(model) > 0)
			{
				msgModel.setSuccess(GLOBAL_MSG_BOOL_SUCCESS);
			}
		}
		catch (Exception e)
		{
			logger.error("DictController modify error：", e);
		}
		return msgModel;
	}
	
	
	/**
	 * 描述信息：删除
	 * 创建时间：2017-06-28 11:54:30
	 * @author WCL (ln_admin@yeah.net)
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/del", method=RequestMethod.POST)
	public @ResponseBody MsgModel delete(DictModel model)
	{
		MsgModel msgModel = new MsgModel();
		try
		{
			if (dictService.delete(model) > 0)
			{
				msgModel.setSuccess(GLOBAL_MSG_BOOL_SUCCESS);
			}
		}
		catch (Exception e)
		{
			logger.error("DictController delete error：", e);
		}
		return msgModel;
	}

//	@RequestMapping(value = "/queryIsLeaf",method =RequestMethod.GET)
//	public @ResponseBody List<DictModel> queryIsLeafList(DictModel model){
//		List<DictModel> list=new ArrayList();
//		try{
//			list=dictService.getIsLeafList(model);
//		}catch (Exception e){
//			logger.error("DictController queryIsLeaf error：", e);
//		}
//		return list;
//	}
}
