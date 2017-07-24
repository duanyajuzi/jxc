 /**
 * 文件名称：
 * 版权所有：Copyright gesoft
 * 创建时间：2017-06-28 11:54:30
 * 创 建 人：WCL (ln_admin@yeah.net)
 * 功能描述：
 **/
package com.gesoft.dao;

import org.springframework.stereotype.Repository;

import com.gesoft.common.EntityDAOImpl;
import com.gesoft.model.DictModel;

import java.util.List;


 @Repository
public class DictDAO extends EntityDAOImpl<DictModel, Long>
{
	@Override
    public String getMybatisSqlMapNamespace()
    {
        return "DictMapper";
    }

//    public List<DictModel> getIsLeafList(DictModel model){
//        return getSqlSession().selectList(getIsLeafListStatement(), model);
//    }

//    public String getIsLeafListStatement()
//    {
//        return getMybatisSqlMapNamespace() + ".queryIsLeaf";
//    }
}
