<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8" />
  <meta name="viewport" content="width=device-width, initial-scale=1.0" />
  <title>THOMAS - Đăng ký</title>
  <link
          rel="icon"
          href="https://cdn-web-servlet.vercel.app/images/favicon.svg"
          type="image/x-icon"
  />
  <link
          href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css"
          rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC"
          crossorigin="anonymous"
  />
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0-alpha1/dist/js/bootstrap.bundle.min.js"></script>
  <link rel="stylesheet" href="https://cdn-web-servlet.vercel.app/css/general.css"/>
  <link rel="stylesheet" href="https://cdn-web-servlet.vercel.app/css/signUp.css"/>
  <link rel="stylesheet" href="https://cdn-web-servlet.vercel.app/css/footer.css"/>
  <!-- Add reCAPTCHA v3 script -->
  <script src="https://www.google.com/recaptcha/api.js?render=6LftTQgrAAAAAG-uHB6MEq9i_F3jIoJQBkEl3CQE"></script>
  <script>
    grecaptcha.ready(function() {
      grecaptcha.execute('6LftTQgrAAAAAG-uHB6MEq9i_F3jIoJQBkEl3CQE', {action: 'signup'}).then(function(token) {
        document.getElementById('g-recaptcha-response').value = token;
      });
    });
  </script>
</head>
<body>
<div class="row p-2 border-bottom border-dark">
  <a
          href="${pageContext.request.contextPath}/index.jsp"
          class="d-sm-none d-md-none d-lg-block d-flex text-dark text-decoration-none fs-4 ms-2"
  >
    THOMAS / Đăng ký
  </a>
</div>

<div class="container-lg d-flex justify-content-between">
  <form method="POST" class="col-12 mt-5 d-flex justify-content-between">
    <div class="col-6 me-5">
      <div class="ps-0">
        <div class="d-flex flex-column mt-3">
          <p class="mb-1 custom_size--16">Email</p>
          <input
                  name="email"
                  type="email"
                  class="form-control form-control-lg custom_design custom_size--16"
                  placeholder="Nhập Email"
                  required
          />
        </div>
        <div class="d-flex flex-column mt-3">
          <p class="mb-1 custom_size--16">Nhập mật khẩu</p>
          <input
                  name="password"
                  type="password"
                  class="form-control form-control-lg custom_design custom_size--16"
                  placeholder="Nhập mật khẩu"
                  required
          />
          <c:if test="${not empty errorMessage}">
            <p class="text-danger mt-2 custom_size--16 mb-0">${errorMessage}</p>
          </c:if>
        </div>
      </div>
      <div class="d-flex mt-3">
        <input
                type="checkbox"
                class="me-2"
                required
        />
        <a href="${pageContext.request.contextPath}/frontend/termsPage/termsPage.jsp" class="mb-0 mt-0 text-dark text-decoration-none custom_size--13" style="font-size: 1.25rem">Tôi đồng ý với chính sách và điều khoản</a>
      </div>
    </div>
    <div class="col-6">
      <div class="ps-0">
        <div class="d-flex flex-column mt-3">
          <p class="mb-1 custom_size--16">Tên</p>
          <input
                  name="name"
                  type="text"
                  class="form-control form-control-lg custom_design custom_size--16"
                  placeholder="Tên"
          />
        </div>
        <div class="d-flex flex-column mt-3">
          <p class="mb-1 custom_size--16">Họ</p>
          <input
                  name="middleName"
                  type="text"
                  class="form-control form-control-lg custom_design custom_size--16"
                  placeholder="Họ"
          />
        </div>
        <div class="d-flex flex-column mt-3">
          <p class="mb-1 custom_size--16">Ngày sinh</p>
          <input
                  name="birthDate"
                  type="date"
                  class="form-control form-control-lg custom_design custom_size--16"
          />
        </div>
        <!-- Hidden reCAPTCHA token field -->
        <input type="hidden" id="g-recaptcha-response" name="g-recaptcha-response">
        <div
                class="d-flex justify-content-end align-items-center mt-5 mb-5"
        >
          <button type="submit" class="btn btn-dark p-2 custom__btn w-50">
            Đăng ký
          </button>
        </div>
      </div>
    </div>
  </form>
</div>

<jsp:include page = "/frontend/header_footer/footer.jsp" />

</body>
</html>