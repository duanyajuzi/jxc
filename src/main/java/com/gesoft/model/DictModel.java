 /**
 * 文件名称：
 * 版权所有：Copyright gesoft
 * 创建时间：2017-06-28 11:54:30
 * 创 建 人：WCL (ln_admin@yeah.net)
 * 功能描述：
 **/
package com.gesoft.model;

import java.util.*;


public class DictModel extends BaseModel
{
	private static final long serialVersionUID = 5454155825314635342L;

	private Integer id;
	private java.lang.String dmbm;
	private java.lang.String dictNo;
	private java.lang.String dictName;
	private java.lang.Integer dictIndex;
	private String nr;

	public Integer getId() {
		return id;
	}

	public DictModel setId(Integer id) {
		this.id = id;
		return this;
	}

	public void setDmbm(java.lang.String value)
	{
		this.dmbm = value;
	}
	
	public java.lang.String getDmbm() 
	{
		return this.dmbm;
	}
	public void setDictNo(java.lang.String value) 
	{
		this.dictNo = value;
	}
	
	public java.lang.String getDictNo() 
	{
		return this.dictNo;
	}
	public void setDictName(java.lang.String value) 
	{
		this.dictName = value;
	}
	
	public java.lang.String getDictName() 
	{
		return this.dictName;
	}
	public void setDictIndex(java.lang.Integer value) 
	{
		this.dictIndex = value;
	}
	
	public java.lang.Integer getDictIndex() 
	{
		return this.dictIndex;
	}

	public String getNr() {
		return nr;
	}

	public DictModel setNr(String nr) {
		this.nr = nr;
		return this;
	}
}

