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
    <link rel="icon" href="${pageContext.request.contextPath}/assets/icons/favicon.svg" type="image/x-icon"/>

    <!-- Custom fonts for this template-->
    <link
            href="${pageContext.request.contextPath}/css/fontawesome-free-6.6.0-web/fontawesome-free-6.6.0-web/css/all.min.css"
            rel="stylesheet"
            type="text/css"
    />
    <link
            href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
            rel="stylesheet"
    />
    <link rel="stylesheet" href="https://cdn.ckeditor.com/ckeditor5/44.0.0/ckeditor5.css">

    <!-- Custom styles for this template-->
    <link href="${pageContext.request.contextPath}/css/dataTables.bootstrap4.min.css" rel="stylesheet"/>
    <link href="${pageContext.request.contextPath}/css/adminGeneral.css" rel="stylesheet"/>

</head>

<body id="page-top">
<div id="wrapper">
    <jsp:include page="/frontend/components/sidebar/sidebar.jsp"/>

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
                            <table
                                    class="table"
                                    id="dataTable"
                                    width="100%"
                                    cellspacing="0"
                            >
                                <thead>
                                <tr>
                                    <th>ID</th>
                                    <th>Tên sản phẩm</th>
                                    <th>Ngày tạo</th>
                                    <th>giá</th>
                                    <th>% giảm giá</th>
                                    <th>Chất liệu</th>
                                    <th>Xóa</th>
                                    <th>Hành động</th>
                                </tr>
                                </thead>
                                <tfoot>
                                <tr>
                                    <th>ID</th>
                                    <th>Tên sản phẩm</th>
                                    <th>Ngày tạo</th>
                                    <th>giá</th>
                                    <th>% giảm giá</th>
                                    <th>Chất liệu</th>
                                    <th>Xóa</th>
                                    <th>Hành động</th>
                                </tr>
                                </tfoot>
                                <tbody>
                                <jsp:useBean id="beltList" scope="request" type="java.util.List"/>
                                <c:forEach var="belt" items="${beltList}">
                                    <tr>
                                        <td class="beltId">${belt.id}</td>
                                        <td>${belt.name}</td>
                                        <td>${belt.createdDate}</td>
                                        <td>${belt.price} vnđ</td>
                                        <td>${belt.discountPercent}</td>
                                        <td>${belt.materialBelt}</td>
                                        <td>${belt.isDeleted ==0 ? "Chưa xóa":"Xóa mềm"}</td>
                                        <td class="text-center">
                                            <input type="hidden" name="beltId" value="${belt.id}"/>
                                            <input type="hidden" name="message" value="update"/>
                                            <a href="${pageContext.request.contextPath}/admin/table/belts/createProduct?id=${belt.id}&message=update"
                                               class="btn btn-dark fa-solid fa-pen-to-square"></a>

                                            <button
                                                    class="btn btn-danger fa-solid fa-trash-can custom_action_hover"
                                                    data-bs-toggle="modal" data-bs-target="#removeModal"
                                            ></button>
                                        </td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                        </div>
                    </div>
                </div>
            </div>

            <jsp:include page="/frontend/components/adminFooter/adminFooter.jsp"/>

        </div>
    </div>

    <a class="scroll-to-top rounded" href="#page-top">
        <i class="fas fa-angle-up"></i>
    </a>

    <!-- Modal -->
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

    <script
            src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
            crossorigin="anonymous"
    ></script>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.bundle.min.js"></script>

    <!-- Core plugin JavaScript-->
    <script src="${pageContext.request.contextPath}/js/jquery.easing.min.js"></script>

    <!-- Custom scripts for all pages-->
    <script src="${pageContext.request.contextPath}/js/allProductAdmin.js"></script>
    <script src="${pageContext.request.contextPath}/js/sb-admin-2.min.js"></script>

    <script src="${pageContext.request.contextPath}/js/jquery.dataTables.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/dataTables.bootstrap4.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/datatable.js"></script>

</div>
</body>
</html>
