 /**
 * 文件名称：
 * 版权所有：Copyright gesoft
 * 创建时间：2017-06-28 12:01:42
 * 创 建 人：WCL (ln_admin@yeah.net)
 * 功能描述：
 **/
package com.gesoft.controller;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.gesoft.model.GoodsModel;
import com.gesoft.model.MsgModel;
import com.gesoft.service.GoodsService;

import java.util.ArrayList;
import java.util.List;


 @Controller
@RequestMapping("/goods")
public class GoodsController extends BaseController
{	
	private static final Logger logger = LoggerFactory.getLogger(GoodsController.class);
	
	@Resource
	private GoodsService goodsService;
	

	/**
	 * 描述信息：分页查询
	 * 创建时间：2017-06-28 12:01:42
	 * @author WCL (ln_admin@yeah.net)
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/query", method=RequestMethod.POST)
	public @ResponseBody MsgModel search(GoodsModel model)
	{
		MsgModel msgModel = new MsgModel();
		try
		{
			goodsService.findPageList(model, msgModel);
		}
		catch (Exception e)
		{
			logger.error("GoodsController search error：", e);
		}
		return msgModel;
	}
	
	
	/**
	 * 描述信息：增加
	 * 创建时间：2017-06-28 12:01:42
	 * @author WCL (ln_admin@yeah.net)
	 * @param model
	 * @param request
	 * @return
	 */
	@RequestMapping(value="/add", method=RequestMethod.POST)
	public @ResponseBody MsgModel add(GoodsModel model, HttpServletRequest request)
	{
		MsgModel msgModel = new MsgModel();
		try
		{
			setSessionUserId(model, request);
			if (goodsService.save(model) > 0)
			{
				msgModel.setSuccess(GLOBAL_MSG_BOOL_SUCCESS);
			}
		}
		catch (Exception e)
		{
			logger.error("GoodsController add error：", e);
		}
		return msgModel;
	}

	
	/**
	 * 描述信息：修改
	 * 创建时间：2017-06-28 12:01:42
	 * @author WCL (ln_admin@yeah.net)
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/modify", method=RequestMethod.POST)
	public @ResponseBody MsgModel modify(GoodsModel model, HttpServletRequest request)
	{
		MsgModel msgModel = new MsgModel();
		try
		{
			setSessionUserId(model, request);
			if (goodsService.update(model) > 0)
			{
				msgModel.setSuccess(GLOBAL_MSG_BOOL_SUCCESS);
			}
		}
		catch (Exception e)
		{
			logger.error("GoodsController modify error：", e);
		}
		return msgModel;
	}
	
	
	/**
	 * 描述信息：删除
	 * 创建时间：2017-06-28 12:01:42
	 * @author WCL (ln_admin@yeah.net)
	 * @param model
	 * @return
	 */
	@RequestMapping(value="/del", method=RequestMethod.POST)
	public @ResponseBody MsgModel delete(GoodsModel model)
	{
		MsgModel msgModel = new MsgModel();
		try
		{
			if (goodsService.delete(model) > 0)
			{
				msgModel.setSuccess(GLOBAL_MSG_BOOL_SUCCESS);
			}
		}
		catch (Exception e)
		{
			logger.error("GoodsController delete error：", e);
		}
		return msgModel;
	}

	@RequestMapping(value = "/queryGoodsList")
	@ResponseBody
	public List<GoodsModel> searchGoodsInfo(GoodsModel model){
		List<GoodsModel> list=null;
		try {
		list = goodsService.queryGoodsInfo(model);
		if (list == null) {
			list = new ArrayList();
		}
		}catch (Exception e){
			logger.error("GoodsController queryGoodsInfo error：", e);
		}
		return list;
	}

	@RequestMapping(value = "/queryMaterialNum")
	@ResponseBody
	public List<GoodsModel> queryMaterialNumList(GoodsModel model){
		List<GoodsModel> list=null;
		try{
			list=goodsService.queryMaterialNum(model);
			if(list==null){
				list=new ArrayList<>();
			}
		}catch (Exception e){
			logger.error("GoodsController queryMaterialNum error：", e);
		}
		return list;
	}
	
	
	@RequestMapping(value = "/queryMaterialNum2")
	@ResponseBody
	public List<GoodsModel> queryMaterialNumList2(GoodsModel model){
		List<GoodsModel> list=null;
		try{
			list=goodsService.queryMaterialNum2(model);
			if(list==null){
				list=new ArrayList<>();
			}
		}catch (Exception e){
			logger.error("GoodsController queryMaterialNum error：", e);
		}
		return list;
	}

	@RequestMapping(value = "updateStorage")
	@ResponseBody
	public void updateStorageByMaterialNum(GoodsModel model){
		try{
			goodsService.updateStorage(model);
		}
		catch (Exception e) {
			logger.error("GoodsController updateStorageByMaterialNum error：", e);
		}
	}

}
