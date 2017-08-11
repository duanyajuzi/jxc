 /**
 * 文件名称：
 * 版权所有：Copyright gesoft
 * 创建时间：2017-07-19 13:24:33
 * 创 建 人：WCL (ln_admin@yeah.net)
 * 功能描述：
 **/
package com.gesoft.service;

import javax.annotation.Resource;

import com.gesoft.model.MsgModel;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.gesoft.common.EntityDAO;
import com.gesoft.common.EntityService;
import com.gesoft.dao.BillDAO;
import com.gesoft.model.BillModel;

import java.util.List;

 @Service
@Transactional
public class BillService extends EntityService<BillModel, Long>
{

	@Resource
	private BillDAO billDAO;
	
	@Override
	protected EntityDAO<BillModel, Long> getEntityDao()
	{
		return this.billDAO;
	}

	public List<BillModel> queryInoutList(BillModel model){
		return billDAO.queryInoutList(model);
	}

	public int updateBillStatus(BillModel model){
		return billDAO.updateBillStatus(model);
	}
	public int updateUnBillStatus(BillModel model){
		return billDAO.updateUnBillStatus(model);
	}
	public int insertBillItem(BillModel model) {
		return billDAO.insertBillItem(model);
	}

	public List<BillModel> queryInoutItemList(BillModel model){
		return  billDAO.queryInoutItemList(model);
	}

	public int updateBillState(BillModel model){
		return billDAO.updatePayState(model);
	}

	public int updateOrderFinalStatus(BillModel model){
		return billDAO.updateOrderFinalStatus(model);
	}

	public int deleteBefore(BillModel model){
		return billDAO.deleteBefore(model);
	}
	public int updateBefore(BillModel model){
		return  billDAO.updateBefore(model);
	}

	//分页查询出入库存表中信息进行开票
	public void findPageInout(BillModel model, MsgModel msgModel)
	{
		//取总记录数
		long recordCount = model.getTotal();
		if (isSearchPageTotal(model))
		{
			recordCount = billDAO.queryInoutCnt(model);
		}
		//分页加载数据
		if (recordCount > 0)
		{
			setPageModel(recordCount, model);
			List<BillModel> argArgs =  billDAO.queryInoutList(model);
			if (argArgs != null)
			{
				msgModel.setData(argArgs);
				msgModel.setTotal(recordCount);
			}
		}
	}

	//分页查询内嵌gird
	public void findPageBillItem(BillModel model, MsgModel msgModel) {
		//取总记录数
		long recordCount = model.getTotal();
		if (isSearchPageTotal(model)) {
			recordCount = billDAO.queryInoutItemCnt(model);
		}
		//分页加载数据
		if (recordCount > 0) {
			setPageModel(recordCount, model);
			List <BillModel> argArgs = billDAO.queryInoutItemList(model);
			if (argArgs != null) {
				msgModel.setData(argArgs);
				msgModel.setTotal(recordCount);
			}
		}
	}
}
