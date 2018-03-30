 /**
 * 文件名称：
 * 版权所有：Copyright gesoft
 * 创建时间：2017-07-19 13:24:33
 * 创 建 人：WCL (ln_admin@yeah.net)
 * 功能描述：
 **/
package com.gesoft.controller;


 import com.gesoft.model.InStockItemModel;
 import com.gesoft.model.MsgModel;
 import com.gesoft.service.InStockItemService;
 import org.slf4j.Logger;
 import org.slf4j.LoggerFactory;
 import org.springframework.stereotype.Controller;
 import org.springframework.web.bind.annotation.RequestMapping;
 import org.springframework.web.bind.annotation.ResponseBody;

 import javax.annotation.Resource;
 import javax.servlet.http.HttpServletRequest;
 import java.util.ArrayList;
 import java.util.List;


 @Controller
@RequestMapping("/inStockItem")
public class InStockItemController extends BaseController
{	
	private static final Logger logger = LoggerFactory.getLogger(InStockItemController.class);
	
	@Resource
	private InStockItemService inStockItemService;
    
    /**
     * 分页查询入库细项
     * @param model
     * @return
     */
    @RequestMapping(value="/queryInStockItem")
    public @ResponseBody MsgModel queryInStockItem(InStockItemModel model)
    {
        MsgModel msgModel = new MsgModel();
        try
        {
            inStockItemService.queryInStockItem(model, msgModel);
        }
        catch (Exception e)
        {
            logger.error("InStockItemController queryInStockItem error：", e);
        }
        return msgModel;
    }
    
    //添加出库入库表信息
    @RequestMapping(value = "/insertTabInoutStock")
    @ResponseBody
    public MsgModel insertInoutStock(InStockItemModel model,HttpServletRequest request){
        MsgModel msgModel = new MsgModel();
        try {
            setSessionUserId(model, request);
            inStockItemService.insertInoutStock(model,msgModel);
        }catch (Exception e){
            logger.error("OrderItemController insertInoutStockItem error：", e);
        }
        return msgModel;
    }
}
