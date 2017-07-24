 /**
 * 文件名称：
 * 版权所有：Copyright gesoft
 * 创建时间：2017-06-28 11:50:22
 * 创 建 人：WCL (ln_admin@yeah.net)
 * 功能描述：
 **/
package com.gesoft.controller;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import com.gesoft.service.RoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gesoft.model.RoleModel;
import com.gesoft.model.MsgModel;

import java.util.ArrayList;
import java.util.List;


 @Controller
@RequestMapping("/role")
public class RoleController extends BaseController
{	
	private static final Logger logger = LoggerFactory.getLogger(RoleController.class);
	
	@Resource
	private RoleService roleService;
	

	/**
	 * 描述信息：分页查询
	 * 创建时间：2017-06-28 11:50:22
	 * @author WCL (ln_admin@yeah.net)
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/query", method=RequestMethod.POST)
	public @ResponseBody MsgModel search(RoleModel model)
	{
		MsgModel msgModel = new MsgModel();
		try
		{
			roleService.findPageList(model, msgModel);
		}
		catch (Exception e)
		{
			logger.error("RoleController search error：", e);
		}
		return msgModel;
	}
	
	
	/**
	 * 描述信息：增加
	 * 创建时间：2017-06-28 11:50:22
	 * @author WCL (ln_admin@yeah.net)
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public @ResponseBody MsgModel add(RoleModel model, HttpServletRequest request)
	{
		MsgModel msgModel = new MsgModel();
		try
		{
			setSessionUserId(model, request);
			if (roleService.save(model) > 0)
			{
				msgModel.setSuccess(GLOBAL_MSG_BOOL_SUCCESS);
			}
		}
		catch (Exception e)
		{
			logger.error("RoleController add error：", e);
		}
		return msgModel;
	}

	
	/**
	 * 描述信息：修改
	 * 创建时间：2017-06-28 11:50:22
	 * @author WCL (ln_admin@yeah.net)
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/modify", method=RequestMethod.POST)
	public @ResponseBody MsgModel modify(RoleModel model, HttpServletRequest request)
	{
		MsgModel msgModel = new MsgModel();
		try
		{
			setSessionUserId(model, request);
			if (roleService.update(model) > 0)
			{
				msgModel.setSuccess(GLOBAL_MSG_BOOL_SUCCESS);
			}
		}
		catch (Exception e)
		{
			logger.error("RoleController modify error：", e);
		}
		return msgModel;
	}
	
	
	/**
	 * 描述信息：删除
	 * 创建时间：2017-06-28 11:50:22
	 * @author WCL (ln_admin@yeah.net)
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/del", method=RequestMethod.POST)
	public @ResponseBody MsgModel delete(RoleModel model)
	{
		MsgModel msgModel = new MsgModel();
		try
		{
			if (roleService.delete(model) > 0)
			{
				msgModel.setSuccess(GLOBAL_MSG_BOOL_SUCCESS);
			}
		}
		catch (Exception e)
		{
			logger.error("RoleController delete error：", e);
		}
		return msgModel;
	}

	@RequestMapping(value="/queryRoleList",method = RequestMethod.GET)
	@ResponseBody
	public List<RoleModel> roleList(RoleModel roleModel){
//		RoleModel roleModel = new RoleModel();
		List<RoleModel> roleList = new ArrayList();
		try
		{
			roleList =  roleService.getRoleList(roleModel);
		}
		catch (Exception e)
		{
			logger.error("RoleController search error：", e);
		}

		return roleList;
	}

	@RequestMapping(value = "/queryRoleId",method=RequestMethod.POST)
	@ResponseBody
	public int getIDbyName(RoleModel roleModel){
		int i=0;
		try{
			i=roleService.getRoleId(roleModel);
		}
		catch (Exception e) {
			logger.error("RoleController search error：", e);
		}
		return i;
	}

	@RequestMapping(value = "/saveRolePower")
	@ResponseBody
	public void saveFunctionById(RoleModel roleModel){
		try{
			roleService.saveRolePower(roleModel);
		}
		catch (Exception e) {
			logger.error("RoleController saveRolePower error：", e);
		}
	}

	@RequestMapping(value = "/queryRolePower")
	public @ResponseBody List queryList(RoleModel model) {
		List<RoleModel> list = new ArrayList();
		try {
			list=roleService.searchRolePower(model);
		} catch (Exception e) {
			logger.error("");
		}
		return list;
	}
}
