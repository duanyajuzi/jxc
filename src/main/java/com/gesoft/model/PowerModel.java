 /**
 * 文件名称：
 * 版权所有：Copyright gesoft
 * 创建时间：2017-06-28 11:51:13
 * 创 建 人：WCL (ln_admin@yeah.net)
 * 功能描述：
 **/
package com.gesoft.model;

import java.util.*;


public class PowerModel extends BaseModel
{
	private static final long serialVersionUID = 5454155825314635342L;

	
	private java.lang.Integer id;
	private java.lang.String powerName;
	private java.lang.String powerNo;
	private java.lang.Integer pid;
	private java.lang.String url;
	private java.lang.Integer leaf;
	private java.lang.String icon;
	private String name1;
	private String name2;
//	private List<PowerModel> children;
//	public List<PowerModel> getChildren() {
//		return children;
//	}
//	public PowerModel setChildren(List<PowerModel> children) {
//		this.children = children;
//		return this;
//	}

	public void setId(java.lang.Integer value)
	{
		this.id = value;
	}
	
	public java.lang.Integer getId() 
	{
		return this.id;
	}
	public void setPowerName(java.lang.String value) 
	{
		this.powerName = value;
	}
	
	public java.lang.String getPowerName() 
	{
		return this.powerName;
	}
	public void setPowerNo(java.lang.String value) 
	{
		this.powerNo = value;
	}
	
	public java.lang.String getPowerNo() 
	{
		return this.powerNo;
	}
	public void setPid(java.lang.Integer value) 
	{
		this.pid = value;
	}
	
	public java.lang.Integer getPid() 
	{
		return this.pid;
	}
	public void setUrl(java.lang.String value) 
	{
		this.url = value;
	}
	
	public java.lang.String getUrl() 
	{
		return this.url;
	}
	public void setLeaf(java.lang.Integer value)
	{
		this.leaf = value;
	}
	
	public java.lang.Integer getLeaf()
	{
		return this.leaf;
	}
	public void setIcon(java.lang.String value) 
	{
		this.icon = value;
	}
	
	public java.lang.String getIcon() 
	{
		return this.icon;
	}

	public String getName1() {
		return name1;
	}

	public PowerModel setName1(String name1) {
		this.name1 = name1;
		return this;
	}

	public String getName2() {
		return name2;
	}

	public PowerModel setName2(String name2) {
		this.name2 = name2;
		return this;
	}

}

