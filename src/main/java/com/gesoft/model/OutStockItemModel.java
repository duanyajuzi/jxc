 /**
 * 文件名称：
 * 版权所有：Copyright gesoft
 * 创建时间：2017-07-19 13:24:33
 * 创 建 人：WCL (ln_admin@yeah.net)
 * 功能描述：
 **/
package com.gesoft.model;


 public class OutStockItemModel extends BaseModel
 {
     /** 自增主键 **/
     private String id;
     /** 订单项ID **/
     private String orderItemId;
     /** 采购价 **/
     private double inprice;
     /** 销售价 **/
     private double outprice;
     /** 出库商品数量 **/
     private long goodNum;
     /** 出库时间 **/
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
     /** 结束时间 **/
     private String stimeEnd;
     /** 开始时间 **/
     private String stimeBegin;
     /** 原厂料号 **/
     private String ycmaterialNum;
    
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
    
     public double getInprice() {
         return inprice;
     }
    
     public void setInprice(double inprice) {
         this.inprice = inprice;
     }
    
     public double getOutprice() {
         return outprice;
     }
    
     public void setOutprice(double outprice) {
         this.outprice = outprice;
     }
    
     public long getGoodNum() {
         return goodNum;
     }
    
     public void setGoodNum(long goodNum) {
         this.goodNum = goodNum;
     }
    
     public String getStime() {
         return stime;
     }
    
     public void setStime(String stime) {
         this.stime = stime;
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
    
     public void setCreateTime(String createTime) {
         this.createTime = createTime;
     }
    
     public Long getModifyUserId() {
         return modifyUserId;
     }
    
     public void setModifyUserId(Long modifyUserId) {
         this.modifyUserId = modifyUserId;
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
    
     public String getStimeEnd() {
         return stimeEnd;
     }
    
     public void setStimeEnd(String stimeEnd) {
         this.stimeEnd = stimeEnd;
     }
    
     public String getStimeBegin() {
         return stimeBegin;
     }
    
     public void setStimeBegin(String stimeBegin) {
         this.stimeBegin = stimeBegin;
     }
    
     public String getYcmaterialNum() {
         return ycmaterialNum;
     }
    
     public void setYcmaterialNum(String ycmaterialNum) {
         this.ycmaterialNum = ycmaterialNum;
     }
 }

