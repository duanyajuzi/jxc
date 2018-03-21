 /**
 * 文件名称：
 * 版权所有：Copyright gesoft
 * 创建时间：2017-06-28 11:58:20
 * 创 建 人：WCL (ln_admin@yeah.net)
 * 功能描述：
 **/
package com.gesoft.dao;

import org.springframework.stereotype.Repository;

import com.gesoft.common.EntityDAOImpl;
import com.gesoft.model.OrderModel;

import java.util.List;


 @Repository
public class OrderDAO extends EntityDAOImpl<OrderModel, String>
{
	@Override
    public String getMybatisSqlMapNamespace()
    {
        return "OrderMapper";
    }

    public int  updateOrderStatus(OrderModel model){
        return getSqlSession().update(getMybatisSqlMapNamespace() + ".updateOrderStatus",model);
    }
    
    public List<OrderModel> getLadderPrice(OrderModel model){
        return getSqlSession().selectList(getMybatisSqlMapNamespace() + ".getLadderPrice", model);
    }

    public long findCntOrderSell(OrderModel model)
    {
        return (Long)getSqlSession().selectOne(getMybatisSqlMapNamespace() + ".countSell", model);
    }

    public List<OrderModel> findListOrderSell(OrderModel orderItemModel){
        return getSqlSession().selectList(getMybatisSqlMapNamespace() + ".listSell", orderItemModel);
    }
    public List<OrderModel> getBluePrintLadderPrice(OrderModel model){
        return getSqlSession().selectList(getMybatisSqlMapNamespace() + ".getBluePrintLadderPrice", model);
    }
    
    
    public List<OrderModel> queryMaterialNum(OrderModel model){
        return getSqlSession().selectList(getMybatisSqlMapNamespace() + ".queryMaterialNum", model);
    }
    
    public List<OrderModel> queryMaterialNum2(OrderModel model){
        return getSqlSession().selectList(getMybatisSqlMapNamespace() + ".queryMaterialNum2", model);
    }
    public OrderModel getpMaterialNum(OrderModel model){
        return getSqlSession().selectOne(getMybatisSqlMapNamespace() + ".getpMaterialNum", model);
    }
    
}
