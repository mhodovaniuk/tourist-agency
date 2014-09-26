<%--
  Created by IntelliJ IDEA.
  User: mychajlo
  Date: 6/18/13
  Time: 4:36 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<c:set var="lang" value="${pageContext.request.locale.language}"/>
<fmt:setLocale value="${lang}_${fn:toUpperCase(lang)}"/>
<fmt:setBundle basename="messages" />
<html>
<head>
    <title></title>
</head>
<body>
<jsp:include page="topbar.jsp"/>
<jsp:include page="message.jsp"/>
 <div class="center">
     <c:url value="/controller" var="confirmUrl" >
         <c:param name="action" value="confirm"/>
         <c:param name="uid" value="${uid}"/>
     </c:url>
     <fmt:message key="totalCost"/>: <c:out value="${cost}"/><br>
     <form action="${confirmUrl}" method="post">
        <input type="submit" value="Confirm">
     </form>
 </div>
<jsp:include page="bottombar.jsp"/>
</body>
</html>