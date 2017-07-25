package com.gesoft.service;

import com.gesoft.common.EntityDAO;
import com.gesoft.common.EntityService;
import com.gesoft.dao.OrderItemDAO;
import com.gesoft.model.OrderItemModel;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by admin on 2017-07-18.
 */
@Service
@Transactional
public class OrderItemService  extends EntityService<OrderItemModel, Long> {
    @Resource
    private OrderItemDAO orderItemDAO;

    @Override
    protected EntityDAO<OrderItemModel, Long> getEntityDao()
    {
        return this.orderItemDAO;
    }

    public List<OrderItemModel> queryNumInfo(OrderItemModel model){
        return orderItemDAO.queryNumInfo(model);
    }

    public List<OrderItemModel> queryGoodPrint(OrderItemModel model){
     return orderItemDAO.queryGoodPLan(model);
    }

    public int updateInoutNum(OrderItemModel model){
        return orderItemDAO.updateInoutNum(model);
    }

    public int insertInoutStock(OrderItemModel model){
        return orderItemDAO.insertInoutStock(model);
    }
}
