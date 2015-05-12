package com.core.jdbc;

import java.util.List;

import org.hibernate.Session;

import com.core.jdbc.util.PageBean;

public interface BaseDao {
	public void delete(Object entity);

	public void executeSql(String sql);

	public <T> List<T> find(String queryString, Class<T> entityClass);

	public <T> T findOne(Integer id, Class<T> entityClass);

	public <T> T findOne(String condition, Class<T> entityClass);

	public <T> PageBean pageQuery(String condition, String orderBy,
			int pageSize, int startIndex, Class<T> entityClass);

	public <T> PageBean pageQuery(String sql, String condition, String orderBy,
			int pageSize, int startIndex, Class<T> entityClass);

	public <T> PageBean pageQuery(String condition, int pageSize,
			int startIndex, Class<T> entityClass);

	public <T> T queryObject(String sql, Class<T> queryClass);

	public void save(Object entity);

	public void update(Object entity); 

	public Session getConnectionSession();
	
	public void releaseConnectionSession(Session session);

	public <T> List<T> noMappedObjectQuery(String sql);

}