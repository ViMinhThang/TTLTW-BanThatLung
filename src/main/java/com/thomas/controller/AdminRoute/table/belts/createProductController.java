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
    private static final String UPLOAD_DIR_SERVER = "C:\\Users\\huynh\\OneDrive\\Desktop\\Nhom38-BanThatLung\\src\\main\\webapp\\uploads";
    private static final ProductService PRODUCT_SERVICE = new ProductService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String beltIdParam = request.getParameter("id");
        String variantIdParam = request.getParameter("variantId");
        if (beltIdParam != null && variantIdParam != null) {
            int beltId = Integer.parseInt(beltIdParam);
            Belts belts = PRODUCT_SERVICE.find(beltId).get(0);
            List<BeltVariant> variants = PRODUCT_SERVICE.findVariants(beltId, null, null, null);
            BeltVariant bv = variants.stream()
                    .filter(v -> v.getId() == Integer.parseInt(variantIdParam))
                    .findFirst()
                    .orElse(null);

            bv.setImages(PRODUCT_SERVICE.getVariantImages(bv.getId()));
            String[] tagsArray = PRODUCT_SERVICE.getTags(beltId);
            if (belts != null) {
                request.setAttribute("product", belts);
                request.setAttribute("variants", bv);
                int count = 0;
                for (String s : bv.getImages()) {
                    request.setAttribute("image" + count, s);
                    count++;
                }
                String tags = String.join(" ", tagsArray);
                request.setAttribute("tags", tags);
            } else {
                response.sendRedirect(request.getContextPath() + "/admin/table/belts");
                return;
            }
        }
        request.getRequestDispatcher("/frontend/AdminPage/createProductPage/createProductPage.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE;
        int productId = 0;
        int variantId = 0;
        if (request.getParameter("beltId") != null && !request.getParameter("beltId").isEmpty()) {
            productId = Integer.parseInt(request.getParameter("beltId"));
        }
        if (request.getParameter("variantId") != null && !request.getParameter("variantId").isEmpty()) {
            variantId = Integer.parseInt(request.getParameter("variantId"));
        }
        String message = request.getParameter("message").trim();
        String productName = request.getParameter("beltName");
        String color = request.getParameter("color");
        String size = request.getParameter("size");
        int stockQuantity = Integer.parseInt(request.getParameter("quantity"));
        String[] tags = null;
        LocalDateTime releaseDate = null;
        String gender = null;
        double price = 0;
        double discountRate = 0;
        int isDeleted = 0;
        String material = null;
        if (!message.equals("createVariant")) {
            tags = request.getParameter("tags").split(" ");
            releaseDate = LocalDate.parse(request.getParameter("releaseDate"), formatter).atStartOfDay();
            gender = request.getParameter("gender");
            price = Double.parseDouble(request.getParameter("price"));
            discountRate = Double.parseDouble(request.getParameter("discountRate"));
            isDeleted = Integer.parseInt(request.getParameter("isDeleted"));
            material = request.getParameter("material");
        }
        if (message.equals("create")) {
            PRODUCT_SERVICE.saveProduct(productName, tags, discountRate, releaseDate, gender, price, stockQuantity, material, isDeleted, color, size);
        } else if (message.equals("update")) {
            productId = Integer.parseInt(request.getParameter("beltId"));
            PRODUCT_SERVICE.updateProduct(productId, productName, tags, discountRate, releaseDate, gender, price, stockQuantity, material, isDeleted, color, size, variantId);
        } else if (message.equals("createVariant")) {
            BeltVariant bv = new BeltVariant();
            bv.setBeltId(productId);
            bv.setColor(color);
            bv.setSize(size);
            bv.setStockQuantity(stockQuantity);
            bv.setCreatedAt(LocalDateTime.now());
            bv.setUpdatedAt(LocalDateTime.now());
            PRODUCT_SERVICE.createVariant(bv);
        }
        String uploadPath = request.getServletContext().getRealPath("") + File.separator + ULOAD_DIR;
        File uploadDir = new File(uploadPath);
        if (!uploadDir.exists()) {
            uploadDir.mkdirs();
        }
        handleFileUpload(request, productName, uploadPath, message, PRODUCT_SERVICE, productId, variantId);
        response.sendRedirect(request.getContextPath() + "/admin/table/belts");
    }

    private void handleFileUpload(HttpServletRequest request, String productName, String uploadPath, String message, ProductService productService, int productId, int variantId) throws ServletException, IOException {
        try {
            int count = 0;
            List<String> extraImages = new ArrayList<>();
            List<String> descImages = new ArrayList<>();
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
                String fieldName = part.getName();
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
                } else if ("createVariant".equals(message)) {
                    if (variantId == 0) {
                        variantId = productService.getLatestVariantId();
                    }
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

