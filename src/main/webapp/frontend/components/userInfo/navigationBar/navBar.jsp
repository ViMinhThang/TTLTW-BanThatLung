<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<div class="row">
    <h2 class="mt-5 mb-3">Tài khoản của tôi</h2>
    <div
            class="d-flex col-12"
            style="white-space: nowrap;"
    >
        <div class="d-inline-block  fw-bold mb-4  <%=request.getHeader("referer").contains("userInfo") ? "custom_active" : "" %>"
             style="padding: 12px">
            <a
                    class="text-black custom_size--19 "
                    href="${pageContext.request.contextPath}/userInfo"
            >Tài khoản</a
            >
        </div>
        <div class="d-inline-block fw-bold <%=request.getHeader("referer").contains("userProfile") ? "custom_active" : "" %>"
             style="padding: 12px">
            <a
                    class="custom_size--19 "
                    href="${pageContext.request.contextPath}/userProfile"
            >Hồ sơ</a
            >
        </div>
        <div class="d-inline-block fw-bold <%=request.getHeader("referer").contains("userAddress") ? "custom_active" : "" %>"
             style="padding: 12px">
            <a
                    class="custom_size--19 "
                    href="${pageContext.request.contextPath}/userAddress?userId=${sessionScope.auth.id}"
            >Địa chỉ</a
            >
        </div>
        <div class="d-inline-block fw-bold <%=request.getHeader("referer").contains("userPrivacy") ? "custom_active" : "" %>"
             style="padding: 12px">
            <a
                    class="custom_size--19 "
                    href="${pageContext.request.contextPath}/userPrivacy"
            >Riêng tư</a
            >
        </div>
        <div class="d-inline-block fw-bold <%=request.getHeader("referer").contains("viewOrders") ? "custom_active" : "" %>"
             style="padding: 12px">
            <a
                    class="custom_size--19 "
                    href="${pageContext.request.contextPath}/viewOrders"
            >Đơn hàng</a
            >
        </div>
    </div>

</div>
