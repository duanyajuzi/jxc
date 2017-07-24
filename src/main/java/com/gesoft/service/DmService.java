 /**
 * 文件名称：
 * 版权所有：Copyright gesoft
 * 创建时间：2017-06-28 11:56:18
 * 创 建 人：WCL (ln_admin@yeah.net)
 * 功能描述：
 **/
package com.gesoft.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.gesoft.common.EntityDAO;
import com.gesoft.common.EntityService;
import com.gesoft.dao.DmDAO;
import com.gesoft.model.DmModel;
import java.util.List;

 @Service
@Transactional
public class DmService extends EntityService<DmModel, Long>
{

	@Resource
	private DmDAO dmDAO;
	
	@Override
	protected EntityDAO<DmModel, Long> getEntityDao()
	{
		return this.dmDAO;
	}

	public List<DmModel> getDmList(DmModel model){
		return dmDAO.getDmList(model);
	}
}
