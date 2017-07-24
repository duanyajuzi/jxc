 /**
 * 文件名称：
 * 版权所有：Copyright gesoft
 * 创建时间：2017-06-28 11:56:18
 * 创 建 人：WCL (ln_admin@yeah.net)
 * 功能描述：
 **/
package com.gesoft.dao;

import com.gesoft.model.RoleModel;
import org.springframework.stereotype.Repository;

import com.gesoft.common.EntityDAOImpl;
import com.gesoft.model.DmModel;

import java.util.List;


 @Repository
public class DmDAO extends EntityDAOImpl<DmModel, Long>
{
	@Override
    public String getMybatisSqlMapNamespace()
    {
        return "DmMapper";
    }

    public List<DmModel> getDmList(DmModel model){
        return getSqlSession().selectList(getFindDmListStatement(),model);
    }

    public String getFindDmListStatement()
    {
        return getMybatisSqlMapNamespace() + ".dmList";
    }
}
