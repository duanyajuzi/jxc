 /**
 * 文件名称：
 * 版权所有：Copyright gesoft
 * 创建时间：2017-06-28 11:51:13
 * 创 建 人：WCL (ln_admin@yeah.net)
 * 功能描述：
 **/
package com.gesoft.controller;


import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import com.gesoft.model.PowerModel;
import com.gesoft.model.MsgModel;
import com.gesoft.service.PowerService;
import java.util.ArrayList;
import java.util.List;


 @Controller
@RequestMapping("/power")
public class PowerController extends BaseController {
	 private static final Logger logger = LoggerFactory.getLogger(PowerController.class);

	 @Resource
	 private PowerService powerService;


	 /**
	  * 描述信息：分页查询
	  * 创建时间：2017-06-28 11:51:13
	  * @author WCL (ln_admin@yeah.net)
	  * @param model
	  * @return
	  */
//	@RequestMapping(value="/query", method=RequestMethod.POST)
//	public @ResponseBody MsgModel search(PowerModel model)
//	{
//		MsgModel msgModel = new MsgModel();
//		try
//		{
//			powerService.findPageList(model, msgModel);
//		}
//		catch (Exception e)
//		{
//			logger.error("PowerController search error：", e);
//		}
//		return msgModel;
//	}


	 /**
	  * 描述信息：增加
	  * 创建时间：2017-06-28 11:51:13
	  *
	  * @param model
	  * @param request
	  * @return
	  * @author WCL (ln_admin@yeah.net)
	  */
	 @RequestMapping(value = "/add", method = RequestMethod.POST)
	 public
	 @ResponseBody
	 MsgModel add(PowerModel model, HttpServletRequest request) {
		 MsgModel msgModel = new MsgModel();
		 try {
			 setSessionUserId(model, request);
			 if (powerService.save(model) > 0) {
				 msgModel.setSuccess(GLOBAL_MSG_BOOL_SUCCESS);
			 }
		 } catch (Exception e) {
			 logger.error("PowerController add error：", e);
		 }
		 return msgModel;
	 }


	 /**
	  * 描述信息：修改
	  * 创建时间：2017-06-28 11:51:13
	  *
	  * @param model
	  * @return
	  * @author WCL (ln_admin@yeah.net)
	  */
	 @RequestMapping(value = "/modify", method = RequestMethod.POST)
	 public
	 @ResponseBody
	 MsgModel modify(PowerModel model, HttpServletRequest request) {
		 MsgModel msgModel = new MsgModel();
		 try {
			 setSessionUserId(model, request);
			 if (powerService.update(model) > 0) {
				 msgModel.setSuccess(GLOBAL_MSG_BOOL_SUCCESS);
			 }
		 } catch (Exception e) {
			 logger.error("PowerController modify error：", e);
		 }
		 return msgModel;
	 }


	 /**
	  * 描述信息：删除
	  * 创建时间：2017-06-28 11:51:13
	  *
	  * @param model
	  * @return
	  * @author WCL (ln_admin@yeah.net)
	  */
	 @RequestMapping(value = "/del", method = RequestMethod.POST)
	 public
	 @ResponseBody
	 MsgModel delete(PowerModel model) {
		 MsgModel msgModel = new MsgModel();
		 try {
			 if (powerService.delete(model) > 0) {
				 msgModel.setSuccess(GLOBAL_MSG_BOOL_SUCCESS);
			 }
		 } catch (Exception e) {
			 logger.error("PowerController delete error：", e);
		 }
		 return msgModel;
	 }

	 @RequestMapping(value = "/queryList")
	 public
	 @ResponseBody
	 List<PowerModel> queryPowerList(PowerModel model) {
		 List<PowerModel> list = null;//查询父节点
		 try {
			 list = powerService.queryList(model);
			 if (list == null) {
				 list = new ArrayList();
			 }
			 PowerModel powerModel = new PowerModel();
			 powerModel.setId(1);
			 powerModel.setName1("所有权限");
			 list.add(powerModel);
		 } catch (Exception e) {
			 logger.error("PowerController queryList error: ", e);
		 }
		 return list;
	 }

	 @RequestMapping(value = "/queryPowerNoNum")
	 @ResponseBody
	 public int queryPowerNoNum(PowerModel model) {
		 int i = 0;
		 try {
			 i = powerService.queryPowerNo(model);
		 } catch (Exception e) {
			 logger.error("PowerController queryPowerNoNum error: ", e);
		 }
		 return i;
	 }


	 //
//	@RequestMapping(value = "/queryAllList",method = RequestMethod.POST)
//	public @ResponseBody List<PowerModel> queryAllList(PowerModel model){
//		List<PowerModel> list1=new ArrayList();//查询父节点
//		try{
//			list1=powerService.queryList1(model);
//		}catch (Exception e){
//			logger.error("PowerController query error: ",e);
//		}
//		for(PowerModel childlist:list1){
//			if(childlist.getId()!=null) {
//				List<PowerModel> list2=new ArrayList();//查询子节点
//				model.setId(childlist.getId());
//				list2 = powerService.queryList2(model);
//				childlist.setChildren(list2);
//			}
//		}
//		return list1;
//	}

 }
