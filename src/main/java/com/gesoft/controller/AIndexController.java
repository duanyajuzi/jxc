/**
 * 文件名称：AIndexController.java
 * 版权所有：Copyright gesoft
 * 创建时间：2015年1月29日
 * 创 建 人：WCL (ln_admin@yeah.net)
 * 功能描述：
 **/
package com.gesoft.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author WCL
 * @version v1.001
 * @since   v1.001
 */
@Controller
@RequestMapping("/admin")
public class AIndexController extends BaseController
{
	
	
	/**
	 * 描述信息：主界面
	 * 创建时间：2015年1月31日 下午2:48:44
	 * @author WCL (ln_admin@yeah.net)
	 * @return
	 */
	@RequestMapping(value="/main")
	public String main()
	{
		return "/common/main";
	}
	

	/**
	 * 描述信息：跳转
	 * 创建时间：2015年1月31日 下午1:54:19
	 * @author WCL (ln_admin@yeah.net)
	 * @return
	 */
	@RequestMapping(value="/redirect.html")
	public String toRedirect()
	{
		return "/admin/common/redirect_page_info";
	}
	
	/**
	 * 描述信息：验证码
	 * 创建时间：2015年1月31日 下午2:48:17
	 * @author WCL (ln_admin@yeah.net)
	 * @return
	 */
	@RequestMapping(value="/img.html")
	public String toImg()
	{
		return "/admin/login/image";
	}
	
	/**
	 * 描述信息：登录界面
	 * 创建时间：2015年1月31日 下午2:48:29
	 * @author WCL (ln_admin@yeah.net)
	 * @return
	 */
	@RequestMapping(value="/login.html")
	public String toLogin()
	{
		return "/admin/login/login";
	}
	
	/**
	 * 描述信息：角色管理
	 * 创建时间：2015年1月29日 下午6:12:39
	 * @author WCL (ln_admin@yeah.net)
	 * @return
	 */
	@RequestMapping(value="/user.html")
	public String toUser()
	{
		return "/admin/baseinfo/manage_user_info";
	}
	
	@RequestMapping(value="/role.html")
	public String role()
	{
		return "/admin/baseinfo/role";
	}
	
	@RequestMapping(value="/piv.html")
	public String piv()
	{
		return "/admin/baseinfo/piv";
	}
	
	/**
	 * 描述信息：定制
	 * 创建时间：2015年2月3日 下午11:19:49
	 * @author WCL (ln_admin@yeah.net)
	 * @return
	 */
	@RequestMapping(value="/custom.html")
	public String toCurstom()
	{
		return "/admin/baseinfo/manage_custom_info";
	}
	

	/**
	 * 描述信息：新闻类型
	 * 创建时间：2015年2月3日 下午11:19:49
	 * @author WCL (ln_admin@yeah.net)
	 * @return
	 */
	@RequestMapping(value="/newtype.html")
	public String toNewType()
	{
		return "/admin/baseinfo/manage_newtype_info";
	}
	

	/**
	 * 描述信息：经典案例类型
	 * 创建时间：2015年2月4日 上午9:22:56
	 * @author WCL (ln_admin@yeah.net)
	 * @return
	 */
	@RequestMapping(value="/casetype.html")
	public String toCaseType()
	{
		return "/admin/baseinfo/manage_casetype_info";
	}
	
	/**
	 * 描述信息：产品中心类型
	 * 创建时间：2015年2月4日 上午11:11:12
	 * @author WCL (ln_admin@yeah.net)
	 * @return
	 */
	@RequestMapping(value="/producetype.html")
	public String toProduceType()
	{
		return "/admin/baseinfo/manage_producetype_info";
	}
	
	/**
	 * 描述信息：联系我们
	 * 创建时间：2015年2月10日 下午1:12:45
	 * @author WCL (ln_admin@yeah.net)
	 * @return
	 */
	@RequestMapping(value="/contactus.html")
	public String toContactUs()
	{
		return "/admin/baseinfo/manage_contactus_info";
	}
	
	
	/**
	 * 描述信息：关于我们
	 * 创建时间：2015年2月5日 上午3:26:34
	 * @author WCL (ln_admin@yeah.net)
	 * @return
	 */
	@RequestMapping(value="/about.html")
	public String toAbout()
	{
		return "/admin/baseinfo/manage_about_info";
	}
	
	

	/**
	 * 描述信息：新闻管理
	 * 创建时间：2015年2月5日 上午3:26:34
	 * @author WCL (ln_admin@yeah.net)
	 * @return
	 */
	@RequestMapping(value="/news.html")
	public String toNews()
	{
		return "/admin/baseinfo/manage_news_info";
	}
	
	
	/**
	 * 描述信息：经典案例
	 * 创建时间：2015年2月5日 上午10:20:27
	 * @author WCL (ln_admin@yeah.net)
	 * @return
	 */
	@RequestMapping(value="/classiccase.html")
	public String toClassicCase()
	{
		return "/admin/baseinfo/manage_classiccase_info";
	}
	
	
	/**
	 * 描述信息：产品中心
	 * 创建时间：2015年2月5日 上午10:23:23
	 * @author WCL (ln_admin@yeah.net)
	 * @return
	 */
	@RequestMapping(value="/produce.html")
	public String toProduce()
	{
		return "/admin/baseinfo/manage_produce_info";
	}
	
	
	/**
	 * 描述信息：客户服务
	 * 创建时间：2015年2月5日 下午2:51:00
	 * @author WCL (ln_admin@yeah.net)
	 * @return
	 */
	@RequestMapping(value="/customer.html")
	public String toCustomer()
	{
		return "/admin/baseinfo/manage_customer_info";
	}
	
	
	/**
	 * 描述信息：客户服务
	 * 创建时间：2015年2月5日 下午2:51:00
	 * @author WCL (ln_admin@yeah.net)
	 * @return
	 */
	@RequestMapping(value="/logo.html")
	public String toLogo()
	{
		return "/admin/baseinfo/manage_logo_info";
	}
	
	
	/**
	 * 描述信息：留言管理
	 * 创建时间：2015年2月10日 下午4:53:34
	 * @author WCL (ln_admin@yeah.net)
	 * @return
	 */
	@RequestMapping(value="/feedback.html")
	public String toFeedBack()
	{
		return "/admin/baseinfo/manage_feedback_info";
	}
	
	/**
	 * 描述信息：友情链接
	 * 创建时间：2015年2月10日 下午10:49:13
	 * @author WCL (ln_admin@yeah.net)
	 * @return
	 */
	@RequestMapping(value="/links.html")
	public String toLinks()
	{
		return "/admin/baseinfo/manage_links_info";
	}
	
	/**
	 * 描述信息：QQ联系
	 * 创建时间：2015年2月10日 下午10:49:29
	 * @author WCL (ln_admin@yeah.net)
	 * @return
	 */
	@RequestMapping(value="/qq.html")
	public String toQQ()
	{
		return "/admin/baseinfo/manage_qq_info";
	}
	
	/**
	 * 描述信息：当前在线登录用户数
	 * 创建时间：2015年1月31日 下午1:54:00
	 * @author WCL (ln_admin@yeah.net)
	 * @return
	 */
	@RequestMapping(value="/online.html")
	public String toOnline()
	{
		return "/admin/baseinfo/query_online_user_info";
	}
	
	
	/**
	 * 描述信息：修改日志
	 * 创建时间：2015年1月31日 下午2:47:02
	 * @author WCL (ln_admin@yeah.net)
	 * @return
	 */
	@RequestMapping(value="/updatelog.html")
	public String toUpDateLog()
	{
		return "/admin/baseinfo/query_update_log";
	}
	
}
