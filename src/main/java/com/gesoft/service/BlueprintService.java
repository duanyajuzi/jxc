 /**
 * 文件名称：
 * 版权所有：Copyright gesoft
 * 创建时间：2017-06-28 13:21:51
 * 创 建 人：WCL (ln_admin@yeah.net)
 * 功能描述：
 **/
package com.gesoft.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gesoft.common.EntityDAO;
import com.gesoft.common.EntityService;
import com.gesoft.dao.BlueprintDAO;
import com.gesoft.model.BlueprintModel;

@Service
@Transactional
public class BlueprintService extends EntityService<BlueprintModel, Long>
{

	@Resource
	private BlueprintDAO blueprintDAO;
	
	@Override
	protected EntityDAO<BlueprintModel, Long> getEntityDao()
	{
		return this.blueprintDAO;
	}
}
