<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<div id="dimmer" class="dimmer"></div>
<nav class="p-12 bg-white rounded justify-content-center pb-0 d-flex mt-2 border-bottom" id="navbar">
    <a
            href=""
            class=" col-3 d-sm-none d-md-none d-lg-block"
            id="logo"
    >
        <svg xmlns="http://www.w3.org/2000/svg" width="280" height="54" viewBox="0 0 280 54" fill="none"
             style="width: 35%">
            <g filter="url(#filter0_d_2001_4)">
                <path d="M10.6464 45C10.0464 45 9.74641 44.78 9.74641 44.34C9.74641 43.9 10.0064 43.66 10.5264 43.62L13.2264 43.2C14.0264 43.08 14.5664 42.88 14.8464 42.6C15.1264 42.28 15.2664 41.7 15.2664 40.86V4.86C15.2664 4.3 15.1464 3.9 14.9064 3.66C14.6664 3.42 14.1664 3.3 13.4064 3.3C11.9264 3.3 10.4664 3.94 9.02641 5.22C7.58641 6.5 6.64641 8.56 6.20641 11.4L5.90641 13.38C5.82641 13.9 5.54641 14.16 5.06641 14.16C4.46641 14.16 4.20641 13.82 4.28641 13.14L4.82641 2.28C4.90641 1.36 5.26641 0.899999 5.90641 0.899999C6.18641 0.899999 6.44641 0.979999 6.68641 1.14C6.92641 1.26 7.32641 1.4 7.88641 1.56C8.48641 1.72 9.42641 1.8 10.7064 1.8H24.0864C25.3664 1.8 26.2864 1.72 26.8464 1.56C27.4464 1.4 27.8664 1.26 28.1064 1.14C28.3864 0.979999 28.6464 0.899999 28.8864 0.899999C29.5264 0.899999 29.8864 1.36 29.9664 2.28L30.5064 13.14C30.5864 13.82 30.3264 14.16 29.7264 14.16C29.2464 14.16 28.9664 13.9 28.8864 13.38L28.5864 11.4C28.1864 8.56 27.2664 6.5 25.8264 5.22C24.3864 3.94 22.9064 3.3 21.3864 3.3C20.6264 3.3 20.1264 3.42 19.8864 3.66C19.6464 3.9 19.5264 4.3 19.5264 4.86V40.86C19.5264 41.7 19.6664 42.28 19.9464 42.6C20.2664 42.88 20.8064 43.08 21.5664 43.2L24.2664 43.62C24.7864 43.66 25.0464 43.9 25.0464 44.34C25.0464 44.78 24.7464 45 24.1464 45H10.6464ZM51.2627 45C50.6627 45 50.3627 44.78 50.3627 44.34C50.3627 43.98 50.6227 43.74 51.1427 43.62L52.5227 43.38C53.3627 43.22 53.9027 43 54.1427 42.72C54.4227 42.44 54.5627 41.88 54.5627 41.04V5.76C54.5627 4.92 54.4227 4.36 54.1427 4.08C53.9027 3.8 53.3627 3.58 52.5227 3.42L51.1427 3.18C50.6227 3.06 50.3627 2.82 50.3627 2.46C50.3627 2.02 50.6627 1.8 51.2627 1.8H62.2427C62.8427 1.8 63.1427 2.04 63.1427 2.52C63.1427 2.88 62.9227 3.1 62.4827 3.18L60.8627 3.42C60.0227 3.54 59.4627 3.76 59.1827 4.08C58.9427 4.4 58.8227 4.98 58.8227 5.82V20.88C58.8227 21.44 59.1027 21.72 59.6627 21.72H71.5427C72.1027 21.72 72.3827 21.44 72.3827 20.88V5.82C72.3827 4.98 72.2427 4.4 71.9627 4.08C71.7227 3.76 71.1827 3.54 70.3427 3.42L68.7227 3.18C68.2827 3.1 68.0627 2.88 68.0627 2.52C68.0627 2.04 68.3627 1.8 68.9627 1.8H79.9427C80.5427 1.8 80.8427 2.02 80.8427 2.46C80.8427 2.82 80.5827 3.06 80.0627 3.18L78.6827 3.42C77.8427 3.58 77.2827 3.8 77.0027 4.08C76.7627 4.36 76.6427 4.92 76.6427 5.76V41.04C76.6427 41.88 76.7627 42.44 77.0027 42.72C77.2827 43 77.8427 43.22 78.6827 43.38L80.0627 43.62C80.5827 43.74 80.8427 43.98 80.8427 44.34C80.8427 44.78 80.5427 45 79.9427 45H68.9627C68.3627 45 68.0627 44.76 68.0627 44.28C68.0627 43.92 68.2827 43.7 68.7227 43.62L70.3427 43.38C71.1827 43.26 71.7227 43.04 71.9627 42.72C72.2427 42.4 72.3827 41.82 72.3827 40.98V24.36C72.3827 23.8 72.1027 23.52 71.5427 23.52H59.6627C59.1027 23.52 58.8227 23.8 58.8227 24.36V40.98C58.8227 41.82 58.9427 42.4 59.1827 42.72C59.4627 43.04 60.0227 43.26 60.8627 43.38L62.4827 43.62C62.9227 43.7 63.1427 43.92 63.1427 44.28C63.1427 44.76 62.8427 45 62.2427 45H51.2627ZM116.177 45.54C114.257 45.54 112.457 44.98 110.777 43.86C109.097 42.7 107.617 41.12 106.337 39.12C105.097 37.12 104.117 34.78 103.397 32.1C102.717 29.38 102.377 26.46 102.377 23.34C102.377 20.22 102.717 17.32 103.397 14.64C104.117 11.96 105.097 9.62 106.337 7.62C107.617 5.58 109.097 4 110.777 2.88C112.457 1.76 114.257 1.2 116.177 1.2C118.097 1.2 119.877 1.76 121.517 2.88C123.197 4 124.677 5.58 125.957 7.62C127.237 9.62 128.217 11.96 128.897 14.64C129.617 17.32 129.977 20.22 129.977 23.34C129.977 26.46 129.617 29.38 128.897 32.1C128.217 34.78 127.237 37.12 125.957 39.12C124.677 41.12 123.197 42.7 121.517 43.86C119.877 44.98 118.097 45.54 116.177 45.54ZM116.177 43.92C119.017 43.92 121.237 42.22 122.837 38.82C124.437 35.42 125.237 30.26 125.237 23.34C125.237 16.42 124.437 11.28 122.837 7.92C121.237 4.52 119.017 2.82 116.177 2.82C113.377 2.82 111.157 4.52 109.517 7.92C107.917 11.28 107.117 16.42 107.117 23.34C107.117 30.26 107.917 35.42 109.517 38.82C111.157 42.22 113.377 43.92 116.177 43.92ZM169.279 44.4C169.119 44.4 168.939 44.34 168.739 44.22C168.539 44.1 168.399 43.84 168.319 43.44L159.259 6.66C159.219 6.38 159.099 6.26 158.899 6.3C158.699 6.3 158.599 6.44 158.599 6.72L157.399 39.84C157.359 40.96 157.539 41.82 157.939 42.42C158.339 42.98 159.039 43.34 160.039 43.5L160.819 43.62C161.339 43.66 161.599 43.9 161.599 44.34C161.599 44.78 161.299 45 160.699 45H152.119C151.519 45 151.219 44.78 151.219 44.34C151.219 43.9 151.479 43.66 151.999 43.62L152.779 43.5C153.819 43.34 154.519 42.98 154.879 42.42C155.279 41.82 155.499 40.96 155.539 39.84L156.739 5.76C156.779 4.92 156.659 4.36 156.379 4.08C156.099 3.8 155.499 3.58 154.579 3.42L153.199 3.18C152.679 3.06 152.419 2.82 152.419 2.46C152.419 2.02 152.719 1.8 153.319 1.8H160.519C161.759 1.8 162.519 2.4 162.799 3.6L170.059 33.54C170.139 33.82 170.259 33.96 170.419 33.96C170.619 33.96 170.739 33.82 170.779 33.54L178.339 3.3C178.579 2.3 179.219 1.8 180.259 1.8H187.339C187.939 1.8 188.239 2.02 188.239 2.46C188.239 2.82 187.979 3.06 187.459 3.18L186.079 3.42C185.239 3.58 184.679 3.8 184.399 4.08C184.119 4.36 183.999 4.92 184.039 5.76L185.239 41.04C185.279 41.88 185.419 42.44 185.659 42.72C185.899 43 186.439 43.22 187.279 43.38L188.659 43.62C189.179 43.74 189.439 43.98 189.439 44.34C189.439 44.78 189.139 45 188.539 45H177.679C177.079 45 176.779 44.78 176.779 44.34C176.779 43.98 177.039 43.74 177.559 43.62L178.939 43.38C179.779 43.22 180.339 43 180.619 42.72C180.899 42.44 181.019 41.88 180.979 41.04L179.959 6.72C179.959 6.44 179.859 6.3 179.659 6.3C179.499 6.26 179.379 6.36 179.299 6.6L170.179 43.44C170.099 43.84 169.959 44.1 169.759 44.22C169.599 44.34 169.439 44.4 169.279 44.4ZM208.101 45C207.501 45 207.201 44.8 207.201 44.4C207.201 44 207.481 43.74 208.041 43.62L208.941 43.44C209.741 43.28 210.341 42.9 210.741 42.3C211.141 41.66 211.481 40.78 211.761 39.66L221.061 2.16C221.141 1.76 221.261 1.5 221.421 1.38C221.621 1.26 221.801 1.2 221.961 1.2C222.161 1.2 222.341 1.26 222.501 1.38C222.701 1.5 222.841 1.76 222.921 2.16L232.401 40.2C232.681 41.32 233.001 42.1 233.361 42.54C233.721 42.98 234.281 43.28 235.041 43.44L235.941 43.62C236.501 43.74 236.781 44 236.781 44.4C236.781 44.8 236.481 45 235.881 45H225.621C225.061 45 224.781 44.78 224.781 44.34C224.781 43.9 225.121 43.66 225.801 43.62L226.941 43.5C227.621 43.42 228.041 43.2 228.201 42.84C228.401 42.48 228.401 41.86 228.201 40.98L225.921 31.68C225.681 30.64 225.021 30.12 223.941 30.12H217.641C216.601 30.12 215.941 30.64 215.661 31.68L213.621 39.72C213.021 42 213.621 43.26 215.421 43.5L216.321 43.62C217.001 43.74 217.341 43.98 217.341 44.34C217.341 44.78 217.061 45 216.501 45H208.101ZM217.461 28.32H224.061C224.701 28.32 224.961 27.98 224.841 27.3L221.001 11.22H220.701L216.681 27.3C216.561 27.98 216.821 28.32 217.461 28.32ZM264.04 45.54C263.24 45.54 262.34 45.46 261.34 45.3C260.38 45.14 259.46 44.94 258.58 44.7C257.74 44.46 257.08 44.2 256.6 43.92C256.32 43.76 256.1 43.6 255.94 43.44C255.82 43.24 255.76 42.92 255.76 42.48L255.46 33.06C255.46 32.26 255.72 31.86 256.24 31.86C256.68 31.86 256.98 32.22 257.14 32.94L257.68 35.22C259.04 40.94 261.58 43.8 265.3 43.8C267.3 43.8 268.88 43.14 270.04 41.82C271.24 40.46 271.84 38.56 271.84 36.12C271.84 33.76 271.22 31.56 269.98 29.52C268.78 27.48 266.84 25.46 264.16 23.46C261.2 21.34 259.04 19.3 257.68 17.34C256.32 15.34 255.64 13.22 255.64 10.98C255.64 8.02 256.66 5.66 258.7 3.9C260.78 2.1 263.42 1.2 266.62 1.2C267.82 1.2 268.98 1.34 270.1 1.62C271.22 1.9 272.14 2.3 272.86 2.82C273.1 2.98 273.28 3.16 273.4 3.36C273.56 3.56 273.64 3.82 273.64 4.14L273.82 13.2C273.82 13.88 273.56 14.22 273.04 14.22C272.68 14.22 272.42 13.94 272.26 13.38L271.84 11.88C270.96 8.8 270.04 6.56 269.08 5.16C268.16 3.72 266.82 3 265.06 3C263.34 3 261.96 3.56 260.92 4.68C259.92 5.8 259.42 7.38 259.42 9.42C259.42 11.14 259.98 12.78 261.1 14.34C262.22 15.9 264.18 17.8 266.98 20.04C269.98 22.48 272.16 24.84 273.52 27.12C274.92 29.36 275.62 31.76 275.62 34.32C275.62 36.56 275.12 38.52 274.12 40.2C273.12 41.88 271.74 43.2 269.98 44.16C268.26 45.08 266.28 45.54 264.04 45.54Z"
                      fill="black"/>
            </g>
            <defs>
                <filter id="filter0_d_2001_4" x="0.166382" y="0.899994" width="279.454" height="52.64"
                        filterUnits="userSpaceOnUse" color-interpolation-filters="sRGB">
                    <feFlood flood-opacity="0" result="BackgroundImageFix"/>
                    <feColorMatrix in="SourceAlpha" type="matrix" values="0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 127 0"
                                   result="hardAlpha"/>
                    <feOffset dy="4"/>
                    <feGaussianBlur stdDeviation="2"/>
                    <feComposite in2="hardAlpha" operator="out"/>
                    <feColorMatrix type="matrix" values="0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0 0.25 0"/>
                    <feBlend mode="normal" in2="BackgroundImageFix" result="effect1_dropShadow_2001_4"/>
                    <feBlend mode="normal" in="SourceGraphic" in2="effect1_dropShadow_2001_4" result="shape"/>
                </filter>
            </defs>
        </svg>
    </a>

    <ul class="navbar-nav col-4 navitem__group d-sm-none d-md-none d-lg-flex justify-content-center align-items-end">
        <li class="nav-item">
            <a href="${pageContext.request.contextPath}/navigate?type=collection" class="nav-link"
            >Bộ Sưu Tập</a
            >
        </li>
        <li class="nav-item dropdown">
            <a
                    class="nav-link dropdown-toggle"
                    href="#"
                    role="button"
                    data-bs-toggle="dropdown"
                    aria-expanded="false"
            >
                Nam
            </a>
            <ul class="dropdown-menu">
                <li>
                    <a class="dropdown-item" href="${pageContext.request.contextPath}/navigate?type=men&page=1"
                    >Tất cả thắt lưng nam</a
                    >
                </li>
                <li>
                    <a class="dropdown-item" href="${pageContext.request.contextPath}/navigate?type=menLeather&page=1"
                    >Thắt Lưng Da Nam</a
                    >
                </li>
                <li>
                    <a class="dropdown-item" href="${pageContext.request.contextPath}/navigate?type=menCanvas&page=1"
                    >Thắt Lưng Vải Canvas Nam</a
                    >
                </li>
            </ul>
        </li>
        <li class="nav-item dropdown">
            <a
                    class="nav-link dropdown-toggle"
                    href="#"
                    role="button"
                    data-bs-toggle="dropdown"
                    aria-expanded="false"
            >
                Nữ
            </a>
            <ul class="dropdown-menu">
                <li>
                    <a class="dropdown-item" href="${pageContext.request.contextPath}/navigate?type=women&page=1"
                    >Tất cả thắt lưng nữ</a
                    >
                </li>
                <li>
                    <a class="dropdown-item"
                       href="${pageContext.request.contextPath}/navigate?type=womenLeather&page=1"
                    >Thắt Lưng Da Nữ
                    </a>
                </li>
                <li>
                    <a class="dropdown-item"
                       href="${pageContext.request.contextPath}/navigate?type=womenCanvas&page=1"
                    >Thắt Lưng Vải Canvas Nữ</a
                    >
                </li>
            </ul>
        </li>
        <li class="nav-item">
            <a href="${pageContext.request.contextPath}/navigate?type=all&page=1" class="nav-link">Sản
                Phẩm</a>
        </li>
    </ul>
    <div class="header__icon__group col-4 d-flex justify-content-end align-items-end">
        <div class="position-relative border border-dark d-flex justify-content-center align-items-center search_container"
             style="border-radius: 0">
            <input type="text" class="px-3 py-1 search__input" style="border-radius: 0"/>
            <div class="position-absolute end-0 pe-2 cursor-pointer search__button">
                <svg
                        xmlns="http://www.w3.org/2000/svg"
                        height="24px"
                        viewBox="0 -960 960 960"
                        width="24px"
                        fill="#00000"
                >
                    <path d="M784-120 532-372q-30 24-69 38t-83 14q-109 0-184.5-75.5T120-580q0-109 75.5-184.5T380-840q109 0 184.5 75.5T640-580q0 44-14 83t-38 69l252 252-56 56ZM380-400q75 0 127.5-52.5T560-580q0-75-52.5-127.5T380-760q-75 0-127.5 52.5T200-580q0 75 52.5 127.5T380-400Z"/>
                </svg>
            </div>
        </div>


        <c:if test="${sessionScope.auth!=null}">
            <a class="nav-item favorite"
               href="${pageContext.request.contextPath}/favorite?userId=${sessionScope.auth.id}">
                <img class="nav-icon" src="${pageContext.request.contextPath}/assets/icons/favorite.svg" alt=""/>
                <span id="favorite_receive" class="favorite__count">0</span>
            </a>
        </c:if>
        <a class="nav-item user user__dropdown" href="#">
            <div class="dropdown text-end dropdown-hover">
                <c:if test="${sessionScope.auth !=null}">
                    <a
                            href="${pageContext.request.contextPath}/userInfo"
                            class="d-flex align-items-center text-decoration-none nav-item"
                            id="dropdownUser"
                    >
                        <c:choose>
                            <c:when test="${sessionScope.auth.image == null}">
                                <img src="${pageContext.request.contextPath}/assets/icons/default_profile.svg"
                                     alt="User Avatar"
                                     width="35"
                                     height="35"
                                     class="rounded-circle me-2">
                            </c:when>
                            <c:otherwise>
                                <img src="${sessionScope.auth.image}"
                                     alt="User Avatar"
                                     width="35"
                                     height="35"
                                     class="rounded-circle me-2">
                            </c:otherwise>
                        </c:choose>

                    </a>
                    <ul
                            class="dropdown-menu dropdown-menu-end text-small"
                            aria-labelledby="dropdownUser"
                    >
                        <c:if test="${sessionScope.auth.role == 1}">
                            <li>
                                <a class="dropdown-item" href="${pageContext.request.contextPath}/admin"
                                >Admin</a
                                >
                            </li>
                        </c:if>

                        <li>
                            <form method="POST" action="${pageContext.request.contextPath}/logout">
                                <button type="submit" class="dropdown-item"
                                >Đăng xuất
                                </button>
                            </form>

                        </li>
                    </ul>
                </c:if>
                <c:if test="${sessionScope.auth ==null}">
                    <a
                            href="${pageContext.request.contextPath}/login"
                            class="d-flex align-items-center text-decoration-none"
                            id="dropdownUser"
                    >
                        <img
                                src="${pageContext.request.contextPath}/assets/icons/user.svg"
                                alt="User Avatar"
                                width="35"
                                height="35"
                                class="rounded-circle me-2"
                        />
                    </a>
                    <ul
                            class="dropdown-menu dropdown-menu-end text-small"
                            aria-labelledby="dropdownUser"
                    >

                        <li>
                            <a href="${pageContext.request.contextPath}/login" class="dropdown-item"
                            >Đăng nhập
                            </a>
                        </li>
                        <li>
                            <a href="/signup" class="dropdown-item"
                            >Đăng ký
                            </a>

                        </li>
                    </ul>
                </c:if>
            </div>
        </a>
        <a class="nav-item cart" href="${pageContext.request.contextPath}/Cart">
            <input type="hidden" class="userId" value="${sessionScope.auth.id}">
            <img
                    class="nav-icon"
                    src="${pageContext.request.contextPath}/assets/icons/cart.svg"
                    alt=""
                    style="width: 26px; height: 26px"
            />
            <span id="cart_received" class="nav-item favorite__count">${cartSize!=null ?cartSize:0}</span>
        </a>

    </div>
</nav>
<div class="dropdown-menu search-dropdown w-100">
    <div id="list__product__row">

    </div>
</div>

<style>
    .dropdown-toggle::after {
        display: none !important;
    }

    .navbar {
        padding-top: 30px;
    }

    .dropdown-menu {
        border-radius: 0;
    }
</style>
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.min.js"></script>
<script src="${pageContext.request.contextPath}/js/header.js"></script>
<script src="https://code.jquery.com/jquery-3.7.1.js"></script>
<script src="${pageContext.request.contextPath}/js/dropdownSearchMenu.js"></script>