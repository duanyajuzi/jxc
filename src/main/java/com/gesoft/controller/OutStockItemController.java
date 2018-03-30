 /**
 * 文件名称：
 * 版权所有：Copyright gesoft
 * 创建时间：2017-07-19 13:24:33
 * 创 建 人：WCL (ln_admin@yeah.net)
 * 功能描述：
 **/
package com.gesoft.controller;


 import com.gesoft.model.MsgModel;
 import com.gesoft.model.OutStockItemModel;
 import com.gesoft.service.OutStockItemService;
 import org.slf4j.Logger;
 import org.slf4j.LoggerFactory;
 import org.springframework.stereotype.Controller;
 import org.springframework.web.bind.annotation.RequestMapping;
 import org.springframework.web.bind.annotation.ResponseBody;

 import javax.annotation.Resource;


 @Controller
@RequestMapping("/outStockItem")
public class OutStockItemController extends BaseController
{	
	private static final Logger logger = LoggerFactory.getLogger(OutStockItemController.class);
	
	@Resource
	private OutStockItemService outStockItemService;
    
    /**
     * 分页查询入库细项
     * @param model
     * @return
     */
    @RequestMapping(value="/queryOutStockItem")
    public @ResponseBody MsgModel queryOutStockItem(OutStockItemModel model)
    {
        MsgModel msgModel = new MsgModel();
        try
        {
            outStockItemService.queryOutStockItem(model, msgModel);
        }
        catch (Exception e)
        {
            logger.error("OutStockItemController queryInStockItem error：", e);
        }
        return msgModel;
    }

}
