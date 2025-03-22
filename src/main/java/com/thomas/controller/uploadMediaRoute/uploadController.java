package com.thomas.controller.uploadMediaRoute;

import com.thomas.services.ProductService;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

@WebServlet(name = "uploadController", value = "/upload")
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024, // 1MB
        maxFileSize = 10 * 1024 * 1024,  // 10MB
        maxRequestSize = 50 * 1024 * 1024 // 50MB
)

public class uploadController extends HttpServlet {
    // Path where files will be saved
    ProductService productService = new ProductService();
    private static final String ULOAD_DIR = "uploads";
    private static final String UPLOAD_DIR_SERVER = "C:\\Users\\huynh\\OneDrive\\Desktop\\Nhom38-BanThatLung\\src\\main\\webapp\\assets\\uploads";

    // Configure the maximum file size
    private final int MAX_FILE_SIZE = 10 * 1024 * 1024; // 10MB
    private final int MAX_REQUEST_SIZE = 50 * 1024 * 1024; // 50MB

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int beltId = Integer.parseInt(request.getParameter("id"));
        int variantId = Integer.parseInt(request.getParameter("variantId"));
        // Check if the request contains a file upload
        if (request.getContentType() != null && request.getContentType().startsWith("multipart/")) {

            // Get the upload path
            String uploadPath = getServletContext().getRealPath("") + ULOAD_DIR + File.separator + "_" + "belt" + beltId;

            // Create the upload directory if it doesn't exist
            File uploadDir = new File(uploadPath);
            if (!uploadDir.exists()) {
                uploadDir.mkdir();
            }
            File serverDirectory = new File(UPLOAD_DIR_SERVER + File.separator + "_" + "belt" + beltId);
            if (!serverDirectory.exists()) {
                serverDirectory.mkdirs();
            }
            // Prepare JSON response
            response.setContentType("application/json");
            PrintWriter out = response.getWriter();
            StringBuilder jsonResponse = new StringBuilder();
            jsonResponse.append("{");
            jsonResponse.append("\"uploaded\": true, \"files\": [");
            List<String> descImage = new ArrayList<>();
            // Iterate through the parts in the request
            boolean firstFile = true;
            for (Part part : request.getParts()) {
                // Check if the part is a file
                String contentDisposition = part.getHeader("content-disposition");
                if (contentDisposition != null && contentDisposition.contains("filename=")) {
                    // Extract file name
                    String fileName = extractFileName(part);

                    if (fileName != null && !fileName.isEmpty()) {
                        // Create a file path where the file will be saved
                        String uniqueFileName = beltId + "_" + "desc" + System.currentTimeMillis() + getFileExtension(fileName);
                        String filePath = uploadDir.getAbsolutePath() + File.separator + uniqueFileName;

                        // Save the file to the server
                        part.write(filePath);
                        part.write(serverDirectory + File.separator + uniqueFileName);
                        descImage.add(File.separator + ULOAD_DIR + File.separator + "_" + "belt" + beltId + File.separator + uniqueFileName);
                        // Append file details to the JSON response
                        if (!firstFile) {
                            jsonResponse.append(",");
                        }
                        String fileUrl = "/uploads/" + fileName; // The URL to access the uploaded file
                        jsonResponse.append("{\"name\": \"").append(fileName).append("\", \"url\": \"").append(fileUrl).append("\"}");
                        firstFile = false;
                    }
                }
            }
            productService.saveDescImg(beltId, descImage, variantId);

            jsonResponse.append("]");
            jsonResponse.append("}");

            // Send JSON response
            out.write(jsonResponse.toString());
            out.flush();
        } else {
            response.setContentType("application/json");
            response.getWriter().write("{\"uploaded\": false, \"error\": \"Not a multipart request.\"}");
        }

    }

    // Helper method to extract the file name from the Part
    private String extractFileName(Part part) {
        String contentDisposition = part.getHeader("content-disposition");
        for (String cd : contentDisposition.split(";")) {
            if (cd.trim().startsWith("filename")) {
                String fileName = cd.substring(cd.indexOf('=') + 2, cd.length() - 1);
                return fileName;
            }
        }
        return null;
    }

    private String getFileExtension(String filename) {
        return filename.substring(filename.lastIndexOf("."));
    }
}

