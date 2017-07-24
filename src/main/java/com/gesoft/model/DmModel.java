 /**
 * 文件名称：
 * 版权所有：Copyright gesoft
 * 创建时间：2017-06-28 11:56:18
 * 创 建 人：WCL (ln_admin@yeah.net)
 * 功能描述：
 **/
package com.gesoft.model;

import java.util.*;


public class DmModel extends BaseModel
{
	private static final long serialVersionUID = 5454155825314635342L;

	
	private java.lang.String bm;
	private java.lang.String nr;

	public void setBm(java.lang.String value) 
	{
		this.bm = value;
	}
	
	public java.lang.String getBm() 
	{
		return this.bm;
	}
	public void setNr(java.lang.String value) 
	{
		this.nr = value;
	}
	
	public java.lang.String getNr() 
	{
		return this.nr;
	}

}

