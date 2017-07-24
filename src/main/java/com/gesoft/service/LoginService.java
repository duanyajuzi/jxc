/**
 * 文件名称：ALoginService.java
 * 版权所有：Copyright gesoft
 * 创建时间：2015年1月30日
 * 创 建 人：WCL (ln_admin@yeah.net)
 * 功能描述：
 **/
package com.gesoft.service;

import com.gesoft.dao.UserDAO;
import com.gesoft.model.UserModel;
import com.gesoft.util.Constants;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * 用户登录
 * @author WCL
 * @version v1.001
 * @since   v1.001
 */
@Service
@Transactional
public class LoginService implements Constants
{
	@Resource
	private UserDAO userDAO;
	
	/**
	 * 描述信息：用户登录
	 * 创建时间：2015年1月31日 上午10:03:43
	 * @author WCL (ln_admin@yeah.net)
	 * @param model
	 * @return
	 */
	@Transactional(readOnly=true)
	public UserModel login(UserModel model)
	{
		UserModel mUserModel = userDAO.getByName(model);
		if (mUserModel != null)
		{
			if (mUserModel.getUserPwd() == null || (!mUserModel.getUserPwd().equals(model.getUserPwd())))
			{

				model.state = GLOBAL_SERVICE_FAIL;
				model.setMsgValue("密码输入不正确！");
			}
			else 
			{
				model.state = GLOBAL_SERVICE_SUCCESS;
			}
		}
		else
		{
			model.state = GLOBAL_SERVICE_FAIL;
			model.setMsgValue("此用户名不存在！");
		}
		return mUserModel;
	}
}
