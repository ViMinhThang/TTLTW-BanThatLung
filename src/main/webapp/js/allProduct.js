$(document).ready(function () {
    $(".fa-trash-can").on("click", function () {
        const variantRow = $(this).closest("tr"); // Get clicked row
        console.log("Variant Row:", variantRow);

        // Find the nearest product row inside the same tbody
        const productRow = variantRow.closest("tbody").find("tr.belt-row").first();
        console.log("Product Row Found:", productRow);

        const productId = productRow.find(".productId").text().trim();
        const variantId = variantRow.find(".variantId").text().trim();

        console.log("Extracted Product ID:", productId);
        console.log("Extracted Variant ID:", variantId);

        $(".removeModalBody").text(`Xóa sản phẩm này #${productId}`);
        $(".deleteBtn input[name='productId']").remove();
        $(".deleteBtn input[name='variantId']").remove();
        $(".deleteBtn input[name='message']").remove();

        $(".deleteBtn").prepend(`
            <input type="hidden" name="productId" value="${productId}">
            <input type="hidden" name="variantId" value="${variantId}">
            <input type="hidden" name="message" value="delete">
        `);
    });

    $('#removeModal').on('hidden.bs.modal', function () {
        $(".deleteBtn input[name='variantId']").remove();
        $(".deleteBtn input[name='productId']").remove();
        $(".deleteBtn input[name='message']").remove();
    });
});
