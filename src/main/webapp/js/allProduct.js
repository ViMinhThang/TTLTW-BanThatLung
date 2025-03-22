$(document).ready(function () {
    $(".fa-trash-can").on("click", function () {
        const variantRow = $(this).closest("tr");
        console.log("Variant Row:", variantRow);

        const productRow = variantRow.closest("tbody").find("tr.belt-row").first();
        console.log("Product Row Found:", productRow);

        const productId = productRow.find(".productId").text().trim();
        const variantId = variantRow.find(".variantId").text().trim();

        console.log("Extracted Product ID:", productId);
        console.log("Extracted Variant ID:", variantId);

        const messageValue = variantId ? "delete" : "deleteRealVariant";

        $(".removeModalBody").text(`Xóa sản phẩm này #${productId}`);
        $(".deleteBtn input[name='productId']").remove();
        $(".deleteBtn input[name='variantId']").remove();
        $(".deleteBtn input[name='message']").remove();

        $(".deleteBtn").prepend(`
            <input type="hidden" name="productId" value="${productId}">
            ${variantId ? `<input type="hidden" name="variantId" value="${variantId}">` : ""}
            <input type="hidden" name="message" value="${messageValue}">
        `);
    });

    $('#removeModal').on('hidden.bs.modal', function () {
        $(".deleteBtn input[name='variantId']").remove();
        $(".deleteBtn input[name='productId']").remove();
        $(".deleteBtn input[name='message']").remove();
    });
});
