package com.gesoft.model;

import java.util.List;

/**
 * Created by admin on 2017-07-18.
 */
public class OrderItemModel extends BaseModel{
    private static final long serialVersionUID = 5454155825314635342L;
    private String id;
    private String orderId;
    private String orderName;
    private String customerGoodId;
    private String goodsName;
    private Long esgouNum;
    private Double unitPrice;
    private Long tmpNum;
    private Long afterNum;
    private Integer itemState;
    private String goodsUnit;
    private String materialNum;
    private String ycmaterialNum;
    private String specUnit;
    private String dictName;
    private Double totalMoney;
    //方案其他字段
    private String goodsId;
    private Long goodsNum;
    private Double price;
    private String unit;
    private String pname;
    private Long storage;
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
    private String orderItemId;
    private Long goodNum;
    private Long goodId;
    private String treeId;
    private String text;
    private List<OrderItemModel> children;
    private String data;
    private String orderNo;
    private String isHasLadder;
    private Integer customerId;
    private String blueprintId;
    private Double oneprice;
    private String specUnitName;
    private String memo;
    
    public String getMemo() {
        return memo;
    }
    
    public void setMemo(String memo) {
        this.memo = memo;
    }
    
    public String getSpecUnitName() {
        return specUnitName;
    }
    
    public void setSpecUnitName(String specUnitName) {
        this.specUnitName = specUnitName;
    }
    
    public String getId() {
        return id;
    }
    
    public void setId(String id) {
        this.id = id;
    }
    
    public String getBlueprintId() {
        return blueprintId;
    }
    
    public void setBlueprintId(String blueprintId) {
        this.blueprintId = blueprintId;
    }
    
    public Integer getCustomerId() {
        return customerId;
    }
    
    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }
    
    public String getIsHasLadder() {
        return isHasLadder;
    }
    
    public void setIsHasLadder(String isHasLadder) {
        this.isHasLadder = isHasLadder;
    }
    
    public String getData() {
        return data;
    }

    public OrderItemModel setData(String data) {
        this.data = data;
        return this;
    }

    public String getTreeId() {
        return treeId;
    }

    public void setTreeId(String treeId) {
        this.treeId = treeId;
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
    
    public static long getSerialVersionUID() {
        return serialVersionUID;
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
    
    public String getCustomerGoodId() {
        return customerGoodId;
    }
    
    public void setCustomerGoodId(String customerGoodId) {
        this.customerGoodId = customerGoodId;
    }
    
    public String getGoodsName() {
        return goodsName;
    }

    public OrderItemModel setGoodsName(String goodsName) {
        this.goodsName = goodsName;
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
    
    public String getGoodsId() {
        return goodsId;
    }
    
    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }
    
    public String getOrderItemId() {
        return orderItemId;
    }
    
    public void setOrderItemId(String orderItemId) {
        this.orderItemId = orderItemId;
    }
    
    public String getStime() {
        return stime;
    }

    public OrderItemModel setStime(String stime) {
        this.stime = stime;
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
    
    public void setCreateUserId(Long createUserId) {
        this.createUserId = createUserId;
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

    public Integer getItemState() {
        return itemState;
    }

    public OrderItemModel setItemState(Integer itemState) {
        this.itemState = itemState;
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

    public String getYcmaterialNum() {
        return ycmaterialNum;
    }

    public void setYcmaterialNum(String ycmaterialNum) {
        this.ycmaterialNum = ycmaterialNum;
    }
    
    public Long getEsgouNum() {
        return esgouNum;
    }
    
    public void setEsgouNum(Long esgouNum) {
        this.esgouNum = esgouNum;
    }
    
    public Long getTmpNum() {
        return tmpNum;
    }
    
    public void setTmpNum(Long tmpNum) {
        this.tmpNum = tmpNum;
    }
    
    public Long getAfterNum() {
        return afterNum;
    }
    
    public void setAfterNum(Long afterNum) {
        this.afterNum = afterNum;
    }
    
    public Long getGoodsNum() {
        return goodsNum;
    }
    
    public void setGoodsNum(Long goodsNum) {
        this.goodsNum = goodsNum;
    }
    
    public Long getStorage() {
        return storage;
    }
    
    public void setStorage(Long storage) {
        this.storage = storage;
    }
    
    public Long getGoodNum() {
        return goodNum;
    }
    
    public void setGoodNum(Long goodNum) {
        this.goodNum = goodNum;
    }
    
    public Double getUnitPrice() {
        return unitPrice;
    }
    
    public void setUnitPrice(Double unitPrice) {
        this.unitPrice = unitPrice;
    }
    
    public Double getTotalMoney() {
        return totalMoney;
    }
    
    public void setTotalMoney(Double totalMoney) {
        this.totalMoney = totalMoney;
    }
    
    public Double getPrice() {
        return price;
    }
    
    public void setPrice(Double price) {
        this.price = price;
    }
    
    public Double getOneprice() {
        return oneprice;
    }
    
    public void setOneprice(Double oneprice) {
        this.oneprice = oneprice;
    }
}

