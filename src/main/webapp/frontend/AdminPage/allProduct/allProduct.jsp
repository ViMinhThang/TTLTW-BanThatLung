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
            <nav
                    class="navbar navbar-expand navbar-light bg-white topbar mb-4 static-top shadow"
            >
                <form method="POST">
                    <button
                            type="submit"
                            class="btn btn-white border border-dark custom_input--btn-group__input"
                            id="createButton"
                            style="color: black"

                    >
                        Tạo 1 sản phẩm mới
                    </button>
                    <input type="hidden" name="message" value="create">
                </form>

                <input name="productId" id="productId" type="hidden"/>
                <ul class="navbar-nav ml-auto">
                    <li class="nav-item dropdown no-arrow d-sm-none">
                        <div
                                class="dropdown-menu dropdown-menu-right p-3 shadow animated--grow-in"
                                aria-labelledby="searchDropdown"
                        >
                            <form class="form-inline mr-auto w-100 navbar-search">
                                <div class="input-group">
                                    <input
                                            type="text"
                                            class="form-control bg-light border-0 small"
                                            placeholder="Search for..."
                                            aria-label="Search"
                                            aria-describedby="basic-addon2"
                                    />
                                    <div class="input-group-append">
                                        <button class="btn btn-primary" type="button">
                                            <i class="fas fa-search fa-sm"></i>
                                        </button>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </li>
                    <!-- Nav Item - User Information -->
                    <li class="nav-item dropdown no-arrow">
                        <a
                                class="nav-link dropdown-toggle"
                                href="#"
                                id="userDropdown"
                                role="button"
                                data-toggle="dropdown"
                                aria-haspopup="true"
                                aria-expanded="false"
                        >
                  <span class="mr-2 d-none d-lg-inline text-gray-600 small"
                  >${sessionScope.auth.name}</span
                  >
                            <c:choose>
                                <c:when test="${sessionScope.auth.image!=null}">
                                    <img
                                            src="${pageContext.request.contextPath}${sessionScope.auth.image}"
                                            alt=" User Avatar"
                                            width="35"
                                            height="35"
                                            class="rounded-circle me-2"
                                    />
                                </c:when>
                                <c:otherwise>
                                    <img
                                            src="${pageContext.request.contextPath}/assets/icons/user.svg"
                                            alt=" User Avatar"
                                            width="35"
                                            height="35"
                                            class="rounded-circle me-2"
                                    />
                                </c:otherwise>
                            </c:choose>
                        </a>
                    </li>
                </ul>
            </nav>

            <div class="container-fluid">
                <h1 class="h3 mb-2 text-gray-800">Sản phẩm</h1>
                <div class="card shadow mb-4">
                    <div class="card-header py-3">
                        <h6 class="m-0 font-weight-bold text-primary">
                            Tất cả sản phẩm
                        </h6>
                    </div>
                    <div class="card-body">
                        <div class="table-responsive">
                            <table class="table" id="dataTable" width="100%" cellspacing="0">
                                <thead>
                                <tr>
                                    <th>ID</th>
                                    <th>Variant ID</th>
                                    <th>Tên sản phẩm</th>
                                    <th>Ngày tạo</th>
                                    <th>Giá</th>
                                    <th>% Giảm giá</th>
                                    <th>Chất liệu</th>
                                    <th>Xóa</th>
                                    <th>Hành động</th>
                                </tr>
                                </thead>
                                <tfoot>
                                <tr>
                                    <th>ID</th>
                                    <th>Variant ID</th>
                                    <th>Tên sản phẩm</th>
                                    <th>Ngày tạo</th>
                                    <th>Giá</th>
                                    <th>% Giảm giá</th>
                                    <th>Chất liệu</th>
                                    <th>Xóa</th>
                                    <th>Hành động</th>
                                </tr>
                                </tfoot>
                                <tbody>
                                <jsp:useBean id="beltList" scope="request" type="java.util.List"/>
                                <c:forEach var="belt" items="${beltList}">
                                    <tr class="belt-row">
                                        <td class="productId">${belt.id}</td>
                                        <td></td>
                                        <td><strong>${belt.name}</strong></td>
                                        <td>${belt.createdAt}</td>
                                        <td><strong>${belt.price} vnđ</strong></td>
                                        <td>${belt.discountRate}</td>
                                        <td>${belt.materialBelt}</td>
                                        <td>${belt.isDeleted == 0 ? "Chưa xóa" : "Xóa mềm"}</td>
                                        <td class="text-center">
                                            <a href="${pageContext.request.contextPath}/admin/table/belts/createProduct?id=${belt.id}&message=createVariant"
                                               class="btn btn-dark fa-solid fa-pen-to-square"></a>
                                            <button class="btn btn-danger fa-solid fa-trash-can custom_action_hover"
                                                    data-bs-toggle="modal" data-bs-target="#removeModal"></button>
                                        </td>
                                    </tr>

                                    <c:forEach var="variant" items="${belt.beltVariants}">
                                        <tr class="variant-row">
                                            <td></td> <!-- Cột ID trống -->
                                            <td class="variantId">${variant.id}</td>
                                            <td colspan="1"><i>Màu: ${variant.color}, Size: ${variant.size}</i></td>
                                            <!-- Chỉ colspan="2" -->
                                            <td>-</td> <!-- Ngày tạo (để trống) -->
                                            <td>-</td> <!-- Giá (để trống) -->
                                            <td>-</td> <!-- % Giảm giá (để trống) -->
                                            <td>-</td> <!-- Chất liệu -->
                                            <td>-</td> <!-- Xóa -->
                                            <td class="text-center">
                                                <a href="${pageContext.request.contextPath}/admin/table/belts/createProduct?id=${belt.id}&message=update&variantId=${variant.id}"
                                                   class="btn btn-dark fa-solid fa-pen-to-square"></a>
                                                <button class="btn btn-danger fa-solid fa-trash-can custom_action_hover"
                                                        data-bs-toggle="modal" data-bs-target="#removeModal"></button>
                                            </td>
                                        </tr>
                                    </c:forEach>


                                </c:forEach>
                                </tbody>
                            </table>

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
    <div class="modal fade" id="removeModal" tabindex="-1" aria-labelledby="removeModal" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="removeModal">Xóa sản phẩm</h5>
                    <button
                            type="button"
                            class="btn-close"
                            data-bs-dismiss="modal"
                            aria-label="Close"
                    >&times;
                    </button>
                </div>
                <div class="modal-body">

                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-secondary custom_btn_style" data-bs-dismiss="modal">Hủy
                    </button>
                    <form class="deleteBtn" action="/admin/table/belts" method="post">
                        <button type="submit" class="btn btn-danger custom_btn_style">Xóa</button>
                    </form>
                </div>
            </div>
        </div>
    </div>

    <jsp:include page="/frontend/components/Admin/scripts/adminScripts/datatable.jsp"/>


</div>
</body>
</html>
<script src="${pageContext.request.contextPath}/js/allProduct.js"></script>
