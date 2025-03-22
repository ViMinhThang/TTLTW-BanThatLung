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
            href="${pageContext.request.contextPath}/css/fontawesome-free-6.6.0-web/fontawesome-free-6.6.0-web/css/all.css"
    />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/general.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/header.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/footer.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/ordersView.css"/>
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
    <div class="row">
        <h2 class="mt-5 mb-3">Tài khoản của tôi</h2>
        <div
                class="d-flex col-12"
                style="white-space: nowrap;"
        >
            <div class="d-inline-block fw-bold mb-4" style="padding: 12px">
                <a
                        class="custom_size--19"
                        href="${pageContext.request.contextPath}/userInfo"
                >Tài khoản</a
                >
            </div>
            <div class="d-inline-block fw-bold " style="padding: 12px">
                <a
                        class="custom_size--19"
                        href="${pageContext.request.contextPath}/userProfile"
                >Hồ sơ</a
                >
            </div>
            <div class="d-inline-block fw-bold" style="padding: 12px">
                <a
                        class="custom_size--19"
                        href="${pageContext.request.contextPath}/userAddress?userId=${sessionScope.auth.id}"
                >Địa chỉ</a
                >
            </div>
            <div class="d-inline-block fw-bold " style="padding: 12px">
                <a
                        class="custom_size--19"
                        href="${pageContext.request.contextPath}/userPrivacy"
                >Riêng tư</a
                >
            </div>
            <div class="d-inline-block fw-bold custom_active" style="padding: 12px">
                <a
                        class="text-white custom_size--19"
                        href="${pageContext.request.contextPath}/viewOrders"
                >Đơn hàng</a
                >
            </div>
        </div>
    </div>

    <div class="container container-sm border rounded">
        <div class="userInfo__content--middle mb-5">
            <h2 class="mt-4 mb-4 fw-light">Các đơn hàng mà bạn đã đặt</h2>
            <c:forEach var="order" items="${orders}">
                <div class="border p-3 container-sm mb-2">
                    <fmt:parseDate value="${order.orderDate}" pattern="yyyy-MM-dd" var="formattedDate"/>
                    <h2 class="fw-light custom_size--19">
                        Ngày đặt hàng: <fmt:formatDate value="${formattedDate}" pattern="dd-MM-yyyy"/>
                    </h2>
                    <h2 class="fw-light custom_size--19">Order#${order.id}</h2>

                    <c:forEach var="item" items="${order.orderDetails}">
                        <div class="container ps-sm-0 border rounded mb-3" id="item1">
                            <div class="row-sm">
                                <div class="col-sm col-md-6 col-lg-12">
                                    <div class="ps-0 row-sm d-lg-flex container-fluid pr-sm-0">
                                        <div
                                                class="col-sm col-md-2 col-lg-2 ps-sm-0 d-flex justify-content-center me-2"
                                        >
                                            <img
                                                    class="img-fluid"
                                                    src="${pageContext.request.contextPath}${item.beltImages[0]}"
                                                    alt="black belt"
                                                    height="200px"
                                            />
                                        </div>
                                        <div
                                                class="col-sm col-md-6 col-lg-10 border-dark ps-sm-0 pr-sm-0 mt-sm-4"
                                        >
                                            <div
                                                    class="d-flex justify-content-between custom_title_container"
                                            >
                                                <h4 class="custom_size--19 m-l3 fw-bold d-lg-none title">
                                                        ${item.beltName}
                                                </h4>
                                                <h4 class="custom_size--19 ml-3 fw-bold d-none d-lg-block">
                                                        ${item.beltName}
                                                </h4>
                                                <h4
                                                        class="d-sm-none d-md-none d-lg-block custom_size--19 fw-light custom_price_container"
                                                >
                                                        ${item.price}₫
                                                </h4>
                                            </div>

                                            <div class="row mt-sm-2 custom_tag_container">
                                                <c:forEach var="category" items="${item.categories}">
                                                      <span
                                                              class="col-sm-1 ms-sm-2 badge bg-light text-dark me-2 tag1"
                                                      >${category}</span
                                                      >
                                                </c:forEach>
                                            </div>
                                            <div class="col-sm col-md-6 col-lg-12 pr-sm-0">
                                                <div
                                                        class="mt-sm-2 mt-lg-3 ms-sm-0 row w-100 custom_quantity_container"
                                                ></div>
                                                <div class="row w-100">
                                                    <p
                                                            class="ms-sm-2 mt-sm-4 mb-sm-4 ps-sm-1 fs-2 d-sm-block d-md-block d-lg-none"
                                                    >
                                                            ${item.price}
                                                    </p>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                <div class="col-sm-4 col-md-6 col-lg-12"></div>
                            </div>
                        </div>

                    </c:forEach>

                    <h2 class="fw-bold custom_size--19">
                        Tổng tiền: ${order.orderTotal} vnđ
                    </h2>
                    <div class="py-2 w-100 d-flex justify-content-between mt-3">
                        <p
                                class="col-sm-10 ms-sm-2 mt-sm-2 mb-sm-2 ps-sm-0 ms-4 ms-2 d-md-none d-lg-none"
                                style="margin-left: 37px"
                        >
                            <strong>Vận chuyển: </strong>nhận hàng từ ${order.shippingDate}
                        </p>
                        <p
                                class="d-sm-none d-md-none d-lg-block col-lg-6 mt-lg-2 fs-6 ps-sm-0 my-1"
                        >
                            <strong>Vận chuyển: </strong>nhận hàng từ ${order.shippingDate}
                        </p>
                        <button
                                class="btn btn-dark custom__btn"
                                data-bs-toggle="modal"
                                data-bs-target="#exampleModal"
                        >
                            Gửi yêu cầu hủy đơn hàng
                        </button>
                    </div>
                </div>

            </c:forEach>
        </div>
    </div>
</div>

<jsp:include page="/frontend/header_footer/footer.jsp"/>

</body>
</html>
