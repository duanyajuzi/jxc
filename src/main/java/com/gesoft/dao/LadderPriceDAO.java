package com.gesoft.dao;

 import com.gesoft.common.EntityDAOImpl;
 import com.gesoft.model.LadderPriceModel;
 import org.springframework.stereotype.Repository;


 @Repository
 public class LadderPriceDAO extends EntityDAOImpl<LadderPriceModel, Long> {
     @Override
     public String getMybatisSqlMapNamespace() {
         return "BlueprintMapper";
     }
 }
