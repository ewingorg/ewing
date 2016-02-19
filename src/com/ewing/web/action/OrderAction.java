package com.ewing.web.action;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.axis.utils.StringUtils;
import org.apache.log4j.Logger;

import com.ewing.busi.order.dto.OrderInfoComplex;
import com.ewing.busi.order.dto.OrderQueryReq;
import com.ewing.busi.order.service.OrderService;
import com.ewing.common.constant.OrderStatus;
import com.ewing.common.constant.SysParamCode;
import com.ewing.core.app.action.base.BaseAction;
import com.ewing.core.app.control.SessionException;
import com.ewing.core.jdbc.DaoException;
import com.ewing.core.jdbc.util.PageBean;

/**
 * 订单
 * 
 * @author tansonlam
 * @createDate 2016年2月4日
 * 
 */
public class OrderAction extends BaseAction {
    private static Logger logger = Logger.getLogger(OrderAction.class);
    @Resource
    private OrderService orderService;
    private static final String LIST_PAGE = "/admin/order/orderlist.html";
    private static final String ORDER_DETAIL = "/admin/order/orderdetail.html";
    private static final String ORDER_FRAME_PAGE = "/admin/order/orderframe.html";

    public void show() {
        try {
            Map<String, Object> dataModel = new HashMap<String, Object>();
            String pageStr = request.getParameter("page");
            String pageSizeStr = request.getParameter("pageSize");
            Integer page = StringUtils.isEmpty(pageStr) ? null : Integer.valueOf(pageStr);
            Integer pageSize = StringUtils.isEmpty(pageSizeStr) ? null : Integer
                    .valueOf(pageSizeStr);
            OrderQueryReq orderQueryReq = new OrderQueryReq();
            buildPageData(orderQueryReq);
            orderQueryReq.setStatus(OrderStatus.WAIT_SEND.getStatus());
            orderQueryReq.setPage(page);
            orderQueryReq.setPageSize(pageSize);
            PageBean<OrderInfoComplex> pageBean = orderService.pageQueryOrder(getLoginUserId(),
                    orderQueryReq);
            pageBean.setPageUrl(getPaginationUrl("/Admin-Order-queryOrderList.action"));
            dataModel.put("orderPageBean", pageBean);
            dataModel.put("status", OrderStatus.WAIT_SEND.getStatus());
            dataModel.put("iseffCode", sysParamService.getSysParam(SysParamCode.ISEFF));
            render(ORDER_FRAME_PAGE, dataModel);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }

    /**
     * 查詢订单列表
     */
    public void queryOrderList() {
        try {
            Map<String, Object> dataModel = new HashMap<String, Object>();
            String pageStr = request.getParameter("page");
            String pageSizeStr = request.getParameter("pageSize");
            Integer page = StringUtils.isEmpty(pageStr) ? null : Integer.valueOf(pageStr);
            Integer pageSize = StringUtils.isEmpty(pageSizeStr) ? null : Integer
                    .valueOf(pageSizeStr);
            OrderQueryReq orderQueryReq = new OrderQueryReq();
            buildPageData(orderQueryReq);
            orderQueryReq.setPage(page);
            orderQueryReq.setPageSize(pageSize);
            PageBean<OrderInfoComplex> pageBean = orderService.pageQueryOrder(getLoginUserId(),
                    orderQueryReq);
            pageBean.setPageUrl(getPaginationUrl("/Admin-Order-queryOrderList.action"));
            dataModel.put("orderPageBean", pageBean);
            dataModel.put("iseffCode", sysParamService.getSysParam(SysParamCode.ISEFF));
            render(LIST_PAGE, dataModel);
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }

    /**
     * 订单详情
     */
    public void orderDetail() {
        Map<String, Object> dataModel = new HashMap<String, Object>();
        Integer orderId = getIntegerParameter("orderId");
        try {
            OrderInfoComplex orderComplex = orderService.findOneOrder(getLoginUserId(), orderId);
            dataModel.put("orderComplex", orderComplex);
            renderWithHead(ORDER_DETAIL, dataModel);
        } catch (SessionException e) {
            logger.error(e.getMessage(), e);
        }
    }

}
