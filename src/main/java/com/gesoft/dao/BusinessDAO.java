 /**
 * 文件名称：
 * 版权所有：Copyright gesoft
 * 创建时间：2017-06-28 11:57:13
 * 创 建 人：WCL (ln_admin@yeah.net)
 * 功能描述：
 **/
package com.gesoft.dao;

import org.springframework.stereotype.Repository;

import com.gesoft.common.EntityDAOImpl;
import com.gesoft.model.BusinessModel;

import java.util.List;


 @Repository
public class BusinessDAO extends EntityDAOImpl<BusinessModel, Long>
{
	@Override
    public String getMybatisSqlMapNamespace()
    {
        return "BusinessMapper";
    }

    public List<BusinessModel> queryBusiness(BusinessModel model){
        return getSqlSession().selectList(getMybatisSqlMapNamespace() + ".queryBusinessList", model);
    }
}
