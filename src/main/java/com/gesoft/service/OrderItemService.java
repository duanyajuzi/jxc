package com.gesoft.service;

import com.gesoft.common.EntityDAO;
import com.gesoft.common.EntityService;
import com.gesoft.dao.OrderItemDAO;
import com.gesoft.model.BaseModel;
import com.gesoft.model.MsgModel;
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
    //修改详细订单的已出库数量
    public int updateInoutNum(OrderItemModel model){
        return orderItemDAO.updateInoutNum(model);
    }
    //插入出入库存信息
    public int insertInoutStock(OrderItemModel model){
        return orderItemDAO.insertInoutStock(model);
    }
    //修改出入库存信息
    public int updateInoutStock(OrderItemModel orderItemModel){
        return orderItemDAO.updateInoutStock(orderItemModel);
    }
    //删除出入库存信息
    public int deleteInoutStock(OrderItemModel model){
        return orderItemDAO.deleteInoutStock(model);
    }

    public void findPageInoutStock(OrderItemModel model, MsgModel msgModel)
    {
        //取总记录数
        long recordCount = model.getTotal();
        if (isSearchPageTotal(model))
        {
            recordCount = orderItemDAO.findCntInoutStock(model);
        }
        //分页加载数据
        if (recordCount > 0)
        {
            setPageModel(recordCount, model);
            List<OrderItemModel> argArgs =  orderItemDAO.findListInoutStock(model);
            if (argArgs != null)
            {
                msgModel.setData(argArgs);
                msgModel.setTotal(recordCount);
            }
        }
    }

    public void findPageInoutStockItem(OrderItemModel model, MsgModel msgModel) {
        //取总记录数
        long recordCount = model.getTotal();
        if (isSearchPageTotal(model)) {
            recordCount = orderItemDAO.findCntInoutStockItem(model);
        }
        //分页加载数据
        if (recordCount > 0) {
            setPageModel(recordCount, model);
            List <OrderItemModel> argArgs = orderItemDAO.findListInoutStockItem(model);
            if (argArgs != null) {
                msgModel.setData(argArgs);
                msgModel.setTotal(recordCount);
            }
        }
    }

    public List<OrderItemModel> queryOrderTree(OrderItemModel orderItemModel){
        return orderItemDAO.queryOrderTree(orderItemModel);
    }

    public List<OrderItemModel> queryOrderTree2(OrderItemModel orderItemModel){
        return orderItemDAO.queryOrderTree2(orderItemModel);
    }

    public int insertInoutStockItem(OrderItemModel model){
        return orderItemDAO.insertInoutStockItem(model);
    }

    //修改订单状态
    //入库
    public int updateInOrderStatus(OrderItemModel model){
        return orderItemDAO.updateInOrderStatus(model);
    }
    //出库
    public int updateOutOrderStatus(OrderItemModel model){
        return orderItemDAO.updateOutOrderStatus(model);
    }
    //开票
    public int updateOrderBillStatus(OrderItemModel model){
        return orderItemDAO.updateOrderBillStatus(model);
    }

    public int updateTabGoodsStorage(OrderItemModel model){
        return  orderItemDAO.updateTabGoodsStorage(model);
    }

    public int updateTabGoodCustomerStorage(OrderItemModel model){
        return  orderItemDAO.updateTabGoodCustomerStorage(model);
    }
}
