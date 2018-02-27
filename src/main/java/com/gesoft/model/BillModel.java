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
	private String customerName;
	private String consigneeName;
	private String consigneeTel;
	private String deliveryAddress;
	private String billTime;
	private String prePayTime;
	private java.lang.Long businessId;
	private String business;
	private Integer billType;
	private Integer payState;
	private String payTime;
	private java.lang.Long payConfirmUser;
	private java.lang.Long cuserId;
	private String ctime;

	//出库信息
	private String stime;
	private Integer pcustomerId;
	private String pcustomerName;
	private String pcontacts;
	private String paddress;
	private String ptel;
	private String contacts;
	private String address;
	private String tel;
	private String pconsigneeName;
	private String pconsigneeTel;
	private String orderTime;
	private Integer orderType;
	private Integer isBIll;
	//tab_bill_inout_stock
	private Long billItemId;
	private Long billId;
	private Long stockId;
	private String data;
	private String leftData;

	private Long goodId;
	private String goodsName;
	private Float goodNum;

	private Integer orderStatus;
	private Long orderId;
	private String orderName;

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
//	public String getBillTimeString()
//	{
//		return DateConvertUtils.format(getBillTime(), FORMAT_BILL_TIME);
//	}
//	public void setBillTimeString(String value)
//	{
//		setBillTime(DateConvertUtils.parse(value, FORMAT_BILL_TIME,java.util.Date.class));
//	}

	public String getBillTime() {
		return billTime;
	}

	public BillModel setBillTime(String billTime) {
		this.billTime = billTime;
		return this;
	}

//	public String getPrePayTimeString()
//	{
//		return DateConvertUtils.format(getPrePayTime(), FORMAT_PRE_PAY_TIME);
//	}
//	public void setPrePayTimeString(String value)
//	{
//		setPrePayTime(DateConvertUtils.parse(value, FORMAT_PRE_PAY_TIME, java.util.Date.class));
//	}


	public String getPrePayTime() {
		return prePayTime;
	}

	public BillModel setPrePayTime(String prePayTime) {
		this.prePayTime = prePayTime;
		return this;
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
//	public String getPayTimeString()
//	{
//		return DateConvertUtils.format(getPayTime(), FORMAT_PAY_TIME);
//	}
//	public void setPayTimeString(String value)
//	{
//		setPayTime(DateConvertUtils.parse(value, FORMAT_PAY_TIME,java.util.Date.class));
//	}


	public String getPayTime() {
		return payTime;
	}

	public BillModel setPayTime(String payTime) {
		this.payTime = payTime;
		return this;
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
//	public String getCtimeString()
//	{
//		return DateConvertUtils.format(getCtime(), FORMAT_CTIME);
//	}
//	public void setCtimeString(String value)
//	{
//		setCtime(DateConvertUtils.parse(value, FORMAT_CTIME,java.util.Date.class));
//	}
	public String getCtime() {
		return ctime;
	}

	public BillModel setCtime(String ctime) {
		this.ctime = ctime;
		return this;
	}

	public BillModel setBusinessId(Long businessId) {
		this.businessId = businessId;
		return this;
	}

	public String getCustomerName() {
		return customerName;
	}

	public BillModel setCustomerName(String customerName) {
		this.customerName = customerName;
		return this;
	}

	public String getBusiness() {
		return business;
	}

	public BillModel setBusiness(String business) {
		this.business = business;
		return this;
	}

	public String getStime() {
		return stime;
	}

	public BillModel setStime(String stime) {
		this.stime = stime;
		return this;
	}

	public Integer getPcustomerId() {
		return pcustomerId;
	}

	public BillModel setPcustomerId(Integer pcustomerId) {
		this.pcustomerId = pcustomerId;
		return this;
	}

	public String getPcontacts() {
		return pcontacts;
	}

	public BillModel setPcontacts(String pcontacts) {
		this.pcontacts = pcontacts;
		return this;
	}

	public String getPaddress() {
		return paddress;
	}

	public BillModel setPaddress(String paddress) {
		this.paddress = paddress;
		return this;
	}

	public String getPtel() {
		return ptel;
	}

	public BillModel setPtel(String ptel) {
		this.ptel = ptel;
		return this;
	}

	public String getOrderTime() {
		return orderTime;
	}

	public BillModel setOrderTime(String orderTime) {
		this.orderTime = orderTime;
		return this;
	}

	public Integer getOrderType() {
		return orderType;
	}

	public BillModel setOrderType(Integer orderType) {
		this.orderType = orderType;
		return this;
	}

	public Integer getIsBIll() {
		return isBIll;
	}

	public BillModel setIsBIll(Integer isBIll) {
		this.isBIll = isBIll;
		return this;
	}

	public Long getBillId() {
		return billId;
	}

	public BillModel setBillId(Long billId) {
		this.billId = billId;
		return this;
	}

	public Long getStockId() {
		return stockId;
	}

	public BillModel setStockId(Long stockId) {
		this.stockId = stockId;
		return this;
	}

	public String getData() {
		return data;
	}

	public BillModel setData(String data) {
		this.data = data;
		return this;
	}

	public Long getGoodId() {
		return goodId;
	}

	public BillModel setGoodId(Long goodId) {
		this.goodId = goodId;
		return this;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public BillModel setGoodsName(String goodsName) {
		this.goodsName = goodsName;
		return this;
	}

	public Float getGoodNum() {
		return goodNum;
	}

	public BillModel setGoodNum(Float goodNum) {
		this.goodNum = goodNum;
		return this;
	}

	public Integer getOrderStatus() {
		return orderStatus;
	}

	public BillModel setOrderStatus(Integer orderStatus) {
		this.orderStatus = orderStatus;
		return this;
	}

	public Long getOrderId() {
		return orderId;
	}

	public BillModel setOrderId(Long orderId) {
		this.orderId = orderId;
		return this;
	}

	public String getOrderName() {
		return orderName;
	}

	public BillModel setOrderName(String orderName) {
		this.orderName = orderName;
		return this;
	}

	public String getPcustomerName() {
		return pcustomerName;
	}

	public BillModel setPcustomerName(String pcustomerName) {
		this.pcustomerName = pcustomerName;
		return this;
	}

	public String getContacts() {
		return contacts;
	}

	public BillModel setContacts(String contacts) {
		this.contacts = contacts;
		return this;
	}

	public String getAddress() {
		return address;
	}

	public BillModel setAddress(String address) {
		this.address = address;
		return this;
	}

	public String getTel() {
		return tel;
	}

	public BillModel setTel(String tel) {
		this.tel = tel;
		return this;
	}

	public String getLeftData() {
		return leftData;
	}

	public BillModel setLeftData(String leftData) {
		this.leftData = leftData;
		return this;
	}

	public Long getBillItemId() {
		return billItemId;
	}

	public BillModel setBillItemId(Long billItemId) {
		this.billItemId = billItemId;
		return this;
	}

	public String getPconsigneeName() {
		return pconsigneeName;
	}

	public BillModel setPconsigneeName(String pconsigneeName) {
		this.pconsigneeName = pconsigneeName;
		return this;
	}

	public String getPconsigneeTel() {
		return pconsigneeTel;
	}

	public BillModel setPconsigneeTel(String pconsigneeTel) {
		this.pconsigneeTel = pconsigneeTel;
		return this;
	}

	public String getConsigneeName() {
		return consigneeName;
	}

	public BillModel setConsigneeName(String consigneeName) {
		this.consigneeName = consigneeName;
		return this;
	}

	public String getConsigneeTel() {
		return consigneeTel;
	}

	public BillModel setConsigneeTel(String consigneeTel) {
		this.consigneeTel = consigneeTel;
		return this;
	}

	public String getDeliveryAddress() {
		return deliveryAddress;
	}

	public BillModel setDeliveryAddress(String deliveryAddress) {
		this.deliveryAddress = deliveryAddress;
		return this;
	}
}

