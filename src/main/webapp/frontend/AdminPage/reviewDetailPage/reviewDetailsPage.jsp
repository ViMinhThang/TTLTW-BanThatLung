<jsp:useBean id="beltName" scope="request" type="java.lang.String"/>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>

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
    <link rel="icon" href="${pageContext.request.contextPath}/assets/icons/favicon.svg" type="image/x-icon"/>

    <!-- Custom fonts for this template-->
    <link
            href="${pageContext.request.contextPath}/css/fontawesome-free-6.6.0-web/fontawesome-free-6.6.0-web/css/all.min.css"
            rel="stylesheet"
            type="text/css"
    />
    <link
            href="https://fonts.googleapis.com/css?family=Nunito:200,200i,300,300i,400,400i,600,600i,700,700i,800,800i,900,900i"
            rel="stylesheet"
    />
    <link rel="stylesheet" href="https://cdn.ckeditor.com/ckeditor5/44.0.0/ckeditor5.css">

    <!-- Custom styles for this template-->
    <script src="https://cdn.ckeditor.com/ckeditor5/44.0.0/ckeditor5.umd.js"></script>
    <link href="${pageContext.request.contextPath}/css/dataTables.bootstrap4.min.css" rel="stylesheet"/>
    <link href="${pageContext.request.contextPath}/css/adminGeneral.css" rel="stylesheet"/>


</head>

<body id="page-top">
<div id="wrapper">
    <jsp:include page="/frontend/components/sidebar/sidebar.jsp"/>

    <div id="content-wrapper" class="d-flex flex-column">
        <div id="content">

            <div class="container-fluid px-0">
                <h1 class="title-create-edit mb-2 mt-5 ml-3">Xem chi tiết reviews</h1>
                <form id="productForm" method="POST"
                      class="d-flex flex-column justify-content-between" enctype="multipart/form-data">

                    <input type="hidden" name="reviewId" value="${param.reviewId}"/>
                    <input type="hidden" name="message" value="delete"/>
                    <div class="d-flex w-100">
                        <div class="col-12 bg-white">
                            <div class="d-flex flex-column mt-5">
                                <div class="mt-5 ms-5">
                                    <h1 class="fw-bold">
                                        Xem reviews #${param.reviewId}
                                    </h1>
                                </div>
                            </div>
                            <div
                                    class="d-flex flex-column justify-content-left align-items-left"
                            >
                                <div class="d-flex flex-column mt-5 w-50">
                                    <div class="row mb-3">
                                        <p
                                                class="col-3 mb-0 d-flex justify-content-end align-items-center fs-5"
                                        >
                                            Tên sản phẩm
                                        </p>
                                        <input
                                                name="beltName"
                                                type="text"
                                                class="col form-control form-control-lg custom_design custom__border inputField"
                                                placeholder="Nhập tên sản phẩm"
                                                value="${beltName}"
                                                disabled

                                        />
                                    </div>
                                    <div class="row mb-3">
                                        <p
                                                class="col-3 mb-0 d-flex justify-content-end align-items-center fs-5"
                                        >
                                            Tên người dùng
                                        </p>
                                        <input
                                                name="beltName"
                                                type="text"
                                                class="col form-control form-control-lg custom_design custom__border inputField"
                                                value="${review.reviewerName}"
                                                disabled
                                        />
                                    </div>
                                    <div class="row mb-3">
                                        <p
                                                class="col-3 mb-0 d-flex justify-content-end align-items-center fs-5"
                                        >
                                            Nội dung
                                        </p>
                                        <textarea
                                                name="tags"
                                                class="col form-control form-control-lg custom_design custom__border"
                                                disabled
                                        >${review.content}</textarea>
                                    </div>
                                    <div class="row mb-3">
                                        <p
                                                class="col-3 mb-0 d-flex justify-content-end align-items-center fs-5"
                                        >
                                            Số sao
                                        </p>
                                        <input
                                                name="ratingStar"
                                                type="number"
                                                class="col form-control form-control-lg custom_design custom__border inputField"
                                                placeholder="Nhập tên sản phẩm"
                                                value="${review.reviewerStar}"
                                                disabled

                                        />
                                    </div>

                                    <div class="row mb-3">
                                        <p
                                                class="col-3 mb-0 d-flex justify-content-end align-items-center fs-5"
                                        >
                                            Ngày review
                                        </p>
                                        <input name="releaseDate" type="date"
                                               class="col form-control form-control-lg custom_design custom__border inputField"
                                               value="${review.createdAt}"
                                               disabled
                                        />
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>

                </form>

            </div>
        </div>

        <jsp:include page="/frontend/components/adminFooter/adminFooter.jsp"/>

    </div>
</div>

<a class="scroll-to-top rounded" href="#page-top">
    <i class="fas fa-angle-up"></i>
</a>

</div>

<script
        src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
        crossorigin="anonymous"
></script>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.bundle.min.js"></script>

<!-- Core plugin JavaScript-->
<script src="${pageContext.request.contextPath}/js/jquery.easing.min.js"></script>

<!-- Custom scripts for all pages-->
<script src="${pageContext.request.contextPath}/js/sb-admin-2.min.js"></script>
<script>

</script>
<script>
    const {
        ClassicEditor,
        Essentials,
        Paragraph,
        Bold,
        Italic,
        Font,
        DecoupledEditor, SimpleUploadAdapter, Image, ImageUpload
    } = CKEDITOR;

    ClassicEditor
        .create(document.querySelector('.document-editor__editing'), {
            licenseKey: 'eyJhbGciOiJFUzI1NiJ9.eyJleHAiOjE3NjU2NzAzOTksImp0aSI6IjU3ZjA1MWMwLTIyZmMtNDNjYy1hYTM4LTY5NjIyODQ1OGYzOCIsInVzYWdlRW5kcG9pbnQiOiJodHRwczovL3Byb3h5LWV2ZW50LmNrZWRpdG9yLmNvbSIsImRpc3RyaWJ1dGlvbkNoYW5uZWwiOlsiY2xvdWQiLCJkcnVwYWwiXSwiZmVhdHVyZXMiOlsiRFJVUCIsIkJPWCJdLCJ2YyI6IjIxMzg3ZTM2In0.2zYk8hnu1hlPu3OCtlLbUqaXqpzQEJVr0vCU_cihJ6f7lsKvqtTMNXiYUwxN9_HlskEP8jb-U3mGF_e-PHnS3w',
            plugins: [Essentials, Paragraph, Bold, Italic, Font, SimpleUploadAdapter, Image, ImageUpload],
            toolbar: {
                items: [
                    'undo', 'redo', '|', 'bold', 'italic', 'link', '|',
                    'fontSize', 'fontFamily', 'fontColor', 'fontBackgroundColor',
                    'bulletedList', 'numberedList', '|',
                    'imageUpload', '|',
                    'undo', 'redo'
                ]
            },
            simpleUpload: {
                uploadUrl: '/upload',
                withCredentials: false,

            },
            image: {
                toolbar: [
                    'imageTextAlternative',
                    'imageStyle:full',
                    'imageStyle:side'
                ]
            }
        })
        .then(editor => {
            const toolbarContainer = document.querySelector('.document-editor__toolbar');
            toolbarContainer.appendChild(editor.ui.view.toolbar.element);
            window.editor = editor;
        })
        .catch(error => {
            console.error('Error initializing CKEditor:', error);
        });
</script>
<script>
    // Reference to the checkbox and hidden input
    const checkbox = document.getElementById('isDeleted');
    const hiddenInput = document.getElementById('hiddenIsDeleted');

    // Update hidden input value when checkbox state changes
    checkbox.addEventListener('change', function () {
        hiddenInput.value = this.checked ? 1 : 0;
    });
</script>
</body>
</html>
