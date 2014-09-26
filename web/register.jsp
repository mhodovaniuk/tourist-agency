<%--
  Created by IntelliJ IDEA.
  User: mychajlo
  Date: 6/18/13
  Time: 6:17 PM
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
<form action="/controller?action=register" method="post">
    <fmt:message key="email"/>: <input type="text" name="email"><br>
    <fmt:message key="password"/>: <input type="password" name="password"><br>
    <fmt:message key="confirm_password"/>:<input type="password" name="password2"><br>
    <fmt:message key="first_name"/>: <input type="text" name="firstName"><br>
    <fmt:message key="last_name"/>:<input type="text" name="lastName"><br>
    <input type="submit" value="Register">
</form>
<jsp:include page="bottombar.jsp"/>
</body>
</body>
</html>