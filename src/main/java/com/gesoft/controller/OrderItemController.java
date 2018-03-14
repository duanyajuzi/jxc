
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
import org.springframework.transaction.annotation.Transactional;
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
@Transactional
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
     * 分页查询入库细项
     * @param model
     * @return
     */
    @RequestMapping(value="/queryInoutStockItem")
    public @ResponseBody MsgModel search3(OrderItemModel model)
    {
        MsgModel msgModel = new MsgModel();
        try
        {
            orderItemService.findPageInoutStockItem(model, msgModel);
        }
        catch (Exception e)
        {
            logger.error("OrderItemController queryInoutStockItem error：", e);
        }
        return msgModel;
    }

    /**
     *分页查询出库细项
     * @param model
     * @return
     */
    @RequestMapping(value="/queryOutStockItem")
    public @ResponseBody MsgModel search4(OrderItemModel model)
    {
        MsgModel msgModel = new MsgModel();
        try
        {
            orderItemService.findPageOutStockItem(model, msgModel);
        }
        catch (Exception e)
        {
            logger.error("OrderItemController queryOutStockItem error：", e);
        }
        return msgModel;
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
            model.setId(Md5Util.UUID());
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
    
    //根据customerGoodId和customerId查询blueprint主键
    @RequestMapping(value = "/getBluePrintInfo")
    @ResponseBody
    public OrderItemModel getBluePrintInfo(OrderItemModel model){
        OrderItemModel orderItemModel=null;
        try{
            orderItemModel = orderItemService.getBluePrintInfo(model);
        }catch (Exception e){
            logger.error("OrderItemController queryPrice error：", e);
        }
        return orderItemModel;
    }
    
    
    //修改已入库商品数量
    @RequestMapping(value = "/updateOrderItemTmpNum")
    @ResponseBody
    public MsgModel updateOrderItemTmpNum(OrderItemModel model,HttpServletRequest request){
        MsgModel msgModel = new MsgModel();
        try{
            setSessionUserId(model, request);
//            orderItemService.updateOrderItemTmpNum(model,msgModel);
        }
        catch (Exception e) {
            logger.error("OrderItemController updateOrderItemTmpNum error：", e);
        }
    
        return msgModel;
    }

    //修改已出库商品数量
    @RequestMapping(value = "/updateOrderItemTmpNumOut")
    @ResponseBody
    public MsgModel updateOrderItemTmpNumOut(OrderItemModel model,HttpServletRequest request){
        MsgModel msgModel = new MsgModel();
        try{
            setSessionUserId(model, request);
//            orderItemService.updateOrderItemTmpNumOut(model,msgModel);
        }
        catch (Exception e) {
            logger.error("OrderItemController updateOrderItemTmpNumOut error：", e);
        }
        return msgModel;
    }

    //添加出库入库表信息
    @RequestMapping(value = "/insertTabInoutStock")
    @ResponseBody
    public MsgModel insertInoutStock(OrderItemModel model,HttpServletRequest request){
        MsgModel msgModel = new MsgModel();
        try {
            setSessionUserId(model, request);
            orderItemService.insertInoutStock(model,msgModel);
        }catch (Exception e){
            logger.error("OrderItemController insertInoutStockItem error：", e);
        }
        return msgModel;
    }

    //修改出入库存价格
    @RequestMapping(value = "/updateInoutStockItem",method=RequestMethod.POST)
    public @ResponseBody MsgModel updateInoutStockItem(OrderItemModel orderItemModel,HttpServletRequest request){
        MsgModel msgModel = new MsgModel();
        try
        {
            setSessionUserId(orderItemModel, request);
            if (orderItemService.updateInoutStockItem(orderItemModel) > 0)
            {
                msgModel.setSuccess(GLOBAL_MSG_BOOL_SUCCESS);
            }
        }
        catch (Exception e)
        {
            logger.error("OrderItemController updateInoutStockItem error：", e);
        }
        return msgModel;

    }

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