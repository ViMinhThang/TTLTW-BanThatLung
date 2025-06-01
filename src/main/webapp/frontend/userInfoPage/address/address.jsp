<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>THOMAS - Địa chỉ</title>
    <link
            rel="icon"
            href="${pageContext.request.contextPath}/assets/icons/favicon.svg"
            type="image/x-icon"
    />
    <jsp:include page="/frontend/components/userInfo/cdnLink/userInfoLink.jsp"/>

</head>
<body>
<jsp:include page="/frontend/header_footer/header.jsp"/>
<div class="breadcumb__container">
    <nav style="--bs-breadcrumb-divider: '>'" aria-label="breadcrumb">
        <ol class="breadcrumb">
            <li class="breadcrumb-item">
                <a href="${pageContext.request.contextPath}/index.jsp">Trang chủ</a>
            </li>
            <li class="breadcrumb-item active" aria-current="page">Địa chỉ</li>
        </ol>
    </nav>
</div>
<div class="container my-3">
    <jsp:include page="/frontend/components/userInfo/navigationBar/navBar.jsp"/>

    <p class="address-title">Địa chỉ giao hàng của bạn</p>
    <div class="container container-sm border border-dark col-12">
        <div class="userInfo--address--display mb-3">
            <button
                    class="btn mt-4 border border-3 rounded-0 border-dark custom_add_address"
                    data-bs-toggle="modal"
                    data-bs-target="#createAddressModal"
            >
                Thêm địa chỉ mới
            </button>
            <div
                    class="modal fade"
                    id="createAddressModal"
                    tabindex="-1"
                    aria-labelledby="exampleModalLabel"
                    aria-hidden="true"
            >
                <div class="modal-dialog">
                    <div class="modal-content p-3">
                        <div class="modal-header">
                            <button
                                    type="button"
                                    class="btn-close"
                                    data-bs-dismiss="modal"
                                    aria-label="Close"
                            ></button>
                        </div>
                        <div class="modal-body">
                            <form action="${pageContext.request.contextPath}/userAddress" method="post">
                                <input type="hidden" name="message" value="create"/>
                                <input type="hidden" name="userId" value="${sessionScope.auth.id}"/>
                                <div class="d-flex justify-content-start gap-3">
                                    <div class="col">
                                        <label class="form-label">Họ và tên</label>
                                        <input type="text" class="form-control custom_input--btn-group__input"
                                               name="fullname"/>
                                    </div>
                                    <div class="col">
                                        <label class="form-label">Số điện thoại</label>
                                        <input type="number" class="form-control custom_input--btn-group__input"
                                               name="phoneNumber"/>
                                    </div>
                                </div>
                                <div class="d-flex justify-content-start gap-3">
                                    <div class="col">
                                        <label class="form-label">Tỉnh / Thành phố</label>
                                        <select id="province"
                                                class="form-select form-control custom_input--btn-group__input"
                                                required name="province"></select>
                                    </div>
                                    <div class="col">
                                        <label class="form-label">Quận / Huyện</label>
                                        <select id="district"
                                                class="form-select form-control custom_input--btn-group__input" required
                                                disabled name="district"></select>
                                    </div>
                                    <div class="col">
                                        <label class="form-label">Phường / Xã</label>
                                        <select id="ward"
                                                class="form-select form-control custom_input--btn-group__input"
                                                required disabled name="ward"></select>
                                    </div>
                                </div>
                                <div class="col">
                                    <label class="form-label">Địa chỉ cụ thể</label>
                                    <input type="text" class="form-control custom_input--btn-group__input"
                                           name="addressDetail"/>
                                </div>
                                <div class="d-flex flex-column">
                                    <button class="btn btn-dark mt-3 custom_save" type="submit">
                                        Thêm
                                    </button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div class="d-flex gap-3">
            <jsp:useBean id="userAddressList" scope="request" type="java.util.List"/>
            <c:forEach var="address" items="${userAddressList}">
                <div
                        class="address__info_div user-addresses d-flex flex-column mb-2 custom_hover p-3 rounded-0"
                        style="${address.isUse == 1 ? "border:2px solid black": "border:1px solid gray"}"
                >
                    <p class="custom_name_display">${address.fullName}</p>
                    <p class="custom_name_display">${address.phone}</p>
                    <p class="custom_address_detail_display">${address.provinceName}</p>
                    <p class="custom_address">${address.districtName}</p>
                    <p class="custom_phoneNumber_display">${address.wardName}</p>

                    <div class="d-flex justify-content-center">
                        <form class="defaultAddressForm" action="${pageContext.request.contextPath}/userAddress"
                              method="POST">
                            <input type="hidden" name="message" value="setDefault">
                            <input class="userAddressId" type="hidden" name="userAddressId" value="${address.id}">
                            <input class="userInfo" type="hidden" name="userId" value="${sessionScope.auth.id}">
                            <button
                                    type="submit"
                                    class="address__info__action btn rounded border-dark text-dark custom_hover"
                            >
                                <input type="hidden" name="message" value="setDefaultAddress"/>
                                Mặc định
                            </button>
                        </form>
                        <div class="address__info__action">
                            <button
                                    class="btn btn-light fw-bold custom_popup"
                                    data-bs-toggle="modal"
                                    data-bs-target="#editaddressModal"
                            >
                                <img src="${pageContext.request.contextPath}/assets/icons/edit.svg"/>Chỉnh sửa
                            </button>

                            <button
                                    type="button"
                                    class="btn btn-light text-dark fw-bold deleteAddress"
                                    data-bs-toggle="modal"
                                    data-bs-target="#deleteModal"
                            >
                                <img src="${pageContext.request.contextPath}/assets/icons/delete.svg"/>Xóa
                            </button>

                        </div>
                    </div>

                </div>

            </c:forEach>

            <div
                    class="modal fade"
                    id="deleteModal"
                    tabindex="-1"
                    aria-labelledby="deleteModal"
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
                                    aria-label=""
                            ></button>
                        </div>
                        <div class="modal-body fs-4">
                            Bạn có muốn xóa địa chỉ này ?
                        </div>
                        <div class="modal-footer">
                            <button
                                    type="button"
                                    class="btn btn-secondary"
                                    data-bs-dismiss="modal"
                            >
                                Hủy
                            </button>
                            <form class="custom_delete_address"
                                  action="${pageContext.request.contextPath}/userAddress"
                                  method="post">
                                <button
                                        type="submit"
                                        class="btn btn-dark rounded"
                                        data-bs-dismiss="modal"
                                >
                                    Xóa
                                </button>
                            </form>

                        </div>
                    </div>
                </div>
            </div>
            <div
                    class="modal fade"
                    id="editaddressModal"
                    tabindex="-1"
                    aria-labelledby="editaddressModal"
                    aria-hidden="true"
            >
                <div class="modal-dialog">
                    <div class="modal-content p-3" style="border-radius: 15px">
                        <div class="modal-body">
                            <form action="${pageContext.request.contextPath}/userAddress" method="post"
                                  id="createAddress">
                                <input type="hidden" name="message" value="updateAddress">
                                <input type="hidden" name="userAddressId" value="${sessionScope.auth.id}">
                                <input type="hidden" name="userId" value="${sessionScope.auth.id}">


                                <div class="col-md-4">
                                    <label class="form-label">Tỉnh / Thành phố</label>
                                    <select id="province" name="province" class="form-select" required></select>
                                </div>
                                <div class="col-md-4">
                                    <label class="form-label">Quận / Huyện</label>
                                    <select id="district" name="district" class="form-select" required
                                            disabled></select>
                                </div>
                                <div class="col-md-4">
                                    <label class="form-label">Phường / Xã</label>
                                    <select id="ward" name="ward" class="form-select" required disabled></select>
                                </div>
                                <div class="d-flex flex-column">
                                    <input type="hidden" name="provinceName" id="provinceName">
                                    <input type="hidden" name="districtName" id="districtName">
                                    <input type="hidden" name="wardName" id="wardName">
                                    <button
                                            class="btn btn-dark mt-3 custom_save custom_border"
                                    >
                                        Lưu
                                    </button>

                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>

        </div>
    </div>
</div>
<script src="${pageContext.request.contextPath}/js/address.js"></script>

<jsp:include page="/frontend/header_footer/footer.jsp"/>
</body>
</html>