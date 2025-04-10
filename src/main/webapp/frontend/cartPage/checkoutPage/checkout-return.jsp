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
            href="${pageContext.request.contextPath}/css/fontawesome-free-6.6.0-web/fontawesome-free-6.6.0-web/css/all.css"
    />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/general.css"/>

    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/header.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/footer.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/cartPage.css"/>
</head>
<body>
<jsp:include page="/frontend/header_footer/header.jsp"/>

    <div class="container mt-5">
        <div class="row my-3">
            <div class="col-12 col-md-8 col-lg-6">
                <form id="redirectForm" action="/checkout-return" method="POST">
                    <h2>Kết quả giao dịch</h2>
                    <hr>
                    <h3>Thông tin đơn hàng</h3>
                    <ul>
                        <li><strong>Tên:</strong> ${userName}</li>
                        <li><strong>Mã đơn hàng:</strong> ${orderId}</li>
                    </ul>
                    <p><strong>Số tiền thanh toán:</strong> ${total}</p>
                    <p>Trạng thái: ${status}</p>


                    <c:if test="${message == 'success'}">
                        <p>✅ Cảm ơn bạn đã mua hàng!</p>
                    </c:if>
                    <c:if test="${message == 'failed'}">
                        <p>❌ Vui lòng thử lại hoặc chọn phương thức khác.</p>
                    </c:if>

                    <div class="mb-md-5 mt-md-4 pb-5">
                        <a href="${pageContext.request.contextPath}/"
                           class="btn border-dark btn-lg px-5 text-dark"
                        >Quay về trang chủ
                        </a>
                    </div>
                </form>

            </div>
        </div>
    </div>

<jsp:include page="/frontend/header_footer/footer.jsp"/>
</body>
</html>
