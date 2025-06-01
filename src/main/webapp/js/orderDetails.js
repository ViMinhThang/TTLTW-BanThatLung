$(document).ready(function () {
    $(".custom_button").on("click", function () {
        const orderItemId = $(this).closest("tr").find(".orderItemId").text();
        console.log(orderItemId);
        $(".removeModalBody").text(`Xóa mục đơn hàng #${orderItemId}`);
        // Xóa các input cũ trước khi thêm mới
        // Xóa các input cũ (nếu có) nhưng giữ lại nút "Xóa"
        $(".deleteBtn input[name='orderItemId']").remove();
        $(".deleteBtn input[name='message']").remove();

        $(".deleteBtn").prepend(`
            <input type="hidden" name="orderItemId" value="${orderItemId}">
            <input type="hidden" name="message" value="delete">
        `);
    });

    $('#deleteModal').on('hidden.bs.modal', function () {
        $(".deleteBtn input[name='orderItemId']").remove();
        $(".deleteBtn input[name='message']").remove();
    });
});