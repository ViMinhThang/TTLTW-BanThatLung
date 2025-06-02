<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.util.*" %>
<%
    Locale locale = (Locale) session.getAttribute("lang");
    if (locale == null) {
        locale = request.getLocale();
        session.setAttribute("lang", locale);
    }

    String contextPath = request.getContextPath();
    String currentPath = request.getRequestURI().substring(contextPath.length()).trim();
%>

<f:setLocale value="${sessionScope.lang}" scope="session" />
<f:setBundle basename="messages" />

<div class="row">
    <h2 class="mt-5 mb-3"><f:message key="nav.my_account"/></h2>
    <div class="d-flex col-12" style="white-space: nowrap;">
        <div class="d-inline-block fw-bold mb-4 <%= currentPath.contains("/account") ? "custom_active" : "" %>"
             style="padding: 12px">
            <a class="text-black custom_size--19"
               href="${pageContext.request.contextPath}/userInfo">
                <f:message key="nav.account"/>
            </a>
        </div>

        <div class="d-inline-block fw-bold <%= currentPath.contains("/profile/") ? "custom_active" : "" %>"
             style="padding: 12px">
            <a class="custom_size--19"
               href="${pageContext.request.contextPath}/userProfile">
                <f:message key="nav.profile"/>
            </a>
        </div>

        <div class="d-inline-block fw-bold <%= currentPath.contains("/address/") ? "custom_active" : "" %>"
             style="padding: 12px">
            <a class="custom_size--19"
               href="${pageContext.request.contextPath}/userAddress?userId=${sessionScope.auth.id}">
                <f:message key="nav.address"/>
            </a>
        </div>

        <div class="d-inline-block fw-bold <%= currentPath.contains("/privacy/") ? "custom_active" : "" %>"
             style="padding: 12px">
            <a class="custom_size--19"
               href="${pageContext.request.contextPath}/userPrivacy">
                <f:message key="nav.privacy"/>
            </a>
        </div>

        <div class="d-inline-block fw-bold <%= currentPath.contains("/orderView/") ? "custom_active" : "" %>"
             style="padding: 12px">
            <a class="custom_size--19"
               href="${pageContext.request.contextPath}/viewOrders">
                <f:message key="nav.orders"/>
            </a>
        </div>
    </div>
</div>
