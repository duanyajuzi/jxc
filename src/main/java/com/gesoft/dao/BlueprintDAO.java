 /**
 * 文件名称：
 * 版权所有：Copyright gesoft
 * 创建时间：2017-06-28 13:21:51
 * 创 建 人：WCL (ln_admin@yeah.net)
 * 功能描述：
 **/
package com.gesoft.dao;

import org.springframework.stereotype.Repository;

import com.gesoft.common.EntityDAOImpl;
import com.gesoft.model.BlueprintModel;


@Repository
public class BlueprintDAO extends EntityDAOImpl<BlueprintModel, Long>
{
	@Override
    public String getMybatisSqlMapNamespace()
    {
        return "BlueprintMapper";
    }
}
