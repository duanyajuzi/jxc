 /**
 * 文件名称：
 * 版权所有：Copyright gesoft
 * 创建时间：2017-06-28 11:56:39
 * 创 建 人：WCL (ln_admin@yeah.net)
 * 功能描述：
 **/
package com.gesoft.model;

import java.util.*;


public class CustomerModel extends BaseModel
{
	private static final long serialVersionUID = 5454155825314635342L;

	
	private java.lang.Long id;
	private java.lang.String customerName;
	private java.lang.String contacts;
	private java.lang.String address;
	private java.lang.String tel;
	private java.lang.String consigneeName;
	private java.lang.String consigneeTel;
	private java.lang.String creditCode;
	private java.lang.String billingAddress;
	private java.lang.String openBank;
	private java.lang.String bankAccount;
	private java.lang.String openTel;
	private java.lang.String memo;

	public void setId(java.lang.Long value) 
	{
		this.id = value;
	}
	
	public java.lang.Long getId() 
	{
		return this.id;
	}
	public void setCustomerName(java.lang.String value) 
	{
		this.customerName = value;
	}
	
	public java.lang.String getCustomerName() 
	{
		return this.customerName;
	}
	public void setContacts(java.lang.String value) 
	{
		this.contacts = value;
	}
	
	public java.lang.String getContacts() 
	{
		return this.contacts;
	}
	public void setAddress(java.lang.String value) 
	{
		this.address = value;
	}
	
	public java.lang.String getAddress() 
	{
		return this.address;
	}
	public void setTel(java.lang.String value) 
	{
		this.tel = value;
	}
	
	public java.lang.String getTel() 
	{
		return this.tel;
	}
	public void setConsigneeName(java.lang.String value) 
	{
		this.consigneeName = value;
	}
	
	public java.lang.String getConsigneeName() 
	{
		return this.consigneeName;
	}
	public void setConsigneeTel(java.lang.String value) 
	{
		this.consigneeTel = value;
	}
	
	public java.lang.String getConsigneeTel() 
	{
		return this.consigneeTel;
	}
	public void setCreditCode(java.lang.String value) 
	{
		this.creditCode = value;
	}
	
	public java.lang.String getCreditCode() 
	{
		return this.creditCode;
	}
	public void setBillingAddress(java.lang.String value) 
	{
		this.billingAddress = value;
	}
	
	public java.lang.String getBillingAddress() 
	{
		return this.billingAddress;
	}
	public void setOpenBank(java.lang.String value) 
	{
		this.openBank = value;
	}
	
	public java.lang.String getOpenBank() 
	{
		return this.openBank;
	}
	public void setBankAccount(java.lang.String value) 
	{
		this.bankAccount = value;
	}
	
	public java.lang.String getBankAccount() 
	{
		return this.bankAccount;
	}
	public void setOpenTel(java.lang.String value) 
	{
		this.openTel = value;
	}
	
	public java.lang.String getOpenTel() 
	{
		return this.openTel;
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

