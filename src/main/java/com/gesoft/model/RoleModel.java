 /**
 * 文件名称：
 * 版权所有：Copyright gesoft
 * 创建时间：2017-06-28 11:50:22
 * 创 建 人：WCL (ln_admin@yeah.net)
 * 功能描述：
 **/
package com.gesoft.model;

import java.util.*;


public class RoleModel extends BaseModel
{
	private static final long serialVersionUID = 5454155825314635342L;

	
	private java.lang.Integer id;
	private String ids;//权限id
	private Integer roleId;
	private java.lang.Integer powerId;
	private java.lang.String roleName;
	private java.lang.String roleMemo;
	private Integer pid;

	public void setId(java.lang.Integer value) 
	{
		this.id = value;
	}
	
	public java.lang.Integer getId() 
	{
		return this.id;
	}
	public void setRoleName(java.lang.String value) 
	{
		this.roleName = value;
	}
	
	public java.lang.String getRoleName() 
	{
		return this.roleName;
	}
	public void setRoleMemo(java.lang.String value) 
	{
		this.roleMemo = value;
	}
	
	public java.lang.String getRoleMemo() 
	{
		return this.roleMemo;
	}

	public Integer getPowerId() {
		return powerId;
	}

	public RoleModel setPowerId(Integer powerId) {
		this.powerId = powerId;
		return this;
	}
	public String getIds() {
		return ids;
	}

	public RoleModel setIds(String ids) {
		this.ids = ids;
		return this;
	}

	public Integer getRoleId() {
		return roleId;
	}

	public RoleModel setRoleId(Integer roleId) {
		this.roleId = roleId;
		return this;
	}

	public Integer getPid() {
		return pid;
	}

	public RoleModel setPid(Integer pid) {
		this.pid = pid;
		return this;
	}
}

