package com.gesoft.model;

 public class LadderPriceModel extends BaseModel {
     /*private static final long serialVersionUID = 5454155825314635342L;*/

     private Long id;
     private Long buleprint_id;
     private Long num;
     private Float price;


     public Long getId() {
         return id;
     }

     public void setId(Long id) {
         this.id = id;
     }

     public Long getBuleprint_id() {
         return buleprint_id;
     }

     public void setBuleprint_id(Long buleprint_id) {
         this.buleprint_id = buleprint_id;
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

