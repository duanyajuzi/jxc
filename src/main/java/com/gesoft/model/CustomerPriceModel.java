package com.gesoft.model;

 public class CustomerPriceModel extends BaseModel {
     private static final long serialVersionUID = 5454155825314615342L;

     private Long id;
     private String  good_customer_id;
     private Long num;
     private Float price;



     public String getGood_customer_id() {
         return good_customer_id;
     }

     public void setGood_customer_id(String good_customer_id) {
         this.good_customer_id = good_customer_id;
     }

     public Long getId() {
         return id;
     }

     public void setId(Long id) {
         this.id = id;
     }


     public Long getNum() {
         return num;
     }

     public void setNum(Long num) {
         this.num = num;
     }

     public Float getPrice() {
         return price;
     }

     public void setPrice(Float price) {
         this.price = price;
     }



 }

