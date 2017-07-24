 /**
 * 文件名称：
 * 版权所有：Copyright gesoft
 * 创建时间：2017-06-28 11:51:13
 * 创 建 人：WCL (ln_admin@yeah.net)
 * 功能描述：
 **/
package com.gesoft.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gesoft.common.EntityDAO;
import com.gesoft.common.EntityService;
import com.gesoft.dao.PowerDAO;
import com.gesoft.model.PowerModel;

import java.util.List;

 @Service
@Transactional
public class PowerService extends EntityService<PowerModel, Long>
{

	@Resource
	private PowerDAO powerDAO;
	
	@Override
	protected EntityDAO<PowerModel, Long> getEntityDao()
	{
		return this.powerDAO;
	}

	public List<PowerModel> queryList(PowerModel model){
		return powerDAO.queryList(model);
	}

	public int queryPowerNo(PowerModel model){
		return powerDAO.queryPowerNo(model);
	}


//	public List<PowerModel> queryList1(PowerModel model){
//		return powerDAO.queryList2(model);
//	}
//	public List<PowerModel> queryList2(PowerModel model){
//		return powerDAO.queryList2(model);
//	}
}
