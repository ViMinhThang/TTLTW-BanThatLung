package com.thomas.controller.userInfoRoutes;

import com.thomas.services.UploadUserService;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;

import java.io.File;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@WebServlet(name = "userProfileController", value = "/userProfile")
@MultipartConfig(
        fileSizeThreshold = 1024 * 1024 * 2,
        maxFileSize = 1024 * 1024 * 10,
        maxRequestSize = 1024 * 1024 * 50
)
public class userProfileController extends HttpServlet {
    UploadUserService uploadUserService = new UploadUserService();
    private static final String ULOAD_DIR = "uploads";
    private static final String UPLOAD_DIR_SERVER = "C:\\Users\\huynh\\OneDrive\\Desktop\\Nhom38-BanThatLung\\src\\main\\webapp\\uploads";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {


        request.getRequestDispatcher("/frontend/userInfoPage/profile/profile.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE;
        String message = request.getParameter("message");
        if (message.equals("update")) {
            int userId = Integer.parseInt(request.getParameter("userId"));
            String gender = request.getParameter("gender");
            String birthdayString = request.getParameter("birthDate");
            LocalDate birthday = LocalDate.parse(birthdayString, formatter);
            String username = request.getParameter("userName");
            long phoneNumber = Long.parseLong(request.getParameter("phoneNumber"));
            uploadUserService.updateUserInfo(userId, gender, birthday, phoneNumber);
            String uploadPath = request.getServletContext().getRealPath("") + File.separator + ULOAD_DIR;
            Part filePart = request.getPart("uploadProfileImage");
            if (filePart != null && filePart.getSize() > 0) {
                handleFileUpload(request, username, uploadPath, uploadUserService, userId);

            }
            response.sendRedirect("/userProfile");
        }

    }

    private void handleFileUpload(HttpServletRequest request, String userName, String uploadPath, UploadUserService uploadUserService, int userId) throws ServletException, IOException {
        try {
            String mainImage = null;
            userName = userName.replace(" ", "_");
            File userDirectory = new File(uploadPath + File.separator + userName);
            if (!userDirectory.exists()) {
                userDirectory.mkdirs();
            }
            File serverDirectory = new File(UPLOAD_DIR_SERVER + File.separator + userName);
            if (!serverDirectory.exists()) {
                serverDirectory.mkdirs();
            }
            for (Part part : request.getParts()) {
                String fieldName = part.getName();
                String fileName = extractedFile(part);

                if (fileName != null && !fileName.isEmpty()) {
                    String uniqueFileName = userName + "_" + userId + "_" + System.currentTimeMillis() + getFileExtension(fileName);
                    String filePath = userDirectory.getAbsolutePath() + File.separator + uniqueFileName;
                    part.write(filePath);
                    part.write(serverDirectory + File.separator + uniqueFileName);
                    mainImage = File.separator + ULOAD_DIR + File.separator + userName + File.separator + uniqueFileName;
                }
            }
            uploadUserService.updateImagePath(request, userId, mainImage);
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

