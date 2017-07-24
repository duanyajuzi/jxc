 /**
 * 文件名称：
 * 版权所有：Copyright gesoft
 * 创建时间：2017-06-28 11:50:08
 * 创 建 人：WCL (ln_admin@yeah.net)
 * 功能描述：
 **/
package com.gesoft.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gesoft.common.EntityDAO;
import com.gesoft.common.EntityService;
import com.gesoft.dao.UserDAO;
import com.gesoft.model.UserModel;

@Service
@Transactional
public class UserService extends EntityService<UserModel, Long>
{

	@Resource
	private UserDAO userDAO;
	
	@Override
	protected EntityDAO<UserModel, Long> getEntityDao()
	{
		return this.userDAO;
	}
}
