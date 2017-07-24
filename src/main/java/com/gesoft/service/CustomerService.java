 /**
 * 文件名称：
 * 版权所有：Copyright gesoft
 * 创建时间：2017-06-28 11:56:39
 * 创 建 人：WCL (ln_admin@yeah.net)
 * 功能描述：
 **/
package com.gesoft.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gesoft.common.EntityDAO;
import com.gesoft.common.EntityService;
import com.gesoft.dao.CustomerDAO;
import com.gesoft.model.CustomerModel;

import java.util.List;

 @Service
@Transactional
public class CustomerService extends EntityService<CustomerModel, Long>
{

	@Resource
	private CustomerDAO customerDAO;
	
	@Override
	protected EntityDAO<CustomerModel, Long> getEntityDao()
	{
		return this.customerDAO;
	}

	public List<CustomerModel> queryCustomerList(CustomerModel model) {
		return customerDAO.queryCustomerList(model);
	}

	public List<CustomerModel> queryOtherInfo(CustomerModel model){
		return  customerDAO.queryOtherInfoList(model);
	}
}
