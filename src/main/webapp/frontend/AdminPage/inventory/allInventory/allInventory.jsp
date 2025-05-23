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
    <style>
        .autocomplete-list {
            position: absolute;
            top: 100%;
            left: 0;
            right: 0;
            z-index: 1000;
            background: white;
            border: 1px solid #ccc;
            max-height: 200px;
            overflow-y: auto;
            display: none;
        }

        .autocomplete-list div {
            padding: 8px;
            cursor: pointer;
        }

        .autocomplete-list div:hover {
            background-color: #f0f0f0;
        }

    </style>
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
                        type="submit"
                        class="btn btn-white border border-dark custom_input--btn-group__input"
                        style="color: black"
                        data-bs-toggle="modal"
                        data-bs-target="#createModal"
                >
                    Thêm hàng
                </button>


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
                        <div class="d-flex justify-content-end align-items-center gap-4 p-3 bg-light rounded shadow-sm">
                            <h3 class="mb-0 text-primary fw-semibold">Import CSV</h3>

                            <form method="post" enctype="multipart/form-data"
                                  action="${pageContext.request.contextPath}/admin/inventory/inventories?message=import"
                                  class="d-flex align-items-center gap-2">

                                <div class="form-group mb-0">
                                    <input type="file" name="file" class="form-control" accept=".csv">
                                </div>

                                <button type="submit" class="btn btn-primary">
                                    <i class="bi bi-upload"></i> Upload
                                </button>
                            </form>
                        </div>
                        <div class="table-responsive">
                            <table class="table" id="dataTable" width="100%" cellspacing="0">
                                <thead>
                                <tr>
                                    <th>ID</th>
                                    <th>Tên sản phẩm</th>
                                    <th>Màu</th>
                                    <th>Size</th>
                                    <th>Tồn kho</th>
                                    <th>Hành động</th>
                                </tr>
                                </thead>
                                <tfoot>
                                <tr>
                                    <th>ID</th>
                                    <th>Tên sản phẩm</th>
                                    <th>Màu</th>
                                    <th>Size</th>
                                    <th>Tồn kho</th>
                                    <th>Hành động</th>
                                </tr>
                                </tfoot>
                                <tbody>
                                <c:forEach var="item" items="${inventoryList}">
                                    <tr class="belt-row">
                                        <td class="productId">${item.id}</td>
                                        <td class="variantId">${item.beltName}</td>
                                        <td class="">${item.variantName[1]}</td>
                                        <td class="">${item.variantName[0]}</td>
                                        <td class="">${item.stockQuantity}</td>
                                        <td class="text-center">
                                            <button class="btn btn-danger fa-solid fa-trash-can custom_action_hover"
                                                    data-bs-toggle="modal" data-bs-target="#removeModal"></button>
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
                <div class="modal-body" id="removeModalBody">

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
    <div
            class="modal fade"
            id="createModal"
            tabindex="-1"
            aria-labelledby="exampleModalLabel"
            aria-hidden="true"
    >
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <h3
                            class="modal-title"
                            id="exampleModalLabel"
                            style="color: black"
                    >
                        Tạo nhà cung cấp
                    </h3>
                    <button
                            type="button"
                            class="btn-close"
                            data-bs-dismiss="modal"
                            aria-label="Close"
                    >&times;
                    </button>
                </div>
                <form method="POST" action="${pageContext.request.contextPath}/admin/inventory/inventories">
                    <input type="hidden" name="message" value="updateQuantity">
                    <div class="modal-body">

                        <div class="d-flex flex-column mt-3">
                            <label
                                    class="fomr-label"
                                    style="color: black"
                            >Tên hàng hóa</label
                            >
                            <div style="position: relative;">
                                <input type="hidden" name="name" class="form-control" autocomplete="off"/>
                                <div id="beltSuggestions" class="autocomplete-list"></div>
                            </div>

                            <input class="form-control custom_input--btn-group__input" name="name" type="text"
                                   required/>
                        </div>
                        <div class="d-flex flex-column mt-3">
                            <label class="fomr-label" style="color: black"
                            >Màu sắc</label
                            >
                            <select class="form-control custom_input--btn-group__input" name="coloSelect"
                                    required>

                            </select>
                        </div>
                        <div class="d-flex flex-column mt-3">
                            <label class="fomr-label" style="color: black"
                            >Size</label
                            >
                            <select class="form-control custom_input--btn-group__input" name="sizeSelect"
                                    required>

                            </select>
                        </div>
                        <div class="d-flex flex-column mt-3">
                            <label class="fomr-label" style="color: black"
                            >Số lượng</label
                            >
                            <input class="form-control custom_input--btn-group__input" name="quantity"
                                   type="number" required/>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button
                                type="button"
                                class="btn btn-white border border-dark custom_input--btn-group__input"
                                data-bs-dismiss="modal"
                                style="color: black"
                        >
                            Hủy
                        </button>

                        <button
                                type="submit"
                                class="btn createOrUpdate custom_input--btn-group__input"
                                style="color: white; background-color: black"
                        >
                            Thêm
                        </button>
                    </div>
                </form>


            </div>
        </div>
    </div>

    <jsp:include page="/frontend/components/Admin/scripts/adminScripts/datatable.jsp"/>

    <script src="${pageContext.request.contextPath}/js/allInventory.js"></script>

</div>
</body>
</html>
