 /**
 * 文件名称：
 * 版权所有：Copyright gesoft
 * 创建时间：2017-07-19 13:24:33
 * 创 建 人：WCL (ln_admin@yeah.net)
 * 功能描述：
 **/
package com.gesoft.service;

 import com.alibaba.druid.support.json.JSONUtils;
 import com.gesoft.common.EntityDAO;
 import com.gesoft.common.EntityService;
 import com.gesoft.dao.InStockItemDAO;
 import com.gesoft.dao.OrderItemDAO;
 import com.gesoft.model.InStockItemModel;
 import com.gesoft.model.MsgModel;
 import org.springframework.stereotype.Service;
 import org.springframework.transaction.annotation.Transactional;

 import javax.annotation.Resource;
 import java.util.ArrayList;
 import java.util.List;
 import java.util.Map;

 @Service
@Transactional
public class InStockItemService extends EntityService<InStockItemModel, Long>
{

	@Resource
	private InStockItemDAO inStockItemDAO;
    @Resource
    private OrderItemDAO orderItemDAO;
    
    
    @Override
	protected EntityDAO<InStockItemModel, Long> getEntityDao() {
		return this.inStockItemDAO;
	}
    
    /**
     * 分页查询入库细项
     * @param model
     * @param msgModel
     */
    public void queryInStockItem(InStockItemModel model, MsgModel msgModel) {
        //取总记录数
        long recordCount = model.getTotal();
        if (isSearchPageTotal(model)) {
            recordCount = inStockItemDAO.findCntInStock(model);
        }
        //分页加载数据
        if (recordCount > 0) {
            setPageModel(recordCount, model);
            List<InStockItemModel> argArgs = inStockItemDAO.findListInStock(model);
            if (argArgs != null) {
                msgModel.setData(argArgs);
                msgModel.setTotal(recordCount);
            }
        }
    }
    
    public void insertInoutStock(InStockItemModel model, MsgModel msgModel){
        String data = model.getData();
        List<Map<String, Object>> list = (List<Map<String, Object>>) JSONUtils.parse(data);
        InStockItemModel inStockItemModel=new InStockItemModel();
        for (int i = 0; i < list.size(); i++) {
            Object obj = list.get(i).get("id");
            Object obj2= list.get(i).get("orderId");
            Object obj1 = list.get(i).get("tmpNum");
            inStockItemModel.setOrderId(String.valueOf(obj2));
            inStockItemModel.setOrderItemId(String.valueOf(obj));
            inStockItemModel.setGoodNum(Long.parseLong(obj1.toString()));
            inStockItemModel.setStime(model.getStime());
            inStockItemModel.setCreateUserId(model.getUserId());
            inStockItemModel.setModifyUserId(model.getUserId());
            inStockItemDAO.insertInoutStockItem(inStockItemModel);
        }
    }
    
}
