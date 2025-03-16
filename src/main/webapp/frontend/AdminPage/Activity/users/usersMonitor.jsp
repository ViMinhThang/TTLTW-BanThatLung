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
                        </div>
                        <div class="table-responsive">
                            <table
                                    class="display nowrap"
                                    id="dataTable"
                                    style="width: 100%"
                            >
                                <thead>
                                <tr>
                                    <th>Email</th>
                                    <th>Lần đăng nhập cuối</th>
                                    <th>Lần hoạt động cuối</th>
                                    <th>IP Address của người dùng</th>
                                    <th>Truy cập mới nhất</th>
                                </tr>
                                </thead>
                                <tfoot>
                                <tr>
                                    <th>Email</th>
                                    <th>Lần đăng nhập cuối</th>
                                    <th>Lần hoạt động cuối</th>
                                    <th>IP Address của người dùng</th>
                                    <th>Truy cập mới nhất</th>
                                </tr>
                                </tfoot>
                                <tbody>
                                <jsp:useBean id="usersUsages" scope="request" type="java.util.List"/>
                                <c:forEach var="user" items="${usersUsages}">
                                <tr>
                                    <td>${user.userEmail}</td>
                                    <td>${user.lastLoginFormatted}</td>
                                    <td>${user.lastActivityFormatted}</td>
                                    <td>${user.ipAddress}</td>
                                    <td>${user.lastActivateRoute}</td>
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
    <jsp:include page="/frontend/components/Admin/scripts/adminScripts/datatable.jsp"/>
</div>
</body>
</html>
