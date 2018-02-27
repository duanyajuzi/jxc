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
    //插入出入库存信息
    public int insertInoutStock(OrderItemModel model){
        return getSqlSession().insert(getMybatisSqlMapNamespace() + ".insertInoutStock", model);
    }
    //修改出入库存信息
    public int updateInoutStock(OrderItemModel orderItemModel){
        return getSqlSession().insert(getMybatisSqlMapNamespace() + ".updateInoutStock", orderItemModel);
    }
    //删除出入库存信息
    public int deleteInoutStock(OrderItemModel model){
        return getSqlSession().insert(getMybatisSqlMapNamespace() + ".deleteInoutStock", model);
    }

    public long findCntInoutStock(OrderItemModel model)
    {
        return (Long)getSqlSession().selectOne(getMybatisSqlMapNamespace() + ".countInout", model);
    }

    public long findCntInoutStockItem(OrderItemModel model)
    {
        return (Long)getSqlSession().selectOne(getMybatisSqlMapNamespace() + ".countInoutItem", model);
    }

    public List<OrderItemModel> findListInoutStock(OrderItemModel orderItemModel){
        return getSqlSession().selectList(getMybatisSqlMapNamespace() + ".queryInoutStock", orderItemModel);
    }
    public List<OrderItemModel> findListInoutStockItem(OrderItemModel orderItemModel){
        return getSqlSession().selectList(getMybatisSqlMapNamespace() + ".queryInoutStockItem", orderItemModel);
    }

    /*
    * 订单树父节点*/
    public List<OrderItemModel> queryOrderTree(OrderItemModel orderItemModel){
        return getSqlSession().selectList(getMybatisSqlMapNamespace() + ".queryOrderTree", orderItemModel);
    }
    /*
    * 订单树子节点*/
    public List<OrderItemModel> queryOrderTree2(OrderItemModel orderItemModel){
        return getSqlSession().selectList(getMybatisSqlMapNamespace() + ".queryOrderTree2", orderItemModel);
    }
    /*插入出入库存细项
    insertInoutStockItem*/
    public int insertInoutStockItem(OrderItemModel model){
        return getSqlSession().insert(getMybatisSqlMapNamespace() + ".insertInoutStockItem", model);
    }

    //修改订单状态
    //入库
    public int updateInOrderStatus(OrderItemModel model){
        return getSqlSession().update(getMybatisSqlMapNamespace() + ".updateInOrderStatus", model);
    }
    //出库
    public int updateOutOrderStatus(OrderItemModel model){
        return getSqlSession().update(getMybatisSqlMapNamespace() + ".updateOutOrderStatus", model);
    }
    //开票
    public int updateOrderBillStatus(OrderItemModel model){
        return getSqlSession().update(getMybatisSqlMapNamespace() + ".updateOrderBillStatus", model);
    }

    public int updateTabGoodsStorage(OrderItemModel model){
        return getSqlSession().update(getMybatisSqlMapNamespace() + ".updateTabGoodsStorage", model);
    }

    public int updateTabGoodCustomerStorage(OrderItemModel model){
        return getSqlSession().update(getMybatisSqlMapNamespace() + ".updateTabGoodCustomerStorage", model);
    }
}
