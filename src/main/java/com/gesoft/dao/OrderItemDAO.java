package com.gesoft.dao;

import com.gesoft.common.EntityDAOImpl;
import com.gesoft.model.OrderItemModel;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class OrderItemDAO extends EntityDAOImpl<OrderItemModel, Long> {
    @Override
    public String getMybatisSqlMapNamespace()
    {
        return "OrderItemMapper";
    }

    public List<OrderItemModel> queryNumInfo(OrderItemModel model){
        return getSqlSession().selectList(getMybatisSqlMapNamespace() + ".queryNumInfo", model);
    }

    public List<OrderItemModel> queryGoodPLan(OrderItemModel model){
        return getSqlSession().selectList(getMybatisSqlMapNamespace() + ".goodsPlan", model);
    }

    //修改详细订单的已出库数量
    public int updateInoutNum(OrderItemModel model){
        return getSqlSession().update(getMybatisSqlMapNamespace() + ".updateInoutNum", model);
    }

    public int insertInoutStock(OrderItemModel model){
        return getSqlSession().insert(getMybatisSqlMapNamespace() + ".updateInoutNum", model);
    }
}
