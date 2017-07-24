/**
 * 文件名称：LoginController.java
 * 版权所有：Copyright gesoft
 * 创建时间：2015年1月30日
 * 创 建 人：WCL (ln_admin@yeah.net)
 * 功能描述：
 **/
package com.gesoft.controller;
import com.gesoft.model.UserModel;
import com.gesoft.service.LoginService;
import com.gesoft.service.PowerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.*;
import java.util.Map.Entry;

/**
 * 用户登录
 * @author WCL
 * @version v1.001
 * @since   v1.001
 */
@Controller
public class LoginController extends BaseController
{
	private static final Logger logger = LoggerFactory.getLogger(LoginController.class);
	
	@Resource
	private LoginService loginService;

	@Resource
	private PowerService powerService;


	/**
	 * 描述信息：登录界面
	 * 创建时间：2015年1月31日 下午2:48:29
	 * @author WCL (ln_admin@yeah.net)
	 * @return
	 */
	@RequestMapping(value="/login")
	public String toLogin()
	{
		return "/login/login";
	}

	
	/**
	 * 描述信息：用户登录
	 * 创建时间：2015年1月30日 下午6:16:02
	 * @author WCL (ln_admin@yeah.net)
	 * @param user
	 * @param model
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value="/user/login")
	public ModelAndView login(UserModel user, ModelMap model, HttpServletRequest request, HttpServletResponse response)
	{
		ModelAndView result = new ModelAndView("/login/login");
		HttpSession session = request.getSession();
		try
		{
			if(user != null)
			{
				UserModel mLoginModel = loginService.login(user);
				if (user.state == GLOBAL_SERVICE_SUCCESS)
				{
					result = new ModelAndView("/common/main");
					model.addAttribute("loginUser", mLoginModel);

					session = request.getSession(true);
					session.setAttribute("OnLineUser", loginService);
					session.setAttribute(SESSION_KEY_ISLOGIN, GLOBAL_YES);
					session.setAttribute(SESSION_KEY_LOGINNAME, mLoginModel.getUserName());
					session.setAttribute(SESSION_KEY_UID, mLoginModel.getUserId());

					List powerList = powerService.queryRolePowerList(mLoginModel.getRoleId());
					result.addObject("powerList", powerList);


				}
				else
				{
					result.addObject("errorinfo", user.msgValue);
				}
			}

			result.addObject("userName", user.getUserName());
		}
		catch (Exception e)
		{
			logger.error("LoginController login error：", e);
		}
		return result;
	}


	/**
	 * 注销
	 * @param request
	 * @param response
     * @return
     */
	@RequestMapping(value="/user/logout")
	public String logout(HttpServletRequest request, HttpServletResponse response)
	{
		try
		{
			HttpSession session = request.getSession();
			session.invalidate();
		}
		catch (Exception e)
		{
			logger.error("LoginController logout error：", e);
		}
		return "/login/login";
	}
}

