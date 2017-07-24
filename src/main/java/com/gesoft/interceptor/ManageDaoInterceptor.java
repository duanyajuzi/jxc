/**
 * 文件名称：ManageDaoInterceptor.java
 * 版权所有：Copyright gesoft
 * 创建时间：2012-07-19
 * 创 建 人：wangcl (ln_admin@yeah.net)
 * 功能描述：
 **/
package com.gesoft.interceptor;

import com.gesoft.util.Constants;
import com.gesoft.util.SystemUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.annotation.Resource;
import javax.sql.DataSource;

/**
 * AOP日志操作功能拦截
 * @author wangcl
 * @version v1.001
 * @since   v1.001
 */
@Aspect
public class ManageDaoInterceptor implements Constants
{
	private static final Logger logger = LoggerFactory.getLogger(ManageDaoInterceptor.class);
	
	@Resource
	private DataSource dataSource;
	
	
	public ManageDaoInterceptor() 
	{  
		
	}
	 
	 
	/**
	 * 描述信息：
	 * 创建时间：2012-07-19 13:47:20
	 * @author wangcl (ln_admin@yeah.net)
	 */
	//@Pointcut("!execution (* com.gesoft.service.ALoginLogsService.*(..)) && execution (* com.gesoft.service..*(..)) || execution (* com.gesoft.common.EntityService.*(..))")
	@Pointcut("execution (* com.gesoft.service..*(..)) || execution (* com.gesoft.common.EntityService.*(..))")
	public void anyMethods()
	{
	}

	
	/**
	 * 描述信息：
	 * 创建时间：2012-07-19 13:47:26
	 * @author wangcl (ln_admin@yeah.net)
	 * @param pjp
	 * @return
	 * @throws Throwable
	 */
	@Around("anyMethods()")
	public Object doBasicProfiling(ProceedingJoinPoint pjp) throws Throwable
	{
		long mStartTimer = System.currentTimeMillis();
		Object result = pjp.proceed();
		try
		{/*
			LogModel model = new LogModel();
			RequestAttributes requestAttributes = RequestContextHolder.getRequestAttributes();
			HttpSession session = ((ServletRequestAttributes)requestAttributes).getRequest().getSession();
			//ServletWebRequest servletContainer = (ServletWebRequest)RequestContextHolder.getRequestAttributes();
	    	//HttpServletRequest request = servletContainer.getRequest();
	    	//HttpServletResponse response = servletContainer.getResponse();
			if (session != null && session.getAttribute(SESSION_KEY_UID) != null)
			{
				model.setUserId(Long.parseLong(session.getAttribute(SESSION_KEY_UID).toString()));
			}
			model.setOperClass(pjp.getTarget().getClass().toString());
			model.setOperMed(pjp.getSignature().getName());
			model.setStime(SystemUtils.getCurrentSystemTime());

			Object[] mObjs = pjp.getArgs();
			if (mObjs != null)
			{
				for (Object object : mObjs)
				{
					if (model.getOperParam() == null)
					{
						model.setOperParam(object.toString());
					}
					model.setOperParam(model.getOperParam() + ", "+ object.toString());
				}
			}*/
			//saveUserOperLogsInfo(model);
		}
		catch (Exception e)
		{
			logger.error("ManageDaoInterceptor doBasicProfiling error：", e);
		}
		SystemUtils.SOUT(SystemUtils.getCurrentSystemTime()+ "  "+pjp.getSignature().getName() + "方法所耗时长为："+(System.currentTimeMillis() - mStartTimer)+" MS");
		return result;
	}
	
	/**
	 * 描述信息：后置通知
	 * 创建时间：2015年1月30日 下午5:01:20
	 * @author WCL (ln_admin@yeah.net)
	 * @param result
	 */
	@AfterReturning(pointcut = "anyMethods()", returning = "result")
	public void doAfterReturning(String result)
	{
		
	}
	
	/**
	 * 描述信息：最终通知
	 * 创建时间：2015年1月30日 下午5:01:12
	 * @author WCL (ln_admin@yeah.net)
	 */
	@After("anyMethods()")
	public void doAfter()
	{
		
	}
}
