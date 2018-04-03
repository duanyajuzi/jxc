package com.gesoft.model;

/**
 * Created by lz on 2018-3-13.
 */
public class OrderExcelItemModel {
    private String goods_name;//商品名称
    private String cust_po_no;//客户号
    private String pn;//客户料号
    private String description;//描述
    private Long qty;//数量
    private Long sqty;//实际数量
    private String unit;//单位
    private Double unit_price_net;//未税单价
    
    public Long getSqty() {
        return sqty;
    }
    
    public void setSqty(Long sqty) {
        this.sqty = sqty;
    }
    
    public String getGoods_name() {
        return goods_name;
    }
    
    public void setGoods_name(String goods_name) {
        this.goods_name = goods_name;
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
    
}
