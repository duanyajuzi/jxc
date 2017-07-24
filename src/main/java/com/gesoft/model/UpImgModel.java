/**
 * 文件名称：UpImgModel.java
 * 版权所有：Copyright njty
 * 创建时间：2015年2月4日
 * 创 建 人：WCL (ln_admin@yeah.net)
 * 功能描述：
 **/
package com.gesoft.model;

/**
 * 上传图片
 * @author WCL
 * @version v1.001
 * @since   v1.001
 */
public class UpImgModel
{
	private String err = "失败！";
	private String msg = "";
	public String getErr()
	{
		return err;
	}
	public void setErr(String err)
	{
		this.err = err;
	}
	public String getMsg()
	{
		return msg;
	}
	public void setMsg(String msg)
	{
		this.msg = msg;
	}
}
