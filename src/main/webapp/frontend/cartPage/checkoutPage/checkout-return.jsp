<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Kết quả thanh toán</title>
    <link rel="icon" href="${pageContext.request.contextPath}/assets/icons/favicon.svg" type="image/x-icon"/>
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
    <script src="${pageContext.request.contextPath}/js/cartPage.js"></script>
    <script src="${pageContext.request.contextPath}/js/favorite.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/favorite.css"/>

    <link
            rel="stylesheet"
            href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.7.2/css/all.min.css"
    />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/general.css"/>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/header.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/footer.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/cartPage.css"/>
</head>
<body>
<jsp:include page="/frontend/header_footer/header.jsp"/>

<div class="container my-5">
    <h2 class="fw-bold text-center">Kết quả giao dịch</h2>
    <div class="row my-5">
        <div class="col-lg-12 p-3">
            <h3 class="fw-bold py-2 bg-light text-center">Thông tin đơn hàng</h3>
            <div class="border-top border-bottom py-3">
                <div class="d-flex flex-column justify-content-between">
                    <p class="fs-4">Tên: ${userName}</p>
                    <p class="fs-4">SĐT: ${phoneNumber}</p>
                    <p class="fs-4">Mã đơn hàng: ${orderId}</p>
                    <p class="fs-4">Số tiền thanh toán: ${total}</p>
                    <p class="fs-4">Trạng thái: ${status}</p>
                </div>
            </div>
            <h3 class="fw-bold py-2 bg-light text-center">Địa chỉ giao hàng</h3>
            <div class="row g-3 mb-3">
                <p id="province" class="mb-2">Tỉnh / Thành phố: <strong>${userAddress.provinceName}</strong></p>
                <p id="district" class="mb-2">Quận / Huyện: <strong>${userAddress.districtName}</strong></p>
                <p id="ward" class="mb-2">Phường / Xã: <strong>${userAddress.wardName}</strong></p>
                <p class="mb-0">Địa chỉ chi tiết: <strong>${userAddress.addressDetail}</strong></p>
            </div>

            <div class="d-flex flex-column justify-content-between">
                <c:if test="${message == 'success'}">
                    <p class="fs-4 text-center">✅ Cảm ơn bạn đã mua hàng!</p>
                </c:if>
                <c:if test="${message == 'failed'}">
                    <p class="fs-4 text-center">❌ Vui lòng thử lại hoặc chọn phương thức khác.</p>
                </c:if>
            </div>
        </div>
    </div>
    <div class="mb-md-5 mt-md-4 pb-5 text-center">
        <a href="${pageContext.request.contextPath}/"
           class="btn border-dark btn-lg px-5 text-dark"
        >Quay về trang chủ
        </a>
    </div>
</div>

<jsp:include page="/frontend/header_footer/footer.jsp"/>
</body>
</html>
