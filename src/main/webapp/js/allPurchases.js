$(document).ready(function () {
    $(".fa-trash-can").on("click", function () {
        const purchaseId = $(this).closest("tr").find(".purchaseId").text();
        console.log(purchaseId);
        $(".removeModalBody").text(`Xóa phiếu nhập #${purchaseId}`);
        // Xóa các input cũ trước khi thêm mới
        // Xóa các input cũ (nếu có) nhưng giữ lại nút "Xóa"
        $(".deleteBtn input[name='purchaseId']").remove();
        $(".deleteBtn input[name='message']").remove();

        // Thêm input ẩn vào form nút delete
        $(".deleteBtn").prepend(`
            <input type="hidden" name="purchaseId" value="${purchaseId}">
            <input type="hidden" name="message" value="delete">
        `);
    });

    $('#removeModal').on('hidden.bs.modal', function () {
        $(".deleteBtn input[name='purchaseId']").remove();
        $(".deleteBtn input[name='message']").remove();
    });
    $(".fa-pen-to-square").on("click", function () {
        const purchaseId = $(this).closest("tr").find(".purchaseId").text();

        $.ajax({
            url: "http://localhost:8080/admin/inventory/purchases",
            method: "GET",
            data: {
                message: "takePurchase",
                purchaseId: purchaseId,
            },
            dataType: "json",
            success: function (data, textStatus, xhr) {
                console.log('AJAX Success:', data);
                console.log('Response Status:', textStatus);
                $(".modal-title").text(`Sửa phiếu nhập #${purchaseId}`);
                $(".messageCreateOrUpdate").val("update");
                $("input[name='name']").val(data.supplierName);
                $("input[name='productName']").val(data.beltName);
                $("input[name='purchaseDate']").val(data.purchaseDate);
                $("input[name='createdAt']").val(data.createdAt);
                $("input[name='updatedAt']").val(data.updatedAt);
                $("input[name='quantity']").val(data.quantity);
                // Change button text to 'Cập nhật'
                $(".createOrUpdate").text("Cập nhật").prepend(`
                    <input type="hidden" name="purchaseId" value="${purchaseId}">
                `);

                // Show the modal
                $("#createModal").modal("show");
            },
            error: function (xhr, textStatus, errorThrown) {
                // Log the error details
                console.error('AJAX Error:', textStatus, errorThrown);  // Logs the error
                alert("Lỗi không lấy được dữ liệu , thử lại sau.");
            }
        });
    });
    $('#createModal').on('hidden.bs.modal', function () {
        $("#createCouponForm")[0].reset();
        $(".messageCreateOrUpdate").val("create");
        $(".modal-title").text("Tạo phiếu nhập");
        $(".createOrUpdate").text("Tạo");
        $(".createOrUpdate input[name='purchaseId']").remove();
    });
    $('input[name="name"]').on('input', function () {
        const keyword = $(this).val().trim();
        if (keyword.length === 0) {
            $('#beltSuggestions').hide();
            return;
        }

        $.ajax({
            url: '/SearchSupplierNames', method: 'GET', data: {keyword}, success: function (response) {
                const suggestionBox = $('#supplierSuggestions');
                suggestionBox.empty();
                if (response.names && response.names.length > 0) {
                    response.names.forEach(name => {
                        suggestionBox.append(`<div class="suggestion-item">${name}</div>`);
                    });
                    suggestionBox.show();
                } else {
                    suggestionBox.hide();
                }
            }
        });
    });

    $(function () {
        const $nameInput = $('input[name="name"]');
        const $productNameInput = $('input[name="productName"]');
        const $colorSelect = $('select[name="colorSelect"]');
        const $sizeSelect = $('select[name="sizeSelect"]');
        const $supplierSuggestions = $('#supplierSuggestions');
        const $beltSupplierSuggestions = $('#BeltSupplierSuggestions');

        function populateSelect($select, items) {
            $select.empty().append('<option value="">--Chọn--</option>');
            items.forEach(item => {
                $select.append(`<option value="${item}">${item}</option>`);
            });
        }

        $(document).on('click', function (e) {
            if (!$(e.target).closest('#supplierSuggestions, input[name="name"]').length) {
                $supplierSuggestions.hide();
            }
            if (!$(e.target).closest('#BeltSupplierSuggestions, input[name="productName"]').length) {
                $beltSupplierSuggestions.hide();
            }
        });

        $supplierSuggestions.on('click', '.suggestion-item', function () {
            const selected = $(this).text();
            $nameInput.val(selected).trigger('blur');
            $supplierSuggestions.hide();
        });

        $productNameInput.on('input', function () {
            const keyword = $(this).val().trim();
            if (!keyword) return $beltSupplierSuggestions.hide();

            $.get('/SearchSupplierProducts', {keyword}, function (response) {
                const names = response.names || [];
                $beltSupplierSuggestions.empty();

                if (names.length > 0) {
                    names.forEach(name => {
                        $beltSupplierSuggestions.append(`<div class="suggestion-item">${name}</div>`);
                    });
                    $beltSupplierSuggestions.show();
                } else {
                    $beltSupplierSuggestions.hide();
                }
            });
        });

        $beltSupplierSuggestions.on('click', '.suggestion-item', function () {
            const selected = $(this).text();
            $productNameInput.val(selected).trigger('blur');
            $beltSupplierSuggestions.hide();
        });

        $productNameInput.on('blur', function () {
            const name = $(this).val().trim();
            if (!name) return;

            $.get('/getColorsAndSizes', {name}, function (response) {
                populateSelect($colorSelect, response.colors || []);
                populateSelect($sizeSelect, response.sizes || []);
            });
        });

        $colorSelect.on('change', function () {
            const name = $productNameInput.val().trim();
            const size = $sizeSelect.val();
            const color = $(this).val();

            if (!color && name && size) {
                $.get('/getColorsByNameAndSize', {name, size}, function (response) {
                    populateSelect($colorSelect, response.colors || []);
                });
            }
        });

        $sizeSelect.on('change', function () {
            const name = $productNameInput.val().trim();
            const color = $colorSelect.val();
            const size = $(this).val();

            if (!size && name && color) {
                $.get('/getSizesByNameAndColor', {name, color}, function (response) {
                    populateSelect($sizeSelect, response.sizes || []);
                });
            }
        });
    });

});