package com.ewing.core.aop;

import java.util.HashMap;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;
import org.apache.commons.lang.time.StopWatch;

/**
 * 记录方法的执行时间
 * 
 * @author ljs
 */
public class MethodTimeAdvice implements MethodInterceptor {

	/**
	 * 拦截要执行的目标方法
	 */

	public Object invoke(MethodInvocation invocation) throws Throwable {

		HashMap retMap = null;

		Long functionTime = 0l;// 调用方法时间
		Object result = null;
		// 用 StopWatch 计时
		StopWatch clock = new StopWatch();
		clock.start(); // 计时开始
		try {
			// 方法进行
			result = invocation.proceed();
			clock.stop();// 计时结束
			functionTime = clock.getTime();// 获取方法的运行时间
			if (result instanceof java.util.HashMap) {
				retMap = (HashMap) result;
				// 检查MAP中是否有该异常值,有的话抛出异常
				if (retMap.get("Exception") != null) {
					throw new Exception(String.valueOf(retMap.get("Exception")));
				}
			}

		} catch (Exception e) {

		}

		return result;
	}

}
