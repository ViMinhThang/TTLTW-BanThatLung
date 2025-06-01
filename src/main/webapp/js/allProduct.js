$(document).ready(function () {
    $(".fa-trash-can").on("click", function () {
        const beltId = $(this).closest("tr").find(".beltId").text();
        console.log(beltId);
        $(".removeModalBody").text(`Xóa sản phẩm #${beltId}`);
        // Xóa các input cũ trước khi thêm mới
        // Xóa các input cũ (nếu có) nhưng giữ lại nút "Xóa"
        $(".deleteBtn input[name='beltId']").remove();
        $(".deleteBtn input[name='message']").remove();

        // Thêm input ẩn vào form nút delete
        $(".deleteBtn").prepend(`
            <input type="hidden" name="beltId" value="${beltId}">
            <input type="hidden" name="message" value="delete">
        `);
    });

    $('#removeModal').on('hidden.bs.modal', function () {
        $(".deleteBtn input[name='couponId']").remove();
        $(".deleteBtn input[name='message']").remove();
    });
    $('#createModal').on('hidden.bs.modal', function () {
        $("#createCouponForm")[0].reset();
        $(".messageCreateOrUpdate").val("create");
        $(".modal-title").text("Tạo Sản phẩm");
        $(".createOrUpdate").text("Tạo");
        $(".createOrUpdate input[name='beltId']").remove();
    });
    $('input[name="supplierName"]').on('input', function () {
        const keyword = $(this).val().trim();
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
        const $nameInput = $('input[name="supplierName"]');
        const $supplierSuggestions = $('#supplierSuggestions');

        $(document).on('click', function (e) {
            if (!$(e.target).closest('input[name="supplierName"]').length) {
                $supplierSuggestions.hide();
            }
        });

        $supplierSuggestions.on('click', '.suggestion-item', function () {
            const selected = $(this).text();
            $nameInput.val(selected).trigger('blur');
            $supplierSuggestions.hide();
        });

    });

});