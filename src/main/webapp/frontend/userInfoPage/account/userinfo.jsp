<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>THOMAS - Tài khoản</title>
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
    <link
            rel="stylesheet"
            href="${pageContext.request.contextPath}/css/fontawesome-free-6.6.0-web/fontawesome-free-6.6.0-web/css/all.css"
    />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/general.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/header.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/footer.css"/>
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
            <li class="breadcrumb-item active" aria-current="page">Người dùng</li>
        </ol>
    </nav>
</div>


<div class="container my-3">
    <div class="row">
        <h2 class="mt-5 mb-3">Tài khoản của tôi</h2>
        <div
                class="d-flex col-12"
                style="white-space: nowrap;"
        >
            <div class="d-inline-block fw-bold custom_active mb-4" style="padding: 12px">
                <a
                        class="text-white custom_size--19"
                        href="${pageContext.request.contextPath}/userInfo"
                >Tài khoản</a
                >
            </div>
            <div class="d-inline-block fw-bold" style="padding: 12px">
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
            <div class="d-inline-block fw-bold" style="padding: 12px">
                <a
                        class="custom_size--19"
                        href="${pageContext.request.contextPath}/userPrivacy"
                >Riêng tư</a
                >
            </div>
            <div class="d-inline-block fw-bold" style="padding: 12px">
                <a
                        class="custom_size--19"
                        href="${pageContext.request.contextPath}/viewOrders"
                >Đơn hàng</a
                >
            </div>
        </div>

    </div>
    <div class="container container-sm border rounded">
        <div class="d-flex flex-column mt-4 mb-2 ms-2">
            <h3 class="font-weight-bold custom_size--19">Chào ${sessionScope.auth.firstName}</h3>
            <h5 class="custom_size--16">Họ tên</h5>
            <p class="custom_size--16">${sessionScope.auth.name}</p>
            <h5 class="font-weight-bold custom_size--19">Gia nhập từ</h5>
            <p class="custom_size--16">${sessionScope.auth.createAt}</p>
        </div>
    </div>
    <form id="emailUpdate" action="${pageContext.request.contextPath}/userInfo" method="POST">
        <input type="hidden" name="message" value="updateEmail">
        <input type="hidden" name="userId" value="${sessionScope.auth.id}">
        <input id="newEmail" type="hidden" name="newEmail" value="">
        <div class="container container-sm border rounded mt-4">
            <div class="d-flex flex-column mt-4 mb-2 ms-2 border-bottom ms-2">
                <h3 class="font-weight-bold custom_size--19">Email</h3>
                <h6 class="custom_size--16">Email hiện tại</h6>
                <p class="custom_size--16">${sessionScope.auth.email}</p>
            </div>
            <div class="d-flex flex-column m-2" style="width: 90%">
                <p class="font-weight-bold mb-1 fs-4 ps-0 mt-4 custom_size--19">
                    Thay đổi email
                </p>
                <p class="mb-1 mt-2 custom_size--16">Email mới</p>
                <input
                        id="currentEmail"
                        type="email"
                        class="form-control form-control-md border custom-lg-input custom_size--16 custom_design"
                        placeholder="Nhập email mới"
                />
            </div>
            <div class="d-flex flex-column m-2" style="width: 90%">
                <p class="mb-1 mt-2 custom_size--16">Mật khẩu</p>
                <input
                        name="password"
                        type="password"
                        class="form-control form-control-md border custom custom-lg-input custom_size--16 custom_design currentPassword"
                        placeholder="Nhập mật khẩu"
                />
            </div>
            <div class="d-flex flex-column m-2 retypePasswordCurrent" style="width: 90%">
                <p class="mb-1 mt-2 custom_size--16">Nhập lại mật khẩu</p>
                <input
                        type="password"
                        class="form-control form-control-md border custom-lg-input custom_size--16 custom_design currentPasswordRetype"
                />
                <c:if test="${not empty missmatchPassword}">
                    <p class="text-danger mt-2 ms-2 custom_size--16 mb-0">${missmatchPassword}</p>
                </c:if>
            </div>
            <div class="d-flex mt-3 mb-5 ms-2">
                <button class="btn btn-dark px-3 py-2 custom__btn ms-2">
                    Thay đổi email
                </button>
            </div>
        </div>

    </form>
    <div class="container container-sm border rounded my-3">
        <form id="updatePasswordForm" action="${pageContext.request.contextPath}/userInfo" method="POST">
            <input type="hidden" name="message" value="updatePassword"/>
            <input type="hidden" name="userId" value="${sessionScope.auth.id}"/>
            <input id="updatePassword" type="hidden" name="updatePassword" value=""/>
            <input id="currentPassword" type="hidden" name="currentPassword" value=""/>
            <div
                    class="d-flex flex-column mt-4 mb-2 ml-2 border-bottom"
                    style="width: 70%"
            >
                <h3 class="font-weight-bold ms-3 custom_size--19">Mật khẩu</h3>
                <h5 class="ms-3 custom_size--16">Thay đổi mật khẩu của bạn</h5>
            </div>
            <div class="d-flex flex-column m-2 currentPasswordContainer" style="width: 90%">
                <p class="mb-1 mt-2 ms-2 custom_size--16">Mật khẩu hiện tại</p>
                <input
                        type="password"
                        class="form-control form-control-md border custom-lg-input ms-2 custom_design custom_size--16 currentPass"
                        placeholder="Mật khẩu hiện tại"
                        required
                />
                <c:if test="${not empty errorMessage}">
                    <p class="text-danger mt-2 ms-2 custom_size--16 mb-0">${errorMessage}</p>
                </c:if>
            </div>
            <div class="d-flex flex-column ms-2" style="width: 90%">
                <p class="mb-1 mt-2 ms-2 custom_size--16">Mật khẩu mới</p>
                <input
                        id="newPassword"
                        type="password"
                        class="form-control form-control-md border custom-lg-input ms-2 custom_design custom_size--16 newPassword"
                        placeholder="Nhập mật khẩu mới"
                        required
                />
            </div>
            <div class="d-flex flex-column ms-2 retypePassword" style="width: 90%">
                <p class="mb-1 mt-2 ms-2 custom_size--16">Nhập lại mật khẩu</p>
                <input
                        type="password"
                        class="form-control form-control-md border custom-lg-input ms-2 custom_design custom_size--16 newPasswordRetype"
                        placeholder="Nhập lại mật khẩu"
                        required
                />
            </div>
            <div class="d-flex mt-3 mb-5 ms-2">

                <button type="submit" class="btn btn-dark px-3 py-2 custom__btn ms-2 submitUpdatePassword">
                    Thay đổi mật khẩu
                </button>
            </div>
        </form>

    </div>
</div>

<jsp:include page="/frontend/header_footer/footer.jsp"/>
<script>
    $(document).ready(function () {

        $("#updatePasswordForm").submit(function (e) {
            $("#updatePassword").val($(".newPassword").val());
            $("#currentPassword").val($(".currentPass").val());

        });
        $("#emailUpdate").submit(function (e) {
            $("#newEmail").val($("#currentEmail").val());

        })
        document.getElementById("updatePasswordForm").addEventListener("submit", function (e) {
            const password = document.getElementsByClassName("newPassword")[0].value;
            const reTypePassword = document.getElementsByClassName("newPasswordRetype")[0].value;

            if (password !== reTypePassword) {
                if (!document.querySelector(".password-mismatch")) {
                    const errorMessage = document.createElement("p");
                    errorMessage.className = "password-mismatch text-danger mt-2 ms-2 custom_size--16 mb-0";
                    errorMessage.textContent = "Mật khẩu không khớp!";
                    document.querySelector(".retypePassword").appendChild(errorMessage);
                    e.preventDefault();
                }
            }
        });
        document.getElementById("emailUpdate").addEventListener("submit", function (e) {
            const password = document.getElementsByClassName("currentPassword")[0].value;
            const reTypePassword = document.getElementsByClassName("currentPasswordRetype")[0].value;

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
    })
</script>
</body>
</html>
