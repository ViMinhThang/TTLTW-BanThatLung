package com.thomas.controller.AdminRoute.table.belts;

import com.thomas.dao.model.BeltVariant;
import com.thomas.dao.model.Belts;
import com.thomas.dao.model.User;
import com.thomas.services.PermissionService;
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
    private static final PermissionService PERMISSION_SERVICE = new PermissionService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        int beltId = Integer.parseInt(request.getParameter("beltId"));
        User user = (User) session.getAttribute("auth");
        boolean permissionToWrite = PERMISSION_SERVICE.checkPermission("ManageProducts", user.getId(), "write");
        boolean permissionToExecute = PERMISSION_SERVICE.checkPermission("ManageProducts", user.getId(), "execute");

        List<Belts> beltsList = PRODUCT_SERVICE.find(beltId);
        List<Belts> display = new ArrayList<>();
        for (Belts belt : beltsList) {
            Integer[] variantId = PRODUCT_SERVICE.getAllVariantId(belt.getId());
            for (int i : variantId) {
                Belts b = new Belts(belt);
                BeltVariant beltVariant = PRODUCT_SERVICE.findVariant(belt.getId(), i, null, null);
                beltVariant.setImages(PRODUCT_SERVICE.getVariantImages(beltVariant.getId()));
                beltVariant.setColor(PRODUCT_SERVICE.findColorNameById(beltVariant.getColorId()));
                beltVariant.setSize(PRODUCT_SERVICE.findSizeNameById(beltVariant.getSizeId()));
                b.setBeltVariant(beltVariant);
                display.add(b);
            }
        }
        request.setAttribute("variants", display);
        request.setAttribute("permissionToWrite", permissionToWrite);
        request.setAttribute("permissionToExecute", permissionToExecute);
        request.getRequestDispatcher("/frontend/AdminPage/createProductPage/createProductPage.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("auth");
        int beltId = Integer.parseInt(request.getParameter("beltId"));
        String message = request.getParameter("message");
        if (message.equals("create")) {
            int color = Integer.parseInt(request.getParameter("color"));
            int size = Integer.parseInt(request.getParameter("size"));
            String description = request.getParameter("description");
            long price = Long.parseLong(request.getParameter("price"));
            PRODUCT_SERVICE.createVariant(new BeltVariant(beltId, size, color, price, LocalDateTime.now(), LocalDateTime.now()));
            String uploadPath = request.getServletContext().getRealPath("") + File.separator + ULOAD_DIR;
            File uploadDir = new File(uploadPath);
            String beltName = PRODUCT_SERVICE.find(beltId).get(0).getName() + "_" + PRODUCT_SERVICE.findColorNameById(color) + "_" + PRODUCT_SERVICE.findSizeNameById(size);
            if (!uploadDir.exists()) {
                uploadDir.mkdirs();
            }
            BeltVariant v = PRODUCT_SERVICE.getLatestVariant();
            PRODUCT_SERVICE.addDescription(v, description);
            handleFileUpload(request, beltName, uploadPath, beltId, v.getId());
        } else if (message.equals("delete")) {
            int variantId = Integer.parseInt(request.getParameter("variantId"));
            PRODUCT_SERVICE.deleteProductVariant(null, variantId, user.getId());
        }
        response.sendRedirect("/admin/table/belts/createProduct?beltId=" + beltId);

    }

    private void handleFileUpload(HttpServletRequest request, String productName, String uploadPath, int productId, int variantId) throws ServletException, IOException {
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
            PRODUCT_SERVICE.saveImagePath(productId, mainImage, extraImages, variantId);
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

