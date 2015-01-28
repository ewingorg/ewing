<%@ page language="java" pageEncoding="UTF-8"%>
<%@ include file="/common/include/head.jsp"%>
<%@ include file="/common/include/html_head.jsp"%>
<div>
 <span>attribut:</span>
${message}
</div>

<div>
 <span>attribut list:</span>
  <c:forEach var="item" items="${users}" varStatus="status">
        <br> ${status.count}：${item.userName}, ${item.phone}
</c:forEach>
</div> 



<jsp:include page="${includejsp}"/>
<%@ include file="/common/include/html_bottom.jsp"%>
