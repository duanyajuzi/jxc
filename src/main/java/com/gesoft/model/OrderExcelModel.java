package com.gesoft.model;

import java.util.List;

/**
 * Created by lz on 2018-3-13.
 */
public class OrderExcelModel {
    private String cust_po_no;//客户号
    private String pn;//原厂料号
    private String pn2;//客户料号
    private String description;//描述
    private Long qty;//数量
    private String unit;//单位
    private Double unit_price_net;//未税单价
    private Double outprice;//销售单价
    private String goodsName;//商品名称
    private Double total_price_net;//未税总价
    private String deliveryTime;
    
    public Double getOutprice() {
        return outprice;
    }
    
    public void setOutprice(Double outprice) {
        this.outprice = outprice;
    }
    
    public String getGoodsName() {
        return goodsName;
    }
    
    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }
    
    public String getDeliveryTime() {
        return deliveryTime;
    }
    
    public void setDeliveryTime(String deliveryTime) {
        this.deliveryTime = deliveryTime;
    }
    
    public String getPn2() {
        return pn2;
    }
    
    public void setPn2(String pn2) {
        this.pn2 = pn2;
    }
    
    public String getCust_po_no() {
        return cust_po_no;
    }
    
    public void setCust_po_no(String cust_po_no) {
        this.cust_po_no = cust_po_no;
    }
    
    public String getPn() {
        return pn;
    }
    
    public void setPn(String pn) {
        this.pn = pn;
    }
    
    public String getDescription() {
        return description;
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public Long getQty() {
        return qty;
    }
    
    public void setQty(Long qty) {
        this.qty = qty;
    }
    
    public String getUnit() {
        return unit;
    }
    
    public void setUnit(String unit) {
        this.unit = unit;
    }
    
    public Double getUnit_price_net() {
        return unit_price_net;
    }
    
    public void setUnit_price_net(Double unit_price_net) {
        this.unit_price_net = unit_price_net;
    }
    
    public Double getTotal_price_net() {
        return total_price_net;
    }
    
    public void setTotal_price_net(Double total_price_net) {
        this.total_price_net = total_price_net;
    }
}
