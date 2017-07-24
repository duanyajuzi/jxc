 /**
 * 文件名称：
 * 版权所有：Copyright gesoft
 * 创建时间：2017-06-28 11:50:08
 * 创 建 人：WCL (ln_admin@yeah.net)
 * 功能描述：
 **/
package com.gesoft.dao;

import org.springframework.stereotype.Repository;

import com.gesoft.common.EntityDAOImpl;
import com.gesoft.model.UserModel;


@Repository
public class UserDAO extends EntityDAOImpl<UserModel, Long>
{
	@Override
    public String getMybatisSqlMapNamespace()
    {
        return "UserMapper";
    }
}
