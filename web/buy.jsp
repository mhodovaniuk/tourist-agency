<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: mychajlo
  Date: 6/18/13
  Time: 3:35 PM
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
      <c:url value="/controller" var="buyUrl" >
          <c:param name="id" value="${param.id}"/>
          <c:param name="action" value="buy"/>
      </c:url>

<div class="center">
<form action="${buyUrl}" method="post">
    <fmt:message key="adult_count"/> <input type="text" name="adultCount"><br>
    <fmt:message key="children_count"/> <input type="text" name="childrenCount"> <br>
    <input type="submit" value="Buy">
</form>
</div>
<jsp:include page="bottombar.jsp"/>
</body>
</html>