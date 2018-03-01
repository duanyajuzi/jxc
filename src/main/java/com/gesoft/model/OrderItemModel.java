package com.gesoft.model;

import java.util.List;

/**
 * Created by admin on 2017-07-18.
 */
public class OrderItemModel extends BaseModel{
    private static final long serialVersionUID = 5454155825314635342L;
    private Long id;
    private String orderId;
    private String orderName;
    private Long customerGoodId;
    private String goodsName;
    private Float esgouNum;
    private Float unitPrice;
    private Float tmpNum;
    private Float afterNum;
    private Integer itemState;
    private String goodsUnit;
    private String materialNum;
    private String specUnit;
    private String dictName;
    private Float totalMoney;
    //方案其他字段
    private Long goodsId;
    private Long goodsNum;
    private Float price;
    private String unit;
    private String pname;
    private Float storage;
//tab_inout_stock
    private String stime;
    private String stimeBegin;
    private String stimeEnd;
    private Integer isBIll;
    private Integer orderType;
    private Long createUserId;
    private String createTime;
    private Long modifyUserId;
    private String modifyTime;
    private Long businessId;
    private String business;

//tab_inout_stock_item
    private Long inout_stock_id;
    private Long orderItemId;
    private Float goodNum;
    private Long goodId;
    private String text;
    private List<OrderItemModel> children;
    private String data;
    private String orderNo;

    public String getData() {
        return data;
    }

    public OrderItemModel setData(String data) {
        this.data = data;
        return this;
    }

    public String getText() {
        return text;
    }

    public OrderItemModel setText(String text) {
        this.text = text;
        return this;
    }

    public List<OrderItemModel> getChildren() {
        return children;
    }

    public OrderItemModel setChildren(List<OrderItemModel> children) {
        this.children = children;
        return this;
    }

    public Long getId() {
        return id;
    }

    public OrderItemModel setId(Long id) {
        this.id = id;
        return this;
    }
    
    public String getOrderId() {
        return orderId;
    }
    
    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
    
    public String getOrderName() {
        return orderName;
    }

    public OrderItemModel setOrderName(String orderName) {
        this.orderName = orderName;
        return this;
    }

    public Long getCustomerGoodId() {
        return customerGoodId;
    }

    public OrderItemModel setCustomerGoodId(Long customerGoodId) {
        this.customerGoodId = customerGoodId;
        return this;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public OrderItemModel setGoodsName(String goodsName) {
        this.goodsName = goodsName;
        return this;
    }

    public Float getEsgouNum() {
        return esgouNum;
    }

    public OrderItemModel setEsgouNum(Float esgouNum) {
        this.esgouNum = esgouNum;
        return this;
    }

    public Float getUnitPrice() {
        return unitPrice;
    }

    public OrderItemModel setUnitPrice(Float unitPrice) {
        this.unitPrice = unitPrice;
        return this;
    }

    public String getGoodsUnit() {
        return goodsUnit;
    }

    public OrderItemModel setGoodsUnit(String goodsUnit) {
        this.goodsUnit = goodsUnit;
        return this;
    }

    public String getMaterialNum() {
        return materialNum;
    }

    public OrderItemModel setMaterialNum(String materialNum) {
        this.materialNum = materialNum;
        return this;
    }

    public String getSpecUnit() {
        return specUnit;
    }

    public OrderItemModel setSpecUnit(String specUnit) {
        this.specUnit = specUnit;
        return this;
    }

    public String getDictName() {
        return dictName;
    }

    public OrderItemModel setDictName(String dictName) {
        this.dictName = dictName;
        return this;
    }

    public Float getTotalMoney() {
        return totalMoney;
    }

    public OrderItemModel setTotalMoney(Float totalMoney) {
        this.totalMoney = totalMoney;
        return this;
    }

    public Float getPrice() {
        return price;
    }

    public OrderItemModel setPrice(Float price) {
        this.price = price;
        return this;
    }

    public String getUnit() {
        return unit;
    }

    public OrderItemModel setUnit(String unit) {
        this.unit = unit;
        return this;
    }

    public String getPname() {
        return pname;
    }

    public OrderItemModel setPname(String pname) {
        this.pname = pname;
        return this;
    }

    public Long getGoodsNum() {
        return goodsNum;
    }

    public OrderItemModel setGoodsNum(Long goodsNum) {
        this.goodsNum = goodsNum;
        return this;
    }

    public Long getGoodsId() {
        return goodsId;
    }

    public OrderItemModel setGoodsId(Long goodsId) {
        this.goodsId = goodsId;
        return this;
    }

    public Float getStorage() {
        return storage;
    }

    public OrderItemModel setStorage(Float storage) {
        this.storage = storage;
        return this;
    }

    public Long getOrderItemId() {
        return orderItemId;
    }

    public OrderItemModel setOrderItemId(Long orderItemId) {
        this.orderItemId = orderItemId;
        return this;
    }

    public String getStime() {
        return stime;
    }

    public OrderItemModel setStime(String stime) {
        this.stime = stime;
        return this;
    }

    public Float getGoodNum() {
        return goodNum;
    }

    public OrderItemModel setGoodNum(Float goodNum) {
        this.goodNum = goodNum;
        return this;
    }

    public Integer getIsBIll() {
        return isBIll;
    }

    public OrderItemModel setIsBIll(Integer isBIll) {
        this.isBIll = isBIll;
        return this;
    }

    public Integer getOrderType() {
        return orderType;
    }

    public OrderItemModel setOrderType(Integer orderType) {
        this.orderType = orderType;
        return this;
    }

    public Long getCreateUserId() {
        return createUserId;
    }

    public OrderItemModel setCreateUserId(Long createUserId) {
        this.createUserId = createUserId;
        return this;
    }

    public String getCreateTime() {
        return createTime;
    }

    public OrderItemModel setCreateTime(String createTime) {
        this.createTime = createTime;
        return this;
    }

    public Long getModifyUserId() {
        return modifyUserId;
    }

    public OrderItemModel setModifyUserId(Long modifyUserId) {
        this.modifyUserId = modifyUserId;
        return this;
    }

    public Long getBusinessId() {
        return businessId;
    }

    public OrderItemModel setBusinessId(Long businessId) {
        this.businessId = businessId;
        return this;
    }

    public Long getInout_stock_id() {
        return inout_stock_id;
    }

    public OrderItemModel setInout_stock_id(Long inout_stock_id) {
        this.inout_stock_id = inout_stock_id;
        return this;
    }

    public Long getGoodId() {
        return goodId;
    }

    public OrderItemModel setGoodId(Long goodId) {
        this.goodId = goodId;
        return this;
    }

    public String getBusiness() {
        return business;
    }

    public OrderItemModel setBusiness(String business) {
        this.business = business;
        return this;
    }

    public Float getTmpNum() {
        return tmpNum;
    }

    public OrderItemModel setTmpNum(Float tmpNum) {
        this.tmpNum = tmpNum;
        return this;
    }

    public Integer getItemState() {
        return itemState;
    }

    public OrderItemModel setItemState(Integer itemState) {
        this.itemState = itemState;
        return this;
    }

    public Float getAfterNum() {
        return afterNum;
    }

    public OrderItemModel setAfterNum(Float afterNum) {
        this.afterNum = afterNum;
        return this;
    }

    public String getModifyTime() {
        return modifyTime;
    }

    public OrderItemModel setModifyTime(String modifyTime) {
        this.modifyTime = modifyTime;
        return this;
    }

    public String getStimeBegin() {
        return stimeBegin;
    }

    public OrderItemModel setStimeBegin(String stimeBegin) {
        this.stimeBegin = stimeBegin;
        return this;
    }

    public String getStimeEnd() {
        return stimeEnd;
    }

    public OrderItemModel setStimeEnd(String stimeEnd) {
        this.stimeEnd = stimeEnd;
        return this;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }
}

