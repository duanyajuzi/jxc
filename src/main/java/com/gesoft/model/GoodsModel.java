 /**
 * 文件名称：
 * 版权所有：Copyright gesoft
 * 创建时间：2017-06-28 12:01:42
 * 创 建 人：WCL (ln_admin@yeah.net)
 * 功能描述：
 **/
package com.gesoft.model;

import java.util.*;


public class GoodsModel extends BaseModel
{
	private static final long serialVersionUID = 5454155825314635342L;

	
	private java.lang.Integer id;
	private java.lang.String materialNum;
	private java.lang.String goodsName;
	private java.lang.Float unitPrice;
	private java.lang.String unit;
	private java.lang.Float spec;
	private java.lang.String specUnit;
	private java.lang.Integer customerId;
	private java.lang.Float storage;
	private Integer status;
	private java.lang.Integer businessId;
	private java.lang.String memo;
	private String customerName;
	private String business;
	private String name1;
	private String name2;
	private Long goodId;
	private Float esgouNum;
	private  String goodsId;

	public String getGoodsId() {
		return goodsId;
	}

	public GoodsModel setGoodsId(String goodsId) {
		this.goodsId = goodsId;
		return this;
	}

	public String getName1() {
		return name1;
	}

	public GoodsModel setName1(String name1) {
		this.name1 = name1;
		return this;
	}

	public String getName2() {
		return name2;
	}

	public GoodsModel setName2(String name2) {
		this.name2 = name2;
		return this;
	}

	public void setId(java.lang.Integer value)
	{
		this.id = value;
	}
	
	public java.lang.Integer getId() 
	{
		return this.id;
	}
	public void setMaterialNum(java.lang.String value) 
	{
		this.materialNum = value;
	}
	
	public java.lang.String getMaterialNum() 
	{
		return this.materialNum;
	}
	public void setGoodsName(java.lang.String value) 
	{
		this.goodsName = value;
	}
	
	public java.lang.String getGoodsName() 
	{
		return this.goodsName;
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
	public void setSpec(java.lang.Float value) 
	{
		this.spec = value;
	}
	
	public java.lang.Float getSpec() 
	{
		return this.spec;
	}
	public void setSpecUnit(java.lang.String value) 
	{
		this.specUnit = value;
	}
	
	public java.lang.String getSpecUnit() 
	{
		return this.specUnit;
	}
	public void setCustomerId(java.lang.Integer value) 
	{
		this.customerId = value;
	}
	
	public java.lang.Integer getCustomerId() 
	{
		return this.customerId;
	}
	public void setStorage(java.lang.Float value) 
	{
		this.storage = value;
	}
	
	public java.lang.Float getStorage() 
	{
		return this.storage;
	}
	public void setStatus(Integer value) 
	{
		this.status = value;
	}
	
	public Integer getStatus() 
	{
		return this.status;
	}
	public void setBusinessId(java.lang.Integer value) 
	{
		this.businessId = value;
	}
	
	public java.lang.Integer getBusinessId() 
	{
		return this.businessId;
	}
	public void setMemo(java.lang.String value) 
	{
		this.memo = value;
	}
	
	public java.lang.String getMemo() 
	{
		return this.memo;
	}

	public String getCustomerName() {
		return customerName;
	}

	public GoodsModel setCustomerName(String customerName) {
		this.customerName = customerName;
		return this;
	}

	public String getBusiness() {
		return business;
	}

	public GoodsModel setBusiness(String business) {
		this.business = business;
		return this;
	}

	public Long getGoodId() {
		return goodId;
	}

	public GoodsModel setGoodId(Long goodId) {
		this.goodId = goodId;
		return this;
	}

	public Float getEsgouNum() {
		return esgouNum;
	}

	public GoodsModel setEsgouNum(Float esgouNum) {
		this.esgouNum = esgouNum;
		return this;
	}
}

