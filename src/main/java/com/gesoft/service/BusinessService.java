 /**
 * 文件名称：
 * 版权所有：Copyright gesoft
 * 创建时间：2017-06-28 11:57:13
 * 创 建 人：WCL (ln_admin@yeah.net)
 * 功能描述：
 **/
package com.gesoft.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gesoft.common.EntityDAO;
import com.gesoft.common.EntityService;
import com.gesoft.dao.BusinessDAO;
import com.gesoft.model.BusinessModel;

import java.util.List;

 @Service
@Transactional
public class BusinessService extends EntityService<BusinessModel, Long>
{

	@Resource
	private BusinessDAO businessDAO;
	
	@Override
	protected EntityDAO<BusinessModel, Long> getEntityDao()
	{
		return this.businessDAO;
	}
	public List<BusinessModel> queryBusiness(BusinessModel model){
		return businessDAO.queryBusiness(model);
	}
}
