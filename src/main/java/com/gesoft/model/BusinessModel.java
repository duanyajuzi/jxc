 /**
 * 文件名称：
 * 版权所有：Copyright gesoft
 * 创建时间：2017-06-28 11:57:13
 * 创 建 人：WCL (ln_admin@yeah.net)
 * 功能描述：
 **/
package com.gesoft.model;

import java.util.*;


public class BusinessModel extends BaseModel
{
	private static final long serialVersionUID = 5454155825314635342L;

	
	private java.lang.Integer id;
	private java.lang.String business;
	private java.lang.String memo;

	public void setId(java.lang.Integer value) 
	{
		this.id = value;
	}
	
	public java.lang.Integer getId() 
	{
		return this.id;
	}
	public void setBusiness(java.lang.String value) 
	{
		this.business = value;
	}
	
	public java.lang.String getBusiness() 
	{
		return this.business;
	}
	public void setMemo(java.lang.String value) 
	{
		this.memo = value;
	}
	
	public java.lang.String getMemo() 
	{
		return this.memo;
	}

}

