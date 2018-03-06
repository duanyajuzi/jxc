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
    
    public List<OrderItemModel> queryNumInfo2(OrderItemModel model){
        return getSqlSession().selectList(getMybatisSqlMapNamespace() + ".queryNumInfo2", model);
    }
    
    public List<OrderItemModel> findList2(OrderItemModel model){
        return getSqlSession().selectList(getMybatisSqlMapNamespace() + ".list2", model);
    }
    
    public OrderItemModel getBluePrintInfo(OrderItemModel model){
        return getSqlSession().selectOne(getMybatisSqlMapNamespace() + ".getBluePrintInfo", model);
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
    
    public int updateStorage(OrderItemModel model){
        return getSqlSession().insert(getMybatisSqlMapNamespace() + ".updateStorage", model);
    }
    
    public float getOrderItemTepNum(OrderItemModel model){
        return getSqlSession().selectOne(getMybatisSqlMapNamespace() + ".getOrderItemTepNum", model);
    }

    //入库分页
    public long findCntInoutStock(OrderItemModel model)
    {
        return (Long)getSqlSession().selectOne(getMybatisSqlMapNamespace() + ".countInout", model);
    }

    public List<OrderItemModel> findListInoutStock(OrderItemModel orderItemModel){
        return getSqlSession().selectList(getMybatisSqlMapNamespace() + ".queryInoutStock", orderItemModel);
    }
    //出库分页
    public long findCntOutStock(OrderItemModel model)
    {
        return (Long)getSqlSession().selectOne(getMybatisSqlMapNamespace() + ".countOut", model);
    }

    public List<OrderItemModel> findListOutStock(OrderItemModel orderItemModel){
        return getSqlSession().selectList(getMybatisSqlMapNamespace() + ".queryOutStock", orderItemModel);
    }

    public long findCntInoutStockItem(OrderItemModel model)
    {
        return (Long)getSqlSession().selectOne(getMybatisSqlMapNamespace() + ".countInoutItem", model);
    }


    /**
     * 入库细项
     * @param orderItemModel
     * @return
     */
    public List<OrderItemModel> findListInoutStockItem(OrderItemModel orderItemModel){
        return getSqlSession().selectList(getMybatisSqlMapNamespace() + ".queryInoutStockItem", orderItemModel);
    }

    /**
     * 出库细项
     * @param orderItemModel
     * @return
     */
    public List<OrderItemModel> findListOutStockItem(OrderItemModel orderItemModel){
        return getSqlSession().selectList(getMybatisSqlMapNamespace() + ".queryOutStockItem", orderItemModel);
    }

    /*
    * 采购订单树父节点*/
    public List<OrderItemModel> queryInOrderTree(OrderItemModel orderItemModel){
        return getSqlSession().selectList(getMybatisSqlMapNamespace() + ".queryInOrderTree", orderItemModel);
    }

    /*
    * 销售订单树父节点*/
    public List<OrderItemModel> queryOrderTree(OrderItemModel orderItemModel){
        return getSqlSession().selectList(getMybatisSqlMapNamespace() + ".queryOrderTree", orderItemModel);
    }
    /*
  * 订单树入库子节点*/
    public List<OrderItemModel> queryOrderTree1(OrderItemModel orderItemModel){
        return getSqlSession().selectList(getMybatisSqlMapNamespace() + ".queryOrderTree1", orderItemModel);
    }
    /*
    * 订单树销售子节点*/
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
    //入库数量
    public int updateTabGoodsStorage(OrderItemModel model){
        return getSqlSession().update(getMybatisSqlMapNamespace() + ".updateTabGoodsStorage", model);
    }

    public int updateTabGoodCustomerStorage(OrderItemModel model){
        return getSqlSession().update(getMybatisSqlMapNamespace() + ".updateTabGoodCustomerStorage", model);
    }
    //出库数量
    public int updateTabGoodsStorageOut(OrderItemModel model){
        return getSqlSession().update(getMybatisSqlMapNamespace() + ".updateTabGoodsStorageOut", model);
    }

    public int updateTabGoodCustomerStorageOut(OrderItemModel model){
        return getSqlSession().update(getMybatisSqlMapNamespace() + ".updateTabGoodCustomerStorageOut", model);
    }
}
