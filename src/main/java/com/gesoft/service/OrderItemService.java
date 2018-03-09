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
    public List<OrderItemModel> queryNumInfo2(OrderItemModel model){
        return orderItemDAO.queryNumInfo2(model);
    }
    
    public List<OrderItemModel> findList2(OrderItemModel model){
        return orderItemDAO.findList2(model);
    }
    
    public OrderItemModel getBluePrintInfo(OrderItemModel model){
        return orderItemDAO.getBluePrintInfo(model);
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
    public int updateInoutStockItem(OrderItemModel orderItemModel){
        return orderItemDAO.updateInoutStockItem(orderItemModel);
    }
    //更新库存
    public int updateStorage(OrderItemModel model){
        return orderItemDAO.updateStorage(model);
    }
    public Long getOrderItemTepNum(OrderItemModel model){
        return orderItemDAO.getOrderItemTepNum(model);
    }

    /**
     * 入库管理分页
     * @param model
     * @param msgModel
     */
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

    /**
     * 出库管理分页
     * @param model
     * @param msgModel
     */
    public void findPageOutStock(OrderItemModel model, MsgModel msgModel)
    {
        //取总记录数
        long recordCount = model.getTotal();
        if (isSearchPageTotal(model))
        {
            recordCount = orderItemDAO.findCntOutStock(model);
        }
        //分页加载数据
        if (recordCount > 0)
        {
            setPageModel(recordCount, model);
            List<OrderItemModel> argArgs =  orderItemDAO.findListOutStock(model);
            if (argArgs != null)
            {
                msgModel.setData(argArgs);
                msgModel.setTotal(recordCount);
            }
        }
    }

    /**
     * 分页查询入库细项
     * @param model
     * @param msgModel
     */
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

    /**
     * 分页查询出库细项
     * @param model
     * @param msgModel
     */
    public void findPageOutStockItem(OrderItemModel model, MsgModel msgModel) {
        //取总记录数
        long recordCount = model.getTotal();
        if (isSearchPageTotal(model)) {
            recordCount = orderItemDAO.findCntOutStockItem(model);
        }
        //分页加载数据
        if (recordCount > 0) {
            setPageModel(recordCount, model);
            List <OrderItemModel> argArgs = orderItemDAO.findListOutStockItem(model);
            if (argArgs != null) {
                msgModel.setData(argArgs);
                msgModel.setTotal(recordCount);
            }
        }
    }


    //入库细项
    public List<OrderItemModel> findListInoutStockItem(OrderItemModel model){
        return orderItemDAO.findListInoutStockItem(model);
    }
    //出库细项
    public List<OrderItemModel> findListOutStockItem(OrderItemModel model){
        return orderItemDAO.findListOutStockItem(model);
    }

    public List<OrderItemModel> queryInOrderTree(OrderItemModel orderItemModel){
        return orderItemDAO.queryInOrderTree(orderItemModel);
    }

    public List<OrderItemModel> queryOrderTree(OrderItemModel orderItemModel){
        return orderItemDAO.queryOrderTree(orderItemModel);
    }

    public List<OrderItemModel> queryOrderTree1(OrderItemModel orderItemModel){
        return orderItemDAO.queryOrderTree1(orderItemModel);
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

    //入库数量
    public int updateTabGoodsStorage(OrderItemModel model){
        return  orderItemDAO.updateTabGoodsStorage(model);
    }

    public int updateTabGoodCustomerStorage(OrderItemModel model){
        return  orderItemDAO.updateTabGoodCustomerStorage(model);
    }

    //出库数量
    public int updateTabGoodsStorageOut(OrderItemModel model){
        return  orderItemDAO.updateTabGoodsStorageOut(model);
    }

    public int updateTabGoodCustomerStorageOut(OrderItemModel model){
        return  orderItemDAO.updateTabGoodCustomerStorageOut(model);
    }
}
