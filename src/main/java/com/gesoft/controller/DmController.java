 /**
 * 文件名称：
 * 版权所有：Copyright gesoft
 * 创建时间：2017-06-28 11:56:18
 * 创 建 人：WCL (ln_admin@yeah.net)
 * 功能描述：
 **/
package com.gesoft.controller;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.gesoft.model.DmModel;
import com.gesoft.model.MsgModel;
import com.gesoft.service.DmService;

import java.util.ArrayList;
import java.util.List;


 @Controller
@RequestMapping("/dm")
public class DmController extends BaseController
{	
	private static final Logger logger = LoggerFactory.getLogger(DmController.class);
	
	@Resource
	private DmService dmService;
	

	/**
	 * 描述信息：分页查询
	 * 创建时间：2017-06-28 11:56:18
	 * @author WCL (ln_admin@yeah.net)
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/query", method=RequestMethod.POST)
	public @ResponseBody MsgModel search(DmModel model)
	{
		MsgModel msgModel = new MsgModel();
		try
		{
			dmService.findPageList(model, msgModel);
		}
		catch (Exception e)
		{
			logger.error("DmController search error：", e);
		}
		return msgModel;
	}
	
	
	/**
	 * 描述信息：增加
	 * 创建时间：2017-06-28 11:56:18
	 * @author WCL (ln_admin@yeah.net)
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public @ResponseBody MsgModel add(DmModel model, HttpServletRequest request)
	{
		MsgModel msgModel = new MsgModel();
		try
		{
			setSessionUserId(model, request);
			if (dmService.save(model) > 0)
			{
				msgModel.setSuccess(GLOBAL_MSG_BOOL_SUCCESS);
			}
		}
		catch (Exception e)
		{
			logger.error("DmController add error：", e);
		}
		return msgModel;
	}

	
	/**
	 * 描述信息：修改
	 * 创建时间：2017-06-28 11:56:18
	 * @author WCL (ln_admin@yeah.net)
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/modify", method=RequestMethod.POST)
	public @ResponseBody MsgModel modify(DmModel model, HttpServletRequest request)
	{
		MsgModel msgModel = new MsgModel();
		try
		{
			setSessionUserId(model, request);
			if (dmService.update(model) > 0)
			{
				msgModel.setSuccess(GLOBAL_MSG_BOOL_SUCCESS);
			}
		}
		catch (Exception e)
		{
			logger.error("DmController modify error：", e);
		}
		return msgModel;
	}
	
	
	/**
	 * 描述信息：删除
	 * 创建时间：2017-06-28 11:56:18
	 * @author WCL (ln_admin@yeah.net)
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/del", method=RequestMethod.POST)
	public @ResponseBody MsgModel delete(DmModel model)
	{
		MsgModel msgModel = new MsgModel();
		try
		{
			if (dmService.delete(model) > 0)
			{
				msgModel.setSuccess(GLOBAL_MSG_BOOL_SUCCESS);
			}
		}
		catch (Exception e)
		{
			logger.error("DmController delete error：", e);
		}
		return msgModel;
	}

	@RequestMapping(value = "/queryDmList",method = RequestMethod.GET)
	public @ResponseBody List<DmModel> dmList(DmModel dmModel){
		List<DmModel> dmList = new ArrayList();
		try
		{
			dmList =  dmService.getDmList(dmModel);
		}
		catch (Exception e)
		{
			logger.error("DmController query error：", e);
		}

		return dmList;
	}


}
