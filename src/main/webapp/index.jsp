<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <title>Trang Chủ</title>
    <link
            rel="shortcut icon"
            href="assets/icons/favicon.svg"
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
    <script src="js/HomePage.js"></script>
    <link rel="stylesheet" href="css/HomePage.css"/>
    <link
            rel="stylesheet"
            href="css/fontawesome-free-6.6.0-web/fontawesome-free-6.6.0-web/css/all.css"
    />
    <link rel="stylesheet" href="css/header.css"/>
    <link rel="stylesheet" href="css/footer.css"/>
    <script src="js/header.js"></script>
</head>
<body>
<%@ include file="/frontend/header_footer/header.jsp" %>
<div class="d-flex flex-column justify-center align-items-center">
    <section id="section_slider">
        <div class="w-100 p-0">
            <div class="carousel-item active">
                <img src="assets/images/homepage/z6088271163993_ca5603db38e0ae9d411375a3aeb3ef65.jpg"
                     class="d-block w-100 img-fluid"/>
            </div>
        </div>
    </section>
    <div class="w-100 mt-5">
        <div class="row">

            <div class="col-12">
                <div id="carouselExampleControls2" class="carousel slide">
                    <div class="carousel-inner carouselInner">
                        <div class="carousel-item active">
                            <div class="d-flex justify-content-center gap-3">
                                <c:forEach var="a" items="${discountProductList}" begin="0" end="3">
                                    <a href="productDetails?beltId=${a.id}" class="text-decoration-none text-dark">
                                        <div class="text-center  hover--black">
                                            <!-- Ảnh sản phẩm lớn hơn -->
                                            <img src="${pageContext.request.contextPath}${a.mainImage}"
                                                 class="img-fluid w-100 rounded shadow-sm"
                                                 alt="${a.name}"
                                                 style="height: 25rem;">
                                            <!-- Thông tin sản phẩm -->
                                            <div class="mt-2 text-start ps-3 pb-3">
                                                <p class="fw-bold fs-5 mb-1">${a.price} VNĐ</p>
                                                <p class="text-muted mb-1">${a.name}</p>
                                                <span class="badge bg-secondary">${a.discountPercent}%</span>
                                            </div>
                                        </div>
                                    </a>
                                </c:forEach>
                            </div>
                        </div>

                        <div class="carousel-item">
                            <div class="d-flex justify-content-center gap-3">
                                <c:forEach var="b" items="${discountProductList}" begin="4" end="7">
                                    <a href="productDetails?beltId=${b.id}" class="text-decoration-none text-dark">
                                        <div class="text-center  hover--black">
                                            <!-- Ảnh sản phẩm lớn hơn -->
                                            <img src="${pageContext.request.contextPath}${b.mainImage}"
                                                 class="img-fluid w-100 rounded shadow-sm"
                                                 alt="${b.name}"
                                                 style="height: 25rem;">
                                            <!-- Thông tin sản phẩm -->
                                            <div class="mt-2 text-start ps-3 pb-3">
                                                <p class="fw-bold fs-5 mb-1">${b.price} VNĐ</p>
                                                <p class="text-muted mb-1">${b.name}</p>
                                                <span class="badge bg-secondary">${b.discountPercent}%</span>
                                            </div>
                                        </div>
                                    </a>
                                </c:forEach>
                            </div>
                        </div>

                        <div class="carousel-item">
                            <div class="d-flex justify-content-center gap-3">
                                <c:forEach var="c" items="${discountProductList}" begin="8" end="11">
                                    <a href="productDetails?beltId=${c.id}" class="text-decoration-none text-dark">
                                        <div class="text-center  hover--black">
                                            <!-- Ảnh sản phẩm lớn hơn -->
                                            <img src="${pageContext.request.contextPath}${c.mainImage}"
                                                 class="img-fluid w-100 rounded shadow-sm"
                                                 alt="${c.name}"
                                                 style="height: 25rem;">
                                            <!-- Thông tin sản phẩm -->
                                            <div class="mt-2 text-start ps-3 pb-3">
                                                <p class="fw-bold fs-5 mb-1">${c.price} VNĐ</p>
                                                <p class="text-muted mb-1">${c.name}</p>
                                                <span class="badge bg-secondary">${c.discountPercent}%</span>
                                            </div>
                                        </div>
                                    </a>
                                </c:forEach>
                            </div>
                        </div>
                    </div>

                    <button
                            class="carousel-control-prev"
                            type="button"
                            data-bs-target="#carouselExampleControls2"
                            data-bs-slide="prev"
                            style="width: 5%;left:10rem"
                    >
                <span
                        class="carousel-control-prev-icon"
                        aria-hidden="true"
                ></span>
                        <span class="visually-hidden">Previous</span>
                    </button>
                    <button
                            class="carousel-control-next"
                            type="button"
                            data-bs-target="#carouselExampleControls2"
                            data-bs-slide="next"
                            style="width: 5%;right:10rem"

                    >
                <span
                        class="carousel-control-next-icon"
                        aria-hidden="true"
                ></span>
                        <span class="visually-hidden">Next</span>
                    </button>
                </div>
            </div>
        </div>
    </div>

    <section id="section_slider">
        <div class="w-100 p-0">
            <img src="assets/images/homepage/Beige Gray Aesthetic Photo Collage Shop New Collection Promotion Banner.png"
                 class="d-block w-100 img-fluid"
                 alt="...">
        </div>

    </section>

    <div class="tabEvent">
        <ul class="nav nav__Tab nav-fill nav-tabs" role="tablist">
            <li class="newarrival__tab nav-item" role="presentation">
                <a
                        class="newarrival__title nav__Link nav-link active"
                        id="fill-tab-0"
                        data-bs-toggle="tab"
                        href="#fill-tabpanel-0"
                        role="tab"
                        aria-controls="fill-tabpanel-0"
                        aria-selected="true"
                >
                    MỚI NHẤT
                </a>
            </li>
            <li class="mostpopular__tab nav-item" role="presentation">
                <a
                        class="mostpopular__title nav__Link nav-link"
                        id="fill-tab-1"
                        data-bs-toggle="tab"
                        href="#fill-tabpanel-1"
                        role="tab"
                        aria-controls="fill-tabpanel-1"
                        aria-selected="false"
                >
                    PHỔ BIẾN NHẤT
                </a>
            </li>
        </ul>
        <div class="tab-content pt-5" id="tab-content">
            <div
                    class="tab-pane tab__Pane1 active"
                    id="fill-tabpanel-0"
                    role="tabpanel"
                    aria-labelledby="fill-tab-0"
            >

                <div class="list__product ">
                    <div class="row" id="list__product__row">
                        <div class="list__product__element">
                            <div class="carousel-item active">
                                <div class="d-flex justify-content-center gap-3 flex-wrap">
                                    <c:forEach var="n" items="${newArrivalsList}" end="7">
                                        <a href="productDetails?beltId=${n.id}"
                                           class="text-decoration-none text-dark">
                                            <div class="text-center  hover--black">
                                                <!-- Ảnh sản phẩm lớn hơn -->
                                                <img src="${pageContext.request.contextPath}${n.mainImage}"
                                                     class="img-fluid w-100 rounded shadow-sm"
                                                     alt="${n.name}"
                                                     style="height: 25rem; object-fit: cover;">
                                                <!-- Thông tin sản phẩm -->
                                                <div class="mt-2 text-start ps-3">
                                                    <p class="fw-bold fs-5 mb-1">${n.price} VNĐ</p>
                                                    <p class="text-muted mb-1">${n.name}</p>
                                                    <span class="badge bg-secondary">${n.discountPercent}%</span>
                                                </div>
                                            </div>
                                        </a>
                                    </c:forEach>
                                </div>
                            </div>
                        </div>
                        <div class="text-center mt-4">
                            <form action="newArrival" method="get">
                                <button name="seeMore" class="btn seeMore">Xem Thêm</button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>

            <div
                    class="tab-pane tab__Pane2"
                    id="fill-tabpanel-1"
                    role="tabpanel"
                    aria-labelledby="fill-tab-1"
            >
                <div class="list__product container">
                    <div class="list__product container">
                        <div class="row" id="list__product__row">
                            <div class="list__product__element">
                                <div class="carousel-item active">
                                    <div class="d-flex justify-content-center gap-3 ">
                                        <c:forEach var="m" items="${mostPopularList}" end="7">
                                            <a href="productDetails?beltId=${m.id}"
                                               class="text-decoration-none text-dark">
                                                <div class="text-center hover--black">
                                                    <!-- Ảnh sản phẩm lớn hơn -->
                                                    <img src="${pageContext.request.contextPath}${m.image[0]}"
                                                         class="img-fluid w-100 rounded shadow-sm"
                                                         alt="${m.name}"
                                                         style="height: 25rem; object-fit: cover;">
                                                    <!-- Thông tin sản phẩm -->
                                                    <div class="mt-2 text-start ps-3 pb-3">
                                                        <p class="fw-bold fs-5 mb-1">${m.price} VNĐ</p>
                                                        <p class="text-muted mb-1">${m.name}</p>
                                                        <span class="badge bg-secondary">${m.discountPercent}%</span>
                                                    </div>
                                                </div>
                                            </a>
                                        </c:forEach>
                                    </div>
                                </div>
                            </div>
                            <div class="text-center mt-4">
                                <form action="newArrival" method="get">
                                    <button name="seeMore" class="btn seeMore">Xem Thêm</button>
                                </form>
                            </div>
                        </div>
                    </div>

                </div>


            </div>

        </div>
    </div>
</div>


<div class="bg-black text-white d-flex flex-column justify-content-center align-items-center px-4">
    <div class="col-6">
        <div class="fs-3 fw-bold text-uppercase mb-3 text-center pt-5">
            CÂU CHUYỆN, PHONG CÁCH VÀ THẮT LƯNG TẠI THOMAS, TỪ NĂM 1949
        </div>
        <p class="fs-5 lh-lg">
            Thể thao giúp chúng ta khỏe mạnh. Giữ cho tâm trí tỉnh táo. Kết nối mọi người lại với nhau. Thông qua thể
            thao, chúng ta có sức mạnh để thay đổi cuộc sống.
            Dù đó là những câu chuyện về các vận động viên truyền cảm hứng. Giúp bạn đứng dậy và vận động. Hay trang
            phục thể thao với công nghệ tiên tiến nhất để nâng cao hiệu suất của bạn. Phá vỡ giới hạn bản thân.
            <br><br>
            THOMAS là ngôi nhà của những người chạy bộ, cầu thủ bóng rổ, những đứa trẻ yêu bóng đá, những người đam mê
            thể hình. Những người thích leo núi vào cuối tuần để thoát khỏi thành phố. Những giáo viên yoga lan tỏa từng
            động tác. Biểu tượng 3 Sọc xuất hiện trong làng nhạc. Trên sân khấu, tại các lễ hội. Trang phục thể thao của
            chúng tôi giúp bạn tập trung trước tiếng còi khai cuộc. Trong suốt cuộc đua. Và khi cán đích.
            <br><br>
            Chúng tôi ở đây để hỗ trợ những người sáng tạo. Cải thiện cuộc chơi của họ. Cuộc sống của họ. Và thay đổi
            thế giới.
        </p>
    </div>
    <div class="fs-2 fw-bold text-uppercase mt-3 letter-spacing pb-5">T H O M A S</div>
</div>

<c:if test="${sessionScope.auth==null}">
    <div class="py-5 " style="background-color: #FFFF12;">
        <div class="d-flex justify-content-center align-items-center px-4 ">
            <!-- Nội dung text -->
            <span class="fs-3 fw-bold text-dark text-center me-5">
                Trở thành thành viên 15% giảm giá
            </span>

            <!-- Nút đăng ký -->
            <a href="#" class="btn btn-dark fs-5 fw-bold px-4 py-3 d-flex align-items-center">
                Đăng ký miễn phí <span class="ms-2">➝</span>
            </a>
        </div>
    </div>

</c:if>

<jsp:include page="/frontend/header_footer/footer.jsp"/>

</body>
</html>
