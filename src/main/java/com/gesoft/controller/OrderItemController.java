
package com.gesoft.controller;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import com.alibaba.druid.support.json.JSONUtils;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.gesoft.model.OrderItemModel;
import com.gesoft.service.OrderItemService;
import com.gesoft.util.Md5Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.gesoft.model.MsgModel;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/orderItem")
public class OrderItemController extends BaseController
{
    private static final Logger logger = LoggerFactory.getLogger(OrderItemController.class);

    @Resource
    private OrderItemService orderItemService;

    /**
     * 描述信息：分页查询
     *  @param model
     * @return
     */
    @RequestMapping(value="/query", method=RequestMethod.POST)
    public @ResponseBody MsgModel search(OrderItemModel model)
    {
        MsgModel msgModel = new MsgModel();
        try
        {
            orderItemService.findPageList(model, msgModel);
        }
        catch (Exception e)
        {
            logger.error("OrderItemController search error：", e);
        }
        return msgModel;
    }
    
    /**
     * 描述信息：销售订单子项查询
     * @param model
     * @return
     */
    @RequestMapping(value="/getList", method=RequestMethod.GET)
    public @ResponseBody MsgModel getList(OrderItemModel model)
    {
        MsgModel msgModel = new MsgModel();
        try
        {
            msgModel.setData(orderItemService.findList(model));
            msgModel.setSuccess(GLOBAL_MSG_BOOL_SUCCESS);
        }
        catch (Exception e)
        {
            logger.error("OrderItemController search error：", e);
        }
        return msgModel;
    }
    
    /**
     * 描述信息：采购订单子项查询
     * @param model
     * @return
     */
    @RequestMapping(value="/getList2", method=RequestMethod.GET)
    public @ResponseBody MsgModel getList2(OrderItemModel model)
    {
        MsgModel msgModel = new MsgModel();
        try
        {
            msgModel.setData(orderItemService.findList2(model));
            msgModel.setSuccess(GLOBAL_MSG_BOOL_SUCCESS);
        }
        catch (Exception e)
        {
            logger.error("OrderItemController search error：", e);
        }
        return msgModel;
    }

    /**
     * 入库清单
     * @param model
     * @return
     */
    @RequestMapping(value="/queryInoutStock", method=RequestMethod.POST)
    public @ResponseBody MsgModel queryInoutStock(OrderItemModel model)
    {
        MsgModel msgModel = new MsgModel();
        try
        {
            orderItemService.findPageInoutStock(model, msgModel);
        }
        catch (Exception e)
        {
            logger.error("OrderItemController queryInoutStock error：", e);
        }
        return msgModel;
    }

    /**
     * 出库清单
     * @param model
     * @return
     */
    @RequestMapping(value="/queryOutStock", method=RequestMethod.POST)
    public @ResponseBody MsgModel queryOutStock(OrderItemModel model)
    {
        MsgModel msgModel = new MsgModel();
        try
        {
            orderItemService.findPageOutStock(model, msgModel);
        }
        catch (Exception e)
        {
            logger.error("OrderItemController queryOutStock error：", e);
        }
        return msgModel;
    }

    /**
     * 入库细项
     * @param model
     * @return
     */
    @RequestMapping(value="/queryInoutStockItem", method=RequestMethod.POST)
    public @ResponseBody List<OrderItemModel> search3(OrderItemModel model)
    {
        List<OrderItemModel> list=new ArrayList<OrderItemModel>();
        try
        {
           list= orderItemService.findListInoutStockItem(model);
        }
        catch (Exception e)
        {
            logger.error("OrderItemController queryInoutStockItem error：", e);
        }
        return list;
    }

    /**
     * 出库细项
     * @param model
     * @return
     */
    @RequestMapping(value="/queryOutStockItem", method=RequestMethod.POST)
    public @ResponseBody List<OrderItemModel> search4(OrderItemModel model)
    {
        List<OrderItemModel> list=new ArrayList<OrderItemModel>();
        try
        {
            list= orderItemService.findListOutStockItem(model);
        }
        catch (Exception e)
        {
            logger.error("OrderItemController queryOutStockItem error：", e);
        }
        return list;
    }

    /**
     * 描述信息：增加
     */
    @RequestMapping(value="/add", method=RequestMethod.POST)
    public @ResponseBody MsgModel add(OrderItemModel model, HttpServletRequest request)
    {
        MsgModel msgModel = new MsgModel();
        try
        {
            setSessionUserId(model, request);
            if (orderItemService.save(model) > 0)
            {
                msgModel.setSuccess(GLOBAL_MSG_BOOL_SUCCESS);
            }
        }
        catch (Exception e)
        {
            logger.error("OrderItemController add error：", e);
        }
        return msgModel;
    }
    



    /**
     * 描述信息：修改
     * @return
     */
    @RequestMapping(value="/modify", method=RequestMethod.POST)
    public @ResponseBody MsgModel modify(OrderItemModel model, HttpServletRequest request)
    {
        MsgModel msgModel = new MsgModel();
        try
        {
            setSessionUserId(model, request);
            if (orderItemService.update(model) > 0)
            {
                msgModel.setSuccess(GLOBAL_MSG_BOOL_SUCCESS);
            }
        }
        catch (Exception e)
        {
            logger.error("OrderItemController modify error：", e);
        }
        return msgModel;
    }


    /**
     * 描述信息：删除
     */
    @RequestMapping(value="/del", method=RequestMethod.POST)
    public @ResponseBody MsgModel delete(OrderItemModel model)
    {
        MsgModel msgModel = new MsgModel();
        try
        {
            if (orderItemService.delete(model) > 0)
            {
                msgModel.setSuccess(GLOBAL_MSG_BOOL_SUCCESS);
            }
        }
        catch (Exception e)
        {
            logger.error("OrderItemController delete error：", e);
        }
        return msgModel;
    }

    //根据客户料号查询详细
    @RequestMapping(value="/queryNumInfoList")
    public @ResponseBody List<OrderItemModel> queryNumInfoList(OrderItemModel model){
        List<OrderItemModel> list=null;
        try{
            if(list==null){
                list=new ArrayList();
            }
            list=orderItemService.queryNumInfo(model);
        }catch (Exception e){
            logger.error("OrderItemController queryNumInfoList error：", e);
        }
        return list;
    }
    
    //根据原厂料号查询详细
    @RequestMapping(value="/queryNumInfoList2")
    public @ResponseBody List<OrderItemModel> queryNumInfoList2(OrderItemModel model){
        List<OrderItemModel> list=null;
        try{
            if(list==null){
                list=new ArrayList();
            }
            list=orderItemService.queryNumInfo2(model);
        }catch (Exception e){
            logger.error("OrderItemController queryNumInfoList error：", e);
        }
        return list;
    }

    //查询当前厂商商品方案信息
    @RequestMapping(value = "/queryPlanPrice")
    @ResponseBody
    public List<OrderItemModel> queryPrice(OrderItemModel model){
        List<OrderItemModel> planList=null;
        try{
            if(planList==null){
                planList=new ArrayList<>();
            }
            planList = orderItemService.queryGoodPrint(model);
        }catch (Exception e){
            logger.error("OrderItemController queryPrice error：", e);
        }
        return planList;
    }

    //修改已入库商品数量
    @RequestMapping(value = "/updateOrderItemTmpNum")
    @ResponseBody
    public void updateOrderItemTmpNum(OrderItemModel model){
        try{
            String str=model.getData();
            List<Map<String,Object>> list= (List<Map<String,Object>>) JSONUtils.parse(str);
            for(int i=0;i<list.size();i++){
                Object obj=list.get(i).get("id");
                Object obj1=list.get(i).get("tmpNum");
                Object obj2=list.get(i).get("customerGoodId");
                Object obj3=list.get(i).get("goodId");
                model.setId(Long.valueOf(String.valueOf(obj)));
                model.setGoodId(Long.valueOf(String.valueOf(obj3)));
                model.setCustomerGoodId(String.valueOf(obj2));
                model.setTmpNum(Float.parseFloat(obj1.toString()));
                orderItemService.updateInoutNum(model);
//                orderItemService.updateTabGoodsStorage(model);
                orderItemService.updateTabGoodCustomerStorage(model);
            }
        }
        catch (Exception e) {
            logger.error("OrderItemController updateOrderItemTmpNum error：", e);
        }
    }

    //修改已出库商品数量
    @RequestMapping(value = "/updateOrderItemTmpNumOut")
    @ResponseBody
    public void updateOrderItemTmpNumOut(OrderItemModel model){
        try{
            String str=model.getData();
            List<Map<String,Object>> list= (List<Map<String,Object>>) JSONUtils.parse(str);
            for(int i=0;i<list.size();i++){
                Object obj=list.get(i).get("id");
                Object obj1=list.get(i).get("tmpNum");
                Object obj2=list.get(i).get("customerGoodId");
                Object obj3=list.get(i).get("goodId");
                model.setId(Long.valueOf(String.valueOf(obj)));
                model.setGoodId(Long.valueOf(String.valueOf(obj3)));
                model.setCustomerGoodId(String.valueOf(obj2));
                model.setTmpNum(Float.parseFloat(obj1.toString()));
                orderItemService.updateInoutNum(model);
//                orderItemService.updateTabGoodsStorageOut(model);
                orderItemService.updateTabGoodCustomerStorageOut(model);
            }
        }
        catch (Exception e) {
            logger.error("OrderItemController updateOrderItemTmpNumOut error：", e);
        }
    }

    //添加出库入库表信息
    @RequestMapping(value = "/insertTabInoutStock")
    @ResponseBody
    public void insertInoutStock(OrderItemModel model,HttpServletRequest request){
        try{
            model.setCreateUserId(getSessionUserId(request));
            model.setCreateTime((new SimpleDateFormat("yyyy-MM-dd H:mm:ss")).format(new Date()));
            model.setModifyUserId(getSessionUserId(request));
            model.setModifyTime((new SimpleDateFormat("yyyy-MM-dd H:mm:ss")).format(new Date()));
            orderItemService.insertInoutStock(model);
        }catch (Exception e){
            logger.error("OrderItemController insertInoutStock error：", e);
        }
//        return model.getId();
        try {
            String data = model.getData();
            List<Map<String, Object>> list = (List<Map<String, Object>>) JSONUtils.parse(data);
            OrderItemModel orderItemModel=new OrderItemModel();
            for (int i = 0; i < list.size(); i++) {
                Object obj = list.get(i).get("id");
                Object obj2= list.get(i).get("orderId");
                Object obj1 = list.get(i).get("tmpNum");
                orderItemModel.setInout_stock_id(model.getId());
                orderItemModel.setOrderId(String.valueOf(obj2));
                orderItemModel.setOrderItemId(Long.valueOf(String.valueOf(obj)));
                orderItemModel.setGoodNum(Float.parseFloat(obj1.toString()));
                orderItemService.insertInoutStockItem(orderItemModel);
            }
            List<OrderItemModel> clist = new ArrayList();//查询子节点
            clist = orderItemService.queryOrderTree2(orderItemModel);
            if(clist.size()!=0 ){
                if(model.getOrderType()==0){//采购订单
                    orderItemService.updateInOrderStatus(orderItemModel);
                }else if(model.getOrderType()==1){//出货订单
                    orderItemService.updateOutOrderStatus(orderItemModel);
                }
            }else if(clist.size()==0 && orderItemModel.getGoodNum()!=0){
                orderItemService.updateOrderBillStatus(orderItemModel);
            }

        }catch (Exception e){
            logger.error("OrderItemController insertInoutStockItem error：", e);
        }
    }

    //插入出入库细项信息
//    @RequestMapping(value = "/insertInoutStockItem")
//    @ResponseBody
//    public void insertInoutStockItem(OrderItemModel model){
//        try {
//            String data = model.getData();
//            List<Map<String, Object>> list = (List<Map<String, Object>>) JSONUtils.parse(data);
//            for (int i = 0; i < list.size(); i++) {
//                Object obj = list.get(i).get("id");
//                Object obj1 = list.get(i).get("tmpNum");
//                model.setOrderItemId(Long.valueOf(String.valueOf(obj)));
//                model.setGoodNum(Float.parseFloat(obj1.toString()));
//                orderItemService.insertInoutStockItem(model);
//            }
//        }catch (Exception e){
//            logger.error("OrderItemController insertInoutStockItem error：", e);
//        }
//    }
//    //修改出入库存信息
//    @RequestMapping(value = "/updateTabInoutStock",method=RequestMethod.POST)
//    @ResponseBody
//    public void updateInoutStock(OrderItemModel orderItemModel,HttpServletRequest request){
//        try{
//            orderItemModel.setModifyUserId(getSessionUserId(request));
//            orderItemModel.setModifyTime((new SimpleDateFormat("yyyyMMddhhmmss")).format(new Date()));
//            orderItemService.updateInoutStock(orderItemModel);
//        }catch (Exception e){
//            logger.error("OrderItemController updateInoutStock error：", e);
//        }
//    }
//    //删除出入库存信息
//    @RequestMapping(value = "/deleteTabInoutStock")
//    @ResponseBody
//    public void deleteInoutStock(OrderItemModel model){
//        try{
//            orderItemService.deleteInoutStock(model);
//        }catch (Exception e){
//            logger.error("OrderItemController deleteInoutStock error：", e);
//        }
//    }

    /**
     * 采购订单树
     * @param model
     * @return
     */
    @RequestMapping(value = "/queryInOrderTreeList")
    @ResponseBody
    public List<OrderItemModel> queryInOrderTreeList(OrderItemModel model) {
        List<OrderItemModel> plist = new ArrayList<>();
        try {
            plist = orderItemService.queryInOrderTree(model);
        } catch (Exception e) {
            logger.error("OrderItemController queryOrderTreeList error: ", e);
        }
        for (OrderItemModel childlist : plist) {
            if (childlist.getTreeId() != null) {
                List<OrderItemModel> clist = new ArrayList();//查询子节点
                model.setOrderId(String.valueOf(childlist.getTreeId()));
                clist = orderItemService.queryOrderTree1(model);
                childlist.setChildren(clist);
            }
        }
        return plist;
    }
    /**
     * 销售订单树
     * @param model
     * @return
     */
    @RequestMapping(value = "/queryOrderTreeList")
    @ResponseBody
    public List<OrderItemModel> queryOrderTreeList(OrderItemModel model) {
        List<OrderItemModel> plist = new ArrayList<>();
        try {
            plist = orderItemService.queryOrderTree(model);
        } catch (Exception e) {
            logger.error("OrderItemController queryOrderTreeList error: ", e);
        }
        for (OrderItemModel childlist : plist) {
                if (childlist.getTreeId() != null) {
                    List<OrderItemModel> clist = new ArrayList();//查询子节点
                    model.setOrderId(String.valueOf(childlist.getTreeId()));
                    clist = orderItemService.queryOrderTree2(model);
                    childlist.setChildren(clist);
                }
        }
        return plist;
    }
}