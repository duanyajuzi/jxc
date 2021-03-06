/**
 * 文件名称：BaseModel.java
 * 版权所有：Copyright gesoft
 * 创建时间：2015年1月22日
 * 创 建 人：WCL (ln_admin@yeah.net)
 * 功能描述：
 **/
package com.gesoft.model;

import java.io.Serializable;

import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

/**
 * @author WCL
 * @version v1.001
 * @since   v1.001
 */
public class BaseModel implements Serializable
{
	private static final long serialVersionUID = -3889995062892270518L;
	protected static final String DATE_FORMAT = "yyyy-MM-dd";
	protected static final String TIME_FORMAT = "HH:mm:ss";
	protected static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";
	protected static final String TIMESTAMP_FORMAT = "yyyy-MM-dd HH:mm:ss.S";
	//状态位
	public int state; 
	//消息key,value
	public String msgKey;
	public String msgValue;
	
	// 用户相关
	private long userId; // 用户ID
	private int userType; // 用户类型；0平台用户；1系统用户
	
	// 分页传入参数
	private int page = 1; // 当前页号
	private int rows; // 每页条数
	private long total; // 总记录数
	private long startNum; // 开始记录数
	private long endNum; // 结束记录数

	private int pageIndex;
	private int pageSize;
	private String sortColumns;

	public int getPageIndex() {
		return pageIndex;
	}

	public void setPageIndex(int pageIndex) {
		this.pageIndex = pageIndex;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getState()
	{
		return state;
	}
	public void setState(int state)
	{
		this.state = state;
	}
	public String getMsgKey()
	{
		return msgKey;
	}
	public void setMsgKey(String msgKey)
	{
		this.msgKey = msgKey;
	}
	public String getMsgValue()
	{
		return msgValue;
	}
	public void setMsgValue(String msgValue)
	{
		this.msgValue = msgValue;
	}
	public long getUserId()
	{
		return userId;
	}
	public void setUserId(long userId)
	{
		this.userId = userId;
	}
	public int getUserType()
	{
		return userType;
	}
	public void setUserType(int userType)
	{
		this.userType = userType;
	}
	public int getPage()
	{
		return page;
	}
	public void setPage(int page)
	{
		this.page = page;
	}
	public int getRows()
	{
		return rows;
	}
	public void setRows(int rows)
	{
		this.rows = rows;
	}
	public long getTotal()
	{
		return total;
	}
	public void setTotal(long total)
	{
		this.total = total;
	}
	public long getStartNum()
	{
		return startNum;
	}
	public void setStartNum(long startNum)
	{
		this.startNum = startNum;
	}
	public long getEndNum()
	{
		return endNum;
	}
	public void setEndNum(long endNum)
	{
		this.endNum = endNum;
	}

	public String getSortColumns() {
		return sortColumns;
	}

	public void setSortColumns(String sortColumns) {
		this.sortColumns = sortColumns;
	}

	public String toString()
	{
		return ToStringBuilder.reflectionToString(this,ToStringStyle.SHORT_PREFIX_STYLE);
	}
}
