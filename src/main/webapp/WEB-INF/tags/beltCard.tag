<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<%@ tag description="Belt Card Component" pageEncoding="UTF-8" %>
<%@ attribute name="belt" required="true" type="com.thomas.dao.model.Belts" %>
<%@ attribute name="width" required="false" type="java.lang.String" %>
<%@ attribute name="height" required="false" type="java.lang.String" %>
<%@ attribute name="padding" required="false" type="java.lang.String" %>

<a href="productDetails?beltId=${belt.id}&variantId=${belt.beltVariant.id}"
   class="text-center hover--black text-decoration-none ${padding!=null? padding:""}">
    <img src="${belt.beltVariant.images[0]}"
         class="img-fluid w-100 shadow-sm" alt="${belt.name}"
         style="height:${height != null ? height : '403.25px'}; width:${width != null ? width : '403.25px'} !important; object-fit: cover;">
    <div class="mt-2 text-start ps-1">
        <p class="mb-1 text-black">
            <fmt:formatNumber value="${belt.beltVariant.price}" type="number" groupingUsed="true"/> VNĐ
        </p>
        <p class="text-muted mb-1">${belt.name} - ${belt.beltVariant.color}, ${belt.beltVariant.size}</p>
    </div>
</a>
