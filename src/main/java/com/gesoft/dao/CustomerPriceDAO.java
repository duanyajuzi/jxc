package com.gesoft.dao;

import com.gesoft.common.EntityDAOImpl;
import com.gesoft.model.CustomerPriceModel;
import org.springframework.stereotype.Repository;


@Repository
public class CustomerPriceDAO extends EntityDAOImpl<CustomerPriceModel, Long> {
    @Override
    public String getMybatisSqlMapNamespace() {
        return "CustomerPriceMapper";
    }
}
