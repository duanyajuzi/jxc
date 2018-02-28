 /**
 * 文件名称：
 * 版权所有：Copyright gesoft
 * 创建时间：2017-06-28 13:21:51
 * 创 建 人：WCL (ln_admin@yeah.net)
 * 功能描述：
 **/
package com.gesoft.model;

import java.util.*;


public class BlueprintModel extends BaseModel
{
	private static final long serialVersionUID = 5454155825314635342L;

	
	private java.lang.Long id;
	private java.lang.Long goodsId;
	private java.lang.Long goodsNum;
	private java.lang.Float price;
	private java.lang.String unit;
	private java.lang.String pname;
	private java.lang.String memo;
	private String goodsName;
	private String dictName;
	private java.lang.String materialNum;

	public void setId(java.lang.Long value) 
	{
		this.id = value;
	}
	
	public java.lang.Long getId() 
	{
		return this.id;
	}
	public void setGoodsId(java.lang.Long value) 
	{
		this.goodsId = value;
	}
	
	public java.lang.Long getGoodsId() 
	{
		return this.goodsId;
	}
	public void setGoodsNum(java.lang.Long value) 
	{
		this.goodsNum = value;
	}
	
	public java.lang.Long getGoodsNum() 
	{
		return this.goodsNum;
	}
	public void setPrice(java.lang.Float value) 
	{
		this.price = value;
	}
	
	public java.lang.Float getPrice() 
	{
		return this.price;
	}
	public void setUnit(java.lang.String value) 
	{
		this.unit = value;
	}
	
	public java.lang.String getUnit() 
	{
		return this.unit;
	}
	public void setPname(java.lang.String value) 
	{
		this.pname = value;
	}
	
	public java.lang.String getPname() 
	{
		return this.pname;
	}
	public void setMemo(java.lang.String value) 
	{
		this.memo = value;
	}
	
	public java.lang.String getMemo() 
	{
		return this.memo;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public BlueprintModel setGoodsName(String goodsName) {
		this.goodsName = goodsName;
		return this;
	}

	public String getDictName() {
		return dictName;
	}

	public BlueprintModel setDictName(String dictName) {
		this.dictName = dictName;
		return this;
	}

	public void setMaterialNum(java.lang.String value)
	{
		this.materialNum = value;
	}
	public java.lang.String getMaterialNum()
	{
		return this.materialNum;
	}
}

