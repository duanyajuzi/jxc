 /**
 * 文件名称：
 * 版权所有：Copyright gesoft
 * 创建时间：2017-06-28 11:56:39
 * 创 建 人：WCL (ln_admin@yeah.net)
 * 功能描述：
 **/
package com.gesoft.dao;

import org.springframework.stereotype.Repository;

import com.gesoft.common.EntityDAOImpl;
import com.gesoft.model.CustomerModel;

import java.util.List;


 @Repository
public class CustomerDAO extends EntityDAOImpl<CustomerModel, Long>
{
	@Override
    public String getMybatisSqlMapNamespace()
    {
        return "CustomerMapper";
    }

    public List<CustomerModel> queryCustomerList(CustomerModel model) {
        return getSqlSession().selectList(getMybatisSqlMapNamespace() + ".queryCustomerList", model);
    }

    public List<CustomerModel> queryOtherInfoList(CustomerModel model){
        return getSqlSession().selectList(getMybatisSqlMapNamespace() + ".queryOtherInfoList", model);
    }

}
