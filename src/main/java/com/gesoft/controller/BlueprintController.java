 /**
 * 文件名称：
 * 版权所有：Copyright gesoft
 * 创建时间：2017-06-28 13:21:51
 * 创 建 人：WCL (ln_admin@yeah.net)
 * 功能描述：
 **/
package com.gesoft.controller;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.gesoft.model.GoodCustomerModel;
import com.gesoft.model.LadderPriceModel;
import com.gesoft.service.LadderPriceService;
import com.gesoft.util.Md5Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gesoft.model.BlueprintModel;
import com.gesoft.model.MsgModel;
import com.gesoft.service.BlueprintService;


@Controller
@RequestMapping("/blueprint")
public class BlueprintController extends BaseController
{	
	private static final Logger logger = LoggerFactory.getLogger(BlueprintController.class);
	
	@Resource
	private BlueprintService blueprintService;


	@Resource
	private LadderPriceService ladderPriceService;
	/**
	 * 描述信息：分页查询
	 * 创建时间：2017-06-28 13:21:51
	 * @author WCL (ln_admin@yeah.net)
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/query", method=RequestMethod.POST)
	public @ResponseBody MsgModel search(BlueprintModel model)
	{
		MsgModel msgModel = new MsgModel();
		try
		{
			blueprintService.findPageList(model, msgModel);
		}
		catch (Exception e)
		{
			logger.error("BlueprintController search error：", e);
		}
		return msgModel;
	}
	
	
	/**
	 * 描述信息：增加
	 * 创建时间：2017-06-28 13:21:51
	 * @author WCL (ln_admin@yeah.net)
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public @ResponseBody MsgModel add(BlueprintModel model, HttpServletRequest request) {
		MsgModel msgModel = new MsgModel();
		String id = Md5Util.UUID();
		model.setId(id);
		try {
			setSessionUserId(model, request);
			if (blueprintService.save(model) > 0) {
				msgModel.setSuccess(GLOBAL_MSG_BOOL_SUCCESS);
				addItem(model);
			}
		}
		catch (Exception e)
		{
			logger.error("BlueprintController add error：", e);
		}
		return msgModel;
	}

	/**
	 * 描述信息：新增订单子项
	 * @return
	 */
	public void addItem(BlueprintModel model) {
		MsgModel msgModel = new MsgModel();
		try {
			String Id = model.getId();
			String data = model.getData();
			JSONArray jsArr = JSONArray.parseArray(data);
			for(Object obj : jsArr){
				LadderPriceModel ladderPriceModel = new LadderPriceModel();
				ladderPriceModel.setBlueprint_id(Id);
				JSONObject jsonObject = JSONObject.parseObject(obj.toString());
				ladderPriceModel.setPrice(Double.parseDouble(jsonObject.get("price").toString()));
				ladderPriceModel.setNum(Long.parseLong(jsonObject.get("num").toString()));
				ladderPriceService.save(ladderPriceModel);
			}
			msgModel.setSuccess(GLOBAL_MSG_BOOL_SUCCESS);
		}catch (Exception e) {
			logger.error("BlueprintController modify error：", e);
		}
	}

	/**
	 * 描述信息：修改
	 * 创建时间：2017-06-28 13:21:51
	 * @author WCL (ln_admin@yeah.net)
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/modify", method=RequestMethod.POST)
	public @ResponseBody MsgModel modify(BlueprintModel model, HttpServletRequest request) {
		MsgModel msgModel = new MsgModel();
		try {
			setSessionUserId(model, request);
			if (blueprintService.update(model) > 0) {
				msgModel.setSuccess(GLOBAL_MSG_BOOL_SUCCESS);
				editItem(model);
			}
		}catch(Exception e){
			logger.error("BlueprintController modify error：", e);
		}
		return msgModel;
	}


	/**
	 * 描述信息：修改订单子项
	 * @return
	 */
	public void editItem(BlueprintModel model) {
		MsgModel msgModel = new MsgModel();
		try {
			String Id = model.getId();
			String data = model.getData();
			JSONArray jsArr = JSONArray.parseArray(data);
			for(Object obj : jsArr){
				LadderPriceModel ladderPriceModel = new LadderPriceModel();
				ladderPriceModel.setBlueprint_id(Id);
				JSONObject jsonObject = JSONObject.parseObject(obj.toString());
				ladderPriceModel.setPrice(Double.parseDouble(jsonObject.get("price").toString()));
				ladderPriceModel.setNum(Long.parseLong(jsonObject.get("num").toString()));
				String state = jsonObject.get("_state").toString();
				if("added".equals(state)){
					ladderPriceService.save(ladderPriceModel);
				}else if("modified".equals(state)){
					ladderPriceModel.setId(Long.parseLong(jsonObject.get("id").toString()));
					ladderPriceService.update(ladderPriceModel);
				}else if("removed".equals(state)){
					ladderPriceModel.setId(Long.parseLong(jsonObject.get("id").toString()));
					ladderPriceService.delete(ladderPriceModel);
				}
			}
			msgModel.setSuccess(GLOBAL_MSG_BOOL_SUCCESS);
		}catch (Exception e) {
			logger.error("BlueprintController modify error：", e);
		}
	}



	/**
	 * 描述信息：删除
	 * 创建时间：2017-06-28 13:21:51
	 * @author WCL (ln_admin@yeah.net)
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/del", method=RequestMethod.POST)
	public @ResponseBody MsgModel delete(BlueprintModel model)
	{
		MsgModel msgModel = new MsgModel();
		try
		{
			if (blueprintService.delete(model) > 0)
			{
				msgModel.setSuccess(GLOBAL_MSG_BOOL_SUCCESS);
			}
		}
		catch (Exception e)
		{
			logger.error("BlueprintController delete error：", e);
		}
		return msgModel;
	}

}
