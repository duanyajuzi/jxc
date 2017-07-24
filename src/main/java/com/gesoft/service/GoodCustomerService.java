 /**
 * 文件名称：
 * 版权所有：Copyright gesoft
 * 创建时间：2017-07-19 13:24:49
 * 创 建 人：WCL (ln_admin@yeah.net)
 * 功能描述：
 **/
package com.gesoft.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gesoft.common.EntityDAO;
import com.gesoft.common.EntityService;
import com.gesoft.dao.GoodCustomerDAO;
import com.gesoft.model.GoodCustomerModel;

@Service
@Transactional
public class GoodCustomerService extends EntityService<GoodCustomerModel, Long>
{

	@Resource
	private GoodCustomerDAO goodCustomerDAO;
	
	@Override
	protected EntityDAO<GoodCustomerModel, Long> getEntityDao()
	{
		return this.goodCustomerDAO;
	}
}
