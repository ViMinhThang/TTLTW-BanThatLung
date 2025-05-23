$(document).ready(function () {
    $(".fa-trash-can").on("click", function () {
        const transactionId = $(this).closest("tr").find(".transactionId").text();
        console.log(transactionId);
        $(".removeModalBody").text(`Xóa bản ghi  #${transactionId}`);
        $(".deleteBtn input[name='transactionId']").remove();
        $(".deleteBtn input[name='message']").remove();

        // Thêm input ẩn vào form nút delete
        $(".deleteBtn").prepend(`
            <input type="hidden" name="transactionId" value="${transactionId}">
            <input type="hidden" name="message" value="delete">
        `);
    });

    $('#removeModal').on('hidden.bs.modal', function () {
        $(".deleteBtn input[name='transactionId']").remove();
        $(".deleteBtn input[name='message']").remove();
    });
});