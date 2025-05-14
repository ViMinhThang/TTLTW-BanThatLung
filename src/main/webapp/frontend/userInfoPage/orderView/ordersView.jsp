<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>THOMAS - Xem đơn hàng</title>
    <link
            rel="icon"
            href="${pageContext.request.contextPath}/assets/icons/favicon.svg"
            type="image/x-icon"
    />
    <jsp:include page="/frontend/components/userInfo/cdnLink/userInfoLink.jsp"/>

    <link rel="stylesheet" href="https://cdn-web-servlet.vercel.app/css/ordersView.css"/>
</head>
<body>
<jsp:include page="/frontend/header_footer/header.jsp"/>
<div class="breadcumb__container">
    <nav style="--bs-breadcrumb-divider: '>'" aria-label="breadcrumb">
        <ol class="breadcrumb">
            <li class="breadcrumb-item">
                <a href="${pageContext.request.contextPath}/index.jsp">Trang chủ</a>
            </li>
            <li class="breadcrumb-item active" aria-current="page">Đơn hàng</li>
        </ol>
    </nav>
</div>
<!-- Modal -->
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
                <h5 class="modal-title" id="exampleModalLabel">Xác nhận</h5>
                <button
                        type="button"
                        class="btn-close"
                        data-bs-dismiss="modal"
                        aria-label="Close"
                ></button>
            </div>
            <div class="modal-body">
                <h4>Bạn có chắc muốn hủy đơn hàng này</h4>
            </div>
            <div class="modal-footer">
                <button
                        type="button"
                        class="btn btn-secondary"
                        data-bs-dismiss="modal"
                >
                    Đóng
                </button>
                <button type="button" class="btn btn-dark">Đồng ý</button>
            </div>
        </div>
    </div>
</div>
<div class="container my-3">
    <jsp:include page="/frontend/components/userInfo/navigationBar/navBar.jsp"/>

    <div class="container-fluid container-sm ps-0" style="width: 1900px">
        <table class="table w-100">
            <thead class="border-bottom">
            <tr class="font-weight-light">
                <th>Đơn hàng</th>
                <th class="text-start">Ngày đặt</th>
                <th>Trạng thái</th>
                <th>Tổng</th>
                <th class="text-end">Hành động</th>
            </tr>
            </thead>

            <tbody class="p-2">
            <c:forEach var="order" items="${orders}">
                <fmt:parseDate value="${order.orderDate}" pattern="yyyy-MM-dd" var="formattedDate"/>
                <tr>
                    <td>#${order.id}</td>
                    <td><fmt:formatDate value="${formattedDate}" pattern="dd-MM-yyyy"/></td>
                    <td>${order.orderStatus}</td>
                    <td>${order.orderTotal} VNĐ</td>
                    <td class="text-end">
                        <a class="bg-dark p-2 text-white text-decoration-none rounded-0"
                           href="${pageContext.request.contextPath}/viewOrders?orderId=${order.id}">Xem</a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>

<jsp:include page="/frontend/header_footer/footer.jsp"/>

</body>
</html>
