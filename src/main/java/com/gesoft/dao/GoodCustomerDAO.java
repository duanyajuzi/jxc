 /**
 * 文件名称：
 * 版权所有：Copyright gesoft
 * 创建时间：2017-07-19 13:24:49
 * 创 建 人：WCL (ln_admin@yeah.net)
 * 功能描述：
 **/
package com.gesoft.dao;

import org.springframework.stereotype.Repository;

import com.gesoft.common.EntityDAOImpl;
import com.gesoft.model.GoodCustomerModel;


@Repository
public class GoodCustomerDAO extends EntityDAOImpl<GoodCustomerModel, Long>
{
	@Override
    public String getMybatisSqlMapNamespace()
    {
        return "GoodCustomerMapper";
    }
}
