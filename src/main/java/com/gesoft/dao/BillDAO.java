 /**
 * 文件名称：
 * 版权所有：Copyright gesoft
 * 创建时间：2017-07-19 13:24:33
 * 创 建 人：WCL (ln_admin@yeah.net)
 * 功能描述：
 **/
package com.gesoft.dao;

import org.springframework.stereotype.Repository;
import com.gesoft.common.EntityDAOImpl;
import com.gesoft.model.BillModel;
import java.util.List;


 @Repository
public class BillDAO extends EntityDAOImpl<BillModel, Long>
{
	@Override
    public String getMybatisSqlMapNamespace()
    {
        return "BillMapper";
    }

    //查询内嵌grid总记录数
    public long queryInoutItemCnt(BillModel model)
    {
        return (Long)getSqlSession().selectOne(getMybatisSqlMapNamespace() + ".queryInoutItemCnt", model);
    }
    public List<BillModel> queryInoutItemList(BillModel model){
        return getSqlSession().selectList(getMybatisSqlMapNamespace() + ".queryInoutItem", model);
    }

    public int updateBillStatus(BillModel model){
        return getSqlSession().update(getMybatisSqlMapNamespace() + ".updateBillStatus", model);
    }
    public int updateUnBillStatus(BillModel model){
        return getSqlSession().update(getMybatisSqlMapNamespace() + ".updateUnBillStatus", model);
    }

    public int insertBillItem(BillModel model) {
        return getSqlSession().insert(getMybatisSqlMapNamespace() + ".insertBillItem", model);
    }

    //查询开票页面左侧入\出库gird总记录数
    public long queryInoutCnt(BillModel model)
    {
        return (Long)getSqlSession().selectOne(getMybatisSqlMapNamespace() + ".queryInoutCnt", model);
    }
    public List<BillModel> queryInoutList(BillModel model){
        return getSqlSession().selectList(getMybatisSqlMapNamespace() + ".queryInout", model);
    }

    public int updatePayState(BillModel model){
        return getSqlSession().update(getMybatisSqlMapNamespace() + ".updatePayState", model);
    }

    public int updateOrderFinalStatus(BillModel model){
        return getSqlSession().update(getMybatisSqlMapNamespace() + ".updateOrderFinalStatus", model);
    }

    public int deleteBefore(BillModel model){
        return getSqlSession().delete(getMybatisSqlMapNamespace() + ".deleteBefore", model);
    }

    public int updateBefore(BillModel model){
        return getSqlSession().update(getMybatisSqlMapNamespace() + ".updateBefore", model);
    }

}
