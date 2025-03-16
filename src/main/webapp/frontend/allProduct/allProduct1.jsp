<%@ page import="java.util.List" %>
<%@ page import="com.thomas.dao.model.Belts" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>


<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>${title}</title>
    <link
            rel="shortcut icon"
            href="${pageContext.request.contextPath}/assets/icons/favicon.svg"
            type="image/png"
    />
    <link
            href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
            rel="stylesheet"
            integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
            crossorigin="anonymous"
    />
    <script
            src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
            integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
            crossorigin="anonymous"
    ></script>
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.min.js"></script>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <link
            rel="stylesheet"
            href="${pageContext.request.contextPath}/css/fontawesome-free-6.6.0-web/fontawesome-free-6.6.0-web/css/all.css"
    />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/header.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/footer.css"/>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/allProduct.css"/>
    <script src="${pageContext.request.contextPath}/js/allProduct.js"></script>
</head>

<body>
<jsp:include page="/frontend/header_footer/header.jsp"/>
<div id="dimmer3" class="dimmer2"></div>
<div id="overlay3" class="d-sm-flex overlay border" style="width: 20%">
    <div class="overlay__content w-100">
        <div class="overlay__header d-flex w-100 justify-content-center align-items-center mt-2">
            <h3 class="filter__header__title mb-0">THOMAS</h3>
            <button id="closeButton" class="btn ms-5">
                <img src="../../assets/icons/close.svg"/>
            </button>
        </div>
        <div class="overlay__body d-flex align-items-left justify-content-between" style="height: 500px">
            <div class="w-100">
                <c:set var="filterUrlBase" value="${pageContext.request.contextPath}/navigate?type=${param.type}" />
                <c:if test="${param.descPrice != null}">
                    <c:set var="filterUrlBase" value="${filterUrlBase}&descPrice=${param.descPrice}" />
                </c:if>

                <div class="accordion border-top-0 w-100" id="filterAccordion">
                    <div class="accordion-item">
                        <h2 class="accordion-header" id="headingPrice">
                            <button
                                    class="accordion-button collapsed"
                                    type="button"
                                    data-bs-toggle="collapse"
                                    data-bs-target="#collapsePrice"
                                    aria-expanded="false"
                                    aria-controls="collapsePrice"
                            >
                                Giá
                            </button>
                        </h2>
                        <div
                                id="collapsePrice"
                                class="accordion-collapse collapse"
                                aria-labelledby="headingPrice"
                                data-bs-parent="#filterAccordion"
                        >
                            <div class="accordion-body">
                                <div class="dropdown-item d-flex align-items-center">
                                    <c:choose>
                                        <c:when test="${param.minPrice != null || param.maxPrice != null}">
                                            <a href="${filterUrlBase}&minPrice=100&maxPrice=500"
                                               class="filter-item text-decoration-none">
                                                Giá từ 100.000 VNĐ đến 500.000 VNĐ
                                            </a>
                                        </c:when>
                                        <c:otherwise>
                                            <a href="${filterUrlBase}"
                                               class="filter-item text-decoration-none">
                                                Giá mặc định
                                            </a>
                                        </c:otherwise>
                                    </c:choose>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>`
                <div class="apply__container mt-3 d-flex flex-column">
                    <!-- Các liên kết áp dụng -->
                    <c:forEach var="priceRange" items="${['100-500', '500-900']}">
                        <c:set var="minPrice" value="${fn:split(priceRange, '-')[0]}" />
                        <c:set var="maxPrice" value="${fn:split(priceRange, '-')[1]}" />
                        <a href="${filterUrlBase}&minPrice=${minPrice}&maxPrice=${maxPrice}"
                           class="apply__button text-decoration-none d-block mt-2 p-2">
                            Giá từ ${minPrice}.000 VNĐ đến ${maxPrice}.000 VNĐ
                        </a>
                    </c:forEach>
                </div>
            </div>

        </div>
    </div>
</div>

<header id="header"></header>

<!-- Banner với chữ lồng vào giữa -->
<div class="banner position-relative">
    <img src="${pageContext.request.contextPath}${mainImage}" alt="" style="width: 100%; height: auto;" />
    <!-- Chữ lồng vào giữa -->
    <div class="position-absolute top-50 start-50 translate-middle text-center">
        <div class="belts-header display-4 text-white ">${bigTitle}</div>
    </div>
</div>

<!-- Filter and Sort -->
<div class="function__bar">
    <div class="container-fluid">
        <div class="row function__bar__row align-items-center">
            <!-- Filter Column -->
            <div class="col-3 filter__column">
                <div class="filter__container d-flex align-items-center">
                    <i class="fa-solid fa-filter me-2" style="color: #171717;"></i>
                    <span class="filter__title" id="filterToggle">Bộ lọc</span>
                </div>
            </div>

            <!-- Breadcrumb Column -->
            <div class="col-3 quantity__column">
                <nav aria-label="breadcrumb">
                    <ol class="breadcrumb mb-0">
                        <li class="breadcrumb-item "><a href="#">Trang chủ</a></li>
                        <li class="breadcrumb-item active " aria-current="page">Sản phẩm</li>
                    </ol>
                </nav>
            </div>

            <!-- Sort Column -->
            <div class="col-3 sort__column">
                <div class="sort__container d-flex align-items-center justify-content-end">
                    <img src="${pageContext.request.contextPath}/assets/icons/sort (1).png" alt="" style="height: 20px;" class="me-2">
                    <div class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                            Sắp Xếp
                        </a>
                        <ul class="dropdown-menu dropdown-menu-end">
                            <li><a class="dropdown-item active" href="#">Mặc Định</a></li>
                            <li><a class="dropdown-item" href="#">Giá Tăng Dần</a></li>
                            <li><a class="dropdown-item" href="#">Giá Giảm Dần</a></li>
                            <li><a class="dropdown-item" href="#">Bán Chạy Nhất</a></li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<div class="list__product container">
    <div class="row" id="list__product__row">
        <div class="list__product__element">
            <div class="carousel-item active">
                <div class="d-flex justify-content-center gap-3 ">
                    <c:forEach var="b" items="${beltsList}" end="7">
                        <a href="${pageContext.request.contextPath}/productDetails?beltId=${b.id}"
                           class="text-decoration-none text-dark">
                            <div class="text-center hover--black">
                                <!-- Ảnh sản phẩm lớn hơn -->
                                <img src="${pageContext.request.contextPath}${b.mainImage}"
                                     class="img-fluid w-100 rounded shadow-sm"
                                     alt="${b.name}"
                                     style="height: 25rem; object-fit: cover;">
                                <!-- Thông tin sản phẩm -->
                                <div class="mt-2 text-start ps-3">
                                    <p class="fw-bold fs-5 mb-1">${b.price} VNĐ</p>
                                    <p class="text-muted mb-1">${b.name}</p>
                                    <span class="badge bg-secondary">${b.discountPercent}%</span>
                                </div>
                            </div>
                        </a>
                    </c:forEach>
                </div>
            </div>
        </div>
    </div>
</div>

<section>
    <nav aria-label="Page__navigation__example" id="pagination__bar">
        <div class="container paginationWrapper">
            <ul class="pagination pagination__Ul">
                <c:forEach var="o" begin="1" end="${totalPages}" step="1">
                    <li class="page-item ${o == currentPage ? 'active' : ''}">
                        <c:choose>
                            <c:when test="${param.descPrice!=null&&param.minPrice!=null&&param.maxPrice!=null}">
                                <a class="page-link"
                                   href="${pageContext.request.contextPath}/navigate?type=${type}&page=${o}&descPrice=${param.descPrice}&minPrice=${param.minPrice}&maxPrice=${param.maxPrice}">${o}</a>
                            </c:when>
                            <c:when test="${param.descPrice == null && param.minPrice!=null&&param.maxPrice!=null}">
                                <a class="page-link"
                                   href="${pageContext.request.contextPath}/navigate?type=${type}&page=${o}&minPrice=${param.minPrice}&maxPrice=${param.maxPrice}">${o}</a>
                            </c:when>
                            <c:when test="${param.descPrice != null && param.minPrice==null&&param.maxPrice==null}">
                                <a class="page-link"
                                   href="${pageContext.request.contextPath}/navigate?type=${type}&page=${o}&descPrice=${param.descPrice}">${o}</a>
                            </c:when>
                            <c:otherwise>
                                <a class="page-link"
                                   href="${pageContext.request.contextPath}/navigate?type=${type}&page=${o}">${o}</a>
                            </c:otherwise>
                        </c:choose>
                    </li>
                </c:forEach>
                <%--                <li class="page-item pageNumber">--%>
                <%--                    <a class="page-link" href="./allProduct1.html">2</a>--%>
                <%--                </li>--%>
                <%--                <li class="page-item pageNumber">--%>
                <%--                    <a class="page-link" href="./allProduct1.html">3</a>--%>
                <%--                </li>--%>
                <%--                <li class="page-item pageNumber">--%>
                <%--                    <a class="page-link" href="./allProduct1.html">...</a>--%>
                <%--                </li>--%>
                <%--                <li class="page-item pageNumber">--%>
                <%--                    <a class="page-link" href="./allProduct1.html">10</a>--%>
                <%--                </li>--%>
                <%--                <li class="page-item pageNumber">--%>
                <%--                    <a class="page-link" href="./allProduct1.html">11</a>--%>
                <%--                </li>--%>
            </ul>
        </div>
    </nav>
</section>

<jsp:include page="/frontend/header_footer/footer.jsp"/>

</body>
</html>
