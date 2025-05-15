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
    <script src="${pageContext.request.contextPath}/js/userAddress.js"></script>
    <link
            rel="stylesheet"
            href="${pageContext.request.contextPath}/css/fontawesome-free-6.6.0-web/fontawesome-free-6.6.0-web/css/all.css"
    />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/general.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/header.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/footer.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/address.css"/>
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
            <div class="d-inline-block fw-bold" style="padding: 12px">
                <a
                        class="custom_size--19"
                        href="${pageContext.request.contextPath}/userProfile"
                >Hồ sơ</a
                >
            </div>
            <div class="d-inline-block fw-bold custom_active" style="padding: 12px">
                <a
                        class="text-white custom_size--19"
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

    <p class="address-title">Địa chỉ giao hàng của bạn</p>
    <div class="container container-sm border rounded col-12">
        <div class="userInfo--address--display mb-3">
            <button
                    class="btn mt-4 border border-3 border-dark custom_add_address"
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
                    <div class="modal-content p-3" style="border-radius: 15px">
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
                                <div class="d-flex flex-column">
                                    <p class="mb-1">Địa chỉ cụ thể</p>
                                    <input
                                            name="addressStreet"
                                            type="text"
                                            class="form-control custom_design address_detail"
                                    />
                                </div>
                                <div class="d-flex flex-column">
                                    <p class="mt-3 mb-1 custom_province">Thành phố</p>
                                    <select name="addressCity" class="form-select custom_design">
                                        <option value="An Giang">An Giang</option>
                                        <option value="Bà Rịa - Vũng Tàu">
                                            Bà Rịa - Vũng Tàu
                                        </option>
                                        <option value="Bắc Giang">Bắc Giang</option>
                                        <option value="Bắc Kạn">Bắc Kạn</option>
                                        <option value="Bạc Liêu">Bạc Liêu</option>
                                        <option value="Bắc Ninh">Bắc Ninh</option>
                                        <option value="Bến Tre">Bến Tre</option>
                                        <option value="Bình Định">Bình Định</option>
                                        <option value="Bình Dương">Bình Dương</option>
                                        <option value="Bình Phước">Bình Phước</option>
                                        <option value="Bình Thuận">Bình Thuận</option>
                                        <option value="Cà Mau">Cà Mau</option>
                                        <option value="Cần Thơ">Cần Thơ</option>
                                        <option value="Cao Bằng">Cao Bằng</option>
                                        <option value="Đà Nẵng">Đà Nẵng</option>
                                        <option value="Đắk Lắk">Đắk Lắk</option>
                                        <option value="Đắk Nông">Đắk Nông</option>
                                        <option value="Điện Biên">Điện Biên</option>
                                        <option value="Đồng Nai">Đồng Nai</option>
                                        <option value="Đồng Tháp">Đồng Tháp</option>
                                        <option value="Gia Lai">Gia Lai</option>
                                        <option value="Hà Giang">Hà Giang</option>
                                        <option value="Hà Nam">Hà Nam</option>
                                        <option value="Hà Nội">Hà Nội</option>
                                        <option value="Hà Tĩnh">Hà Tĩnh</option>
                                        <option value="Hải Dương">Hải Dương</option>
                                        <option value="Hải Phòng">Hải Phòng</option>
                                        <option value="Hậu Giang">Hậu Giang</option>
                                        <option value="Hòa Bình">Hòa Bình</option>
                                        <option value="Hưng Yên">Hưng Yên</option>
                                        <option value="Khánh Hòa">Khánh Hòa</option>
                                        <option value="Kiên Giang">Kiên Giang</option>
                                        <option value="Kon Tum">Kon Tum</option>
                                        <option value="Lai Châu">Lai Châu</option>
                                        <option value="Lâm Đồng">Lâm Đồng</option>
                                        <option value="Lạng Sơn">Lạng Sơn</option>
                                        <option value="Lào Cai">Lào Cai</option>
                                        <option value="Long An">Long An</option>
                                        <option value="Nam Định">Nam Định</option>
                                        <option value="Nghệ An">Nghệ An</option>
                                        <option value="Ninh Bình">Ninh Bình</option>
                                        <option value="Ninh Thuận">Ninh Thuận</option>
                                        <option value="Phú Thọ">Phú Thọ</option>
                                        <option value="Phú Yên">Phú Yên</option>
                                        <option value="Quảng Bình">Quảng Bình</option>
                                        <option value="Quảng Nam">Quảng Nam</option>
                                        <option value="Quảng Ngãi">Quảng Ngãi</option>
                                        <option value="Quảng Ninh">Quảng Ninh</option>
                                        <option value="Quảng Trị">Quảng Trị</option>
                                        <option value="Sóc Trăng">Sóc Trăng</option>
                                        <option value="Sơn La">Sơn La</option>
                                        <option value="Tây Ninh">Tây Ninh</option>
                                        <option value="Thái Bình">Thái Bình</option>
                                        <option value="Thái Nguyên">Thái Nguyên</option>
                                        <option value="Thanh Hóa">Thanh Hóa</option>
                                        <option value="Thừa Thiên Huế">Thừa Thiên Huế</option>
                                        <option value="Tiền Giang">Tiền Giang</option>
                                        <option value="TP Hồ Chí Minh">TP Hồ Chí Minh</option>
                                        <option value="Trà Vinh">Trà Vinh</option>
                                        <option value="Tuyên Quang">Tuyên Quang</option>
                                        <option value="Vĩnh Long">Vĩnh Long</option>
                                        <option value="Vĩnh Phúc">Vĩnh Phúc</option>
                                        <option value="Yên Bái">Yên Bái</option>
                                    </select>
                                </div>
                                <div class="d-flex flex-column">
                                    <button class="btn btn-dark mt-3 custom_save">
                                        Thêm
                                    </button>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>

        <div class="d-flex">
            <jsp:useBean id="userAddressList" scope="request" type="java.util.List"/>
            <c:forEach var="address" items="${userAddressList}">
                <div
                        class="address__info_div user-addresses d-flex flex-column mb-2 custom_hover p-3 rounded-0 ${address.isUse == 1 ? "border-dark bg-light": "border-light"}"
                >
                    <p class="custom_name_display">${address.userName}</p>
                    <p class="custom_address_detail_display">${address.addressStreet}</p>
                    <p class="custom_address">${address.addressCity}</p>
                    <p class="custom_phoneNumber_display">${address.phoneNumber}</p>

                    <div class="d-flex justify-content-center">
                        <form class="defaultAddressForm" action="${pageContext.request.contextPath}/userAddress"
                              method="POST">
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
                            <form action="${pageContext.request.contextPath}/userAddress" method="post">
                                <input type="hidden" name="message" value="updateAddress">
                                <input type="hidden" name="userAddressId" value="${sessionScope.auth.id}">
                                <input type="hidden" name="userId" value="${sessionScope.auth.id}">
                                <div class="d-flex flex-column">
                                    <p class="mb-1">Địa chỉ cụ thể</p>
                                    <input
                                            name="AddressStreet"
                                            type="text"
                                            class="form-control custom_design address_detail"
                                    />
                                </div>
                                <div class="d-flex flex-column">
                                    <p class="mt-3 mb-1 custom_province">Thành phố</p>
                                    <select
                                            name="AddressCity"
                                            id="province"
                                            class="form-select custom_design"
                                    >
                                        <option value="An Giang">An Giang</option>
                                        <option value="Bà Rịa - Vũng Tàu">
                                            Bà Rịa - Vũng Tàu
                                        </option>
                                        <option value="Bắc Giang">Bắc Giang</option>
                                        <option value="Bắc Kạn">Bắc Kạn</option>
                                        <option value="Bạc Liêu">Bạc Liêu</option>
                                        <option value="Bắc Ninh">Bắc Ninh</option>
                                        <option value="Bến Tre">Bến Tre</option>
                                        <option value="Bình Định">Bình Định</option>
                                        <option value="Bình Dương">Bình Dương</option>
                                        <option value="Bình Phước">Bình Phước</option>
                                        <option value="Bình Thuận">Bình Thuận</option>
                                        <option value="Cà Mau">Cà Mau</option>
                                        <option value="Cần Thơ">Cần Thơ</option>
                                        <option value="Cao Bằng">Cao Bằng</option>
                                        <option value="Đà Nẵng">Đà Nẵng</option>
                                        <option value="Đắk Lắk">Đắk Lắk</option>
                                        <option value="Đắk Nông">Đắk Nông</option>
                                        <option value="Điện Biên">Điện Biên</option>
                                        <option value="Đồng Nai">Đồng Nai</option>
                                        <option value="Đồng Tháp">Đồng Tháp</option>
                                        <option value="Gia Lai">Gia Lai</option>
                                        <option value="Hà Giang">Hà Giang</option>
                                        <option value="Hà Nam">Hà Nam</option>
                                        <option value="Hà Nội">Hà Nội</option>
                                        <option value="Hà Tĩnh">Hà Tĩnh</option>
                                        <option value="Hải Dương">Hải Dương</option>
                                        <option value="Hải Phòng">Hải Phòng</option>
                                        <option value="Hậu Giang">Hậu Giang</option>
                                        <option value="Hòa Bình">Hòa Bình</option>
                                        <option value="Hưng Yên">Hưng Yên</option>
                                        <option value="Khánh Hòa">Khánh Hòa</option>
                                        <option value="Kiên Giang">Kiên Giang</option>
                                        <option value="Kon Tum">Kon Tum</option>
                                        <option value="Lai Châu">Lai Châu</option>
                                        <option value="Lâm Đồng">Lâm Đồng</option>
                                        <option value="Lạng Sơn">Lạng Sơn</option>
                                        <option value="Lào Cai">Lào Cai</option>
                                        <option value="Long An">Long An</option>
                                        <option value="Nam Định">Nam Định</option>
                                        <option value="Nghệ An">Nghệ An</option>
                                        <option value="Ninh Bình">Ninh Bình</option>
                                        <option value="Ninh Thuận">Ninh Thuận</option>
                                        <option value="Phú Thọ">Phú Thọ</option>
                                        <option value="Phú Yên">Phú Yên</option>
                                        <option value="Quảng Bình">Quảng Bình</option>
                                        <option value="Quảng Nam">Quảng Nam</option>
                                        <option value="Quảng Ngãi">Quảng Ngãi</option>
                                        <option value="Quảng Ninh">Quảng Ninh</option>
                                        <option value="Quảng Trị">Quảng Trị</option>
                                        <option value="Sóc Trăng">Sóc Trăng</option>
                                        <option value="Sơn La">Sơn La</option>
                                        <option value="Tây Ninh">Tây Ninh</option>
                                        <option value="Thái Bình">Thái Bình</option>
                                        <option value="Thái Nguyên">Thái Nguyên</option>
                                        <option value="Thanh Hóa">Thanh Hóa</option>
                                        <option value="Thừa Thiên Huế">
                                            Thừa Thiên Huế
                                        </option>
                                        <option value="Tiền Giang">Tiền Giang</option>
                                        <option value="TP Hồ Chí Minh">
                                            TP Hồ Chí Minh
                                        </option>
                                        <option value="Trà Vinh">Trà Vinh</option>
                                        <option value="Tuyên Quang">Tuyên Quang</option>
                                        <option value="Vĩnh Long">Vĩnh Long</option>
                                        <option value="Vĩnh Phúc">Vĩnh Phúc</option>
                                        <option value="Yên Bái">Yên Bái</option>
                                    </select>
                                </div>
                                <div class="d-flex flex-column">
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


<jsp:include page="/frontend/header_footer/footer.jsp"/>
</body>
</html>