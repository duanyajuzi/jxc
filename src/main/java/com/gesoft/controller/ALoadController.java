/**
 * 文件名称：ALoadController.java
 * 版权所有：Copyright njty
 * 创建时间：2015年2月4日
 * 创 建 人：WCL (ln_admin@yeah.net)
 * 功能描述：
 **/
package com.gesoft.controller;

import java.io.File;
import java.io.FileOutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.gesoft.model.UpImgModel;
import com.gesoft.util.FileUtil;
import com.gesoft.util.SystemUtils;

/**
 * 数据加载类
 * @author WCL
 * @version v1.001
 * @since   v1.001
 */
@Controller
@RequestMapping("/admin/load")
public class ALoadController extends BaseController
{
	private static final Logger logger = LoggerFactory.getLogger(ALoadController.class);
	
	
	
	@RequestMapping(value="/fileupload", method=RequestMethod.POST)
	public @ResponseBody UpImgModel fileupload(@RequestParam("filedata") MultipartFile filedata, HttpServletRequest request, HttpServletResponse response)
	{
		UpImgModel mRetModel = new UpImgModel();
		try
		{
			
			String filedataFileName = filedata.getOriginalFilename();
			filedataFileName = SystemUtils.getCurrentSystemTime("yyyyMMddHHmmssSSS") + "_" + filedataFileName;
			String basePath = "/upload/" + FileUtil.createFoldersByType(2);
			//String realpath = request.getSession().getServletContext().getRealPath("/") + "//" + basePath;
			String realpath = 	"E:\\NjtyPro\\eclipse\\yge\\src\\main\\webapp"+ "//" + basePath;
			if (filedata != null)
			{
				File savefile = new File(new File(realpath), filedataFileName);
				if (!savefile.getParentFile().exists())
				{
					savefile.getParentFile().mkdirs();
				}
				IOUtils.copy(filedata.getInputStream(), new FileOutputStream(savefile));
			}
			mRetModel.setErr("");
			mRetModel.setMsg( request.getContextPath() + "/"+basePath + "/" + filedataFileName);
		}
		catch (Exception e)
		{
			logger.error("ALoadController login error：", e);
		}
		response.setContentType("text/html; charset=UTF-8");
		response.setHeader("Cache-Control", "no-cache");
		return mRetModel;
	}
	
}
