/**
 * 
 */
package com.core.tool.trace;

import java.util.concurrent.LinkedBlockingQueue;

import org.apache.log4j.Logger;

import com.core.app.service.SysLogTraceService;
import com.core.factory.SpringCtx;

/**
 * @author Administrator
 * 
 */
public class SysLogTraceThread extends Thread {
	private static Logger logger = Logger.getLogger(SysLogTraceThread.class);
	private static LinkedBlockingQueue<SysTrace> dataQueue = new LinkedBlockingQueue<SysTrace>();
	private static final SysLogTraceThread sysLogTraceThread = new SysLogTraceThread();
	private SysLogTraceService sysLogTraceService;
	private static boolean isStarted;

	private SysLogTraceThread() {
		sysLogTraceService = (SysLogTraceService) SpringCtx
				.getByBeanName("sysLogTraceService"); 
		this.start();
	}

	public static SysLogTraceThread getInstance() {
		return sysLogTraceThread;
	}

	public void run() {

		while (true) {

			try { 
				SysTrace sysTrace = dataQueue.take();
				sysLogTraceService.logTrace(sysTrace.getUserId(), sysTrace
						.getMenuUrl());
			} catch (Exception e) {
				logger.error(e.getMessage(), e);
			}
		}
	}

	public void addToQueue(SysTrace sysTrace) {
		dataQueue.add(sysTrace);
	}

}