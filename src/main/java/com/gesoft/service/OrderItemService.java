package com.gesoft.service;

import com.alibaba.druid.support.json.JSONUtils;
import com.gesoft.common.EntityDAO;
import com.gesoft.common.EntityService;
import com.gesoft.dao.OrderItemDAO;
import com.gesoft.model.BaseModel;
import com.gesoft.model.MsgModel;
import com.gesoft.model.OrderItemModel;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static com.gesoft.util.Constants.GLOBAL_MSG_BOOL_SUCCESS;

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
    
    public List<OrderItemModel> queryCList(OrderItemModel model){
        return orderItemDAO.queryCList(model);
    }
    public List<OrderItemModel> queryPriceList(OrderItemModel model){
        return orderItemDAO.queryPriceList(model);
    }
    
    public List<OrderItemModel> findList2(OrderItemModel model){
        return orderItemDAO.findList2(model);
    }
    
    public List<OrderItemModel> getOutStockList(OrderItemModel model){
        return orderItemDAO.getOutStockList(model);
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
    
    public void updateOrderItemTmpNum(OrderItemModel model){
        String str=model.getData();
        List<Map<String,Object>> list= (List<Map<String,Object>>) JSONUtils.parse(str);
        OrderItemModel itemModel;
        for(int i=0;i<list.size();i++){
            itemModel = new OrderItemModel();
            Object obj=list.get(i).get("id");
            Object obj1=list.get(i).get("tmpNum");
            Object obj2=list.get(i).get("customerGoodId");
            Object obj3=list.get(i).get("goodId");
            itemModel.setId(String.valueOf(obj));
            itemModel.setGoodId(Long.valueOf(String.valueOf(obj3)));
            itemModel.setCustomerGoodId(String.valueOf(obj2));
            itemModel.setTmpNum(Long.parseLong(obj1.toString()));
            orderItemDAO.updateInoutNum(itemModel);
            orderItemDAO.updateTabGoodCustomerStorage(itemModel);
        }
    }
    
    public void updateOrderItemTmpNumOut(OrderItemModel model){
        String str=model.getData();
        List<Map<String,Object>> list= (List<Map<String,Object>>) JSONUtils.parse(str);
        OrderItemModel itemModel;
        for(int i=0;i<list.size();i++){
            itemModel = new OrderItemModel();
            Object obj=list.get(i).get("id");
            Object obj1=list.get(i).get("tmpNum");
            Object obj2=list.get(i).get("customerGoodId");
            Object obj3=list.get(i).get("goodId");
            itemModel.setId(String.valueOf(obj));
            itemModel.setGoodId(Long.valueOf(String.valueOf(obj3)));
            itemModel.setCustomerGoodId(String.valueOf(obj2));
            itemModel.setTmpNum(Long.parseLong(obj1.toString()));
            orderItemDAO.updateInoutNum(itemModel);
            orderItemDAO.updateTabGoodCustomerStorageOut(itemModel);
        }
    }
    
    public void insertInoutStock(OrderItemModel model, MsgModel msgModel){
        String data = model.getData();
        List<Map<String, Object>> list = (List<Map<String, Object>>) JSONUtils.parse(data);
        OrderItemModel orderItemModel=new OrderItemModel();
        for (int i = 0; i < list.size(); i++) {
            Object obj = list.get(i).get("id");
            Object obj2= list.get(i).get("orderId");
            Object obj1 = list.get(i).get("tmpNum");
            if(model.getOrderType()==1){
                orderItemModel.setInprice(Double.parseDouble(list.get(i).get("inprice").toString()));
                orderItemModel.setOutprice(Double.parseDouble(list.get(i).get("outprice").toString()));
            }
            orderItemModel.setOrderId(String.valueOf(obj2));
            orderItemModel.setOrderItemId(String.valueOf(obj));
            orderItemModel.setGoodNum(Long.parseLong(obj1.toString()));
            orderItemModel.setStime(model.getStime());
            orderItemModel.setCreateUserId(model.getUserId());
            orderItemModel.setModifyUserId(model.getUserId());
            orderItemModel.setOrderType(model.getOrderType());
            if(model.getOrderType()==0){
                orderItemDAO.insertInStockItem(orderItemModel);
            }else{
                orderItemDAO.insertOutStockItem(orderItemModel);
            }
            
        }
        List<OrderItemModel> clist = new ArrayList();//查询子节点
        clist = orderItemDAO.queryOrderTree2(orderItemModel);
        if(clist.size()!=0 ){
            if(model.getOrderType()==0){//采购订单
                orderItemDAO.updateInOrderStatus(orderItemModel);
            }else if(model.getOrderType()==1){//出货订单
                orderItemDAO.updateOutOrderStatus(orderItemModel);
            }
        }else if(clist.size()==0 && orderItemModel.getGoodNum()!=0){
            orderItemDAO.updateOrderBillStatus(orderItemModel);
        }
        if(model.getOrderType()==0){
            updateOrderItemTmpNum(model);
        }else{
            updateOrderItemTmpNumOut(model);
        }
        
        msgModel.setSuccess(GLOBAL_MSG_BOOL_SUCCESS);
    }
    
    
}
