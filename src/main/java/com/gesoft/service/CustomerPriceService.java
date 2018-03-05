package com.gesoft.service;

import com.gesoft.common.EntityDAO;
import com.gesoft.common.EntityService;
import com.gesoft.dao.CustomerPriceDAO;
import com.gesoft.model.CustomerPriceModel;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

@Service
@Transactional
public class CustomerPriceService extends EntityService<CustomerPriceModel, Long> {

    @Resource
    private CustomerPriceDAO customerPriceDAO;

    @Override
    protected EntityDAO<CustomerPriceModel, Long> getEntityDao() {
        return this.customerPriceDAO;
    }
}
