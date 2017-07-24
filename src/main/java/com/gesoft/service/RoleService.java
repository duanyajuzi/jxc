 /**
 * 文件名称：
 * 版权所有：Copyright gesoft
 * 创建时间：2017-06-28 11:50:22
 * 创 建 人：WCL (ln_admin@yeah.net)
 * 功能描述：
 **/
package com.gesoft.service;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gesoft.common.EntityDAO;
import com.gesoft.common.EntityService;
import com.gesoft.dao.RoleDAO;
import com.gesoft.model.RoleModel;

import java.util.ArrayList;
import java.util.List;

 @Service
@Transactional
public class RoleService extends EntityService<RoleModel, Long>
{

	@Resource
	private RoleDAO roleDAO;

	@Override
	protected EntityDAO<RoleModel, Long> getEntityDao()
	{
		return this.roleDAO;
	}


	public List<RoleModel> getRoleList(RoleModel roleModel){
		return roleDAO.getRoleList(roleModel);
	}

	public int getRoleId(RoleModel roleModel){
		return roleDAO.getRoleId(roleModel);
	}

	public void saveRolePower(RoleModel roleModel)
	{
		// 删除以前数据
		roleDAO.deleteRolePower(roleModel.getId());
		int i=roleModel.getId();

		// 插入角色权限数据
		if (null ==  roleModel.getIds() || "".equals(roleModel.getIds()) )
		{
			return ;
		}
		String[] idFly = roleModel.getIds().split("\\,");
		for (int j = 0; j < idFly.length; j++)
		{
			RoleModel model=new RoleModel();
			model.setId(i);
			model.setPowerId(Integer.parseInt(idFly[j]));
			roleDAO.addRolePower(model);
		}
	}

	public  List<RoleModel> searchRolePower(RoleModel model) {
		List<RoleModel> rplist = new ArrayList();
		rplist = roleDAO.queryRolePower(model);
		return rplist;
	}

}
