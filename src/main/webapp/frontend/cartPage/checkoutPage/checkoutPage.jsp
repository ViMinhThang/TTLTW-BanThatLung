<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>Thanh toán đơn hàng</title>
    <link rel="icon" href="${pageContext.request.contextPath}/assets/icons/favicon.svg" type="image/x-icon"/>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH" crossorigin="anonymous"/>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
            crossorigin="anonymous"></script>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <link rel="stylesheet"
          href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.7.2/css/all.min.css/>
    <script src="${pageContext.request.contextPath}/js/checkout.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/general.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/header.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/footer.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/checkoutPage.css"/>
    <script>
        $(document).ready(function () {
            $('input[name="paymentMethod"]').change(function () {
                const selectedMethod = $(this).val();
                $('.submitPaymentMethod').val(selectedMethod);
                $('#openFormButtonPayment').text('Thanh toán với ' + selectedMethod);
            });
        });

        $(document).ready(function () {
            $('select[name="selectedAddress"]').change(function () {
                const selectedAddress = $(this).val();
                $('.submitDeliveryAddress').val(selectedAddress);
            });
        });

        $(document).ready(function () {
            loadProvinces();

            $('#province').change(function () {
                const provinceId = $(this).val();
                $('#district').prop('disabled', false);
                $.getJSON('/location?action=district&province_id=' + provinceId, function (data) {
                    const districts = data.data;
                    $('#district').empty().append('<option value="">Chọn quận/huyện</option>');
                    districts.forEach(d => {
                        $('#district').append('<option value="' + d.DistrictID + '">' + d.DistrictName + '</option>');
                    });
                });
            });

            $('#district').change(function () {
                const districtId = $(this).val();
                $('#ward').prop('disabled', false);
                $.getJSON('/location?action=ward&district_id=' + districtId, function (data) {
                    const wards = data.data;
                    $('#ward').empty().append('<option value="">Chọn phường/xã</option>');
                    wards.forEach(w => {
                        $('#ward').append('<option value="' + w.WardCode + '">' + w.WardName + '</option>');
                    });
                });
            });

            $('#ward').change(function () {
                const districtId = $('#district').val();
                const wardCode = $(this).val();
                calculateShipping(districtId, wardCode);
            });

            function loadProvinces() {
                $.getJSON('/location?action=province', function (data) {
                    const provinces = data.data;
                    $('#province').empty().append('<option value="">Chọn tỉnh/thành phố</option>');
                    provinces.forEach(p => {
                        $('#province').append('<option value="' + p.ProvinceID + '">' + p.ProvinceName + '</option>');
                    });
                });
            }

            function calculateShipping(districtId, wardCode) {
                $.ajax({
                    url: '/shipping-fee',
                    method: 'POST',
                    contentType: 'application/json',
                    data: JSON.stringify({
                        to_district_id: districtId,
                        to_ward_code: wardCode
                    }),
                    success: function (response) {
                        const fee = response.data.total;
                        $('.shipmentPrice').text(fee.toLocaleString("vi-VN") + ' VNĐ');
                        const productTotal = parseInt('${totalPrice}');
                        const grandTotal = productTotal + fee;
                        $('.grandTotal').text(grandTotal.toLocaleString("vi-VN") + ' VNĐ');
                    }
                });
            }
        });
    </script>
</head>
<body>
<jsp:include page="/frontend/header_footer/header.jsp"/>

<div class="container my-5">
    <h2 class="fw-bold text-center">Vui lòng xác nhận và gửi đơn đặt hàng của bạn</h2>
    <div class="row my-5">
        <div class="col-lg-8 p-3">
            <h3 class="fw-bold py-2 bg-light">Thông tin giao hàng</h3>
            <div class="border-top border-bottom py-3">
                <div class="d-flex flex-column justify-content-between">
                    <p class="fs-4">Tên: ${sessionScope.auth.name}</p>
                    <p class="fs-4">SĐT: ${sessionScope.auth.phoneNumber}</p>
                </div>
            </div>

            <h3 class="fw-bold py-2 bg-light">Địa chỉ giao hàng</h3>
            <div class="row g-3 mb-3">
                <div class="col-md-4">
                    <label class="form-label">Tỉnh / Thành phố</label>
                    <select id="province" class="form-select" required></select>
                </div>
                <div class="col-md-4">
                    <label class="form-label">Quận / Huyện</label>
                    <select id="district" class="form-select" required disabled></select>
                </div>
                <div class="col-md-4">
                    <label class="form-label">Phường / Xã</label>
                    <select id="ward" class="form-select" required disabled></select>
                </div>
            </div>

            <select class="form-select" name="selectedAddress" id="selectedAddress">
                <c:forEach var="address" items="${userAddresses}">
                    <option value="${address}" ${address.isUse == 1 ? 'selected' : ''}>
                            ${address.addressStreet}, ${address.addressCity} - SĐT: ${sessionScope.auth.phoneNumber}
                    </option>
                </c:forEach>
            </select>

            <h3 class="fw-bold py-2 bg-light mt-3">Phương thức thanh toán</h3>
            <div class="d-inline-flex flex-row" role="group">
                <c:forEach var="method" items="${paymentMethods}">
                    <div class="payment-option m-2">
                        <label class="payment-label">
                            <input type="radio" name="paymentMethod" value="${method.name}">
                            <span class="custom-check"></span>
                            <span class="payment-text">${method.name}</span>
                            <img class="payment-icon"
                                 src="${pageContext.request.contextPath}/assets/icons/${method.name}.svg"
                                 alt="${method.name}"/>
                        </label>
                    </div>
                </c:forEach>
            </div>
        </div>

        <div class="col-lg-4 bg-light p-4 rounded">
            <h3 class="fw-bold">Tóm tắt đơn hàng</h3>
            <div class="d-flex justify-content-between mb-2 border-top pt-2">
                <p class="mb-0">${cartSize} sản phẩm</p>

                <p class="mb-0"><fmt:formatNumber value="${totalPrice}" type="number" groupingUsed="true"/> VNĐ</p>
            </div>
            <div class="d-flex justify-content-between mb-2">
                <p class="mb-0">Vận chuyển</p>
                <p class="shipmentPrice mb-0"> VNĐ</p>
            </div>
            <div class="d-flex justify-content-between mb-2">
                <p class="mb-0">Giảm giá</p>
                <p class="mb-0"><fmt:formatNumber value="${discountAmount}" type="number" groupingUsed="true"/> VNĐ
                </p>
            </div>
            <div class="border-top pt-2">
                <div class="d-flex justify-content-between fw-bold mb-2">
                    <p class="mb-0">Tổng cộng</p>
                    <p class="mb-0 grandTotal" data-base="${grandTotal}"><fmt:formatNumber value="${grandTotal}"
                                                                                           type="number"
                                                                                           groupingUsed="true"/> VNĐ
                    </p>
                </div>
                <p class="text-muted small mb-0">(bao gồm cả thuế)</p>
            </div>
            <c:choose>
                <c:when test="${userAddresses == null || paymentMethods == null || paymentMethods.isEmpty()||sessionScope.auth==null}">
                    <button class="btn btn-dark mt-4 fs-4 w-100" disabled id="openFormButtonPaymentDisabled">Thanh toán
                        với COD
                    </button>
                </c:when>
                <c:otherwise>
                    <form method="POST" action="${request.context.path}/Order">
                        <input type="hidden" name="submitDeliveryAddress" value="Việt Nam"/>
                        <input class="submitPaymentMethod" type="hidden" name="paymentMethod" value="COD">
                        <input type="hidden" name="grandTotal" value="${grandTotal}"/>
                        <button type="submit" class="btn btn-dark mt-4 fs-4 w-100" id="openFormButtonPayment">Thanh toán
                            với COD
                        </button>
                    </form>
                </c:otherwise>
            </c:choose>
            <div class="input-group mt-4">
                <input type="text" class="form-control rounded-start fs-5" placeholder="Nhập coupon"/>
                <button class="btn btn-dark input-group-text rounded-end">Áp dụng</button>
            </div>
        </div>
    </div>
</div>

<jsp:include page="/frontend/header_footer/footer.jsp"/>
</body>
</html>