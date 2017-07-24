 /**
 * 文件名称：
 * 版权所有：Copyright gesoft
 * 创建时间：2017-07-19 13:24:33
 * 创 建 人：WCL (ln_admin@yeah.net)
 * 功能描述：
 **/
package com.gesoft.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gesoft.common.EntityDAO;
import com.gesoft.common.EntityService;
import com.gesoft.dao.BillDAO;
import com.gesoft.model.BillModel;

@Service
@Transactional
public class BillService extends EntityService<BillModel, Long>
{

	@Resource
	private BillDAO billDAO;
	
	@Override
	protected EntityDAO<BillModel, Long> getEntityDao()
	{
		return this.billDAO;
	}
}
