<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>Thanh toán đơn hàng</title>
    <link rel="icon" href="${pageContext.request.contextPath}/assets/icons/favicon.svg" type="image/x-icon"/>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous"/>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz" crossorigin="anonymous"></script>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/fontawesome-free-6.6.0-web/fontawesome-free-6.6.0-web/css/all.css"/>
    <script src="${pageContext.request.contextPath}/js/checkout.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/general.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/header.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/footer.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/checkoutPage.css"/>
</head>
<body>
<jsp:include page="/frontend/header_footer/header.jsp"/>

<div class="container my-5">
    <h2 class="fw-bold text-center">Vui lòng xác nhận và gửi đơn đặt hàng của bạn</h2>
    <div class="row my-5">
        <div class="col-lg-8 p-3">
            <h3 class="fw-bold my-3">Thông tin giao hàng</h3>
            <div class="border-top border-bottom py-3">
                <div class="d-flex flex-column justify-content-between">
                    <p class="fs-4">Tên: ${sessionScope.auth.name}</p>
                    <select class="form-select" name="selectedAddress" id="selectedAddress">
                        <c:forEach var="address" items="${userAddresses}">
                            <option value="${address.id}" ${address.isUse == 1 ? 'selected' : ''}>
                                    ${address.addressStreet}, ${address.addressCity} - SĐT: ${sessionScope.auth.phoneNumber}
                            </option>
                        </c:forEach>
                    </select>
                </div>
<%--                <button class="btn btn-outline-dark" id="openFormButton">Thay đổi</button>--%>
            </div>

            <div class="mt-3">
                <h3 class="fw-bold my-3">Phương thức thanh toán</h3>
                <div class="d-inline-flex flex-column" role="group">
                    <c:forEach var="method" items="${paymentMethods}">
                        <c:set var="counter" value="${counter + 1}"/>
                        <div>
                            <input type="radio" name="paymentMethod" id="paymentMethod${method.id}" value="${method.name}" autocomplete="off" ${counter == 1 ? 'checked' : ''} required>
                            <label class="m-2" for="paymentMethod${method.id}">
                                <img class="me-2" src="${pageContext.request.contextPath}/assets/icons/${method.name}.svg" alt="${method.name}"/>
                            </label>
                            ${method.name}
                        </div>
                    </c:forEach>
                </div>
<%--                <div class="d-flex align-items-center">--%>
<%--                    <img class="me-3" src="${pageContext.request.contextPath}/assets/icons/GooglePay.svg" alt="googlePay" style="max-width: 50px"/>--%>
<%--                    <button class="btn btn-outline-dark" id="openFormButtonPay">Thay đổi</button>--%>
<%--                </div>--%>
            </div>
        </div>

<%--        <div id="popupForm" class="popup">--%>
<%--            <div class="popup-content">--%>
<%--                <div class="popup-content--title">--%>
<%--                    <p>Chọn địa chỉ giao hàng</p>--%>
<%--                </div>--%>
<%--                <c:if test="${userAddresses!=null}">--%>
<%--                    <c:forEach var="address" items="${userAddresses}">--%>
<%--                        <input type="hidden" name="userId" value="${sessionScope.auth.id}"/>--%>
<%--                        <input type="hidden" name="addressId" value="${address.id}"/>--%>
<%--                        <c:if test="${address.isUse ==0}">--%>
<%--                            <div class="popup-content--desc">--%>
<%--                                <div class="popup--content--userInfo--action">--%>
<%--                                    <div class="popup--content--userdesc">--%>
<%--                                        <span>Mặc định</span>--%>
<%--                                        <p class="name__field1">${sessionScope.auth.name}</p>--%>
<%--                                        <p class="address__field1">${address.addressStreet}</p>--%>
<%--                                        <p class="city__field1">${address.addressCity}</p>--%>
<%--                                        <p class="number__field1">${sessionScope.auth.phoneNumber}</p>--%>
<%--                                    </div>--%>
<%--                                    <div class="popup--content--right">--%>
<%--                                        <div class="popup--content--button">--%>
<%--                                            <button class="popup--content--submit custom__btn" id="submit1">Giao đến đây</button>--%>
<%--                                        </div>--%>
<%--                                    </div>--%>
<%--                                </div>--%>
<%--                            </div>--%>
<%--                        </c:if>--%>
<%--                    </c:forEach>--%>
<%--                </c:if>--%>
<%--            </div>--%>
<%--        </div>--%>

<%--        <div id="popUpPayment" class="popup">--%>
<%--            <div class="popup-content">--%>
<%--                <c:forEach var="method" items="${paymentMethods}">--%>
<%--                    <c:if test="${method.isActive==1}">--%>
<%--                        <div class="popup-content--desc">--%>
<%--                            <div class="popup--content--userInfo--action">--%>
<%--                                <div class="popup--content--userdesc">--%>
<%--                                    <span class="paymentMethod">--%>
<%--                                        <c:choose>--%>
<%--                                            <c:when test="${method.name=='Delivery'}">Thanh toán khi nhận hàng</c:when>--%>
<%--                                            <c:otherwise>${method.name}</c:otherwise>--%>
<%--                                        </c:choose>--%>
<%--                                    </span>--%>
<%--                                    <img src="${pageContext.request.contextPath}/assets/icons/${method.name}.svg" alt="${method.name}"/>--%>
<%--                                </div>--%>
<%--                                <div class="popup--content--right">--%>
<%--                                    <div class="popup--content--button">--%>
<%--                                        <button class="popup--content--submitPayment">Sử dụng</button>--%>
<%--                                    </div>--%>
<%--                                </div>--%>
<%--                            </div>--%>
<%--                        </div>--%>
<%--                    </c:if>--%>
<%--                </c:forEach>--%>
<%--            </div>--%>
<%--        </div>--%>

        <div class="col-lg-4 bg-light p-4 rounded">
            <h3 class="fw-bold">Tóm tắt đơn hàng</h3>
            <div class="d-flex justify-content-between mb-2 border-top pt-2">
                <p class="mb-0">${cartSize} sản phẩm</p>
                <p class="mb-0">${totalPrice} VNĐ</p>
            </div>
            <div class="d-flex justify-content-between mb-2">
                <p class="mb-0">Vận chuyển</p>
                <p class="mb-0">${shipmentPrice} VNĐ</p>
            </div>
            <div class="border-top pt-2">
                <div class="d-flex justify-content-between fw-bold mb-2">
                    <p class="mb-0">Tổng cộng</p>
                    <p class="mb-0 totalCostDisplay">${grandTotal} VNĐ</p>
                </div>
                <p class="text-muted small mb-0">(bao gồm cả thuế)</p>
            </div>
            <c:choose>
                <c:when test="${userAddresses == null || paymentMethods == null || paymentMethods.isEmpty()||sessionScope.auth==null}">
                    <button class="btn btn-dark mt-4 fs-4 w-100" disabled id="openFormButtonPaymentDisabled">Thanh toán với Google</button>
                </c:when>
                <c:otherwise>
                    <form method="POST" action="${request.context.path}/Order">
                        <input class="submitPaymentMethod" type="hidden" name="paymentMethod" value="GooglePay">
                        <button type="submit" class="btn btn-dark mt-4 fs-4 w-100" id="openFormButtonPayment">Thanh toán với Google</button>
                    </form>
                </c:otherwise>
            </c:choose>
            <div class="input-group mt-4">
                <input type="text" class="form-control rounded-start fs-5" placeholder="Nhập coupon"/>
                <button class="btn btn-dark input-group-text rounded-end">Áp dụng</button>
            </div>
        </div>
    </div>
</div>

<jsp:include page="/frontend/header_footer/footer.jsp"/>
</body>
</html>