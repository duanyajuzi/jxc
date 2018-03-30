 /**
 * 文件名称：
 * 版权所有：Copyright gesoft
 * 创建时间：2017-07-19 13:24:33
 * 创 建 人：WCL (ln_admin@yeah.net)
 * 功能描述：
 **/
package com.gesoft.dao;

 import com.gesoft.common.EntityDAOImpl;
 import com.gesoft.model.InStockItemModel;
 import com.gesoft.model.OutStockItemModel;
 import org.springframework.stereotype.Repository;

 import java.util.List;


 @Repository
public class InStockItemDAO extends EntityDAOImpl<InStockItemModel, Long>
{
	@Override
    public String getMybatisSqlMapNamespace()
    {
        return "InStockItemMapper";
    }
    
    public long findCntInStock(InStockItemModel model)
    {
        return (Long)getSqlSession().selectOne(getMybatisSqlMapNamespace() + ".countIn", model);
    }
    
    public List<InStockItemModel> findListInStock(InStockItemModel inStockItemModel){
        return getSqlSession().selectList(getMybatisSqlMapNamespace() + ".queryInStock", inStockItemModel);
    }
    
    public int insertInoutStockItem(InStockItemModel model){
        return getSqlSession().insert(getMybatisSqlMapNamespace() + ".insertInoutStockItem", model);
    }
    
}
