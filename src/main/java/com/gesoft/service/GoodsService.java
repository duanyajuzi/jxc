 /**
 * 文件名称：
 * 版权所有：Copyright gesoft
 * 创建时间：2017-06-28 12:01:42
 * 创 建 人：WCL (ln_admin@yeah.net)
 * 功能描述：
 **/
package com.gesoft.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gesoft.common.EntityDAO;
import com.gesoft.common.EntityService;
import com.gesoft.dao.GoodsDAO;
import com.gesoft.model.GoodsModel;

import java.util.List;

 @Service
@Transactional
public class GoodsService extends EntityService<GoodsModel, Long>
{

	@Resource
	private GoodsDAO goodsDAO;
	
	@Override
	protected EntityDAO<GoodsModel, Long> getEntityDao()
	{
		return this.goodsDAO;
	}

	public List<GoodsModel> queryGoodsInfo(GoodsModel model){
		return goodsDAO.queryGoodsList(model);
	}

	public List<GoodsModel> queryMaterialNum(GoodsModel model){
		return goodsDAO.queryMaterialNum(model);
	}

//	public int updateStorage(GoodsModel model){
//		return goodsDAO.updateStorage(model);
//	}

	public int updateStorage(GoodsModel model)
	{
		return goodsDAO.updateStorage(model);
	}
}
