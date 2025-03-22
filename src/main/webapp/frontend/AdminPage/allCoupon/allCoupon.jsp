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
                    Tạo 1 coupon mới
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

                    <!-- Nav Item - Alerts -->
                    <li class="nav-item dropdown no-arrow mx-1">
                        <div
                                class="dropdown-list dropdown-menu dropdown-menu-right shadow animated--grow-in"
                                aria-labelledby="alertsDropdown"
                        >
                            <h6 class="dropdown-header">Alerts Center</h6>
                            <a class="dropdown-item d-flex align-items-center" href="#">
                                <div class="mr-3">
                                    <div class="icon-circle bg-primary">
                                        <i class="fas fa-file-alt text-white"></i>
                                    </div>
                                </div>
                                <div>
                                    <div class="small text-gray-500">December 12, 2019</div>
                                    <span class="font-weight-bold"
                                    >A new monthly report is ready to download!</span
                                    >
                                </div>
                            </a>
                            <a class="dropdown-item d-flex align-items-center" href="#">
                                <div class="mr-3">
                                    <div class="icon-circle bg-success">
                                        <i class="fas fa-donate text-white"></i>
                                    </div>
                                </div>
                                <div>
                                    <div class="small text-gray-500">December 7, 2019</div>
                                    $290.29 has been deposited into your account!
                                </div>
                            </a>
                            <a class="dropdown-item d-flex align-items-center" href="#">
                                <div class="mr-3">
                                    <div class="icon-circle bg-warning">
                                        <i class="fas fa-exclamation-triangle text-white"></i>
                                    </div>
                                </div>
                                <div>
                                    <div class="small text-gray-500">December 2, 2019</div>
                                    Spending Alert: We've noticed unusually high spending for
                                    your account.
                                </div>
                            </a>
                            <a
                                    class="dropdown-item text-center small text-gray-500"
                                    href="#"
                            >Show All Alerts</a
                            >
                        </div>
                    </li>

                    <!-- Nav Item - Messages -->
                    <li class="nav-item dropdown no-arrow mx-1">
                        <!-- Dropdown - Messages -->
                        <div
                                class="dropdown-list dropdown-menu dropdown-menu-right shadow animated--grow-in"
                                aria-labelledby="messagesDropdown"
                        >
                            <h6 class="dropdown-header">Message Center</h6>
                            <a class="dropdown-item d-flex align-items-center" href="#">
                                <div class="dropdown-list-image mr-3">
                                    <img
                                            class="rounded-circle"
                                            src="img/undraw_profile_1.svg"
                                            alt="..."
                                    />
                                    <div class="status-indicator bg-success"></div>
                                </div>
                                <div class="font-weight-bold">
                                    <div class="text-truncate">
                                        Hi there! I am wondering if you can help me with a
                                        problem I've been having.
                                    </div>
                                    <div class="small text-gray-500">Emily Fowler · 58m</div>
                                </div>
                            </a>
                            <a class="dropdown-item d-flex align-items-center" href="#">
                                <div class="dropdown-list-image mr-3">
                                    <img
                                            class="rounded-circle"
                                            src="img/undraw_profile_2.svg"
                                            alt="..."
                                    />
                                    <div class="status-indicator"></div>
                                </div>
                                <div>
                                    <div class="text-truncate">
                                        I have the photos that you ordered last month, how would
                                        you like them sent to you?
                                    </div>
                                    <div class="small text-gray-500">Jae Chun · 1d</div>
                                </div>
                            </a>
                            <a class="dropdown-item d-flex align-items-center" href="#">
                                <div class="dropdown-list-image mr-3">
                                    <img
                                            class="rounded-circle"
                                            src="img/undraw_profile_3.svg"
                                            alt="..."
                                    />
                                    <div class="status-indicator bg-warning"></div>
                                </div>
                                <div>
                                    <div class="text-truncate">
                                        Last month's report looks great, I am very happy with
                                        the progress so far, keep up the good work!
                                    </div>
                                    <div class="small text-gray-500">Morgan Alvarez · 2d</div>
                                </div>
                            </a>
                            <a class="dropdown-item d-flex align-items-center" href="#">
                                <div class="dropdown-list-image mr-3">
                                    <img
                                            class="rounded-circle"
                                            src="https://source.unsplash.com/Mv9hjnEUHR4/60x60"
                                            alt="..."
                                    />
                                    <div class="status-indicator bg-success"></div>
                                </div>
                                <div>
                                    <div class="text-truncate">
                                        Am I a good boy? The reason I ask is because someone
                                        told me that people say this to all dogs, even if they
                                        aren't good...
                                    </div>
                                    <div class="small text-gray-500">
                                        Chicken the Dog · 2w
                                    </div>
                                </div>
                            </a>
                            <a
                                    class="dropdown-item text-center small text-gray-500"
                                    href="#"
                            >Read More Messages</a
                            >
                        </div>
                    </li>

                    <div class="topbar-divider d-none d-sm-block"></div>

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
                <h1 class="h3 mb-2 text-gray-800">Coupon</h1>
                <div class="card shadow mb-4">
                    <div class="card-header py-3">
                        <h6 class="m-0 font-weight-bold text-primary">Tất cả Coupon</h6>
                    </div>
                    <div class="card-body">
                        <div class="d-flex justify-content-end align-items-center">
                            <h3 class="mr-5">Import CSV</h3>
                            <form method="post" enctype="multipart/form-data"
                                  action="${pageContext.request.contextPath}/admin/table/coupons?message=import">
                                <input type="file" name="file">
                                <button type="submit">Upload</button>
                            </form>
                        </div>
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
                                    <th>Mã</th>
                                    <th>% giảm giá</th>
                                    <th>Ngày giảm giá</th>
                                    <th>Ngày hết hạn</th>
                                    <th>Trạng thái</th>
                                    <th>Hành động</th>
                                </tr>
                                </thead>
                                <tfoot>
                                <tr>
                                    <th>ID</th>
                                    <th>Mã</th>
                                    <th>% giảm giá</th>
                                    <th>Ngày giảm giá</th>
                                    <th>Ngày hết hạn</th>
                                    <th>Trạng thái</th>
                                    <th>Hành động</th>
                                </tr>
                                </tfoot>
                                <tbody>
                                <jsp:useBean id="couponList" scope="request" type="java.util.List"/>
                                <c:forEach var="coupon" items="${couponList}">
                                    <tr>
                                        <td class="couponId">${coupon.id}</td>
                                        <td>${coupon.code}</td>
                                        <td>${coupon.discountRate}</td>
                                        <td>${coupon.startDate}</td>
                                        <td>${coupon.endDate}</td>
                                        <td>${coupon.isActive == 0 ?"Không" : "Có"}</td>
                                        <td class="text-center">
                                            <button
                                                    class="btn btn-dark fa-solid fa-pen-to-square"
                                                    data-bs-toggle="modal"
                                                    data-bs-target="#createModal"
                                            ></button>
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
            id="logoutModal"
            tabindex="-1"
            role="dialog"
            aria-labelledby="exampleModalLabel"
            aria-hidden="true"
    >
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <h5 class="modal-title" id="exampleModalLabel">
                        Ready to Leave?
                    </h5>
                    <button
                            class="close"
                            type="button"
                            data-dismiss="modal"
                            aria-label="Close"
                    >
                        <span aria-hidden="true">×</span>
                    </button>
                </div>
                <div class="modal-body">
                    Select "Logout" below if you are ready to end your current
                    session.
                </div>
                <div class="modal-footer">
                    <button
                            class="btn btn-secondary"
                            type="button"
                            data-dismiss="modal"
                    >
                        Cancel
                    </button>
                    <a class="btn btn-primary" href="login.html">Logout</a>
                </div>
            </div>
        </div>
    </div>

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
                        Bạn có muốn xóa coupon này?
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
                    <form class="deleteBtn" action="/admin/table/coupons" method="POST">
                        <button
                                id="removeUserBtn"
                                type="submit"
                                class="btn btn-dark custom_btn_style"
                                style="color: white; background-color: black"
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
            id="createModal"
            tabindex="-1"
            aria-labelledby="exampleModalLabel"
            aria-hidden="true"
    >
        <div class="modal-dialog">
            <div class="modal-content">
                <form id="createCouponForm" action="${pageContext.request.contextPath}/admin/table/coupons"
                      method="POST">
                    <input class="messageCreateOrUpdate" type="hidden" name="message" value="create">
                    <div class="modal-header">
                        <h3
                                class="modal-title"
                                id="exampleModalLabel"
                                style="color: black"
                        >
                            Tạo coupon mới
                        </h3>
                        <button
                                type="button"
                                class="btn-close"
                                data-bs-dismiss="modal"
                                aria-label="Close"
                        >&times;
                        </button>
                    </div>
                    <div class="modal-body">
                        <input class="messageCreateOrUpdate" type="hidden" name="message" value="create">
                        <div class="d-flex flex-column mt-3">
                            <label
                                    class="fomr-label"
                                    for="couponCode"
                                    style="color: black"
                            >Mã coupon</label
                            >
                            <input class="form-control custom_input--btn-group__input" name="couponCode" type="text"
                                   required/>
                        </div>
                        <div class="d-flex flex-column mt-3">
                            <label class="fomr-label" for="discountPercentage" style="color: black"
                            >% Giảm giá</label
                            >
                            <input class="form-control custom_input--btn-group__input" name="discountPercentage"
                                   type="number" required/>
                        </div>
                        <div class="d-flex flex-column mt-3">
                            <label class="form-label" for="startDate" style="color: black"
                            >Nhập ngày bắt đầu</label
                            >
                            <input class="form-control custom_input--btn-group__input" name="startDate" type="date"
                                   required/>
                        </div>
                        <div class="d-flex flex-column mt-3">
                            <label class="form-label" for="endDate" style="color: black"
                            >Nhập ngày hết hạn</label
                            >
                            <input class="form-control custom_input--btn-group__input" name="endDate" type="date"
                                   required/>
                        </div>
                        <div class="d-flex flex mt-3">
                            <label class="form-label m-0 mr-2" style="color: black" for="isDeleted">
                                Kích hoạt
                            </label>
                            <input
                                    type="checkbox"
                                    id="showActive"
                                    name="showActive"
                                    value="1"
                            />
                            <input class="isActive" type="hidden" name="isActive" value="0" required/>
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
                        <form>
                            <button
                                    type="submit"
                                    class="btn createOrUpdate custom_input--btn-group__input"
                                    style="color: white; background-color: black"
                            >
                                Tạo
                            </button>
                        </form>

                    </div>
                </form>

            </div>
        </div>
    </div>
    <!-- Bootstrap core JavaScript-->

    <jsp:include page="/frontend/components/Admin/scripts/adminScripts/datatable.jsp"/>

    <script>
        const checkbox = document.getElementById('showActive');
        const hiddenInput = document.querySelector('.isActive');

        checkbox.addEventListener('change', function () {
            hiddenInput.value = this.checked ? 1 : 0;
        });
    </script>
    <script src="${pageContext.request.contextPath}/js/allCoupon.js"></script>

</div>
</body>
</html>
