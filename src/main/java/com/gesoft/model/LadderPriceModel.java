package com.gesoft.model;

 public class LadderPriceModel extends BaseModel {
     /*private static final long serialVersionUID = 5454155825314635342L;*/

     private Long id;
     private String  blueprint_id;
     private Long num;
     private Double price;


     public String getBlueprint_id() {
         return blueprint_id;
     }

     public void setBlueprint_id(String blueprint_id) {
         this.blueprint_id = blueprint_id;
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
    
     public Double getPrice() {
         return price;
     }
    
     public void setPrice(Double price) {
         this.price = price;
     }
 }

