package com.ewing.core.jbpm;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.ewing.core.jbpm.model.FlowTask;
import com.ewing.core.jbpm.model.FlowTaskTransition;
import com.ewing.core.jdbc.BaseDao;
import com.ewing.core.jdbc.DaoException;

/**
 * 流程任务定义服务类
 * 
 */
@Repository("flowTaskService")
public class FlowTaskService {
	@Resource
	private BaseDao baseDao;

	/**
	 * 获取流程中所有任务
	 * 
	 * @param processName
	 * @return
	 * @throws DaoException
	 */
	public List<FlowTask> queryAllFlow(String processName) throws DaoException {
		return baseDao.find("process_name='" + processName + "'",
				FlowTask.class);
	}

	/**
	 * 获取流程定义的任务设置
	 * 
	 * @param processName
	 * @return
	 * @throws DaoException
	 */
	public FlowTask getTask(String processName, int taskId) throws DaoException {
		List<FlowTask> list = baseDao.find("process_name='" + processName
				+ "' and id=" + taskId, FlowTask.class);
		if (list.isEmpty())
			return null;
		return list.get(0);
	}

	/**
	 * 获取流程定义的任务设置
	 * 
	 * @param processName
	 * @return
	 * @throws DaoException
	 */
	public FlowTask getTask(String processName, String taskName)
			throws DaoException {
		List<FlowTask> list = baseDao.find("process_name='" + processName
				+ "' and task_name='" + taskName + "'", FlowTask.class);
		if (list.isEmpty())
			return null;
		return list.get(0);
	}

	/**
	 * 获取流程定义的任务设置
	 * 
	 * @param processName
	 * @return
	 * @throws DaoException
	 */
	public FlowTask getStartTask(String processName) throws DaoException {
		List<FlowTask> list = baseDao.find(" id = (select min(id) from "
				+ FlowTask.class.getName() + " a where a.processName='"
				+ processName + "')", FlowTask.class);
		if (list.isEmpty())
			return null;
		return list.get(0);
	}

	/**
	 * 获取流程任务的执行转变设置
	 * 
	 * @param processName
	 * @param taskName
	 * @return
	 * @throws DaoException
	 */
	public List<FlowTaskTransition> getTaskTransition(String processName,
			int taskId) throws DaoException {
		FlowTask flowTask = getTask(processName, taskId);
		if (flowTask == null)
			return null;
		List<FlowTaskTransition> transList = baseDao.find("task_id=" + taskId,
				FlowTaskTransition.class);
		return transList;
	}

	/**
	 * 获取流程任务的执行转变设置
	 * 
	 * @param processName
	 * @param taskName
	 * @return
	 * @throws DaoException
	 */
	public List<FlowTaskTransition> getTaskTransition(String processName,
			String taskName) throws DaoException {
		FlowTask flowTask = getTask(processName, taskName);
		if (flowTask == null)
			return null;
		List<FlowTaskTransition> transList = baseDao.find("task_id="
				+ flowTask.getId(), FlowTaskTransition.class);
		return transList;
	}

	public FlowTaskTransition getTaskTransition(String processName, int taskId,
			String transitionName) throws DaoException {
		FlowTask flowTask = getTask(processName, taskId);
		if (flowTask == null)
			return null;
		List<FlowTaskTransition> transList = baseDao.find("task_id=" + taskId
				+ " and name='" + transitionName + "'",
				FlowTaskTransition.class);
		if (transList.isEmpty())
			return null;
		return transList.get(0);
	}

	public FlowTask getTask(String processName, int taskId,
			String transitionName) throws DaoException {
		FlowTaskTransition flowTaskTransition = this.getTaskTransition(
				processName, taskId, transitionName);
		return getTask(processName, flowTaskTransition.getToId());
	}

	public FlowTask getTask(String processName, String taskName,
			String transitionName) throws DaoException {
		FlowTask curTask = this.getTask(processName, taskName);
		FlowTaskTransition flowTaskTransition = this.getTaskTransition(
				processName, curTask.getId(), transitionName);
		return getTask(processName, flowTaskTransition.getToId());
	}

}
