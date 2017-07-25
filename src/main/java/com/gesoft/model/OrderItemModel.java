package com.gesoft.model;

/**
 * Created by admin on 2017-07-18.
 */
public class OrderItemModel extends BaseModel{
    private static final long serialVersionUID = 5454155825314635342L;
    private Long id;
    private Long orderId;
    private String orderName;
    private Long customerGoodId;
    private String goodsName;
    private Float esgouNum;
    private Float unitPrice;
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
    private Long orderItemId;
    private String stime;
    private Float goodNum;
    private Integer isBIll;
    private Integer orderType;


    public Long getId() {
        return id;
    }

    public OrderItemModel setId(Long id) {
        this.id = id;
        return this;
    }

    public Long getOrderId() {
        return orderId;
    }

    public OrderItemModel setOrderId(Long orderId) {
        this.orderId = orderId;
        return this;
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
}

