 /**
 * 文件名称：
 * 版权所有：Copyright gesoft
 * 创建时间：2017-06-28 11:54:30
 * 创 建 人：WCL (ln_admin@yeah.net)
 * 功能描述：
 **/
package com.gesoft.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gesoft.common.EntityDAO;
import com.gesoft.common.EntityService;
import com.gesoft.dao.DictDAO;
import com.gesoft.model.DictModel;

import java.util.List;

 @Service
@Transactional
public class DictService extends EntityService<DictModel, Long>
{

	@Resource
	private DictDAO dictDAO;
	
	@Override
	protected EntityDAO<DictModel, Long> getEntityDao()
	{
		return this.dictDAO;
	}

//	public List<DictModel> getIsLeafList(DictModel model){
//		return dictDAO.getIsLeafList(model);
//	}
}
