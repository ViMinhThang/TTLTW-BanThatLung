<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>THOMAS - Đặt lại mật khẩu</title>
    <link
            rel="icon"
            href="https://cdn-web-servlet.vercel.app/images/favicon.svg"
            type="image/x-icon"
    />
    <link
            href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
            rel="stylesheet"
            integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
            crossorigin="anonymous"
    />
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
    <link rel="stylesheet" href="https://cdn-web-servlet.vercel.app/css/general.css"/>
    <link rel="stylesheet" href="https://cdn-web-servlet.vercel.app/css/footer.css"/>
    <link rel="stylesheet" href="https://cdn-web-servlet.vercel.app/css/resetPasswordPage.css"/>
</head>
<body>
<div class="row p-2 border-bottom border-dark">
    <a
            href="${pageContext.request.contextPath}/index.jsp"
            class="d-sm-none d-md-none d-lg-block d-flex text-dark text-decoration-none fs-4 ms-2"
    >
        THOMAS / Quên mật khẩu
    </a>
</div>
<div
        class="container-lg d-flex mt-5 justify-content-between"
        style="margin-bottom: 200px"
>
    <form id="passwordUpdate" method="POST" action="/resetPassword" class="col-7 mt-5">
        <input type="hidden" name="token" value="${param.token}"/>
        <h2 class="custom_size--19 fw-light">Đặt lại mật khẩu</h2>
        <h2 class="custom_size--19 fw-light">
            Nhập mật khẩu mới của bạn.
        </h2>
        <div class="ps-0">
            <div class="d-flex flex-column mt-3">
                <p class="mb-1 custom_size--16">Mật khẩu</p>
                <input
                        name="password"
                        type="password"
                        class="form-control form-control-lg custom_design custom_size--16 newPassword"
                        placeholder="Nhập mật khẩu"
                />
            </div>
            <div class="d-flex flex-column mt-3 retypePassword">
                <p class="mb-1 custom_size--16">Nhập lại Mật khẩu</p>
                <input
                        name="retypePassword"
                        type="password"
                        class="form-control form-control-lg custom_design custom_size--16 newPasswordRetype"
                        placeholder="Nhập lại mật khẩu"
                />
            </div>
            <div class="d-flex justify-content-end align-items-center mt-5 mb-5">
                <button class="btn btn-dark p-2 custom__btn w-50">Xác nhận</button>
            </div>
        </div>
    </form>
    <div class="col-4 my-auto custom_bg--gray border rounded p-5">
        <div class="text-start my-auto">
            <div
                    class="py-4 border-bottom text-left custom_size--13 d-flex align-item-center"
            >
                <img
                        class="me-2"
                        src="${pageContext.request.contextPath}/assets/icons/cart.svg"
                        width="20px"
                        height="20px"
                />
                Theo dõi đơn hàng , mua sắm.
            </div>
            <div
                    class="py-4 border-bottom text-left custom_size--13 d-flex align-item-center"
            >
                <img
                        class="me-2"
                        src="${pageContext.request.contextPath}/assets/icons/user.svg"
                        width="20px"
                        height="20px"
                />
                Quản lý thông tin cá nhân của bạn
            </div>
            <div
                    class="py-4 border-bottom text-left custom_size--13 d-flex align-item-center"
            >
                <img
                        class="me-2"
                        src="${pageContext.request.contextPath}/assets/icons/favorite.svg"
                        width="20px"
                        height="20px"
                />
                Tạo danh sách yêu thích
            </div>
        </div>
    </div>
</div>

<jsp:include page="/frontend/header_footer/footer.jsp"/>
</body>
<script>
    document.getElementById("passwordUpdate").addEventListener("submit", function (e) {
        const password = document.getElementsByClassName("newPassword")[0].value;
        const reTypePassword = document.getElementsByClassName("newPasswordRetype")[0].value;

        if (password !== reTypePassword) {
            if (!document.querySelector(".password-mismatch")) {
                const errorMessage = document.createElement("p");
                errorMessage.className = "password-mismatch text-danger mt-2 ms-2 custom_size--16 mb-0";
                errorMessage.textContent = "Mật khẩu không khớp!";
                document.querySelector(".retypePasswordCurrent").appendChild(errorMessage);
                e.preventDefault();
            }
        }
    });
</script>
</html>