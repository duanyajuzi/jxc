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
	
	private String id;
	private java.lang.String orderNo;
	private java.lang.String orderName;
//	@DateTimeFormat(pattern="yyyy-MM-dd")
	private String orderTime;
	private String orderTimeBegin;
	private String orderTimeEnd;
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
	private long cuserid;
	private java.lang.String memo;
	private String customerName;
	private String pcustomerName;
	private String name1;
	private String name2;
	private String iskh;
	private String zdsc;
	private String data;
	private String orderId;
	private Double price;
	private String num;
	private String customerGoodId;
	private String materialNum;
	private String ycmaterialNum;
	
	public String getYcmaterialNum() {
		return ycmaterialNum;
	}
	
	public void setYcmaterialNum(String ycmaterialNum) {
		this.ycmaterialNum = ycmaterialNum;
	}
	
	public String getCustomerGoodId() {
		return customerGoodId;
	}
	
	public void setCustomerGoodId(String customerGoodId) {
		this.customerGoodId = customerGoodId;
	}
	
	public Double getPrice() {
		return price;
	}
	
	public void setPrice(Double price) {
		this.price = price;
	}
	
	public String getNum() {
		return num;
	}
	
	public void setNum(String num) {
		this.num = num;
	}
	
	public String getOrderId() {
		return orderId;
	}
	
	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}
	
	public String getData() {
		return data;
	}
	
	public void setData(String data) {
		this.data = data;
	}
	
	public String getIskh() {
		return iskh;
	}
	
	public void setIskh(String iskh) {
		this.iskh = iskh;
	}
	
	public String getZdsc() {
		return zdsc;
	}
	
	public void setZdsc(String zdsc) {
		this.zdsc = zdsc;
	}
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
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

	public String getOrderTimeBegin() {
		return orderTimeBegin;
	}

	public void setOrderTimeBegin(String orderTimeBegin) {
		this.orderTimeBegin = orderTimeBegin;
	}

	public String getOrderTimeEnd() {
		return orderTimeEnd;
	}

	public void setOrderTimeEnd(String orderTimeEnd) {
		this.orderTimeEnd = orderTimeEnd;
	}

	public String getMaterialNum() {
		return materialNum;
	}

	public void setMaterialNum(String materialNum) {
		this.materialNum = materialNum;
	}

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
	
	
	public long getCuserid() {
		return cuserid;
	}
	
	public void setCuserid(long cuserid) {
		this.cuserid = cuserid;
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

