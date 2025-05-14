<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html lang="en">
  <head>
    <meta charset="UTF-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0" />
    <title>THOMAS Điều Khoản và Điều Kiện</title>
    <link rel="icon" href="${pageContext.request.contextPath}/assets/icons/favicon.svg" type="image/x-icon" />
    <link
      href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
      rel="stylesheet"
      integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
      crossorigin="anonymous"
    />
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.min.js"></script>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <link
      rel="stylesheet"
      href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.7.2/css/all.min.css"
    />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/general.css" />

    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/header.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/footer.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/termsPage.css" />
  </head>
  <body>
  <jsp:include page="/frontend/header_footer/header.jsp"/>
  <header id="header"></header>
    <div class="breadcumb__container">
      <nav style="--bs-breadcrumb-divider: '>'" aria-label="breadcrumb">
        <ol class="breadcrumb">
          <li class="breadcrumb-item"><a href="${pageContext.request.contextPath}/index.jsp">Trang chủ</a></li>
          <li class="breadcrumb-item active" aria-current="page">Điều khoản</li>
        </ol>
      </nav>
    </div>
    <div class="row justify-content-center" style="margin-top: 118px">
      <div class="d-flex flex-column col-3 border-end">
        <a class="custom_ref border-bottom " href="#temrs_use">Điều khoản sử dụng.</a>
        <a class="custom_ref border-bottom " href="#terms_rule">1.Quy định chung.</a>
        <a class="custom_ref border-bottom " href="#terms_product">2.Sản phẩm và dịch vụ.</a>
        <a class="custom_ref border-bottom " href="#terms_cart">3.Đơn hàng và giá cả.</a>
        <a class="custom_ref border-bottom " href="#term_intel">4.Quyền sở hữu trí tuệ.</a>
        <a class="custom_ref border-bottom " href="#term_response">5.Giới hạn trách nhiệm.</a>
        <a class="custom_ref border-bottom " href="#term_condition">6.Thay đổi điều kiện.</a>
        <a class="custom_ref border-bottom " href="#term_tel">7.Liên hệ</a>
      </div>
      <div class="col-5 ps-5 mt-5">
        <div class="custom_section" id="temrs_use">
          <h3 class="fs-1 pb-4">Điều Khoản Sử Dụng</h3>
          <p class="content">
            Chào mừng bạn đến với website của chúng tôi. Khi truy cập và sử dụng
            dịch vụ tại trang web này, bạn đồng ý tuân thủ các điều khoản và
            điều kiện được quy định dưới đây. Vui lòng đọc kỹ các điều khoản này
            trước khi sử dụng dịch vụ của chúng tôi.
          </p>
        </div>
        <div class="custom_section"  id="terms_rule">
          <h2 class="pb-4">1. Quy Định Chung</h2>
          <p class="content">
            Khi sử dụng website của chúng tôi, bạn đồng ý không sử dụng website
            cho các mục đích bất hợp pháp hoặc bị cấm theo các điều khoản này.
            Bạn sẽ không thực hiện các hành động có thể gây ảnh hưởng hoặc làm
            gián đoạn website hoặc dịch vụ của chúng tôi.
          </p>
        </div>
        <div class="custom_section" id="terms_product">
          <h2 class="pb-4">2. Sản Phẩm và Dịch Vụ</h2>
          <p class="content">
            Chúng tôi cam kết cung cấp thông tin chính xác nhất có thể về sản
            phẩm, tuy nhiên không tránh khỏi những sai sót. Tất cả các sản phẩm
            trên website đều có thể thay đổi mà không cần báo trước. Chúng tôi
            không chịu trách nhiệm cho bất kỳ thông tin nào về sản phẩm không
            chính xác hoặc chưa cập nhật.
          </p>
        </div>
        <div class="custom_section" id="terms_cart">
          <h2 class="pb-4">3. Đơn Hàng và Giá Cả</h2>
          <p class="content">
            Khi đặt hàng tại trang web, bạn đồng ý cung cấp thông tin đầy đủ,
            chính xác và cam kết thực hiện thanh toán đúng thời hạn. Giá cả và
            khuyến mãi có thể thay đổi mà không cần thông báo trước. Chúng tôi
            bảo lưu quyền từ chối hoặc hủy bỏ đơn hàng nếu có sai sót trong giá
            cả hoặc khi vi phạm chính sách của chúng tôi.
          </p>
        </div>
        <div class="custom_section" id="term_intel">
          <h2 class="pb-4">4. Quyền Sở Hữu Trí Tuệ</h2>
          <p class="content">
            Toàn bộ nội dung, hình ảnh, video và các yếu tố đồ họa trên website
            thuộc quyền sở hữu của chúng tôi hoặc đối tác. Bạn không được phép
            sao chép, tái sử dụng hoặc chỉnh sửa nội dung từ trang web mà không
            có sự cho phép bằng văn bản từ chúng tôi.
          </p>
        </div>
        <div class="custom_section" id="term_response">
          <h2 class="pb-4">5. Giới Hạn Trách Nhiệm</h2>
          <p class="content">
            Chúng tôi không chịu trách nhiệm với bất kỳ tổn thất hoặc thiệt hại
            nào phát sinh từ việc sử dụng website của chúng tôi. Điều này bao
            gồm cả việc không truy cập được website, hoặc bất kỳ lỗi nào trong
            việc hiển thị thông tin.
          </p>
        </div>
        <div class="custom_section" id="term_condition">
          <h2>6. Thay Đổi Điều Khoản</h2>
          <p class="content">
            Chúng tôi bảo lưu quyền thay đổi, bổ sung hoặc cập nhật điều khoản
            này vào bất kỳ thời điểm nào mà không cần thông báo trước. Bạn có
            trách nhiệm xem lại các điều khoản thường xuyên để đảm bảo bạn đã
            nắm rõ và tuân thủ.
          </p>
        </div>
        <div class="custom_section" id="term_tel">
          <h2>7. Liên Hệ</h2>
          <p class="content">
            Nếu bạn có bất kỳ câu hỏi nào về Điều Khoản Sử Dụng, vui lòng liên
            hệ với chúng tôi qua email [Thomas@contact.tm.com] hoặc số điện
            thoại [0123456789].
          </p>
        </div>
      </div>
    </div>
    <jsp:include page = "/frontend/header_footer/footer.jsp" />

  </body>
</html>
