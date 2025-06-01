$(document).ready(function () {
    $(".fa-trash-can").on("click", function () {
        const variantId = $(this).closest("tr").find(".variantId").text();
        console.log(variantId);
        $(".removeModalBody").text(`Xóa biến thể #${variantId}`);
        // Xóa các input cũ trước khi thêm mới
        // Xóa các input cũ (nếu có) nhưng giữ lại nút "Xóa"
        $(".deleteBtn input[name='variantId']").remove();
        $(".deleteBtn input[name='message']").remove();

        // Thêm input ẩn vào form nút delete
        $(".deleteBtn").prepend(`
            <input type="hidden" name="variantId" value="${variantId}">
            <input type="hidden" name="message" value="delete">
        `);
    });

    $('#removeModal').on('hidden.bs.modal', function () {
        $(".deleteBtn input[name='variantId']").remove();
        $(".deleteBtn input[name='message']").remove();
    });
    $('#createModal').on('hidden.bs.modal', function () {
        $("#createCouponForm")[0].reset();
        $(".messageCreateOrUpdate").val("create");
        $(".modal-title").text("Tạo Sản phẩm");
        $(".createOrUpdate").text("Tạo");
        $(".createOrUpdate input[name='beltId']").remove();
    });
});