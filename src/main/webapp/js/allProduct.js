$(document).ready(function () {
    $(document).on("click", ".fa-trash-can", function () {
        const variantRow = $(this).closest("tr");
        console.log("🗑️ Clicked on variant row:", variantRow);

        // Find the corresponding product row by getting the closest 'belt-row' parent
        const productRow = variantRow.closest("tbody").find("tr.belt-row").first();
        console.log("🛍️ Found product row:", productRow);

        // Get the productId from the product row by targeting the .productId cell
        const productId = productRow.find(".productId").text().trim();  // Fixed here
        const variantId = variantRow.find(".variantId").text().trim();

        console.log("📦 Extracted Product ID:", productId);
        console.log("🎨 Extracted Variant ID:", variantId);

        const messageValue = variantId ? "delete" : "deleteRealVariant";

        $(".removeModalBody").text(`Xóa sản phẩm này #${productId}`);
        $(".deleteBtn input").remove(); // Clear previous inputs

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
