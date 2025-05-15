<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="com.thomas.constant.Iconstant" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>THOMAS - Đăng nhập</title>
    <link rel="icon" href="https://cdn-web-servlet.vercel.app/images/favicon.svg" type="image/x-icon"/>
    <link
            href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
            rel="stylesheet"
            integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
            crossorigin="anonymous"
    />
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.4.0/css/all.min.css">
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
    <link rel="stylesheet" href="https://cdn-web-servlet.vercel.app/css/general.css"/>
    <link rel="stylesheet" href="https://cdn-web-servlet.vercel.app/css/signInPage.css"/>
    <link rel="stylesheet" href="https://cdn-web-servlet.vercel.app/css/footer.css"/>
    <!-- Thêm script reCAPTCHA v3 -->
    <script src="https://www.google.com/recaptcha/api.js?render=6Ld-QAgrAAAAAJTKPkDgBUW7_qoOrzAMiDOocwfR"></script>
    <script>
        grecaptcha.ready(function() {
            grecaptcha.execute('6Ld-QAgrAAAAAJTKPkDgBUW7_qoOrzAMiDOocwfR', {action: 'login'}).then(function(token) {
                document.getElementById('g-recaptcha-response').value = token;
            });
        });
    </script>
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
<%-- khối hiển thị lỗi OAuth --%>
<div class="container-lg">
    <c:if test="${not empty param.error}">
        <div class="alert alert-danger mt-4">
            <c:choose>
                <c:when test="${param.error == 'email_exists'}">
                    ⚠️ Email này đã được đăng ký bằng phương thức thủ công.
                    Vui lòng đăng nhập bằng email/mật khẩu.
                </c:when>
                <c:when test="${param.error == 'google_error'}">
                    ⚠️ Đăng nhập Google thất bại. Vui lòng thử lại.
                </c:when>
            </c:choose>
        </div>
    </c:if>
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
            <input type="hidden" id="g-recaptcha-response" name="g-recaptcha-response">
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
            <div class="d-flex flex-column gap-4 mt-5">
                <!-- Nút Đăng nhập (không bo viền) -->
                <div class="d-flex justify-content-end">
                    <button type="submit"
                            class="btn btn-dark py-3 px-5 custom__btn custom-login-btn w-100 w-lg-50">
                        Đăng nhập
                    </button>
                </div>

                <!-- Phần đăng nhập bằng Google (có viền) -->
                <div class="d-flex flex-column align-items-center gap-3 mt-4">
                    <div class="d-flex align-items-center w-100">
                        <hr class="flex-grow-1">
                        <span class="px-3 custom_size--14 text-muted">Hoặc đăng nhập bằng</span>
                        <hr class="flex-grow-1">
                    </div>

                    <div class="d-flex justify-content-center gap-3">
                        <a href="https://accounts.google.com/o/oauth2/auth?client_id=<%=Iconstant.GOOGLE_CLIENT_ID%>&redirect_uri=<%=Iconstant.GOOGLE_REDIRECT_URI%>&response_type=code&scope=email profile"
                           class="btn btn-floating btn-lg hover-lift custom-google-btn">
                            <i class="fab fa-google"></i>
                        </a>
                    </div>
                    <div class="d-flex justify-content-center gap-3">
                        <a href="https://www.facebook.com/v13.0/dialog/oauth?client_id=<%=Iconstant.FACEBOOK_CLIENT_ID%>&redirect_uri=<%=Iconstant.FACEBOOK_REDIRECT_URI%>&scope=email"
                           class="btn btn-outline-primary btn-floating btn-lg">
                            <i class="fab fa-facebook-f"></i>
                        </a>
                    </div>
                </div>
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