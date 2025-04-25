package com.thomas.controller.AdminRoute.table.belts;

import com.thomas.dao.model.BeltVariant;
import com.thomas.dao.model.Belts;
import com.thomas.services.ProductService;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet(name = "createProductController", value = "/admin/table/belts/createProduct")
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 2,
        maxFileSize = 1024 * 1024 * 10,
        maxRequestSize = 1024 * 1024 * 50
)
public class createProductController extends HttpServlet {
    private static final String ULOAD_DIR = "uploads";
    private static final String UPLOAD_DIR_SERVER = "D:\\University\\ttltw\\TTLTW-BanThatLung\\src\\main\\webapp\\assets\\images\\uploads";
    private static final ProductService PRODUCT_SERVICE = new ProductService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String message = request.getParameter("message");
        switch (message) {
            case "edit":
                String beltIdParam = request.getParameter("id");
                int variantIdParam = Integer.parseInt(request.getParameter("variantId"));
                int colorId = Integer.parseInt(request.getParameter("colorId"));
                int sizeId = Integer.parseInt(request.getParameter("sizeId"));
                if (beltIdParam != null) {
                    int beltId = Integer.parseInt(beltIdParam);
                    Belts belt = PRODUCT_SERVICE.find(beltId).get(0);
                    BeltVariant variants = PRODUCT_SERVICE.findVariant(beltId, variantIdParam, colorId, sizeId);
                    variants.setImages(PRODUCT_SERVICE.getVariantImages(variants.getId()));
                    variants.setColor(PRODUCT_SERVICE.findColorNameById(variants.getColorId()));
                    variants.setSize(PRODUCT_SERVICE.findSizeNameById(variants.getSizeId()));
                    belt.setBeltVariant(variants);
                    String[] tagsArray = PRODUCT_SERVICE.getTags(beltId);
                    request.setAttribute("belt", belt);
                    String tags = String.join(" ", tagsArray);
                    request.setAttribute("tags", tags);

                }
                break;
            case "create":
                break;
        }

        request.getRequestDispatcher("/frontend/AdminPage/createProductPage/createProductPage.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDateTime createdAt = LocalDateTime.now();

        String message = request.getParameter("message");
        String beltName = request.getParameter("beltName");
        String gender = request.getParameter("gender");
        String tags = request.getParameter("tags");
        String material = request.getParameter("material");
        String releaseDateRaw = request.getParameter("releaseDate");
        LocalDateTime releaseDate;
        int beltId = 0;
        int variantId = 0;
        try {
            releaseDate = LocalDate.parse(releaseDateRaw, formatter).atStartOfDay();
        } catch (DateTimeParseException e) {
            throw new ServletException("Invalid release date format", e);
        }
        long price = Long.parseLong(request.getParameter("price"));
        int isDeleted = Integer.parseInt(request.getParameter("isDeleted"));
        double discountRate = Double.parseDouble(request.getParameter("discountRate"));
        switch (message) {
            case "edit":
                beltId = Integer.parseInt(request.getParameter("beltId"));
                variantId = Integer.parseInt(request.getParameter("variantId"));
                int oldColorId = Integer.parseInt(request.getParameter("colorId"));
                int oldSizeId = Integer.parseInt(request.getParameter("sizeId"));
                int newColorId = Integer.parseInt(request.getParameter("color"));
                int newSizeId = Integer.parseInt(request.getParameter("size"));

                Belts belt = PRODUCT_SERVICE.find(beltId).get(0);
                BeltVariant variant = PRODUCT_SERVICE.findVariant(beltId, variantId, oldColorId, oldSizeId);


                belt.setName(beltName);
                belt.setMaterialBelt(material);
                belt.setGender(gender);
                belt.setReleaseDate(releaseDate);
                belt.setIsDeleted(isDeleted);
                belt.setDiscountRate(discountRate);
                belt.setUpdatedAt(createdAt);

                variant.setBeltId(beltId);
                variant.setPrice(price);
                variant.setSizeId(newSizeId);
                variant.setColorId(newColorId);
                variant.setUpdatedAt(createdAt);

                PRODUCT_SERVICE.saveTags(tags, beltId, variantId);

                if (PRODUCT_SERVICE.updateProduct(belt)) {
                    PRODUCT_SERVICE.updateVariant(variant);
                    response.sendRedirect(request.getContextPath() + "/admin/table/belts");
                }
                break;
            case "create":
                int colorId = Integer.parseInt(request.getParameter("color"));
                int sizeId = Integer.parseInt(request.getParameter("size"));

                Belts b = new Belts();
                b.setName(beltName);
                b.setGender(gender);
                b.setMaterialBelt(material);
                b.setReleaseDate(releaseDate);
                b.setIsDeleted(isDeleted);
                b.setDiscountRate(discountRate);
                b.setCreatedAt(createdAt);
                b.setUpdatedAt(createdAt);

                boolean created = PRODUCT_SERVICE.createBelt(b);

                BeltVariant v = new BeltVariant();

                if (created) {
                    beltId = PRODUCT_SERVICE.getLatestProductId();

                    v.setBeltId(beltId);
                    v.setSizeId(sizeId);
                    v.setColorId(colorId);
                    v.setPrice(price);
                    v.setCreatedAt(createdAt);
                    v.setUpdatedAt(createdAt);
                    if (PRODUCT_SERVICE.createVariant(v)) {
                        variantId = PRODUCT_SERVICE.getLatestVariantId();
                        PRODUCT_SERVICE.saveTags(tags, beltId, variantId);
                        response.sendRedirect(request.getContextPath() + "/admin/table/belts");
                    }
                }
                break;
        }


        String uploadPath = request.getServletContext().getRealPath("") + File.separator + ULOAD_DIR;
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }
        handleFileUpload(request, beltName, uploadPath, message, PRODUCT_SERVICE, beltId, variantId);
    }

    private void handleFileUpload(HttpServletRequest request, String productName, String uploadPath, String message, ProductService productService, int productId, int variantId) throws ServletException, IOException {
        try {
            int count = 0;
            List<String> extraImages = new ArrayList<>();
            String mainImage = null;

            File productDirectory = new File(uploadPath + File.separator + productName);
            if (!productDirectory.exists()) {
                productDirectory.mkdirs();
            }
            File serverDirectory = new File(UPLOAD_DIR_SERVER + File.separator + productName);
            if (!serverDirectory.exists()) {
                serverDirectory.mkdirs();
            }
            for (Part part : request.getParts()) {
                String fileName = extractedFile(part);

                if (fileName != null && !fileName.isEmpty() && part.getSize() > 0) {
                    String uniqueFileName = productName + "_" + count + "_" + System.currentTimeMillis() + getFileExtension(fileName);
                    String filePath = productDirectory.getAbsolutePath() + File.separator + uniqueFileName;
                    count++;

                    part.write(filePath);
                    part.write(serverDirectory + File.separator + uniqueFileName);
                    if (count == 1) {
                        mainImage = File.separator + ULOAD_DIR + File.separator + productName + File.separator + uniqueFileName;
                    } else {
                        extraImages.add(File.separator + ULOAD_DIR + File.separator + productName + File.separator + uniqueFileName);
                    }
                }
            }

            if (mainImage != null) {
                if ("create".equals(message)) {
                    int beltId = productService.getLatestProductId();
                    int varId = productService.getLatestVariantId();
                    productService.saveOrUpdateImagePath(beltId, mainImage, extraImages, varId, false);
                } else if ("update".equals(message)) {
                    productService.saveOrUpdateImagePath(productId, mainImage, extraImages, variantId, true);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServletException("Error handling file upload", e);
        }
    }

    private String extractedFile(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        for (String content : contentDisp.split(";")) {
            if (content.trim().startsWith("filename")) {
                return content.substring(content.indexOf("=") + 2, content.length() - 1);
            }
        }
        return null;
    }

    private String getFileExtension(String filename) {
        return filename.substring(filename.lastIndexOf("."));
    }
}

