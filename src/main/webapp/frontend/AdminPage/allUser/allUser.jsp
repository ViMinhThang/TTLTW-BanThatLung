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
                    Tạo 1 người dùng mới
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
                <h1 class="h3 mb-2 text-gray-800">Người dùng</h1>
                <div class="card shadow mb-4">
                    <div class="card-header py-3">
                        <h6 class="m-0 font-weight-bold text-primary">
                            Tất cả người dùng
                        </h6>
                    </div>
                    <div class="card-body">
                        <div class="d-flex justify-content-end align-items-center">
                            <h3 class="mr-5">Import CSV</h3>
                            <form method="post" enctype="multipart/form-data"
                                  action="${pageContext.request.contextPath}/admin/table/users?message=import">
                                <input type="file" name="file">
                                <button type="submit">Upload</button>
                            </form>
                        </div>
                        <div class="table-responsive">
                            <table
                                    class="display nowrap"
                                    id="dataTable"
                                    style="width: 100%"
                            >
                                <thead>
                                <tr>
                                    <th>ID</th>
                                    <th>Tên</th>
                                    <th>Email</th>
                                    <th>Mật khẩu</th>
                                    <th>Giới tính</th>
                                    <th>Quyền</th>
                                    <th>Ngày sinh</th>
                                    <th>Số điện thoại</th>
                                    <th>Trạng thái</th>
                                    <th>Hành động</th>
                                </tr>
                                </thead>
                                <tfoot>
                                <tr>
                                    <th>ID</th>
                                    <th>Tên</th>
                                    <th>Email</th>
                                    <th>Mật khẩu</th>
                                    <th>Giới tính</th>
                                    <th>Quyền</th>
                                    <th>Ngày sinh</th>
                                    <th>Số điện thoại</th>
                                    <th>Trạng thái</th>
                                    <th>Hành động</th>
                                </tr>
                                </tfoot>
                                <tbody>
                                <jsp:useBean id="userList" scope="request" type="java.util.List"/>
                                <c:forEach var="user" items="${userList}">
                                    <tr>
                                        <td class="userId">${user.id}</td>
                                        <td>${user.name}</td>
                                        <td>${user.email}</td>
                                        <td>${user.password}</td>
                                        <td>${user.gender}</td>
                                        <td>${user.role}</td>
                                        <td>${user.dateOfBirth}</td>
                                        <td>${user.phoneNumber}</td>
                                        <td>${user.isDeleted == 0 ? "Chưa xóa":"Xóa mềm"}</td>
                                        <td class="text-center">
                                            <button class="btn fa-solid fa-pen-to-square custom_action_hover_edit"
                                                    data-bs-toggle="modal" data-bs-target="#createModal"
                                                    style="border-radius: 0px;"></button>
                                            <button class="btn btn-dark fa-solid fa-trash-can custom_action_hover"
                                                    data-bs-toggle="modal"
                                                    data-bs-target="#removeModal" style="border-radius: 0px"></button>
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
                    <h5 class="modal-title removeUser" id="removeModal">
                        Bạn có muốn xóa người dùng này?
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
                    <form class="deleteBtn" action="/admin/table/users" method="POST">
                        <button
                                id="removeUserBtn"
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
                <form id="createUserForm" class="d-flex flex-column justify-content-between"
                      action="${pageContext.request.contextPath}/admin/table/users" method="POST">

                    <div class="modal-header">
                        <h5
                                class="modal-title"
                                id="exampleModalLabel"
                                style="color: black"
                        >
                            Tạo người dùng
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
                        <input class="messageCreateOrUpdate" type="hidden" name="message" value="create">
                        <div class="d-flex flex-column mt-3">
                            <label class="fomr-label" for="userName" style="color: black"
                            >Tên người dùng</label
                            >
                            <input
                                    class="form-control custom_input--btn-group__input"
                                    name="userName"
                                    type="text"
                                    placeholder="Nhập tên người dùng"
                                    required
                            />
                        </div>
                        <div class="d-flex flex-column mt-3">
                            <label class="fomr-label" for="Email" style="color: black"
                            >Email</label
                            >
                            <input
                                    class="form-control custom_input--btn-group__input"
                                    name="Email"
                                    type="text"
                                    placeholder="Nhập email"
                                    required
                            />
                        </div>
                        <div class="d-flex flex-column mt-3 password-fields">
                            <label class="form-label " for="password" style="color: black"
                            >Nhập mật khẩu</label
                            >
                            <input
                                    id="password"
                                    class="form-control custom_input--btn-group__input"
                                    name="password"
                                    type="password"
                                    placeholder="Nhập mật khẩu cho người dùng"

                            />
                        </div>
                        <div class="d-flex flex-column mt-3 password-fields">
                            <label
                                    class="form-label"
                                    for="reTypePassword"
                                    style="color: black"
                            >Nhập lại mật khẩu</label
                            >
                            <input
                                    id="reTypePassword"
                                    class="form-control custom_input--btn-group__input"
                                    name="reTypePassword"
                                    type="password"
                                    placeholder="Nhập lại mật khẩu cho người dùng"

                            />
                        </div>
                        <div class="d-flex flex-column mt-3">
                            <label class="fomr-label" style="color: black"
                            >Giới tính</label
                            >
                            <div class="d-flex">
                                <div
                                        class="d-flex align-items-center"
                                        style="margin-right: 10px"
                                >
                                    <input
                                            value="M"
                                            class="my-0"
                                            name="gender"
                                            style="margin-right: 5px"
                                            type="radio"
                                            required
                                    />
                                    <p class="my-0" style="color: black">Nam</p>
                                </div>
                                <div class="d-flex align-items-center">
                                    <input
                                            value="F"
                                            class="my-0"
                                            name="gender"
                                            style="margin-right: 5px"
                                            type="radio"
                                    />
                                    <p class="my-0" style="color: black">Nữ</p>
                                </div>
                            </div>
                        </div>
                        <div class="d-flex flex-column mt-3">
                            <select
                                    style="color: black"
                                    class="form-control custom_input--btn-group__input"
                                    name="role"
                                    aria-label="Default select example"
                                    required
                            >
                                <option selected>Chọn quyền</option>
                                <option value="1">Admin</option>
                                <option value="0">User</option>
                            </select>
                        </div>
                        <div class="d-flex flex-column mt-3">
                            <label class="form-label " for="birthDate" style="color: black"
                            >Ngày sinh</label
                            >
                            <input class="form-control custom_input--btn-group__input" name="birthDate" type="date"
                                   required/>
                        </div>
                        <div class="d-flex flex-column mt-3">
                            <label class="form-label " for="phoneNumber" style="color: black"
                            >Số điện thoại</label
                            >
                            <input class="form-control custom_input--btn-group__input" name="phoneNumber" type="number"
                                   required/>
                        </div>
                        <div class="d-flex flex mt-3">
                            <label class="form-label m-0 mr-2" style="color: black" for="isDeleted">
                                Xóa mềm
                            </label>
                            <input
                                    type="checkbox"
                                    id="showDeleted"
                                    name="showDeleted"
                                    value="1"
                            />
                            <input class="isDeleted" type="hidden" name="isDeleted" value="0" required/>
                        </div>
                    </div>
                    <div class="modal-footer">
                        <button
                                type="button"
                                class="btn btn-white border border-dark hideModal custom_btn_style"
                                data-bs-dismiss="modal"
                                style="color: black"
                        >
                            Hủy
                        </button>
                        <button
                                type="submit"
                                class="btn createOrUpdate custom_btn_style"
                                style="color: white; background-color: black"
                        >
                            Tạo
                        </button>
                    </div>
                </form>
            </div>
        </div>
    </div>

    <jsp:include page="/frontend/components/Admin/scripts/adminScripts/datatable.jsp"/>
    <script>
        const checkbox = document.getElementById('showDeleted');
        const hiddenInput = document.querySelector('.isDeleted');

        checkbox.addEventListener('change', function () {
            hiddenInput.value = this.checked ? 1 : 0;
        });
        document.getElementById("createUserForm").addEventListener("submit", function (e) {
            const messageCreateOrUpdate = document.getElementsByClassName("messageCreateOrUpdate").value;
            if (messageCreateOrUpdate == "create") {
                const password = document.getElementById("password").value;
                const reTypePassword = document.getElementById("reTypePassword").value;
                if (password !== reTypePassword) {
                    alert("Passwords do not match. Please check and try again.");
                    e.preventDefault();
                }
            }
        });
    </script>
</div>
</body>
</html>
