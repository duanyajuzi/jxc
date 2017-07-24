 /**
 * 文件名称：
 * 版权所有：Copyright gesoft
 * 创建时间：2017-06-28 11:51:13
 * 创 建 人：WCL (ln_admin@yeah.net)
 * 功能描述：
 **/
package com.gesoft.dao;

import org.springframework.stereotype.Repository;

import com.gesoft.common.EntityDAOImpl;
import com.gesoft.model.PowerModel;

import java.util.List;


 @Repository
public class PowerDAO extends EntityDAOImpl<PowerModel, Long>
{
	@Override
    public String getMybatisSqlMapNamespace()
    {
        return "PowerMapper";
    }

    public List<PowerModel> queryList(PowerModel model)
    {
        return getSqlSession().selectList(getMybatisSqlMapNamespace() + ".powerList", model);
    }

    public int queryPowerNo(PowerModel model){
        return getSqlSession().selectOne(getMybatisSqlMapNamespace() + ".queryPowerNo",model);
    }

//
//    public List<PowerModel> queryList1(PowerModel model)
//    {
//        return getSqlSession().selectList(getMybatisSqlMapNamespace() + ".parentList", model);
//    }
//
//    public List<PowerModel> queryList2(PowerModel model){
//        return getSqlSession().selectList(getMybatisSqlMapNamespace()+".childrenList",model);
//    }

}
