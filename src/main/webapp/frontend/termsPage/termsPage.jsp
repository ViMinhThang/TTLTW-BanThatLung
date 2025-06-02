<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>THOMAS Điều Khoản và Điều Kiện</title>
    <link rel="icon" href="${pageContext.request.contextPath}/assets/icons/favicon.svg" type="image/x-icon"/>
    <jsp:include page="/frontend/components/userInfo/cdnLink/userInfoLink.jsp"/>

    <link rel="stylesheet" href="https://cdn-web-servlet.vercel.app/css/termsPage.css"/>
</head>
<body>
<f:setLocale value="${sessionScope.lang}" scope="session" />
<f:setBundle basename="messages" />
<jsp:include page="/frontend/header_footer/header.jsp"/>
<header id="header"></header>
<div class="breadcumb__container">
    <nav style="--bs-breadcrumb-divider: '>'" aria-label="breadcrumb">
        <ol class="breadcrumb">
            <li class="breadcrumb-item"><a href="${pageContext.request.contextPath}/index.jsp"><f:message key="breadcrumb.home"/></a></li>
            <li class="breadcrumb-item active" aria-current="page"><f:message key="breadcrumb.terms"/></li>
        </ol>
    </nav>
</div>
<div class="row justify-content-center" style="margin-top: 118px">
    <div class="d-flex flex-column col-3 border-end">
        <a class="custom_ref border-bottom " href="#temrs_use"><f:message key="terms.nav.usage"/></a>
        <a class="custom_ref border-bottom " href="#terms_rule"><f:message key="terms.nav.general_rules"/></a>
        <a class="custom_ref border-bottom " href="#terms_product"><f:message key="terms.nav.products_services"/></a>
        <a class="custom_ref border-bottom " href="#terms_cart"><f:message key="terms.nav.orders_pricing"/></a>
        <a class="custom_ref border-bottom " href="#term_intel"><f:message key="terms.nav.intellectual_property"/></a>
        <a class="custom_ref border-bottom " href="#term_response"><f:message key="terms.nav.liability_limitation"/></a>
        <a class="custom_ref border-bottom " href="#term_condition"><f:message key="terms.nav.terms_changes"/></a>
        <a class="custom_ref border-bottom " href="#term_tel"><f:message key="terms.nav.contact"/></a>
    </div>
    <div class="col-5 ps-5 mt-5">
        <div class="custom_section" id="temrs_use">
            <h3 class="fs-1 pb-4"><f:message key="terms.title.usage"/></h3>
            <p class="content">
                <f:message key="terms.content.usage"/>
            </p>
        </div>
        <div class="custom_section" id="terms_rule">
            <h2 class="pb-4"><f:message key="terms.title.general_rules"/></h2>
            <p class="content">
                <f:message key="terms.content.general_rules"/>
            </p>
        </div>
        <div class="custom_section" id="terms_product">
            <h2 class="pb-4"><f:message key="terms.title.products_services"/></h2>
            <p class="content">
                <f:message key="terms.content.products_services"/>
            </p>
        </div>
        <div class="custom_section" id="terms_cart">
            <h2 class="pb-4"><f:message key="terms.title.orders_pricing"/></h2>
            <p class="content">
                <f:message key="terms.content.orders_pricing"/>
            </p>
        </div>
        <div class="custom_section" id="term_intel">
            <h2 class="pb-4"><f:message key="terms.title.intellectual_property"/></h2>
            <p class="content">
                <f:message key="terms.content.intellectual_property"/>
            </p>
        </div>
        <div class="custom_section" id="term_response">
            <h2 class="pb-4"><f:message key="terms.title.liability_limitation"/></h2>
            <p class="content">
                <f:message key="terms.content.liability_limitation"/>
            </p>
        </div>
        <div class="custom_section" id="term_condition">
            <h2><f:message key="terms.title.terms_changes"/></h2>
            <p class="content">
                <f:message key="terms.content.terms_changes"/>
            </p>
        </div>
        <div class="custom_section" id="term_tel">
            <h2><f:message key="terms.title.contact"/></h2>
            <p class="content">
                <f:message key="terms.content.contact"/>
            </p>
        </div>
    </div>
</div>
<jsp:include page="/frontend/header_footer/footer.jsp"/>

</body>
</html>