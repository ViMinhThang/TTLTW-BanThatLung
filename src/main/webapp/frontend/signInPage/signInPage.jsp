<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>THOMAS - Đăng nhập</title>
    <link rel="icon" href="${pageContext.request.contextPath}/assets/icons/favicon.svg" type="image/x-icon"/>
    <link
            href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
            rel="stylesheet"
            integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
            crossorigin="anonymous"
    />
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/general.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/signInPage.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/footer.css"/>
    <script src="https://www.google.com/recaptcha/api.js" async defer></script>
</head>

<body>
<div class="row p-2 border-bottom border-dark">
    <a
            href="${pageContext.request.contextPath}/index.jsp"
            class="d-sm-none d-md-none d-lg-block d-flex text-dark text-decoration-none fs-4 ms-2"
    >
        THOMAS / Đăng nhập
    </a>
</div>

<div class="container-lg d-flex justify-content-between">
    <form method="POST" class="col-7 mt-5" action="/login">
        <h2 class="custom_size--19 fw-light">Chào mừng bạn trở lại</h2>
        <h2 class="custom_size--19 fw-light">
            Đăng nhập bằng địa chỉ email và mật khẩu
        </h2>
        <div class="ps-0">
            <p class="row justify-content-end text-danger">Thông tin bắt buộc *</p>
            <div class="d-flex flex-column mt-3">
                <p class="mb-1 custom_size--16">Email</p>
                <input
                        name="userEmail"
                        type="email"
                        class="form-control form-control-lg custom_design custom_size--16"
                        placeholder="Nhập Email"
                />
            </div>
            <div class="d-flex flex-column mt-3">
                <p class="mb-1 custom_size--16">Nhập mật khẩu</p>
                <input
                        name="password"
                        type="password"
                        class="form-control form-control-lg custom_design custom_size--16"
                        placeholder="Nhập mật khẩu"
                />
                <c:if test="${not empty errorMessage}">
                    <p class="text-danger mt-2 custom_size--16 mb-0">${errorMessage}</p>
                </c:if>
            </div>
            <div class="g-recaptcha" data-sitekey="6Le5fgQrAAAAAMgyKfVTQAw72R84_TEawwQORoVY"></div>
            <div class="d-flex flex-column mt-3">
                <a
                        class="text-dark"
                        href="${pageContext.request.contextPath}/forgotPassword"
                >Quên mật khẩu</a
                >
                <a
                        class="text-dark"
                        href="${pageContext.request.contextPath}/signup"
                >Đăng ký</a
                >
            </div>
            <div class="d-flex justify-content-end align-items-center mt-5 mb-5">
                <button type="submit"
                        class="btn btn-dark p-2 custom__btn w-50">Đăng nhập
                </button>
            </div>
        </div>
    </form>
    <div class="col-4 my-auto custom_bg--gray border rounded p-5">
        <div class="text-start my-auto">
            <div class="py-4 border-bottom text-left custom_size--13 d-flex align-item-center">
                <img class="me-2" src="${pageContext.request.contextPath}/assets/icons/cart.svg" width="20px"
                     height="20px"/> Theo dõi đơn hàng , mua sắm.
            </div>
            <div class="py-4 border-bottom text-left custom_size--13 d-flex align-item-center">
                <img class="me-2" src="${pageContext.request.contextPath}/assets/icons/user.svg" width="20px"
                     height="20px"/> Quản lý thông tin cá nhân của bạn
            </div>
            <div class="py-4 border-bottom text-left custom_size--13 d-flex align-item-center">
                <img class="me-2" src="${pageContext.request.contextPath}/assets/icons/favorite.svg" width="20px"
                     height="20px"/> Tạo danh sách yêu thích
            </div>
        </div>
    </div>
</div>

<jsp:include page="/frontend/header_footer/footer.jsp"/>

</body>
</html>