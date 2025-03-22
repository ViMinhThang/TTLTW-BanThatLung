<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <meta
            name="viewport"
            content="width=device-width, initial-scale=1, shrink-to-fit=no"
    />
    <meta name="description" content=""/>
    <meta name="author" content=""/>

    <title>THOMAS - Admin</title>
    <jsp:include page="/frontend/components/Admin/adminLink/adminLink.jsp"/>

</head>

<body id="page-top">
<div id="wrapper">
    <jsp:include page="/frontend/components/Admin/sidebar/sidebar.jsp"/>

    <div id="content-wrapper" class="d-flex flex-column">
        <div id="content">

            <div class="container-fluid px-0">
                <h1 class="title-create-edit mb-2 mt-5 ml-5">Xem chi tiết đơn hàng</h1>
                <div class="d-flex w-100 ml-5">
                    <div class="col-11 bg-light">
                        <div class="d-flex flex-column">
                            <div class="ms-5 mt-5 ml-5">
                                <h1 class="fw-bold">Đơn hàng id ${order.id}</h1>
                                <h3 class="fs-4">Xem chi tiết</h3>
                            </div>
                        </div>
                        <div
                                class="d-flex flex-column"
                        >
                            <div
                                    class="px-5 ps-0"
                            >
                                <div class="d-flex flex-column mt-5 ms-5">
                                    <div
                                            class="d-flex flex-column border border-dark rounded p-3 fs-5 w-50"
                                    >
                                        <div class="d-flex justify-content-between">
                                            <p class="text-end fw-bold">Anh / Chị:</p>
                                            <p class="ms-2">${order.userName}</p>
                                        </div>
                                        <div class="d-flex justify-content-between">
                                            <p class="text-end fw-bold">Địa chỉ:</p>
                                            <p>${order.addresse}</p>
                                        </div>
                                        <div class="d-flex justify-content-between">
                                            <p class="text-end fw-bold">Phương thức thanh toán:</p>
                                            <p>${order.paymentMethod}</p>
                                        </div>
                                    </div>
                                </div>
                                <div class="d-flex justify-content-center mt-5 w-100">
                                    <table class="table text-center">
                                        <thead>
                                        <tr>
                                            <th scope="col">Mã sản phẩm</th>
                                            <th scope="col">Tên sản phẩm</th>
                                            <th scope="col">Số lượng</th>
                                            <th scope="col">Giá</th>
                                            <th scope="col">Hành động</th>
                                        </tr>
                                        </thead>
                                        <tbody>
                                        <jsp:useBean id="orderDetailsList" scope="request" type="java.util.List"/>
                                        <c:forEach var="orderItem" items="${orderDetailsList}">
                                            <tr>
                                                <th class="p-4 fs-5 orderItemId" scope="row">${orderItem.id}</th>
                                                <td class="p-4 fs-5">${orderItem.beltName}</td>
                                                <td class="p-4 fs-5">${orderItem.quantity}</td>
                                                <td class="p-4 fs-5">${orderItem.price}</td>
                                                <td class="p-4 fs-5">
                                                    <button class="custom_button custom_click"
                                                            data-bs-toggle="modal"
                                                            data-bs-target="#deleteModal">
                                                        <img src="../../../assets/icons/delete.svg" width="24px"/>
                                                    </button>
                                                </td>
                                            </tr>
                                        </c:forEach>
                                        <tr>
                                            <form id="addOrderForm">
                                                <input type="hidden" name="orderId" id="orderId" value="${param.id}"/>
                                                <td></td>
                                                <td><input type="text" name="productName" id="productName"
                                                           placeholder="Tên sản phẩm" required/></td>
                                                <td><input type="number" name="quantity" id="quantity"
                                                           placeholder="Số lượng" required min="1"/></td>
                                                <td><input type="number" name="price" id="price" placeholder="Giá"
                                                           required min="0" step="0.01"/></td>
                                                <td>
                                                    <button type="submit" class="btn btn-success">Thêm</button>
                                                </td>
                                            </form>
                                        </tr>
                                        </tbody>
                                    </table>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>


            </div>
        </div>

        <jsp:include page="/frontend/components/Admin/adminFooter/adminFooter.jsp"/>

    </div>
</div>

<a class="scroll-to-top rounded" href="#page-top">
    <i class="fas fa-angle-up"></i>
</a>

<!-- Modal -->
<div class="modal fade" id="deleteModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">Xóa mục đơn hàng này</h5>
                <button
                        type="button"
                        class="btn-close"
                        data-bs-dismiss="modal"
                        aria-label="Close"
                >&times;
                </button>
            </div>
            <div class="modal-body removeModalBody">

            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-secondary " data-bs-dismiss="modal">Hủy</button>
                <form class="deleteBtn" action="${pageContext.request.contextPath}/admin/table/orders/details"
                      method="post">
                    <c:if test="${orderDetailsList.size()==1}"><input type="hidden" name="oneItem"
                                                                      value="1"></c:if>
                    <input type="hidden" name="orderId" value="${param.id}">
                    <button type="submit" class="btn btn-dark">Xóa</button>
                </form>
            </div>
        </div>
    </div>
</div>

<jsp:include page="/frontend/components/Admin/scripts/adminScripts/datatable.jsp"/>

<script src="${pageContext.request.contextPath}/js/orderDetails.js"></script>
</body>
</html>
