 /**
 * 文件名称：
 * 版权所有：Copyright gesoft
 * 创建时间：2017-07-19 13:24:33
 * 创 建 人：WCL (ln_admin@yeah.net)
 * 功能描述：
 **/
package com.gesoft.dao;

 import com.gesoft.common.EntityDAOImpl;
 import com.gesoft.model.OutStockItemModel;
 import org.springframework.stereotype.Repository;

 import java.util.List;


 @Repository
public class OutStockItemDAO extends EntityDAOImpl<OutStockItemModel, Long>
{
	@Override
    public String getMybatisSqlMapNamespace()
    {
        return "OutStockItemMapper";
    }
    
    public long findCntOutStock(OutStockItemModel model)
    {
        return (Long)getSqlSession().selectOne(getMybatisSqlMapNamespace() + ".countOut", model);
    }
    
    public List<OutStockItemModel> findListOutStock(OutStockItemModel outStockItemModel){
        return getSqlSession().selectList(getMybatisSqlMapNamespace() + ".queryOutStock", outStockItemModel);
    }
    
}
