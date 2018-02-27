 /**
 * 文件名称：
 * 版权所有：Copyright gesoft
 * 创建时间：2017-06-28 11:58:20
 * 创 建 人：WCL (ln_admin@yeah.net)
 * 功能描述：
 **/
package com.gesoft.model;

 import com.gesoft.util.DateConvertUtils;
 import org.springframework.format.annotation.DateTimeFormat;

 import java.util.Date;

 public class OrderModel extends BaseModel
{
	private static final long serialVersionUID = 5454155825314635342L;

	public static final String FORMAT_ORDER_TIME = DATE_FORMAT ;
	public static final String FORMAT_DELIVERY_TIME = DATE_FORMAT ;
	public static final String FORMAT_CTIME = DATE_FORMAT ;
	
	private java.lang.Long id;
	private java.lang.String orderNo;
	private java.lang.String orderName;
//	@DateTimeFormat(pattern="yyyy-MM-dd")
	private String orderTime;
	private Integer orderType;
	private Integer orderStatus;
//	@DateTimeFormat(pattern="yyyy-MM-dd")
	private String deliveryTime;
	private Long businessId;
	private String business;
	private java.lang.String deliveryAddress;
	private java.lang.Integer customerId;
	private java.lang.String ccontacts;
	private java.lang.String caddress;
	private java.lang.String ctel;
	private java.lang.Integer pcustomerId;
	private java.lang.String pcontacts;
	private java.lang.String paddress;
	private java.lang.String ptel;
//	@DateTimeFormat(pattern="yyyy-MM-dd")
	private String ctime;
	private java.lang.Integer cuserid;
	private java.lang.String memo;
	private String customerName;
	private String pcustomerName;
	private String name1;
	private String name2;

	public void setId(java.lang.Long value) 
	{
		this.id = value;
	}
	
	public java.lang.Long getId() 
	{

		return this.id;
	}
	public void setOrderNo(java.lang.String value) 
	{
		this.orderNo = value;
	}
	
	public java.lang.String getOrderNo() 
	{
		return this.orderNo;
	}
	public void setOrderName(java.lang.String value) 
	{
		this.orderName = value;
	}

	public java.lang.String getOrderName() 
	{
		return this.orderName;
	}

//	public String getOrderTimeString()
//	{
//		return DateConvertUtils.format(getOrderTime(), FORMAT_ORDER_TIME);
//	}
//	public void setOrderTimeString(String value)
//	{
//		setOrderTime(DateConvertUtils.parse(value, FORMAT_ORDER_TIME,java.util.Date.class));
//	}


	public void setOrderType(Integer value)
	{
		this.orderType = value;
	}
	
	public Integer getOrderType() 
	{
		return this.orderType;
	}
	public void setOrderStatus(Integer value) 
	{
		this.orderStatus = value;
	}
	
	public Integer getOrderStatus() 
	{
		return this.orderStatus;
	}

//	public String getDeliveryTimeString()
//	{
//		return DateConvertUtils.format(getDeliveryTime(), FORMAT_DELIVERY_TIME);
//	}
//	public void setDeliveryTimeString(String value)
//	{
//		setDeliveryTime(DateConvertUtils.parse(value, FORMAT_DELIVERY_TIME,java.util.Date.class));
//	}
	
	public void setDeliveryAddress(java.lang.String value)
	{
		this.deliveryAddress = value;
	}
	
	public java.lang.String getDeliveryAddress() 
	{
		return this.deliveryAddress;
	}
	public void setCustomerId(java.lang.Integer value) 
	{
		this.customerId = value;
	}
	
	public java.lang.Integer getCustomerId() 
	{
		return this.customerId;
	}
	public void setCcontacts(java.lang.String value) 
	{
		this.ccontacts = value;
	}
	
	public java.lang.String getCcontacts() 
	{
		return this.ccontacts;
	}
	public void setCaddress(java.lang.String value) 
	{
		this.caddress = value;
	}
	
	public java.lang.String getCaddress() 
	{
		return this.caddress;
	}
	public void setCtel(java.lang.String value) 
	{
		this.ctel = value;
	}
	
	public java.lang.String getCtel() 
	{
		return this.ctel;
	}
	public void setPcustomerId(java.lang.Integer value) 
	{
		this.pcustomerId = value;
	}
	
	public java.lang.Integer getPcustomerId() 
	{
		return this.pcustomerId;
	}
	public void setPcontacts(java.lang.String value) 
	{
		this.pcontacts = value;
	}
	
	public java.lang.String getPcontacts() 
	{
		return this.pcontacts;
	}
	public void setPaddress(java.lang.String value) 
	{
		this.paddress = value;
	}
	
	public java.lang.String getPaddress() 
	{
		return this.paddress;
	}
	public void setPtel(java.lang.String value) 
	{
		this.ptel = value;
	}
	
	public java.lang.String getPtel() 
	{
		return this.ptel;
	}

//	public String getCtimeString()
//	{
//		return DateConvertUtils.format(getCtime(), FORMAT_CTIME);
//	}
//	public void setCtimeString(String value)
//	{
//		setCtime(DateConvertUtils.parse(value, FORMAT_CTIME,java.util.Date.class));
//	}

	public void setCuserid(java.lang.Integer value)
	{
		this.cuserid = value;
	}
	
	public java.lang.Integer getCuserid() 
	{
		return this.cuserid;
	}
	public void setMemo(java.lang.String value) 
	{
		this.memo = value;
	}
	
	public java.lang.String getMemo() 
	{
		return this.memo;
	}

	public String getOrderTime() {

		return orderTime;
	}

	public OrderModel setOrderTime(String orderTime) {
		this.orderTime = orderTime;
		return this;
	}

	public String getDeliveryTime() {
		return deliveryTime;
	}

	public OrderModel setDeliveryTime(String deliveryTime) {
		this.deliveryTime = deliveryTime;
		return this;
	}

	public String getCtime() {
		return ctime;
	}

	public OrderModel setCtime(String ctime) {
		this.ctime = ctime;
		return this;
	}

	public String getName1() {
		return name1;
	}

	public OrderModel setName1(String name1) {
		this.name1 = name1;
		return this;
	}

	public String getName2() {
		return name2;
	}

	public OrderModel setName2(String name2) {
		this.name2 = name2;
		return this;
	}

	public String getCustomerName() {
		return customerName;
	}

	public OrderModel setCustomerName(String customerName) {
		this.customerName = customerName;
		return this;
	}

	public String getPcustomerName() {
		return pcustomerName;
	}

	public OrderModel setPcustomerName(String pcustomerName) {
		this.pcustomerName = pcustomerName;
		return this;
	}

	public Long getBusinessId() {
		return businessId;
	}

	public OrderModel setBusinessId(Long businessId) {
		this.businessId = businessId;
		return this;
	}

	public String getBusiness() {
		return business;
	}

	public OrderModel setBusiness(String business) {
		this.business = business;
		return this;
	}
}

