<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>


<ul
        class="navbar-nav sidebar accordion"
        id="accordionSidebar"
>
    <a
            class="sidebar-brand d-flex align-items-center justify-content-center"
            href="${pageContext.request.contextPath}/"
    >
        <div class="sidebar-brand-icon rotate-n-15">
        </div>
        <div class="sidebar-brand-text mx-3">THOMAS Admin</div>
    </a>


    <li class="nav-item active">
        <a class="nav-link" href="${pageContext.request.contextPath}/admin">
            <i class="fas fa-fw fa-tachometer-alt"></i>
            <span>Dashboard</span></a
        >
    </li>
    <%
        String referer = request.getHeader("referer");
        if (referer != null && referer.endsWith("admin")) {
    %>
    <hr class="sidebar-divider"/>
    <%
        }
    %>

    <li class="nav-item active">
        <a
                class="nav-link collapsed"
                href="#"
                data-toggle="collapse"
                data-target="#collapseUtilities"
                aria-expanded="true"
                aria-controls="collapseUtilities"
        >
            <i class="fas fa-fw fa-table"></i>
            <span>Bảng</span>
        </a>
        <div
                id="collapseUtilities"
                class="collapse"
                aria-labelledby="headingUtilities"
                data-parent="#accordionSidebar"
        >
            <div class="py-2 collapse-inner rounded" style="background-color:#dddddd;">
                <a class="collapse-item <%=request.getHeader("referer").endsWith("users") ? "active" : "" %>"
                   href="${pageContext.request.contextPath}/admin/table/users">
                    Người dùng
                </a>
                <a class="collapse-item <%= request.getHeader("referer").endsWith("belts") ? "active" : "" %>"
                   href="${pageContext.request.contextPath}/admin/table/belts">
                    Sản phẩm
                </a>
                <a class="collapse-item <%= request.getHeader("referer").endsWith("reviews") ? "active" : "" %>"
                   href="${pageContext.request.contextPath}/admin/table/reviews">
                    Đánh giá
                </a>
                <a class="collapse-item <%= request.getHeader("referer").endsWith("coupons") ? "active" : "" %>"
                   href="${pageContext.request.contextPath}/admin/table/coupons">
                    Coupon
                </a>
                <a class="collapse-item <%= request.getHeader("referer").endsWith("orders") ? "active" : "" %>"
                   href="${pageContext.request.contextPath}/admin/table/orders">
                    Đơn hàng
                </a>
                <a class="collapse-item <%= request.getHeader("referer").endsWith("collections") ? "active" : "" %>"
                   href="${pageContext.request.contextPath}/admin/table/collections">
                    Bộ sưu tập
                </a>

            </div>
        </div>
    </li>

    <%
        if (referer != null && !referer.endsWith("admin")) {
    %>
    <hr class="sidebar-divider"/>
    <%
        }
    %>

    <!-- Sidebar Toggler (Sidebar) -->
    <div class="text-center d-none d-md-inline">
        <button class="rounded-circle border-0" id="sidebarToggle"></button>
    </div>
</ul>
