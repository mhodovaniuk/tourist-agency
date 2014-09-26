<%--
  Created by IntelliJ IDEA.
  User: mychajlo
  Date: 6/19/13
  Time: 1:17 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="my" uri="/WEB-INF/my.tld"%>
<%@ taglib uri="http://java.sun.com/jstl/fmt_rt" prefix="fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<jsp:useBean id="historyDao"  scope="page" class="ua.database.dao.postgre.PostgreHistoryDAO"/>
<jsp:useBean id="tourDao"  scope="page" class="ua.database.dao.postgre.PostgreTourDAO"/>
<jsp:useBean id="hotelDao"  scope="page" class="ua.database.dao.postgre.PostgreHotelDAO"/>
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
<c:choose>
    <c:when test="${sessionScope.user==null}">
        Please, login!
    </c:when>
    <c:when test="${sessionScope.user.admin}">
        <%-- Add/remove tour block --%>
        <div class="left">
            <form action="/controller?action=addTour" method="post">
                <fmt:message key="hot"/>: <input type="checkbox" name="hot"><br>
                <fmt:message key="hotel_id"/>: <input type="text" name="hotelId"><br>
                <fmt:message key="start_date"/>: <input type="text" name="startDate"><br>
                <fmt:message key="days_count"/>: <input type="text" name="dayCount"><br>
                <fmt:message key="night_count"/>: <input type="text" name="nightCount"><br>
                <fmt:message key="adult_cost"/>: <input type="text" name="adultCost"><br>
                <fmt:message key="children_cost"/>: <input type="text" name="childrenCost"><br>
                <fmt:message key="discount_for_regular_customer"/>: <input type="text" name="discount"><br>
                <input type="submit" value="Add"/>
            </form>
            <form action="/controller?action=removeTour" method="post">
                <fmt:message key="tour_id"/>: <input type="text" name="id">
                <input type="submit" value="Remove">
            </form>
        </div>

        <%-- Add/remove hotel block --%>
        <div class="right">
            <form action="/controller?action=addHotel" method="post">
                <fmt:message key="country"/>: <input type="text" name="country"><br>
                <fmt:message key="city"/>: <input type="text" name="city"><br>
                <fmt:message key="name"/>: <input type="text" name="name"><br>
                <fmt:message key="stars"/>: <input type="text" name="stars"><br>
                <input type="submit" value="Add"/>
            </form>
            <form action="/controller?action=removeHotel" method="post">
                <fmt:message key="hotel_id"/>: <input type="text" name="id">
                <input type="submit" value="Remove">
            </form>
        </div>
        <%-- Tours --%>
        <div class="left">
            <table border="1">
                <tr>
                    <th>ID</th>
                    <th><fmt:message key="hot"/></th>
                    <th><fmt:message key="hotel_id"/></th>
                    <th><fmt:message key="start_date"/></th>
                    <th><fmt:message key="days_count"/></th>
                    <th><fmt:message key="night_count"/></th>
                    <th><fmt:message key="adult_cost"/></th>
                    <th><fmt:message key="children_cost"/></th>
                    <th><fmt:message key="discount_for_regular_customer"/></th>
                </tr>
                <my:print list="${tourDao.getAllTours()}"
                properties="id;hot;hotel.id;startDate;dayCount;nightCount;adultCost;childrenCost;discountForRegularCustomer"/>
            </table>
        </div>
        <%-- Hotels --%>
        <div class="right">
            <table border="1">
                <tr>
                    <th>ID</th>
                    <th><fmt:message key="country"/></th>
                    <th><fmt:message key="city"/></th>
                    <th><fmt:message key="name"/></th>
                    <th><fmt:message key="stars"/></th>
                </tr>
                <my:print list="${hotelDao.getAllHotels()}"
                          properties="id;country;city;name;stars"/>
            </table>
        </div>
    </c:when>
    <c:otherwise>
        <div class="center">
            <table border="1">
                <tr>
                    <th><fmt:message key="country"/></th>
                    <th><fmt:message key="city"/></th>
                    <th><fmt:message key="start_date"/></th>
                    <th><fmt:message key="totalCost"/></th>
                </tr>
                <my:print list="${historyDao.getHistoryByUser(sessionScope.user)}"
                          properties="tour.hotel.country;tour.hotel.city;tour.startDate;totalCost"/>
            </table>
        </div>
    </c:otherwise>
</c:choose>


<jsp:include page="bottombar.jsp"/>
</body>
</html>