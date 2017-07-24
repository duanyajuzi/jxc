 /**
 * 文件名称：
 * 版权所有：Copyright gesoft
 * 创建时间：2017-07-19 13:24:33
 * 创 建 人：WCL (ln_admin@yeah.net)
 * 功能描述：
 **/
package com.gesoft.model;

import com.gesoft.util.DateConvertUtils;

import java.util.*;


public class BillModel extends BaseModel
{
	private static final long serialVersionUID = 5454155825314635342L;

	public static final String FORMAT_BILL_TIME = DATE_FORMAT;
	public static final String FORMAT_PRE_PAY_TIME = DATE_FORMAT;
	public static final String FORMAT_PAY_TIME = DATE_FORMAT;
	public static final String FORMAT_CTIME = DATE_FORMAT;
	
	private java.lang.Long id;
	private java.lang.String billNo;
	private java.lang.Long customerId;
	private java.util.Date billTime;
	private java.util.Date prePayTime;
	private java.lang.Long businessId;
	private Integer billType;
	private Integer payState;
	private java.util.Date payTime;
	private java.lang.Long payConfirmUser;
	private java.lang.Long cuserId;
	private java.util.Date ctime;

	public void setId(java.lang.Long value) 
	{
		this.id = value;
	}
	
	public java.lang.Long getId() 
	{
		return this.id;
	}
	public void setBillNo(java.lang.String value) 
	{
		this.billNo = value;
	}
	
	public java.lang.String getBillNo() 
	{
		return this.billNo;
	}
	public void setCustomerId(java.lang.Long value) 
	{
		this.customerId = value;
	}
	
	public java.lang.Long getCustomerId() 
	{
		return this.customerId;
	}
	public String getBillTimeString() 
	{
		return DateConvertUtils.format(getBillTime(), FORMAT_BILL_TIME);
	}
	public void setBillTimeString(String value) 
	{
		setBillTime(DateConvertUtils.parse(value, FORMAT_BILL_TIME,java.util.Date.class));
	}
	
	public void setBillTime(java.util.Date value) 
	{
		this.billTime = value;
	}
	
	public java.util.Date getBillTime() 
	{
		return this.billTime;
	}
	public String getPrePayTimeString() 
	{
		return DateConvertUtils.format(getPrePayTime(), FORMAT_PRE_PAY_TIME);
	}
	public void setPrePayTimeString(String value) 
	{
		setPrePayTime(DateConvertUtils.parse(value, FORMAT_PRE_PAY_TIME, java.util.Date.class));
	}
	
	public void setPrePayTime(java.util.Date value) 
	{
		this.prePayTime = value;
	}
	
	public java.util.Date getPrePayTime() 
	{
		return this.prePayTime;
	}
	public void setBusinessId(java.lang.Long value) 
	{
		this.businessId = value;
	}
	
	public java.lang.Long getBusinessId() 
	{
		return this.businessId;
	}
	public void setBillType(Integer value) 
	{
		this.billType = value;
	}
	
	public Integer getBillType() 
	{
		return this.billType;
	}
	public void setPayState(Integer value) 
	{
		this.payState = value;
	}
	
	public Integer getPayState() 
	{
		return this.payState;
	}
	public String getPayTimeString() 
	{
		return DateConvertUtils.format(getPayTime(), FORMAT_PAY_TIME);
	}
	public void setPayTimeString(String value) 
	{
		setPayTime(DateConvertUtils.parse(value, FORMAT_PAY_TIME,java.util.Date.class));
	}
	
	public void setPayTime(java.util.Date value) 
	{
		this.payTime = value;
	}
	
	public java.util.Date getPayTime() 
	{
		return this.payTime;
	}
	public void setPayConfirmUser(java.lang.Long value) 
	{
		this.payConfirmUser = value;
	}
	
	public java.lang.Long getPayConfirmUser() 
	{
		return this.payConfirmUser;
	}
	public void setCuserId(java.lang.Long value) 
	{
		this.cuserId = value;
	}
	
	public java.lang.Long getCuserId() 
	{
		return this.cuserId;
	}
	public String getCtimeString() 
	{
		return DateConvertUtils.format(getCtime(), FORMAT_CTIME);
	}
	public void setCtimeString(String value) 
	{
		setCtime(DateConvertUtils.parse(value, FORMAT_CTIME,java.util.Date.class));
	}
	
	public void setCtime(java.util.Date value) 
	{
		this.ctime = value;
	}
	
	public java.util.Date getCtime() 
	{
		return this.ctime;
	}

}

