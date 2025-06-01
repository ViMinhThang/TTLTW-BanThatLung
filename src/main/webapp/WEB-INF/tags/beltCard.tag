<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ tag description="Belt Card Component" pageEncoding="UTF-8" %>
<%@ attribute name="belt" required="true" type="com.thomas.dao.model.Belts" %>
<%@ attribute name="width" required="false" type="java.lang.String" %>
<%@ attribute name="height" required="false" type="java.lang.String" %>
<%@ attribute name="padding" required="false" type="java.lang.String" %>

<c:choose>
    <c:when test="${belt.beltVariant.stockQuantity<=0}">
        <div class="position-relative text-center hover--black text-decoration-none ${padding!=null? padding:""}">
            <div class="position-absolute top-0 start-0 w-100 h-100 d-flex justify-content-center align-items-center"
                 style="z-index: 2;">
                <div style="
            background-color: rgba(0, 0, 0, 0.7);
            color: white;
            width: 200px;
            height: 200px;
            border-radius: 50%;
            display: flex;
            align-items: center;
            justify-content: center;
            font-size: 20px;
            font-weight: bold;
        ">
                    Hết hàng
                </div>
            </div>

            <img src="${belt.beltVariant.images[0]}"
                 class="img-fluid w-100 shadow-sm" alt="${belt.name}"
                 style="height:${height != null ? height : '403.25px'}; width:${width != null ? width : '403.25px'} !important; object-fit: cover; opacity: 0.3;">

            <div class="mt-2 text-start ps-1">
                <p class="mb-1 text-black">
                    <fmt:formatNumber value="${belt.beltVariant.price}" type="number" groupingUsed="true"/> VNĐ
                </p>
                <p class="text-muted mb-1">${belt.name} - ${belt.beltVariant.color}, ${belt.beltVariant.size}</p>
            </div>
        </div>

    </c:when>
    <c:otherwise>
        <a href="${pageContext.request.contextPath}/belt/${belt.id}/variant/${belt.beltVariant.id}"
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
    </c:otherwise>

</c:choose>

