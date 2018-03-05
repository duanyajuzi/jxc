package com.gesoft.controller;


 import com.alibaba.fastjson.JSONArray;
 import com.gesoft.model.LadderPriceModel;
 import com.gesoft.model.MsgModel;
 import com.gesoft.model.OrderItemModel;
 import com.gesoft.service.LadderPriceService;
 import org.slf4j.Logger;
 import org.slf4j.LoggerFactory;
 import org.springframework.stereotype.Controller;
 import org.springframework.web.bind.annotation.RequestMapping;
 import org.springframework.web.bind.annotation.RequestMethod;
 import org.springframework.web.bind.annotation.ResponseBody;

 import javax.annotation.Resource;
 import javax.servlet.http.HttpServletRequest;


 @Controller
 @RequestMapping("/ladderPrice")
 public class LadderPriceController extends BaseController {
     private static final Logger logger = LoggerFactory.getLogger(LadderPriceController.class);

     @Resource
     private LadderPriceService ladderPriceService;


     /**
      * 描述信息：分页查询
      * @param model
      * @return
      */
     @RequestMapping(value="/query", method=RequestMethod.POST)
     public @ResponseBody MsgModel search(LadderPriceModel model) {
         MsgModel msgModel = new MsgModel();
         try{
             ladderPriceService.findPageList(model, msgModel);
         }catch (Exception e) {
             logger.error("LadderPriceController search error：", e);
         }
         return msgModel;
     }



     /**
      * 描述信息：列表查询
      * @param model
      * @return
      */
     @RequestMapping(value="/getList", method=RequestMethod.GET)
     public @ResponseBody MsgModel getList(LadderPriceModel model) {
         MsgModel msgModel = new MsgModel();
         try {
             msgModel.setData(ladderPriceService.findList(model));
             msgModel.setSuccess(GLOBAL_MSG_BOOL_SUCCESS);
         } catch (Exception e) {
             logger.error("LadderPriceController search error：", e);
         }
         return msgModel;
     }

     /**
      * 描述信息：增加
      * @param model
      * @param request
      * @return
      */
     @RequestMapping(value="/add", method=RequestMethod.POST)
     public @ResponseBody MsgModel add(LadderPriceModel model, HttpServletRequest request){
         MsgModel msgModel = new MsgModel();
        /*String data = model.();
        JSONArray jsArr = JSONArray.parseArray(data);*/
         try {
             setSessionUserId(model, request);
             if (ladderPriceService.save(model) > 0) {
                 msgModel.setSuccess(GLOBAL_MSG_BOOL_SUCCESS);
             }
         }catch (Exception e) {
             logger.error("LadderPriceController add error：", e);
         }
         return msgModel;
     }


     /**
      * 描述信息：修改
      * @param model
      * @return
      */
     @RequestMapping(value="/modify", method=RequestMethod.POST)
     public @ResponseBody MsgModel modify(LadderPriceModel model, HttpServletRequest request) {
         MsgModel msgModel = new MsgModel();
         try{
             setSessionUserId(model, request);
             if (ladderPriceService.update(model) > 0) {
                 msgModel.setSuccess(GLOBAL_MSG_BOOL_SUCCESS);
             }
         }catch (Exception e) {
             logger.error("LadderPriceController modify error：", e);
         }
         return msgModel;
     }


     /**
      * 描述信息：删除
      * @param model
      * @return
      */
     @RequestMapping(value="/del", method=RequestMethod.POST)
     public @ResponseBody MsgModel delete(LadderPriceModel model) {
         MsgModel msgModel = new MsgModel();
         try {
             if (ladderPriceService.delete(model) > 0) {
                 msgModel.setSuccess(GLOBAL_MSG_BOOL_SUCCESS);
             }
         }catch (Exception e) {
             logger.error("LadderPriceController delete error：", e);
         }
         return msgModel;
     }

 }
