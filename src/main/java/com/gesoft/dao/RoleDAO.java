 /**
 * 文件名称：
 * 版权所有：Copyright gesoft
 * 创建时间：2017-06-28 11:50:22
 * 创 建 人：WCL (ln_admin@yeah.net)
 * 功能描述：
 **/
package com.gesoft.dao;

import org.springframework.stereotype.Repository;

import com.gesoft.common.EntityDAOImpl;
import com.gesoft.model.RoleModel;

import java.util.List;


 @Repository
public class RoleDAO extends EntityDAOImpl<RoleModel, Long>
{
	@Override
    public String getMybatisSqlMapNamespace()
    {
        return "RoleMapper";
    }

    public List<RoleModel> getRoleList(RoleModel roleModel){
        return getSqlSession().selectList(getFindRoleListStatement(),roleModel);
    }

    public String getFindRoleListStatement()
    {
        return getMybatisSqlMapNamespace() + ".roleList";
    }

    public int getRoleId(RoleModel roleModel){
        return (int) getSqlSession().selectOne(getRoleIdStatement(),roleModel);
    }
    public String getRoleIdStatement(){
        return getMybatisSqlMapNamespace() + ".queryId";
    }

    /**
     * 根据角色ID来删除角色权限关系表中数据
     * @param roleId
     * @return
     */
    public int deleteRolePower(int roleId)
    {
        return getSqlSession().delete(getMybatisSqlMapNamespace() + ".deleteRolePower",roleId);
    }

    /**
     * 插入角色权限功能
     * @param roleModel
     * @return
     */
    public int addRolePower(RoleModel roleModel)
    {
        return getSqlSession().insert(getMybatisSqlMapNamespace() + ".addRolePower", roleModel);
    }

    public  List<RoleModel> queryRolePower(RoleModel model) {
        return getSqlSession().selectList(getMybatisSqlMapNamespace() + ".queryRolePower", model);
    }
}
