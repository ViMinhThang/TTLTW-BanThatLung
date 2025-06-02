<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<f:setLocale value="${sessionScope.lang}" scope="session" />
<f:setBundle basename="messages" />
<footer class="border-top">
    <div class="container">
        <div class="row footer mt-4">
            <!-- About Us Column -->
            <div class="col-sm-3 text-start">
                <h5><f:message key="footer.about_us"/></h5>
                <p><f:message key="footer.how_we_work"/></p>
                <p><f:message key="footer.investors"/></p>
                <p><f:message key="footer.careers"/></p>
                <p><f:message key="footer.press"/></p>
                <p><f:message key="footer.impact"/></p>
                <p><f:message key="footer.legal_info"/></p>
            </div>

            <!-- Support Column -->
            <div class="col-sm-3 text-start">
                <h5><f:message key="footer.support"/></h5>
                <p><f:message key="footer.become_investor"/></p>
                <p><f:message key="footer.your_feedback"/></p>
                <p><f:message key="footer.donations"/></p>
                <p><f:message key="footer.surveys"/></p>
            </div>

            <!-- Terms and Conditions Column -->
            <a href="${pageContext.request.contextPath}/frontend/termsPage/termsPage.jsp" class="col-sm-3 text-start text-decoration-none text-dark">
                <h5><f:message key="footer.terms_conditions"/></h5>
                <p><f:message key="footer.privacy_terms"/></p>
                <p><f:message key="footer.usage_terms"/></p>
                <p><f:message key="footer.intellectual_property"/></p>
                <p><f:message key="footer.shipping_policy"/></p>
                <p><f:message key="footer.community_standards"/></p>

            </a>

            <!-- Contact Us Column -->
            <div class="col-sm-3 text-start">
                <h5><f:message key="footer.contact_us"/></h5>
                <p>thomas@contact.tm.com</p>
                <div class="d-flex social-icons">
                    <span class="icon me-2">
                        <img src="https://cdn-web-servlet.vercel.app/images/Facebook.svg" alt="Facebook" />
                    </span>
                    <span class="icon me-2">
                        <img src="https://cdn-web-servlet.vercel.app/images/Instagram.svg" alt="Instagram" />
                    </span>
                    <span class="icon">
                        <img src="https://cdn-web-servlet.vercel.app/images/TwitterX.svg" alt="TwitterX" />
                    </span>
                </div>
            </div>
        </div>
    </div>
    <hr />
    <div class="container pb-5">
        <div class="text-start">
            <p><f:message key="footer.company_name"/></p>
            <p><f:message key="footer.office_address"/></p>
            <p><f:message key="footer.business_license"/></p>
        </div>
    </div>
</footer>