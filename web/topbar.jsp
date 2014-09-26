<%--
  Created by IntelliJ IDEA.
  User: mychajlo
  Date: 6/10/13
  Time: 10:49 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<c:set var="lang" value="${pageContext.request.locale.language}"/>
<fmt:setLocale value="${lang}_${fn:toUpperCase(lang)}"/>
<fmt:setBundle basename="messages" />
<div class="right">
<c:choose>
    <c:when test="${user != null}">
        <div class="right">
            <fmt:message key="hello"/>, <c:out value="${user.firstName}"/> <c:out value="${user.lastName}"/>.<br>
        <a href="/cabinet.jsp"><fmt:message key="cabinet"/></a><a href="/controller?action=logout">Log Out</a>
        </div>
    </c:when>
    <c:otherwise>
        <div class="right">
        <a href="/login.jsp"> <fmt:message key="log_in"/></a>
        <a href="/register.jsp"> <fmt:message key="register"/></a>
        </div>
    </c:otherwise>
</c:choose>
</div>
