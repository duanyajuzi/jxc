package com.gesoft.model;

/**
 * Created by admin on 2017-06-30.
 */
public class RolePowerModel extends BaseModel{
    private static final long serialVersionUID = 5454155825314635342L;
    private Integer id;
    private Integer pid;
    private Integer roleId;
    private String roleName;
    private String powerName;
    private Integer isLeaf;


    public Integer getId() {
        return id;
    }

    public RolePowerModel setId(Integer id) {
        this.id = id;
        return this;
    }

    public Integer getPid() {
        return pid;
    }

    public RolePowerModel setPid(Integer pid) {
        this.pid = pid;
        return this;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public RolePowerModel setRoleId(Integer roleId) {
        this.roleId = roleId;
        return this;
    }

    public String getPowerName() {
        return powerName;
    }

    public RolePowerModel setPowerName(String powerName) {
        this.powerName = powerName;
        return this;
    }

    public String getRoleName() {
        return roleName;
    }

    public RolePowerModel setRoleName(String roleName) {
        this.roleName = roleName;
        return this;
    }

    public Integer getIsLeaf() {
        return isLeaf;
    }

    public RolePowerModel setIsLeaf(Integer isLeaf) {
        this.isLeaf = isLeaf;
        return this;
    }
}
