 /**
 * 文件名称：
 * 版权所有：Copyright gesoft
 * 创建时间：2017-07-19 13:24:33
 * 创 建 人：WCL (ln_admin@yeah.net)
 * 功能描述：
 **/
package com.gesoft.service;

 import com.gesoft.common.EntityDAO;
 import com.gesoft.common.EntityService;
 import com.gesoft.dao.OutStockItemDAO;
 import com.gesoft.model.MsgModel;
 import com.gesoft.model.OutStockItemModel;
 import org.springframework.stereotype.Service;
 import org.springframework.transaction.annotation.Transactional;

 import javax.annotation.Resource;
 import java.util.List;

 @Service
@Transactional
public class OutStockItemService extends EntityService<OutStockItemModel, Long>
{

	@Resource
	private OutStockItemDAO outStockItemDAO;
	
	@Override
	protected EntityDAO<OutStockItemModel, Long> getEntityDao() {
		return this.outStockItemDAO;
	}
    /**
     * 分页查询入库细项
     * @param model
     * @param msgModel
     */
    public void queryOutStockItem(OutStockItemModel model, MsgModel msgModel) {
        //取总记录数
        long recordCount = model.getTotal();
        if (isSearchPageTotal(model)) {
            recordCount = outStockItemDAO.findCntOutStock(model);
        }
        //分页加载数据
        if (recordCount > 0) {
            setPageModel(recordCount, model);
            List<OutStockItemModel> argArgs = outStockItemDAO.findListOutStock(model);
            if (argArgs != null) {
                msgModel.setData(argArgs);
                msgModel.setTotal(recordCount);
            }
        }
    }
    
}
