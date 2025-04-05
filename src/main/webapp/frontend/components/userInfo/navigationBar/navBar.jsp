<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
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
