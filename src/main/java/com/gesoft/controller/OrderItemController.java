
package com.gesoft.controller;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import com.gesoft.model.OrderItemModel;
import com.gesoft.service.OrderItemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.gesoft.model.MsgModel;
import java.util.ArrayList;
import java.util.List;

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

    //根据商品名称查询物料号
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

    //查询当前厂商商品方案信息
    @RequestMapping(value = "queryPlanPrice")
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
}