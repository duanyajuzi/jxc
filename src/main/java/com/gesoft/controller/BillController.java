 /**
 * 文件名称：
 * 版权所有：Copyright gesoft
 * 创建时间：2017-07-19 13:24:33
 * 创 建 人：WCL (ln_admin@yeah.net)
 * 功能描述：
 **/
package com.gesoft.controller;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import com.alibaba.druid.support.json.JSONUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gesoft.model.BillModel;
import com.gesoft.model.MsgModel;
import com.gesoft.service.BillService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


 @Controller
@RequestMapping("/bill")
public class BillController extends BaseController
{	
	private static final Logger logger = LoggerFactory.getLogger(BillController.class);
	private static long tmpID = 0;
	public static String getStringOrderNo(){
		if(tmpID > 999999){
			tmpID=1;
		}
		return "NO"+(new SimpleDateFormat("yyyyMMddhhmmss")).format(new Date())+tmpID;
	}
	@Resource
	private BillService billService;
	

	/**
	 * 描述信息：分页查询
	 * 创建时间：2017-07-19 13:24:33
	 * @author WCL (ln_admin@yeah.net)
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/query", method=RequestMethod.POST)
	public @ResponseBody MsgModel search(BillModel model)
	{
		MsgModel msgModel = new MsgModel();
		try
		{
			billService.findPageList(model, msgModel);
		}
		catch (Exception e)
		{
			logger.error("BillController search error：", e);
		}
		return msgModel;
	}
	
	
	/**
	 * 描述信息：增加
	 * 创建时间：2017-07-19 13:24:33
	 * @author WCL (ln_admin@yeah.net)
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public @ResponseBody MsgModel add(BillModel model, HttpServletRequest request)
	{
		MsgModel msgModel = new MsgModel();
		try {
//			setSessionUserId(model, request);
//			model.setPrePayTime(queryPrePayTime(model.getBillTime()));
			if (model.getId() == null) {
				model.setBillNo(getStringOrderNo());
				model.setPayConfirmUser(getSessionUserId(request));
				model.setCuserId(getSessionUserId(request));
				model.setCtime((new SimpleDateFormat("yyyy-MM-dd HH:mm:ss")).format(new Date()));
				if (billService.save(model) > 0) {
					msgModel.setSuccess(GLOBAL_MSG_BOOL_SUCCESS);
				}
			}else {
				billService.updateBefore(model);
				try{
					String str = model.getData();
					String left = model.getLeftData();
					BillModel billModel=new BillModel();
					billModel.setBillId(model.getId());
					billService.deleteBefore(billModel);
					List<Map<String, Object>> list = (List<Map<String, Object>>) JSONUtils.parse(str);
					List<Map<String, Object>> leftlist = (List<Map<String, Object>>) JSONUtils.parse(left);
					//修改出票状态，并保存数据到tab_bill_inout_stock
					for (int i = 0; i < list.size(); i++) {
						Object obj = list.get(i).get("id");
						billModel.setBillId(model.getId());
						billModel.setStockId(Long.valueOf(String.valueOf(obj)));
						billService.insertBillItem(billModel);
						billService.updateBillStatus(billModel);
					}
					//修改之前已出票的数据出票状态
					for (int i = 0; i < leftlist.size(); i++) {
						Object obj = leftlist.get(i).get("id");
						billModel.setStockId(Long.valueOf(String.valueOf(obj)));
						billService.updateUnBillStatus(billModel);
					}
				}catch (Exception e){
					logger.error("BillController insertBillItem error：", e);
				}
			}
		}
		catch (Exception e)
		{
			logger.error("BillController add error：", e);
		}
		return msgModel;
	}
	
	/**
	 * 描述信息：修改
	 * 创建时间：2017-07-19 13:24:33
	 * @author WCL (ln_admin@yeah.net)
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/modify", method=RequestMethod.POST)
	public @ResponseBody MsgModel modify(BillModel model, HttpServletRequest request)
	{
		MsgModel msgModel = new MsgModel();
		try
		{
//			setSessionUserId(model, request);
			model.setCuserId(getSessionUserId(request));
			if (billService.update(model) > 0)
			{
				msgModel.setSuccess(GLOBAL_MSG_BOOL_SUCCESS);
			}
	//		billService.updateOrderFinalStatus(model);//获取不到orderId
		}
		catch (Exception e)
		{
			logger.error("BillController modify error：", e);
		}
		return msgModel;
	}
	
	
	/**
	 * 描述信息：删除
	 * 创建时间：2017-07-19 13:24:33
	 * @author WCL (ln_admin@yeah.net)
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/del", method=RequestMethod.POST)
	public @ResponseBody MsgModel delete(BillModel model)
	{
		MsgModel msgModel = new MsgModel();
		try
		{
			if (billService.delete(model) > 0)
			{
				msgModel.setSuccess(GLOBAL_MSG_BOOL_SUCCESS);
			}
		}
		catch (Exception e)
		{
			logger.error("BillController delete error：", e);
		}
		return msgModel;
	}

//	@RequestMapping(value="/queryInoutList")
//	@ResponseBody
//	public List<BillModel> queryInoutList(BillModel model){
//		List<BillModel> list=null;
//		try{
//			if(list==null){
//				list=new ArrayList<>();
//			}
//			list = billService.queryInoutList(model);
//		}catch (Exception e){
//			logger.error("BillController queryInoutList error：", e);
//		}
//		return list;
//	}
	@RequestMapping(value="/queryInoutList", method=RequestMethod.POST)
	@ResponseBody
	public  MsgModel searchInoutList(BillModel model)
	{
		MsgModel msgModel = new MsgModel();
		try
		{
			billService.findPageInout(model, msgModel);
		}
		catch (Exception e)
		{
			logger.error("BillController queryInoutList error：", e);
		}
		return msgModel;
	}


	@RequestMapping(value="/updateTabInoutStock")
	@ResponseBody
	public void updateTabInoutStock(BillModel model) {
		try{
			String str = model.getData();
			List<Map<String, Object>> list = (List<Map<String, Object>>) JSONUtils.parse(str);
			for (int i = 0; i < list.size(); i++) {
				Object obj = list.get(i).get("id");
				model.setId(Long.valueOf(String.valueOf(obj)));
				billService.updateBillStatus(model);
			}
		}catch (Exception e){
			logger.error("BillController updateTabInoutStock error：", e);
		}
	}

	@RequestMapping(value="/queryInoutItemList")
	@ResponseBody
//	public List<BillModel> queryInoutItemList(BillModel model){
//		List<BillModel> list=null;
//		try{
//			if(list==null){
//				list=new ArrayList<>();
//			}
//			list = billService.queryInoutItemList(model);
//		}catch (Exception e){
//			logger.error("BillController queryInoutItemList error：", e);
//		}
//		return list;
//	}
	public  MsgModel searchInoutListItem(BillModel model)
	{
		MsgModel msgModel = new MsgModel();
		try
		{
			billService.findPageBillItem(model, msgModel);
		}
		catch (Exception e)
		{
			logger.error("BillController queryInoutItemList error：", e);
		}
		return msgModel;
	}

	//指定日期加60天
	public static String queryPrePayTime(String time) throws ParseException {
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); // 日期格式
		Date date = dateFormat.parse(time); // 指定日期
		Date newDate = addDate(date, 60); // 指定日期加上60天
		System.out.println(dateFormat.format(date));// 输出格式化后的日期
		System.out.println(dateFormat.format(newDate));
		return dateFormat.format(newDate);
	}
	public static Date addDate(Date date,long day) throws ParseException {
		long time = date.getTime(); // 得到指定日期的毫秒数
		day = day*24*60*60*1000; // 要加上的天数转换成毫秒数
		time+=day; // 相加得到新的毫秒数
		return new Date(time); // 将毫秒数转换成日期
	}
}
