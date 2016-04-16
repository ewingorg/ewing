package com.ewing.core.app.action.base;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import com.ewing.busi.system.service.SysParamService;
import com.ewing.core.app.control.SessionControl;
import com.ewing.core.app.control.SessionException;
import com.ewing.core.app.service.BaseModelService;
import com.ewing.core.app.service.CacheModelService;
import com.ewing.core.factory.SysParamFactory;
import com.ewing.core.jdbc.DaoException;
import com.ewing.core.jdbc.util.PageBean;
import com.ewing.core.template.FreeMarkerTool;
import com.ewing.util.DataFormat;
import com.ewing.util.StringUtil;
import com.google.gson.Gson;
import com.opensymphony.xwork2.ActionSupport;

/**
 * @author tanson lin
 * 
 * @create:2012-2-23
 * @description: action的父类，实现了构造器传入的Dao的增删改查的基本操作。
 */

public class BaseAction extends ActionSupport implements ServletRequestAware, ServletResponseAware {
    private static Logger logger = Logger.getLogger(BaseAction.class);
    private static final long serialVersionUID = 1L;
    public HttpServletRequest request;
    public HttpServletResponse response;
    public String condition;
    private final static String SEARCH_CODE = "_QUERY";
    private final static String SEARCH_ORDER_CODE = "_ORDERBY";
    public String contextPath;
    @Resource
    public BaseModelService baseModelService;
    @Resource
    public CacheModelService cacheModelService;
    @Resource
    public FreeMarkerConfigurer freeMarkerConfigurer;
    @Resource
    public SysParamService sysParamService;

    protected final static Gson gson = new Gson();

    public BaseAction() {

    }

    public void setServletRequest(HttpServletRequest request) {
        this.request = request;
        try {
            request.setCharacterEncoding("utf-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
    }

    public void setServletResponse(HttpServletResponse response) {
        this.response = response;
        this.response.setContentType("text/html;charset=UTF-8");
    }

    /**
     * 获取登陆用户ID
     * 
     * @return
     * @throws SessionException
     */
    public Integer getLoginUserId() throws SessionException {
        return SessionControl.getUserId(request);
    }

    /**
     * 分页查询
     * 
     * @throws ActionException
     */
    public <T> PageBean<T> pageQuery(Class<T> entityClass) throws ActionException {
        if (entityClass == null)
            throw new ActionException("entityClass must be defined in Action");
        String page = request.getParameter("page");
        String pageSize = request.getParameter("pageSize");
        PageBean<T> pageBean = baseModelService.pageQuery(bulidConditionSql(), bulidOrderBySql(),
                Integer.valueOf(pageSize), Integer.valueOf(page), entityClass);
        request.setAttribute("pageBean", pageBean);
        return pageBean;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    /**
     * dao查询
     * 
     * @throws ActionException
     * @throws DaoException
     */
    public <T> List<T> query(Class<T> entityClass) throws ActionException, DaoException {
        if (entityClass == null)
            throw new ActionException("entityClass must be defined in Action");
        List<T> resultList = baseModelService.find(condition, entityClass);
        request.setAttribute("resultList", resultList);
        return resultList;
    }

    /**
     * dao缓存查询
     * 
     * @throws ActionException
     * @throws DaoException
     */
    public <T> List<T> queryByCache(Class<T> entityClass) throws ActionException, DaoException {
        if (entityClass == null)
            throw new ActionException("entityClass must be defined in Action");
        List<T> resultList = cacheModelService.find(condition, entityClass);
        request.setAttribute("resultList", resultList);
        return resultList;

    }

    /**
     * dao新增
     * 
     * @throws ActionException
     */

    public <T> Boolean save(Class<T> entityClass) throws ActionException {
        try {
            if (entityClass == null)
                throw new ActionException("entityClass must be defined in Action");
            T t = entityClass.newInstance();
            this.buildPageData(t);
            baseModelService.save(t);
            request.setAttribute("result", "success");
            return true;
        } catch (Exception e) {
            logger.error(e, e);
            request.setAttribute("result", "fail");
            return false;
        }
    }

    /**
     * dao修改
     * 
     * @throws ActionException
     */
    public <T> Boolean update(Class<T> entityClass) throws ActionException {
        try {
            if (entityClass == null)
                throw new ActionException("entityClass must be defined in Action");
            T t = entityClass.newInstance();
            this.buildPageData(t);
            baseModelService.update(t);
            request.setAttribute("result", "success");
            return true;
        } catch (Exception e) {
            logger.error(e, e);
            request.setAttribute("result", "fail");
            return false;
        }
    }

    /**
     * dao删除
     * 
     * @throws ActionException
     */
    public <T> Boolean delete(Class<T> entityClass) throws ActionException {
        try {
            if (entityClass == null)
                throw new ActionException("entityClass must be defined in Action");
            T t = entityClass.newInstance();
            this.buildPageData(t);
            baseModelService.delete(t);
            request.setAttribute("result", "success");
            return true;
        } catch (Exception e) {
            logger.error(e, e);
            request.setAttribute("result", "fail");
            return false;
        }
    }

    public <T> T findOne(Integer id, Class<T> entityClass) throws DaoException {
        return (T) baseModelService.findOne(id, entityClass);
    }

    /**
     * 从页面拷贝数据值到dao中.
     * 
     * @throws IllegalArgumentException
     * @throws IllegalAccessException
     */
    public <T> T buildPageData(Object entityBean) throws IllegalArgumentException,
            IllegalAccessException {
        Field[] fields = entityBean.getClass().getDeclaredFields();
        Map paramMap = request.getParameterMap();
        // Map paramMap = new HashMap();
        // paramMap.put("name", "dada");
        for (Field f : fields) {
            f.setAccessible(true);
            String fieldType = f.getType().getName();
            String fName = f.getName();
            if (!paramMap.containsKey(fName))
                continue;
            Object fValue = paramMap.get(fName);
            if (fValue.getClass().isArray()) {

                Object[] sValue = (Object[]) fValue;
                String value = sValue[0].toString();
                // If empty ,set null to field.
                if (value.trim().isEmpty())
                    f.set(entityBean, null);
                if (value.trim().isEmpty())
                    continue;
                if (fieldType.equals("java.lang.String")) {
                    f.set(entityBean, value);
                } else if (fieldType.equals("java.lang.Integer") || fieldType.equals("int")) {
                    f.set(entityBean, Integer.valueOf(value));
                } else if (fieldType.equals("java.lang.Float") || fieldType.equals("float")) {
                    f.set(entityBean, Float.valueOf(value));
                } else if (fieldType.equals("java.util.Date")) {
                    if (value.length() == 10) {
                        f.set(entityBean, DataFormat.stringToDate(value, DataFormat.DATE_FORMAT));
                    } else if (value.length() == 19) {
                        f.set(entityBean,
                                DataFormat.stringToDate(value, DataFormat.DATETIME_FORMAT));
                    }
                } else if (fieldType.equals("java.sql.Timestamp")) {
                    if (value.length() == 10) {
                        f.set(entityBean,
                                new java.sql.Timestamp(DataFormat.stringToDate(value,
                                        DataFormat.DATE_FORMAT).getTime()));
                    } else if (value.length() == 19) {
                        f.set(entityBean,
                                new java.sql.Timestamp(DataFormat.stringToDate(value,
                                        DataFormat.DATETIME_FORMAT).getTime()));
                    }
                }

            }

        }
        return (T) entityBean;
    }

    public String bulidConditionSql() {
        StringBuffer sql = new StringBuffer();
        Map<String, String> queryMap = new HashMap<String, String>();
        String orderSql = "";
        Map paramMap = request.getParameterMap();
        for (Iterator itor = paramMap.keySet().iterator(); itor.hasNext();) {
            Object key = itor.next();
            Object object = paramMap.get(key);
            if (key instanceof String && key.toString().startsWith(SEARCH_CODE) && object != null) {
                String queryKey = String.valueOf(key).substring(SEARCH_CODE.length());
                if (object.getClass().isArray()) {
                    Object[] sValue = (Object[]) object;
                    String value = sValue[0].toString();
                    if (value.trim().isEmpty())
                        continue;
                    queryMap.put(queryKey, value);
                }

            }
            if (key instanceof String && key.toString().startsWith(SEARCH_ORDER_CODE)
                    && object != null) {
                if (object.getClass().isArray()) {
                    Object[] sValue = (Object[]) object;
                    String value = sValue[0].toString();
                    if (value.trim().isEmpty())
                        continue;
                    orderSql = value;
                }
            }
        }
        String queryMapSql = baseModelService.buildSQLWhere(queryMap);
        if (!StringUtils.isEmpty(condition)) {
            sql.append(condition);
        }
        if (!StringUtils.isEmpty(queryMapSql)) {
            sql.append(queryMapSql);
        }

        return sql.toString();
    }

    public String bulidOrderBySql() {
        String orderSql = "";
        Map paramMap = request.getParameterMap();
        for (Iterator itor = paramMap.keySet().iterator(); itor.hasNext();) {
            Object key = itor.next();
            Object object = paramMap.get(key);

            if (key instanceof String && key.toString().startsWith(SEARCH_ORDER_CODE)
                    && object != null) {
                if (object.getClass().isArray()) {
                    Object[] sValue = (Object[]) object;
                    String value = sValue[0].toString();
                    if (value.trim().isEmpty())
                        continue;
                    orderSql = value;
                }
            }
        }
        return orderSql;
    }

    /**
     * 返回組裝的JSON信息到页面
     * 
     * @param responseData
     */
    public void outResult(ResponseData responseData) {
        if (responseData == null)
            throw new IllegalArgumentException("responseData should not be null");
        try {
            String json = gson.toJson(responseData);
            logger.debug(json);
            response.getWriter().write(json);
        } catch (IOException e) {
            logger.error(e.getMessage(), e);
        }
    }

    public void forward(String page) {
        forward(page, null);
    }

    public <T> void forward(String page, Map<String, T> dataModel) {
        if (dataModel != null) {
            for (String key : dataModel.keySet()) {
                request.setAttribute(key, dataModel.get(key));
            }
        }
        try {
            request.getRequestDispatcher(page).forward(request, response);

        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }

    public <T> void forwardAsContent(String page, String container, Map<String, T> dataModel) {
        if (dataModel != null) {
            for (String key : dataModel.keySet()) {
                request.setAttribute(key, dataModel.get(key));
            }
        }
        request.setAttribute("includejsp", page);
        String actionUrl = "/" + container;
        try {
            request.getRequestDispatcher(actionUrl).forward(request, response);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }

    /**
     * 使用模板渲染
     * 
     * @param template 模板名稱
     * @param container 页面容器
     * @param dataModel 模板參數
     */
    public <T> void render(String template, String container, Map<String, T> dataModel) {
        if (dataModel != null)
            dataModel.put("includejsp", (T) template);
        render(container, dataModel);
    }

    /**
     * 使用模板渲染,并且选择加载头部css和js
     * 
     * @param template
     * @param dataModel
     */
    public <T> void renderWithHead(String template, Map dataModel) {
        String hl = request.getParameter("hl");
        if (hl != null && !Boolean.valueOf(hl))
            dataModel.put("hl", false);
        else
            dataModel.put("hl", true);
        render(template, dataModel);
    }

    /**
     * 使用模板渲染
     * 
     * @param template 模板名稱
     * @param dataModel 模板參數
     */
    public <T> void render(String template, Map<String, T> dataModel) {
        try {
            if (dataModel == null)
                dataModel = new HashMap<String, T>();

            dataModel.put("contextPath", (T) getContextPath(request));
            // 模板中传入session值
            HttpSession session = request.getSession();
            String[] sessionName = session.getValueNames();
            for (String name : sessionName) {
                dataModel.put(name, (T) request.getSession().getAttribute(name));
            }
            // 模板中传入request值
            Map paramMap = request.getParameterMap();
            for (Iterator itor = paramMap.keySet().iterator(); itor.hasNext();) {
                Object key = itor.next();
                Object object = paramMap.get(key);
                if (key instanceof String && object != null) {

                    if (object.getClass().isArray()) {
                        Object[] sValue = (Object[]) object;
                        String value = sValue[0].toString();
                        if (value.trim().isEmpty())
                            continue;
                        dataModel.put(key.toString(), (T) value);
                    }
                }

            }
            String result = FreeMarkerTool.getSingleton().getTemplateResult(template, dataModel);
            response.getWriter().write(result);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }

    private String getContextPath(HttpServletRequest request) {
        if (contextPath != null)
            return contextPath;
        String port = request.getServerPort() == 80 || request.getServerPort() == 0 ? "" : ":"
                + request.getServerPort();
        contextPath = request.getScheme() + "://" + request.getServerName() + port
                + request.getContextPath();
        return contextPath;
    }

    /**
     * 分页url
     * 
     * @param paginationUrl
     * @return
     */
    public String getPaginationUrl(String paginationUrl) {
        String url = SysParamFactory.WEB_CONTEXT_PATH
                + (paginationUrl.startsWith("/") ? paginationUrl : "/" + paginationUrl);
        return url.indexOf("?") > -1 ? url : url + "?aqwertyu=123";
    }

    public String getUTFParameter(String key) {
        String value = request.getParameter(key);
        if (StringUtil.isEmpty(value) || (value != null && value.equals("undefined")))
            return null;
        try {
            return StringUtil.iso2Utf8(value);
        } catch (UnsupportedEncodingException e) {
            logger.error(e);
        }
        return null;
    }

    public Integer getIntegerParameter(String key) {
        String value = request.getParameter(key);
        if (StringUtil.isEmpty(value) || (value != null && value.equals("undefined")))
            return null;
        return Integer.valueOf(value);
    }

}
