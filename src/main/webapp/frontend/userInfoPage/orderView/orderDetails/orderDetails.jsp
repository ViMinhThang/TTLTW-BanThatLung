<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<f:setLocale value="${sessionScope.lang}" scope="session" />
<f:setBundle basename="messages" />
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>THOMAS - <f:message key="order.details.title"/></title>
    <link
            rel="icon"
            href="${pageContext.request.contextPath}/assets/icons/favicon.svg"
            type="image/x-icon"
    />
    <jsp:include page="/frontend/components/userInfo/cdnLink/userInfoLink.jsp"/>

    <link rel="stylesheet" href="https://cdn-web-servlet.vercel.app/css/ordersView.css"/>
</head>
<body>
<jsp:include page="/frontend/header_footer/header.jsp"/>
<div class="breadcumb__container">
    <nav style="--bs-breadcrumb-divider: '>'" aria-label="breadcrumb">
        <ol class="breadcrumb">
            <li class="breadcrumb-item">
                <a href="${pageContext.request.contextPath}/index.jsp"><f:message key="nav.home"/></a>
            </li>
            <li class="breadcrumb-item active" aria-current="page"><f:message key="order.breadcrumb"/></li>
        </ol>
    </nav>
</div>
<!-- Modal -->
<div
        class="modal fade"
        id="exampleModal"
        tabindex="-1"
        aria-labelledby="exampleModalLabel"
        aria-hidden="true"
>
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel"><f:message key="order.modal.confirm"/></h5>
                <button
                        type="button"
                        class="btn-close"
                        data-bs-dismiss="modal"
                        aria-label="Close"
                ></button>
            </div>
            <div class="modal-body">
                <h4><f:message key="order.modal.cancel_confirm"/></h4>
            </div>
            <div class="modal-footer">
                <button
                        type="button"
                        class="btn btn-secondary"
                        data-bs-dismiss="modal"
                >
                    <f:message key="order.modal.close"/>
                </button>
                <button type="button" class="btn btn-dark"><f:message key="order.modal.agree"/></button>
            </div>
        </div>
    </div>
</div>
<div class="container my-3">
    <jsp:include page="/frontend/components/userInfo/navigationBar/navBar.jsp"/>

    <div class="container-fluid container-sm ps-0" style="width: 1900px">
        <div class="userInfo__content--middle mb-5">

            <div class="p-0 container-sm mb-2">
                <fmt:parseDate value="${userOrder.orderDate}" pattern="yyyy-MM-dd" var="formattedDate"/>
                <h2 class="fw-bold custom_size--19 p-0">
                    <f:message key="order.details.placed_on"/> <fmt:formatDate value="${formattedDate}" pattern="dd-MM-yyyy"/>
                </h2>
                <h2 class="p-0 custom_size--19"><f:message key="order.details.header"/></h2>
                <div class="row">
                    <div class="col-11">
                        <f:message key="order.details.product"/>
                    </div>
                    <div class="col-1 text-end">
                        <f:message key="order.details.total"/>
                    </div>
                </div>
                <hr/>
                <c:forEach var="item" items="${listOrderDetail}">
                    <div class="row text-start">
                        <div class="col-11">
                            <img
                                    src="${pageContext.request.contextPath}${item.beltImages[0]}"
                                    alt="Belt Image" height="50px"/> ${item.beltName} x ${item.quantity}
                        </div>
                        <div class="col-1 text-end">
                                ${item.price} <f:message key="currency.vnd"/>
                        </div>
                    </div>
                    <hr/>
                </c:forEach>
                <div class="row py-3" style="font-size: 20px">
                    <div class="col-8 text-end">
                        <f:message key="order.details.grand_total"/>:
                    </div>
                    <div class="col-4 text-end">
                        ${order.orderTotal}
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<jsp:include page="/frontend/header_footer/footer.jsp"/>

</body>
</html>