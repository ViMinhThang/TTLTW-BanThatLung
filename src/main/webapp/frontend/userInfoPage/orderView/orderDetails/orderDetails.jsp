<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>THOMAS - Xem đơn hàng</title>
    <link
            rel="icon"
            href="${pageContext.request.contextPath}/assets/icons/favicon.svg"
            type="image/x-icon"
    />
    <link
            href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
            rel="stylesheet"
            integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
            crossorigin="anonymous"
    />
    <script
            src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
            crossorigin="anonymous"
    ></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.min.js"></script>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="../../header footer/header__footer.js"></script>
    <link
            rel="stylesheet"
            href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.7.2/css/all.min.css"
    />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/general.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/header.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/footer.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/ordersView.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/userInfo.css"/>
</head>
<body>
<jsp:include page="/frontend/header_footer/header.jsp"/>
<div class="breadcumb__container">
    <nav style="--bs-breadcrumb-divider: '>'" aria-label="breadcrumb">
        <ol class="breadcrumb">
            <li class="breadcrumb-item">
                <a href="${pageContext.request.contextPath}/index.jsp">Trang chủ</a>
            </li>
            <li class="breadcrumb-item active" aria-current="page">Đơn hàng</li>
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
                <h5 class="modal-title" id="exampleModalLabel">Xác nhận</h5>
                <button
                        type="button"
                        class="btn-close"
                        data-bs-dismiss="modal"
                        aria-label="Close"
                ></button>
            </div>
            <div class="modal-body">
                <h4>Bạn có chắc muốn hủy đơn hàng này</h4>
            </div>
            <div class="modal-footer">
                <button
                        type="button"
                        class="btn btn-secondary"
                        data-bs-dismiss="modal"
                >
                    Đóng
                </button>
                <button type="button" class="btn btn-dark">Đồng ý</button>
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
                    Đơn hàn được đặt vào ngày <fmt:formatDate value="${formattedDate}" pattern="dd-MM-yyyy"/>
                </h2>
                <h2 class="p-0 custom_size--19">Chi tiết đơn hàng</h2>
                <div class="row">
                    <div class="col-11">
                        Sản phẩm
                    </div>
                    <div class="col-1 text-end">
                        Tổng
                    </div>
                </div>
                <hr/>
                <c:forEach var="item" items="${userOrder.orderDetails}">
                    <div class="row text-start">
                        <div class="col-11">
                            <img
                                    src="${pageContext.request.contextPath}${item.beltImages[0]}"
                                    alt="Belt Image" height="50px"/> ${item.beltName} x ${item.quantity}
                        </div>
                        <div class="col-1 text-end">
                                ${item.price} vnđ
                        </div>
                    </div>
                    <hr/>
                </c:forEach>
                <div class="row py-3" style="font-size: 20px">
                    <div class="col-8 text-end">
                        Tổng tiền:
                    </div>
                    <div class="col-4 text-end">
                        ${userOrder.orderTotal}
                    </div>
                </div>
                <div class="row py-3" style="font-size: 20px">
                    <div class="col-8 text-end">
                        Nhận hàng từ
                    </div>
                    <div class="col-4 text-end">
                        ${userOrder.shippingDate}
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<jsp:include page="/frontend/header_footer/footer.jsp"/>

</body>
</html>
