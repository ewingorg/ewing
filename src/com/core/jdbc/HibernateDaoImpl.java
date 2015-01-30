package com.core.jdbc;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.springframework.dao.DataAccessException;
import org.springframework.orm.hibernate3.HibernateCallback;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;
import org.springframework.stereotype.Repository;

import com.core.jdbc.util.BeanListProcessHandler;
import com.core.jdbc.util.Count;
import com.core.jdbc.util.PageBean;

@Repository("baseDao")
public class HibernateDaoImpl extends HibernateDaoSupport implements BaseDao {

	@Override
	public void delete(Object entity) throws DaoException {
		try {
			Method method = entity.getClass().getMethod("getId");
			Object primaryId = method.invoke(entity);
			if (primaryId == null)
				throw new DaoException("the primary key should not be null");
			Object object = findOne((Integer) primaryId, entity.getClass());
			getHibernateTemplate().delete(object);
		} catch (Exception e) {
			throw new DaoException(e);
		}

	}

	@Override
	public <T> List<T> find(String condition, Class<T> entityClass)
			throws DaoException {
		try {
			getHibernateTemplate().setCacheQueries(true);
			List<T> list = getHibernateTemplate().find(
					generateQuerySql(condition, entityClass));
			return list;
		} catch (DataAccessException e) {
			logger.error(e, e);
		}
		return null;
	}

	@Override
	public <T> T findOne(Integer id, Class<T> entityClass) throws DaoException {
		List<T> list = find("id=" + id, entityClass);
		if (!list.isEmpty())
			return (T) list.get(0);
		return null;
	}

	@Override
	public <T> T findOne(String condition, Class<T> entityClass)
			throws DaoException {
		try {
			getHibernateTemplate().setCacheQueries(true);
			List<T> list = getHibernateTemplate().find(
					generateQuerySql(condition, entityClass));
			if (!list.isEmpty())
				return list.get(0);
		} catch (DataAccessException e) {
			logger.error(e, e);
		}
		return null;
	}

	/**
	 * 根本查询条件创建SQL
	 * 
	 * @param condition
	 * @param entityClass
	 * @return
	 */
	private String generateQuerySql(String condition, Class entityClass) {
		return generateQuerySql(condition, "", entityClass);
	}

	/**
	 * 根本查询条件创建SQL
	 * 
	 * @param condition
	 * @param entityClass
	 * @return
	 */
	private String generateQuerySql(String condition, String orderBy,
			Class entityClass) {
		StringBuffer sql = new StringBuffer();
		sql.append(" from ").append(entityClass.getName());
		orderBy = orderBy != null ? " " + orderBy : "";
		if (!StringUtils.isEmpty(condition)) {
			if (condition.trim().startsWith("select"))
				return condition + orderBy;
			if (condition.trim().startsWith("from"))
				return condition + orderBy;

			sql.append(" where 1=1 ");
			if (!(condition.trim().startsWith("and") || condition.trim()
					.startsWith("AND"))) {
				sql.append(" and ");
			}
			sql.append(condition);
		}
		sql.append(orderBy);
		return sql.toString();
	}

	/**
	 * 根本查询条件创建SQL
	 * 
	 * @param condition
	 * @param entityClass
	 * @return
	 */
	private String generateQueryCountSql(String condition, Class entityClass) {
		StringBuffer sql = new StringBuffer();
		sql.append("select * from ").append(entityClass.getName());
		if (!StringUtils.isEmpty(condition))
			sql.append(" where ").append(condition);
		return "select count(*) from ( " + sql.toString() + " )";
	}

	@Override
	public void save(Object entity) throws DaoException {
		bulidEntityTime(entity, true);
		getHibernateTemplate().persist(entity);
	}

	@Override
	public void update(Object entity) throws DaoException {
		bulidEntityTime(entity, false);
		getHibernateTemplate().update(getHibernateTemplate().merge(entity));
	}

	@Override
	public PageBean pageQuery(final String condition, int limit,
			int startIndex, Class entityClass) {
		return pageQuery(condition, "", limit, startIndex, entityClass);
	}

	@Override
	public PageBean pageQuery(final String condition, String orderBy,
			final int limit, final int start, Class entityClass) {
		final String _orderBy = orderBy;
		final Class entity = entityClass;

		Object ps = super.getHibernateTemplate().execute(
				new HibernateCallback() {
					@Override
					public Object doInHibernate(Session s)
							throws HibernateException, SQLException {
						Query rowCountQuery = s.createQuery(generateQuerySql(
								condition, _orderBy, entity));
						int totalCount = rowCountQuery.list().size();
						Query pageQuery = s
								.createQuery(
										generateQuerySql(condition, _orderBy,
												entity)).setMaxResults(limit)
								.setFirstResult(start);
						PageBean ps = new PageBean(start, totalCount, limit,
								pageQuery.list());
						return ps;
					}
				});
		return (PageBean) ps;
	}

	private void bulidEntityTime(Object entity, boolean isNew) {
		Class zzz = entity.getClass();
		Object oldentity = null;
		try {
			Field id = entity.getClass().getDeclaredField("id");
			id.setAccessible(true);
			oldentity = findOne(Integer.valueOf(id.get(entity).toString()),
					entity.getClass());
		} catch (Exception e1) {
			logger.warn("error find id in " + entity.getClass().toString());
		}
		try {
			Field createtime = entity.getClass().getDeclaredField("createTime");
			createtime.setAccessible(true);
			if (isNew) {
				createtime.set(entity, new java.sql.Timestamp(
						(new java.util.Date()).getTime()));
			} else {
				if (oldentity != null) {
					createtime = oldentity.getClass().getDeclaredField(
							"createTime");
					createtime.setAccessible(true);
					createtime.set(entity, createtime.get(oldentity));
				}
			}
		} catch (Exception e) {
			logger.warn("error in bulid  createtime in "
					+ entity.getClass().toString());
		}
		try {

			Field last_update = entity.getClass()
					.getDeclaredField("lastUpdate");
			if (last_update != null) {
				last_update.setAccessible(true);
				last_update.set(entity, new java.sql.Timestamp(
						(new java.util.Date()).getTime()));
			}
		} catch (Exception e) {
			logger.warn("error bulid  last_update in "
					+ entity.getClass().toString());
		}
	}

	@Override
	public <T> T queryObject(String sql, Class<T> queryClass)
			throws DaoException {
		List list = getHibernateTemplate().find(sql);
		if (!list.isEmpty()) {
			if (list.size() > 1)
				throw new DaoException("result more than one , is not legal.");
			return (T) list.get(0);
		}
		return null;
	}

	@Override
	public void executeSql(String sql) throws DaoException {
		try {
			Connection conn = this.getSession().connection();
			Statement stmt = conn.createStatement();
			boolean ret = stmt.execute(sql);
			logger.info("executeSql sql:" + sql + "");
			logger.info("executeSql result:" + ret + "");
		} catch (Exception e) {
			logger.error("fail to execute sql:" + sql, e);
		}

	}

	@Override
	public List executeQuery(String sql) throws DaoException {
		try {
			return this.getSession().createQuery(sql).setCacheable(true).list();
			// return this.getSession().createQuery(sql).list();
		} catch (Exception e) {
			logger.error("fail to execute sql:" + sql, e);
		}
		return null;

	}

	@Override
	public List noMappedObjectQuery(String sql) throws DaoException {
		List list;
		try {
			Connection connection = getConnection();
			ResultSet rs = connection.createStatement().executeQuery(sql);
			list = new ArrayList();
			ResultSetMetaData md = rs.getMetaData();
			int columnCount = md.getColumnCount(); // Map rowData;
			while (rs.next()) { // rowData = new HashMap(columnCount);
				Map rowData = new HashMap();
				for (int i = 1; i <= columnCount; i++) {
					rowData.put(md.getColumnName(i), rs.getObject(i));
				}
				list.add(rowData);
			}
		} catch (SQLException e) {
			throw new DaoException(e);
		}
		return list;

	}

	@Override
	public Connection getConnection() {
		return this.getSession().connection();
	}

	@Override
	public PageBean pageQuery(String sql, String condition, String orderBy,
			int limit, int startIndex, Class entityClass) {
		if (!StringUtils.isEmpty(condition))
			sql += condition;
		if (!StringUtils.isEmpty(orderBy))
			sql += orderBy;
		return pageQuery(sql, limit, startIndex, entityClass);
	}

	@Override
	public <T> PageBean executePageQuery(String sql, int limit, int startIndex,
			Class<T> entityClass) {
		BeanListProcessHandler beanProcess = new BeanListProcessHandler();
		try {
			String sqlCount = "select count(*) as count from ("
					+ sql.toString() + " ) z";
			if (sql.toLowerCase().indexOf(" limit ") == -1)
				sql = sql + " limit " + startIndex + "," + limit;
			Connection conn = this.getSession().connection();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);
			List<T> list = beanProcess.toBeanList(rs, entityClass);
			Statement stmt2 = conn.createStatement();
			ResultSet rs2 = stmt2.executeQuery(sqlCount);
			List<Count> count = beanProcess.toBeanList(rs2, Count.class);
			return new PageBean(startIndex, count.get(0).getCount(), limit,
					list);
		} catch (Exception e) {
			logger.error("fail to execute sql:" + sql, e);
		}
		return null;
	}

}
