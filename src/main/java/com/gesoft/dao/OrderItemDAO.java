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
    
    public List<OrderItemModel> queryCList(OrderItemModel model){
        return getSqlSession().selectList(getMybatisSqlMapNamespace() + ".queryCList", model);
    }
    public List<OrderItemModel> queryPriceList(OrderItemModel model){
        return getSqlSession().selectList(getMybatisSqlMapNamespace() + ".queryPriceList", model);
    }
    
    public List<OrderItemModel> getOutStockList(OrderItemModel model){
        return getSqlSession().selectList(getMybatisSqlMapNamespace() + ".getOutStockList", model);
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
    //修改出入库存信息
    public int updateInoutStock(OrderItemModel orderItemModel){
        return getSqlSession().insert(getMybatisSqlMapNamespace() + ".updateInoutStock", orderItemModel);
    }
    //删除出入库存信息
    public int deleteInoutStock(OrderItemModel model){
        return getSqlSession().insert(getMybatisSqlMapNamespace() + ".deleteInoutStock", model);
    }
    
    public int deleteInStockByItemId(OrderItemModel model){
        return getSqlSession().insert(getMybatisSqlMapNamespace() + ".deleteInStockByItemId", model);
    }
    public int deleteOutStockByItemId(OrderItemModel model){
        return getSqlSession().insert(getMybatisSqlMapNamespace() + ".deleteOutStockByItemId", model);
    }
    
    public int updateStorage(OrderItemModel model){
        return getSqlSession().insert(getMybatisSqlMapNamespace() + ".updateStorage", model);
    }
    
    public Long getOrderItemTepNum(OrderItemModel model){
        return getSqlSession().selectOne(getMybatisSqlMapNamespace() + ".getOrderItemTepNum", model);
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
    public int insertInStockItem(OrderItemModel model){
        return getSqlSession().insert(getMybatisSqlMapNamespace() + ".insertInStockItem", model);
    }
    public int insertOutStockItem(OrderItemModel model){
        return getSqlSession().insert(getMybatisSqlMapNamespace() + ".insertOutStockItem", model);
    }
    /*修改出入库存细项价格
      updateInoutStockItem*/
    public int updateInoutStockItem(OrderItemModel model){
        return getSqlSession().insert(getMybatisSqlMapNamespace() + ".updateInoutStockItem", model);
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
    
    public List<OrderItemModel> getItemIdByOrderId(OrderItemModel orderItemModel){
        return getSqlSession().selectList(getMybatisSqlMapNamespace() + ".getItemIdByOrderId", orderItemModel);
    }
}
