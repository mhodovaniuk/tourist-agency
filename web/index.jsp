<%--
  Created by IntelliJ IDEA.
  User: Mychajlo Godovanjuk
  Date: 6/8/13
  Time: 2:22 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<c:set var="lang" value="${pageContext.request.locale.language}"/>
<fmt:setLocale value="${lang}_${fn:toUpperCase(lang)}"/>
<fmt:setBundle basename="messages" />

<jsp:useBean id="tourDao"  scope="page" class="ua.database.dao.postgre.PostgreTourDAO"/>

<html>
  <head>
    <title></title>
  </head>
  <body>
    <jsp:include page="topbar.jsp"/>
    <jsp:include page="message.jsp"/>
    <table>
        <tr>
            <th></th>
            <th><fmt:message key="country"/></th>
            <th><fmt:message key="city"/></th>
            <th><fmt:message key="start_date"/></th>
            <th><fmt:message key="days_count"/></th>
            <th><fmt:message key="night_count"/></th>
            <th><fmt:message key="adult_cost"/></th>
            <th><fmt:message key="children_cost"/></th>
            <th><fmt:message key="discount_for_regular_customer"/></th>
            <th></th>
        </tr>

        <c:forEach var="tour" items="${tourDao.getAllTours()}">
            <tr>
            <td><c:if test="${tour.hot}">
                    <img src="res/hot.png"/>
                </c:if></td>
            <td><c:out value="${tour.hotel.country}"/></td>
            <td><c:out value="${tour.hotel.city}"/></td>
            <td><c:out value="${tour.startDate}"/></td>
            <td><c:out value="${tour.dayCount}"/></td>
            <td><c:out value="${tour.nightCount}"/></td>
            <td><c:out value="${tour.getStrAdultCost()}"/></td>
            <td><c:out value="${tour.getStrChildrenCost()}"/></td>
            <td><c:out value="${tour.getStrDiscountForRegularCustomer()}"/></td>
             <c:url var="buyUrl" value="/buy.jsp">
                 <c:param name="id" value="${tour.id}"></c:param>
             </c:url>
            <td><a href="${buyUrl}"><fmt:message key="buy"/></a></td>
            </tr>
        </c:forEach>
    </table>
    <jsp:include page="bottombar.jsp"/>
  </body>
</html>