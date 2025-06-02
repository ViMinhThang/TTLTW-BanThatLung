<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0"/>
    <f:setLocale value="${sessionScope.lang}" scope="session" />
    <f:setBundle basename="messages" />
    <title><f:message key="home.title"/></title>
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
    <link rel="stylesheet" href="https://cdn-web-servlet.vercel.app/css/HomePage.css"/>
    <link
            rel="stylesheet"
            href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.7.2/css/all.min.css"
    />
    <link rel="stylesheet" href="https://cdn-web-servlet.vercel.app/css/header.css"/>
    <link rel="stylesheet" href="https://cdn-web-servlet.vercel.app/css/footer.css"/>
    <script src="js/header.js"></script>
</head>
<body>
<%@ include file="/frontend/header_footer/header.jsp" %>
<div class="d-flex flex-column justify-center align-items-center">
    <section id="section_slider">
        <div class="w-100 p-0">
            <div class="carousel-item active">
                <img src="/assets/images/banner/Hero-Banner-Home-1.jpg"
                     loading="lazy"
                     class="d-block w-100 img-fluid"/>
            </div>
        </div>
    </section>
    <div class="w-100 mt-5">
        <div class="row">

            <div class="col-12">
                <div class="d-flex justify-content-start align-items-center gap-2"
                     style="margin-right: 108px;margin-left: 124px;">
                    <c:forEach var="color" items="${colors}">
                        <a class="text-black border-tag d-flex justify-content-center align-items-center">
                                ${color}
                        </a>
                    </c:forEach>

                </div>
                <div id="carouselExampleControls2" class="carousel slide" data-bs-ride="carousel">
                    <div class="carousel-inner">

                        <c:forEach var="chunk" items="${chunkedProductList}" varStatus="i">
                            <div class="carousel-item ${i.index == 0 ? 'active' : ''}">
                                <div class="py-4" style="margin-right: 108px;margin-left: 108px;">
                                    <div class="d-flex justify-content-center gap-2">
                                        <c:forEach var="belt" items="${chunk}">
                                            <t:beltCard belt="${belt}"/>
                                        </c:forEach>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>

                    </div>
                    <div class="carousel-indicators-line d-flex justify-content-center mt-3 mb-5">
                        <c:forEach var="chunk" items="${chunkedProductList}" varStatus="i">
                            <div
                                    data-bs-target="#carouselExampleControls2"
                                    data-bs-slide-to="${i.index}"
                                    class="indicator-segment ${i.index == 0 ? 'active' : ''}">
                            </div>
                        </c:forEach>
                    </div>
                    <button class="carousel-control-prev" type="button" data-bs-target="#carouselExampleControls2"
                            data-bs-slide="prev">
                        <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                        <span class="visually-hidden">Previous</span>
                    </button>
                    <button class="carousel-control-next" type="button" data-bs-target="#carouselExampleControls2"
                            data-bs-slide="next">
                        <span class="carousel-control-next-icon" aria-hidden="true"></span>
                        <span class="visually-hidden">Next</span>
                    </button>
                </div>
            </div>

        </div>
    </div>

    <section id="section_slider">
        <div class="w-100 p-0">
            <img src="/assets/images/banner/Hero-Banner-Home-2.png"
                 class="d-block w-100 img-fluid"
                 loading="lazy"
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
                    <f:message key="home.new_arrivals"/>
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
                    <f:message key="home.most_popular"/>
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
                                    <c:forEach var="belt" items="${newArrivalsList}">
                                        <t:beltCard belt="${belt}"/>
                                    </c:forEach>
                                </div>
                            </div>
                        </div>
                        <div class="text-center mt-4">
                            <form action="newArrival" method="get">
                                <button name="seeMore" class="btn seeMore"><f:message key="home.see_more"/></button>
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
                                        <c:forEach var="belt" items="${mostPopularList}">
                                            <t:beltCard belt="${belt}"/>
                                        </c:forEach>
                                    </div>
                                </div>
                            </div>
                            <div class="text-center mt-4">
                                <form action="newArrival" method="get">
                                    <button name="seeMore" class="btn seeMore"><f:message key="home.see_more"/></button>
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
            <f:message key="home.story_title"/>
        </div>
        <p class="fs-5 lh-lg">
            <f:message key="home.story_paragraph1"/>
            <f:message key="home.story_paragraph2"/>
            <br><br>
            <f:message key="home.story_paragraph3"/>
            <br><br>
            <f:message key="home.story_paragraph4"/>
        </p>
    </div>
    <div class="fs-2 fw-bold text-uppercase mt-3 letter-spacing pb-5">T H O M A S</div>
</div>

<c:if test="${sessionScope.auth==null}">
    <div class="py-5 " style="background-color: #FFFF12;">
        <div class="d-flex justify-content-center align-items-center px-4 ">
            <!-- Nội dung text -->
            <span class="fs-3 fw-bold text-dark text-center me-5">
                <f:message key="home.member_discount"/>
            </span>

            <!-- Nút đăng ký -->
            <a href="#" class="btn btn-dark fs-5 fw-bold px-4 py-3 d-flex align-items-center">
                <f:message key="home.register_free"/> <span class="ms-2">➝</span>
            </a>
        </div>
    </div>

</c:if>

<jsp:include page="/frontend/header_footer/footer.jsp"/>
<script>
    const carousel = document.querySelector('#carouselExampleControls2');
    const indicators = document.querySelectorAll('.indicator-segment');

    carousel.addEventListener('slid.bs.carousel', function (e) {
        indicators.forEach((el, idx) => {
            el.classList.toggle('active', idx === e.to);
        });
    });
</script>
</body>
</html>