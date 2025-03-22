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
    <link rel="stylesheet" href="https://cdn.ckeditor.com/ckeditor5/44.0.0/ckeditor5.css">

    <!-- Custom styles for this template-->
    <script src="https://cdn.ckeditor.com/ckeditor5/44.0.0/ckeditor5.umd.js"></script>
    <jsp:include page="/frontend/components/Admin/sidebar/sidebar.jsp"/>

</head>

<body id="page-top">
<div id="wrapper">
    <jsp:include page="/frontend/components/Admin/sidebar/sidebar.jsp"/>

    <div id="content-wrapper" class="d-flex flex-column">
        <div id="content">

            <div class="container-fluid px-0">
                <h1 class="title-create-edit mb-2 mt-5 ml-3"> Mô tả Sản phẩm</h1>
                <div class="d-flex w-100">
                    <div class="col-12 bg-light">
                        <c:if test="${param.id!=null}">
                            <div
                                    class="overflow-auto d-flex justify-content-center fs-3 mt-5"
                                    style="white-space: nowrap;color:black"
                            >
                                <div class="d-inline-block p-3">
                                    <a
                                            class="text-decoration-none fs-4"
                                            href="${pageContext.request.contextPath}/admin/table/belts/createProduct?id=${param.id}&message=update"
                                            style="font-size: 32px"
                                    >Tổng quan</a
                                    >
                                </div>
                                <div class="d-inline-block p-3 ms-0">
                                    <a
                                            class="text-decoration-none custom_active"
                                            href="${pageContext.request.contextPath}/admin/table/belts/createProductDescription?id=${param.id}&message=update"
                                            style="font-size: 32px"
                                    >Mô tả</a>

                                </div>

                            </div>
                        </c:if>

                    </div>
                </div>
                <form id="productForm"
                      action="${pageContext.request.contextPath}/admin/table/belts/createProductDescription"
                      method="POST"
                      class="d-flex flex-column justify-content-between">
                    <input type="hidden" name="message" value="<c:choose>
    <c:when test='${param.message == "update"}'>update</c:when>
    <c:otherwise>create</c:otherwise>
</c:choose>"/>
                    <input type="hidden" name="beltId" value="${param.id}"/>
                    <input type="hidden" name="variantId" value="${param.variantId}"/>
                    <div class="d-flex w-100">
                        <div class="col-12 bg-white">
                            <div
                                    class="d-flex flex-column mt-5 w-50 pt-5"
                            >
                                <div class="row mb-3">
                                    <p class="col-3 mb-0 d-flex justify-content-end align-items-top"
                                       style="font-size: 32px">
                                        Mô tả
                                    </p>
                                    <div class="document-editor col pr-0">
                                        <div class="document-editor__toolbar"></div>
                                        <div class="document-editor__editing"
                                        ></div>
                                    </div>
                                    <input type="hidden" name="desc" id="editorContent">
                                    <!-- Hidden input to hold CKEditor content -->

                                </div>
                                <div class="row d-flex justify-content-end mb-3 ps-sm-5">
                                    <button type="submit" class="btn btn-dark "
                                            style="width: 20%; height: 47px">
                                        Tạo
                                    </button>
                                </div>
                            </div>

                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

<jsp:include page="/frontend/components/Admin/adminFooter/adminFooter.jsp"/>


<a class="scroll-to-top rounded" href="#page-top">
    <i class="fas fa-angle-up"></i>
</a>

<div
        class="modal fade"
        id="logoutModal"
        tabindex="-1"
        role="dialog"
        aria-labelledby="exampleModalLabel"
        aria-hidden="true"
>
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLabel">
                    Ready to Leave?
                </h5>
                <button
                        class="close"
                        type="button"
                        data-dismiss="modal"
                        aria-label="Close"
                >
                    <span aria-hidden="true">×</span>
                </button>
            </div>
            <div class="modal-body">
                Select "Logout" below if you are ready to end your current
                session.
            </div>
            <div class="modal-footer">
                <button
                        class="btn btn-secondary"
                        type="button"
                        data-dismiss="modal"
                >
                    Cancel
                </button>
                <a class="btn btn-primary" href="login.html">Logout</a>
            </div>
        </div>
    </div>
</div>

<jsp:include page="/frontend/components/Admin/scripts/adminScripts/datatable.jsp"/>

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
                uploadUrl: '/upload?id=${param.id}&message=update',
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
            document.getElementById('productForm').addEventListener('submit', function (e) {
                let editorContent = editor.getData(); // Get the content from CKEditor
                console.log(editorContent)
                editorContent = editorContent.replace(/<figure class="image">.*?<\/figure>/g, "");

                document.getElementById('editorContent').value = editorContent; // Set the hidden input value
                console.log($("#editorContent").val())
            });
        })
        .catch(error => {
            console.error('Error initializing CKEditor:', error);
        });

</script>

</body>
</html>
