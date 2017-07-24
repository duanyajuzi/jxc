 /**
 * 文件名称：
 * 版权所有：Copyright gesoft
 * 创建时间：2017-07-19 13:24:49
 * 创 建 人：WCL (ln_admin@yeah.net)
 * 功能描述：
 **/
package com.gesoft.model;

import java.util.*;


public class GoodCustomerModel extends BaseModel
{
	private static final long serialVersionUID = 5454155825314635342L;

	
	private java.lang.Long id;
	private java.lang.String goodsName;
	private java.lang.String dictName;
	private java.lang.String business;
	private java.lang.String customerName;
	private java.lang.Long businessId;
	private java.lang.Long goodId;
	private java.lang.Long customerId;
	private java.lang.String materialNum;
	private java.lang.Float unitPrice;
	private java.lang.String unit;
	private java.lang.Float storage;

	public String getDictName() {
		return dictName;
	}

	public void setDictName(String dictName) {
		this.dictName = dictName;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public String getBusiness() {
		return business;
	}

	public void setBusiness(String business) {
		this.business = business;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public Long getBusinessId() {
		return businessId;
	}

	public void setBusinessId(Long businessId) {
		this.businessId = businessId;
	}

	public void setId(java.lang.Long value)
	{
		this.id = value;
	}
	
	public java.lang.Long getId() 
	{
		return this.id;
	}
	public void setGoodId(java.lang.Long value) 
	{
		this.goodId = value;
	}
	
	public java.lang.Long getGoodId() 
	{
		return this.goodId;
	}
	public void setCustomerId(java.lang.Long value) 
	{
		this.customerId = value;
	}
	
	public java.lang.Long getCustomerId() 
	{
		return this.customerId;
	}
	public void setMaterialNum(java.lang.String value) 
	{
		this.materialNum = value;
	}
	
	public java.lang.String getMaterialNum() 
	{
		return this.materialNum;
	}
	public void setUnitPrice(java.lang.Float value) 
	{
		this.unitPrice = value;
	}
	
	public java.lang.Float getUnitPrice() 
	{
		return this.unitPrice;
	}
	public void setUnit(java.lang.String value) 
	{
		this.unit = value;
	}
	
	public java.lang.String getUnit() 
	{
		return this.unit;
	}
	public void setStorage(java.lang.Float value) 
	{
		this.storage = value;
	}
	
	public java.lang.Float getStorage() 
	{
		return this.storage;
	}

}

