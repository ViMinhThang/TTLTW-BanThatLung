<%@ tag description="Belt Card Component" pageEncoding="UTF-8" %>
<%@ attribute name="belt" required="true" type="com.thomas.dao.model.Belts" %>

<a href="productDetails?beltId=${belt.id}&variantId=${belt.beltVariant.id}" class="text-center hover--black">
    <img src="${belt.beltVariant.images[0]}"
         class="img-fluid w-100 shadow-sm" alt="${belt.name}"
         style="height:403.25px;width: 403.25px !important; object-fit: cover;">
    <div class="mt-2 text-start ps-3">
        <p class="mb-1 text-black">${belt.beltVariant.price} VNĐ</p>
        <p class="text-muted mb-1">${belt.name}</p>
    </div>
</a>
