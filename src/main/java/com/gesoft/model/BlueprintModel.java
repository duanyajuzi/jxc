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

	
	private String id;
	private String fid;
	private String goodsId;
	private String data;
	private String customerName;
	private java.lang.Long goodsNum;
	private Double price;
	private java.lang.String unit;
	private java.lang.String pname;
	private java.lang.String memo;
	private String goodsName;
	private String dictName;
	private java.lang.String  materialNum;
	private java.lang.String fmaterialNum;
	private String isHasLadder;

	public String getIsHasLadder() {
		return isHasLadder;
	}

	public void setIsHasLadder(String isHasLadder) {
		this.isHasLadder = isHasLadder;
	}

	public String getFmaterialNum() {
		return fmaterialNum;
	}

	public void setFmaterialNum(String fmaterialNum) {
		this.fmaterialNum = fmaterialNum;
	}

	public String getFid() {
		return fid;
	}

	public void setFid(String fid) {
		this.fid = fid;
	}
	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}



	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getGoodsId() {
		return goodsId;
	}
	
	public void setGoodsId(String goodsId) {
		this.goodsId = goodsId;
	}
	
	public void setId(String value)
	{
		this.id = value;
	}
	
	public String getId()
	{
		return this.id;
	}
	public void setGoodsNum(java.lang.Long value)
	{
		this.goodsNum = value;
	}
	
	public java.lang.Long getGoodsNum() 
	{
		return this.goodsNum;
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
	
	public Double getPrice() {
		return price;
	}
	
	public void setPrice(Double price) {
		this.price = price;
	}
}

