<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>Thông tin của bạn</title>
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
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/privacy.css"/>
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
            <li class="breadcrumb-item active" aria-current="page">Riêng tư</li>
        </ol>
    </nav>
</div>
<div class="container my-3">
    <jsp:include page="/frontend/components/userInfo/navigationBar/navBar.jsp"/>

    <div class="container mb-5 ps-0">
        <div class="userInfo__content--middle">
            <div class="userInfo--aboutyou">
                <h2 class="fs-3">Cài đặt quyền riêng tư</h2>
                <h3 class="mt-2 mb-1 fs-3">Khả năng tìm kiếm</h3>
                <div class="toogle__option d-flex align-items-center">
                    <p class="flex-shrink-1 fs-4" style="width: 95%">
                        Bạn có muốn người khác có thể tìm thấy bạn bằng địa chỉ email
                        của bạn không? Địa chỉ email của bạn sẽ không được hiển thị công
                        khai.
                    </p>
                    <div class="toggle-switch flex-grow-1 d-flex justify-content-end">
                        <input type="checkbox" id="toggle"/>
                        <label for="toggle" class="switch">
                            <span class="slider"></span>
                            <span class="checkmark">✔</span>
                        </label>
                    </div>
                </div>

                <div class="userInfoAll">
                    <div class="userInfo--element">
                        <h3 class="fs-4">Sự đồng ý</h3>
                        <p class="fs-4">
                            Thomas sử dụng cookie và các công nghệ tương tự để mang lại
                            cho bạn trải nghiệm tốt hơn, cho phép những điều như:
                        </p>
                        <ul class="pl-3 fs-4">
                            <li>chức năng trang web cơ bản</li>
                            <li>đảm bảo giao dịch an toàn, bảo mật</li>
                            <li>đăng nhập tài khoản an toàn</li>
                            <li>ghi nhớ tài khoản, trình duyệt và tùy chọn khu vực</li>
                            <li>ghi nhớ cài đặt quyền riêng tư và bảo mật</li>
                            <li>phân tích lưu lượng truy cập và sử dụng trang web</li>
                            <li>tìm kiếm, nội dung và đề xuất được cá nhân hóa</li>
                            <li>giúp người bán hiểu khán giả của họ</li>
                            <li>
                                hiển thị quảng cáo liên quan, được nhắm mục tiêu trên và
                                ngoài Etsy
                            </li>
                        </ul>
                    </div>
                    <div class="userInfo--elements">
                        <h3 class="fs-2">
                            Cookie & Công nghệ cần thiết
                        </h3>
                        <p class="fs-4">
                            Một số công nghệ chúng tôi sử dụng là cần thiết cho các chức
                            năng quan trọng như bảo mật và tính toàn vẹn của trang web,
                            xác thực tài khoản, tùy chọn bảo mật và quyền riêng tư, dữ
                            liệu sử dụng và bảo trì trang web nội bộ, và để làm cho trang
                            web hoạt động chính xác cho việc duyệt và giao dịch.
                        </p>
                    </div>
                    <div class="userInfo--element">
                        <a href="${pageContext.request.contextPath}/frontend/userInfoPage/account/userinfo.jsp">
                            <button>Lưu cài đặt</button>
                        </a>
                    </div>
                </div>
            </div>
            <div class="userInfo--aboutyou userInfo--aboutyou--location">
                <div class="userInfo__location--desc">
                    <h2>Đóng và xóa vĩnh viễn tài khoản Thomas của bạn</h2>
                    <p class="fs-4">
                        Đóng và xóa vĩnh viễn tài khoản của bạn. Một khi đã xóa, tài
                        khoản của bạn không thể được khôi phục.
                        <span>Tìm hiểu thêm. </span>
                    </p>
                </div>
                <div class="userInfo--element">
                    <button
                            class="p-2 rounded"
                            style="background-color: white"
                            data-bs-toggle="modal"
                            data-bs-target="#exampleModal"
                    >
                        Yêu cầu xóa tài khoản của bạn
                    </button>
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
                                    <h5 class="modal-title" id="exampleModalLabel">
                                        Xác nhận
                                    </h5>
                                    <button
                                            type="button"
                                            class="btn-close"
                                            data-bs-dismiss="modal"
                                            aria-label="Close"
                                    ></button>
                                </div>
                                <div class="modal-body fs-4 fw-bold">
                                    <p style="font-size: 32px;">Bạn có chắc muốn xóa tài khoản</p>
                                </div>
                                <div class="modal-footer">
                                    <button
                                            type="button"
                                            class="btn btn-secondary"
                                            data-bs-dismiss="modal"
                                    >
                                        Hủy
                                    </button>
                                    <form action="${pageContext.request.contextPath}/userPrivacy" method="post">
                                        <input type="hidden" name="userId" value="${sessionScope.auth.id}">
                                        <button type="submit" class="btn btn-dark rounded">
                                            Xóa
                                        </button>
                                    </form>

                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<jsp:include page="/frontend/header_footer/footer.jsp"/>

</body>
</html>
