<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
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
    <style>
        #suggestionList {
            border: 1px solid #ccc;
            background: white;
            max-height: 200px;
            overflow-y: auto;
            cursor: pointer;
        }

        #suggestionList .list-group-item:hover {
            background: #f0f0f0;
        }
    </style>
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
                <button
                        class="btn btn-white border border-dark custom_input--btn-group__input"
                        style="color: black"
                        data-bs-toggle="modal"
                        data-bs-target="#createModal"
                >
                    Tạo 1 bộ sưu tập mới
                </button>

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
                                            class="form-control bg-light border-0 small custom_input--btn-group__input"
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
                <h1 class="h3 mb-2 text-gray-800">Đơn hàng</h1>
                <div class="card shadow mb-4">
                    <div class="card-header py-3">
                        <h6 class="m-0 font-weight-bold text-primary">
                            Tất cả Đơn hàng
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
                                    <th>Tên Bộ sưu tập</th>
                                    <th>Ngày Tạo</th>
                                    <th>Hành động</th>
                                </tr>
                                </thead>
                                <tfoot>
                                <tr>
                                    <th>ID</th>
                                    <th>Tên Bộ sưu tập</th>
                                    <th>Ngày tạo</th>
                                    <th>Hành động</th>
                                </tr>
                                </tfoot>
                                <tbody>
                                <tr>
                                    <c:forEach var="collection" items="${collections}">
                                    <td class="orderId">${collection.id}</td>
                                    <td>${collection.collectionName}</td>
                                    <td>${collection.createAt}</td>
                                    <td class="text-center">
                                        <a
                                                href="${pageContext.request.contextPath}/admin/table/collections/details?id=${collection.id}"
                                                class="btn btn-dark fa-solid fa-pen-to-square"

                                        ></a>
                                        <button
                                                class="btn btn-danger fa-solid fa-trash-can"
                                                data-bs-toggle="modal"
                                                data-bs-target="#removeModal"
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
            <jsp:include page="/frontend/components/Admin/adminFooter/adminFooter.jsp"/>

        </div>
    </div>

    <a class="scroll-to-top rounded" href="#page-top">
        <i class="fas fa-angle-up"></i>
    </a>
    <div
            class="modal fade"
            id="removeModal"
            tabindex="-1"
            aria-labelledby="removeModal"
            aria-hidden="true"
    >
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">
                        Bạn có muốn xóa bộ sưu tập này ?
                    </h5>
                    <button
                            type="button"
                            class="btn-close"
                            data-bs-dismiss="modal"
                            aria-label="Close"
                    ></button>
                </div>
                <div class="modal-body removeModalBody">

                </div>
                <div class="modal-footer">
                    <button
                            type="button"
                            class="btn btn-white border border-dark custom_btn_style"
                            data-bs-dismiss="modal"
                            style="color: black"
                    >
                        Hủy
                    </button>
                    <form class="deleteBtn" method="POST" action="/admin/table/collections">
                        <button
                                type="submit"
                                class="btn btn-dark custom_btn_style"
                                style="color: white; background-color: black"
                        >
                            Xóa vĩnh viễn
                        </button>
                    </form>

                </div>
            </div>
        </div>
    </div>

    <div
            class="modal fade"
            id="createModal"
            tabindex="-1"
            aria-labelledby="exampleModalLabel"
            aria-hidden="true"
    >
        <div class="modal-dialog">
            <div class="modal-content">
                <form action="${pageContext.request.contextPath}/admin/table/collections" method="POST">
                    <input type="hidden" name="message" value="create">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel">
                            Tạo 1 bộ sưu tập mới
                        </h5>
                        <button
                                type="button"
                                class="btn-close"
                                data-bs-dismiss="modal"
                                aria-label="Close"
                        >&times;
                        </button>
                    </div>
                    <div class="modal-body">
                        <div class="container mt-2">
                            <input type="hidden" name="message" value="create">
                            <div class="mb-3">
                                <label for="collectionName" class="form-label">Tên của bộ sưu tập</label>
                                <input
                                        type="text"
                                        class="form-control custom_input--btn-group__input"
                                        id="collectionName"
                                        name="collectionName"
                                        required
                                        autocomplete="off"
                                />
                            </div>
                            <div class="mb-3">
                                <label for="createdDate" class="form-label"
                                >Ngày tạo</label
                                >
                                <input
                                        type="date"
                                        class="form-control custom_input--btn-group__input"
                                        id="createdDate"
                                        name="createdDate"
                                />
                            </div>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button
                                class="btn btn-white custom_input--btn-group__input"
                                data-bs-dismiss="modal"
                        >
                            Hủy
                        </button>
                        <button type="submit" class="btn custom_input--btn-group__input"
                                style="background-color: black;color: white">Tạo
                        </button>
                    </div>
                </form>
            </div>
        </div>
    </div>
    <!-- Bootstrap core JavaScript-->
    <jsp:include page="/frontend/components/Admin/scripts/adminScripts/datatable.jsp"/>

    <script>
        const checkbox = document.getElementById('showActive');
        const hiddenInput = document.querySelector('.isDeleted');

        checkbox.addEventListener('change', function () {
            hiddenInput.value = this.checked ? 1 : 0;
        });
    </script>
</div>
</body>
</html>
