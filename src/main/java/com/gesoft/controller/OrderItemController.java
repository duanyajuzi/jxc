
package com.gesoft.controller;


import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.druid.support.json.JSONUtils;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.gesoft.model.OrderExcelItemModel;
import com.gesoft.model.OrderExcelModel;
import com.gesoft.model.OrderItemModel;
import com.gesoft.service.OrderItemService;
import com.gesoft.util.ExportExecl;
import com.gesoft.util.Md5Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.gesoft.model.MsgModel;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@Transactional
@RequestMapping("/orderItem")
public class OrderItemController extends BaseController
{
    private static final Logger logger = LoggerFactory.getLogger(OrderItemController.class);

    @Resource
    private OrderItemService orderItemService;
    
    /**
     * 描述信息：导出对账单
     * @param model
     * @return
     */
    @RequestMapping(value="/exportStatement", method=RequestMethod.GET)
    public @ResponseBody MsgModel exportStatement(OrderItemModel model, HttpServletRequest request,HttpServletResponse response)
    {
        MsgModel msgModel = new MsgModel();
        try
        {
            Map<String, Object> map = new HashMap<String, Object>();
            
            List<OrderExcelModel> orderItems = new ArrayList<>();
            
            //订单项列表
            OrderItemModel orderItemModel = new OrderItemModel();
            orderItemModel.setOrderId(model.getId());
            List<OrderItemModel> orderItemModels = orderItemService.queryCList(model);
            
            int size = orderItemModels.size();
            
            map.put("expandedRowCount", size);
            
            OrderExcelModel orderExcelModel;
            for(OrderItemModel orderItem : orderItemModels){
                orderExcelModel = new OrderExcelModel();
                orderExcelModel.setUnit(orderItem.getUnit());
                orderExcelModel.setPn(orderItem.getYcmaterialNum());
                orderExcelModel.setPn2(orderItem.getMaterialNum());
                orderExcelModel.setUnit_price_net(orderItem.getInprice());
                orderExcelModel.setOutprice(orderItem.getOutprice());
                orderExcelModel.setQty(orderItem.getGoodNum());
                orderExcelModel.setCust_po_no(orderItem.getOrderName());
                orderExcelModel.setDeliveryTime(orderItem.getDeliveryTime());
                orderItems.add(orderExcelModel);
            }
            map.put("orderItems", orderItems);
            String pname = model.getPname();
            
            File file = null;
            InputStream inputStream = null;
            ServletOutputStream out = null;
            try {
                request.setCharacterEncoding("UTF-8");
                file = ExportExecl.createExcel(map, "myExcel", "statement.ftl");//调用创建excel帮助类
                inputStream = new FileInputStream(file);
                response.setCharacterEncoding("utf-8");
                response.setContentType("application/msexcel");
                response.setHeader("content-disposition", "attachment;filename="+ URLEncoder.encode(pname + "价格列表" + ".xls", "UTF-8"));
                out = response.getOutputStream();
                byte[] buffer = new byte[512]; // 缓冲区
                int bytesToRead = -1;
                // 通过循环将读入的Excel文件的内容输出到浏览器中
                while ((bytesToRead = inputStream.read(buffer)) != -1) {
                    out.write(buffer, 0, bytesToRead);
                }
                out.flush();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (inputStream != null)
                    inputStream.close();
                if (out != null)
                    out.close();
                if (file != null)
                    file.delete(); // 删除临时文件
            }
        }
        catch (Exception e)
        {
            logger.error("OrderItemController search error：", e);
        }
        return msgModel;
    }
    
    /**
     * 描述信息：导出价格列表
     * @param model
     * @return
     */
    @RequestMapping(value="/exportPriceList", method=RequestMethod.GET)
    public @ResponseBody MsgModel exportPriceList(OrderItemModel model, HttpServletRequest request,HttpServletResponse response)
    {
        MsgModel msgModel = new MsgModel();
        try
        {
            Map<String, Object> map = new HashMap<String, Object>();
    
            List<OrderExcelModel> orderItems = new ArrayList<>();
    
            //订单项列表
            OrderItemModel orderItemModel = new OrderItemModel();
            orderItemModel.setOrderId(model.getId());
            List<OrderItemModel> orderItemModels = orderItemService.queryPriceList(model);
    
            int size = orderItemModels.size();
    
            map.put("expandedRowCount", size);
    
            OrderExcelModel orderExcelModel;
            for(OrderItemModel orderItem : orderItemModels){
                orderExcelModel = new OrderExcelModel();
                orderExcelModel.setUnit(orderItem.getUnit());
                orderExcelModel.setPn(orderItem.getYcmaterialNum());
                orderExcelModel.setPn2(orderItem.getMaterialNum());
                orderExcelModel.setUnit_price_net(orderItem.getUnitPrice());
                orderExcelModel.setOutprice(orderItem.getPrice());
                orderExcelModel.setGoodsName(orderItem.getGoodsName());
                orderItems.add(orderExcelModel);
            }
            map.put("orderItems", orderItems);
            String pname = model.getPname();
    
            File file = null;
            InputStream inputStream = null;
            ServletOutputStream out = null;
            try {
                request.setCharacterEncoding("UTF-8");
                file = ExportExecl.createExcel(map, "myExcel", "price_list.ftl");//调用创建excel帮助类
                inputStream = new FileInputStream(file);
                response.setCharacterEncoding("utf-8");
                response.setContentType("application/msexcel");
                response.setHeader("content-disposition", "attachment;filename="+ URLEncoder.encode(pname + "价格列表" + ".xls", "UTF-8"));
                out = response.getOutputStream();
                byte[] buffer = new byte[512]; // 缓冲区
                int bytesToRead = -1;
                // 通过循环将读入的Excel文件的内容输出到浏览器中
                while ((bytesToRead = inputStream.read(buffer)) != -1) {
                    out.write(buffer, 0, bytesToRead);
                }
                out.flush();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (inputStream != null)
                    inputStream.close();
                if (out != null)
                    out.close();
                if (file != null)
                    file.delete(); // 删除临时文件
            }
        }
        catch (Exception e)
        {
            logger.error("OrderItemController search error：", e);
        }
        return msgModel;
    }
    
    /**
     * 描述信息：在选择的时间段内，查询某客户的销售金额
     * @param model
     * @return
     */
    @RequestMapping(value="/queryCList", method=RequestMethod.GET)
    public @ResponseBody MsgModel queryCList(OrderItemModel model)
    {
        MsgModel msgModel = new MsgModel();
        try
        {
            msgModel.setData(orderItemService.queryCList(model));
            msgModel.setSuccess(GLOBAL_MSG_BOOL_SUCCESS);
        }
        catch (Exception e)
        {
            logger.error("OrderItemController search error：", e);
        }
        return msgModel;
    }
    
    /**
     * 描述信息：查询某客户所有产品销售价格/采购价格
     * @param model
     * @return
     */
    @RequestMapping(value="/queryPriceList", method=RequestMethod.GET)
    public @ResponseBody MsgModel queryPriceList(OrderItemModel model)
    {
        MsgModel msgModel = new MsgModel();
        try
        {
            msgModel.setData(orderItemService.queryPriceList(model));
            msgModel.setSuccess(GLOBAL_MSG_BOOL_SUCCESS);
        }
        catch (Exception e)
        {
            logger.error("OrderItemController search error：", e);
        }
        return msgModel;
    }
    
    
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