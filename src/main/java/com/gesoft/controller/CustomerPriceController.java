package com.gesoft.controller;


import com.gesoft.model.CustomerPriceModel;
import com.gesoft.model.MsgModel;
import com.gesoft.service.CustomerPriceService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;


@Controller
@RequestMapping("/customerPrice")
public class CustomerPriceController extends BaseController {
    private static final Logger logger = LoggerFactory.getLogger(CustomerPriceController.class);

    @Resource
    private CustomerPriceService customerPriceService;


    /**
     * 描述信息：分页查询
     * @param model
     * @return
     */
    @RequestMapping(value="/query", method=RequestMethod.POST)
    public @ResponseBody MsgModel search(CustomerPriceModel model) {
        MsgModel msgModel = new MsgModel();
        try{
            customerPriceService.findPageList(model, msgModel);
        }catch (Exception e) {
            logger.error("CustomerPriceController search error：", e);
        }
        return msgModel;
    }



    /**
     * 描述信息：列表查询
     * @param model
     * @return
     */
    @RequestMapping(value="/getList", method=RequestMethod.GET)
    public @ResponseBody MsgModel getList(CustomerPriceModel model) {
        MsgModel msgModel = new MsgModel();
        try {
            msgModel.setData(customerPriceService.findList(model));
            msgModel.setSuccess(GLOBAL_MSG_BOOL_SUCCESS);
        } catch (Exception e) {
            logger.error("CustomerPriceController search error：", e);
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
    public @ResponseBody MsgModel add(CustomerPriceModel model, HttpServletRequest request){
        MsgModel msgModel = new MsgModel();
        try {
            setSessionUserId(model, request);
            if (customerPriceService.save(model) > 0) {
                msgModel.setSuccess(GLOBAL_MSG_BOOL_SUCCESS);
            }
        }catch (Exception e) {
            logger.error("CustomerPriceController add error：", e);
        }
        return msgModel;
    }


    /**
     * 描述信息：修改
     * @param model
     * @return
     */
    @RequestMapping(value="/modify", method=RequestMethod.POST)
    public @ResponseBody MsgModel modify(CustomerPriceModel model, HttpServletRequest request) {
        MsgModel msgModel = new MsgModel();
        try{
            setSessionUserId(model, request);
            if (customerPriceService.update(model) > 0) {
                msgModel.setSuccess(GLOBAL_MSG_BOOL_SUCCESS);
            }
        }catch (Exception e) {
            logger.error("CustomerPriceController modify error：", e);
        }
        return msgModel;
    }


    /**
     * 描述信息：删除
     * @param model
     * @return
     */
    @RequestMapping(value="/del", method=RequestMethod.POST)
    public @ResponseBody MsgModel delete(CustomerPriceModel model) {
        MsgModel msgModel = new MsgModel();
        try {
            if (customerPriceService.delete(model) > 0) {
                msgModel.setSuccess(GLOBAL_MSG_BOOL_SUCCESS);
            }
        }catch (Exception e) {
            logger.error("CustomerPriceController delete error：", e);
        }
        return msgModel;
    }

}
