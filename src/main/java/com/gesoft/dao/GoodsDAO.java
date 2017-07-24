 /**
 * 文件名称：
 * 版权所有：Copyright gesoft
 * 创建时间：2017-06-28 11:58:46
 * 创 建 人：WCL (ln_admin@yeah.net)
 * 功能描述：
 **/
package com.gesoft.dao;

import com.gesoft.model.GoodsModel;
import org.springframework.stereotype.Repository;

import com.gesoft.common.EntityDAOImpl;

import java.util.List;


 @Repository
public class GoodsDAO extends EntityDAOImpl<GoodsModel, Long>
{
	@Override
    public String getMybatisSqlMapNamespace()
    {
        return "GoodsMapper";
    }

    public List<GoodsModel> queryGoodsList(GoodsModel model){
        return getSqlSession().selectList(getMybatisSqlMapNamespace() + ".queryGoodsList", model);
    }

    public List<GoodsModel> queryMaterialNum(GoodsModel model){
        return getSqlSession().selectList(getMybatisSqlMapNamespace() + ".queryMaterialNum", model);
    }

    public int updateStorage(GoodsModel model){
        return getSqlSession().update(getMybatisSqlMapNamespace() + ".updateStorage",model);
    }
}
