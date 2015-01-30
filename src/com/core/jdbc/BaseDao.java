package com.core.jdbc;

import java.sql.Connection;
import java.util.List;

import com.core.jdbc.util.PageBean;

public interface BaseDao {
	public void delete(Object entity) throws DaoException;

	public <T> PageBean executePageQuery(String sql, int limit, int startIndex,
			Class<T> entityClass) throws DaoException;

	public List executeQuery(String sql) throws DaoException;

	public void executeSql(String sql) throws DaoException;

	public <T> List<T> find(String queryString, Class<T> entityClass)
			throws DaoException;

	public <T> T findOne(Integer id, Class<T> entityClass) throws DaoException;

	public <T> T findOne(String condition, Class<T> entityClass)
			throws DaoException;

	public Connection getConnection();

	public List noMappedObjectQuery(String sql) throws DaoException;

	public PageBean pageQuery(String condition, int pageSize, int startIndex,
			Class entityClass);

	public PageBean pageQuery(String condition, String orderBy, int pageSize,
			int startIndex, Class entityClass);

	public PageBean pageQuery(String sql, String condition, String orderBy,
			int pageSize, int startIndex, Class entityClass);

	public <T> T queryObject(String sql, Class<T> queryClass)
			throws DaoException;

	public void save(Object entity) throws DaoException;

	public void update(Object entity) throws DaoException;
}