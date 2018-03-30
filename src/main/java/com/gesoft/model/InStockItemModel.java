 /**
 * 文件名称：
 * 版权所有：Copyright gesoft
 * 创建时间：2017-07-19 13:24:33
 * 创 建 人：WCL (ln_admin@yeah.net)
 * 功能描述：
 **/
package com.gesoft.model;


 import java.util.List;

 public class InStockItemModel extends BaseModel
 {
     /** 自增主键 **/
     private String id;
     /** 订单项ID **/
     private String orderItemId;
     /** 入库时间 **/
     private String stime;
     /** 创建人 **/
     private Long createUserId;
     /** 创建时间 **/
     private String createTime;
     /** 最后修改人 **/
     private Long modifyUserId;
     /** 最后修改时间 **/
     private String modifyTime;
     /** 业务类型 **/
     private String businessId;
     /** 原厂料号 **/
     private String materialNum;
     /** 客户订单号 **/
     private String orderName;
     /** 订单号 **/
     private String orderNo;
     /** 商品名称 **/
     private String goodsName;
     /** 入库数目 **/
     private Long goodNum;
     /** 结束时间 **/
     private String stimeEnd;
     /** 开始时间 **/
     private String stimeBegin;
     private String orderId;
     private String data;
    
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
    
     public String getId() {
         return id;
     }
    
     public void setId(String id) {
         this.id = id;
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
    
     public void setStime(String stime) {
         this.stime = stime;
     }
    
     public String getCreateTime() {
         return createTime;
     }
    
     public void setCreateTime(String createTime) {
         this.createTime = createTime;
     }
    
     public String getModifyTime() {
         return modifyTime;
     }
    
     public void setModifyTime(String modifyTime) {
         this.modifyTime = modifyTime;
     }
    
     public String getBusinessId() {
         return businessId;
     }
    
     public void setBusinessId(String businessId) {
         this.businessId = businessId;
     }
    
     public String getMaterialNum() {
         return materialNum;
     }
    
     public void setMaterialNum(String materialNum) {
         this.materialNum = materialNum;
     }
    
     public String getOrderName() {
         return orderName;
     }
    
     public void setOrderName(String orderName) {
         this.orderName = orderName;
     }
    
     public String getOrderNo() {
         return orderNo;
     }
    
     public void setOrderNo(String orderNo) {
         this.orderNo = orderNo;
     }
    
     public String getGoodsName() {
         return goodsName;
     }
    
     public void setGoodsName(String goodsName) {
         this.goodsName = goodsName;
     }
    
     public Long getGoodNum() {
         return goodNum;
     }
    
     public void setGoodNum(Long goodNum) {
         this.goodNum = goodNum;
     }
    
     public String getStimeBegin() {
         return stimeBegin;
     }
    
     public void setStimeBegin(String stimeBegin) {
         this.stimeBegin = stimeBegin;
     }
    
     public String getStimeEnd() {
         return stimeEnd;
     }
    
     public void setStimeEnd(String stimeEnd) {
         this.stimeEnd = stimeEnd;
     }
    
     public Long getCreateUserId() {
         return createUserId;
     }
    
     public void setCreateUserId(Long createUserId) {
         this.createUserId = createUserId;
     }
    
     public Long getModifyUserId() {
         return modifyUserId;
     }
    
     public void setModifyUserId(Long modifyUserId) {
         this.modifyUserId = modifyUserId;
     }
 }

