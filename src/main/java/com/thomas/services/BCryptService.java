package com.thomas.services;

import org.mindrot.jbcrypt.BCrypt;

public class BCryptService {

    public static String hashPassword(String password) {
        //strength mặc định là 10
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    public static boolean checkPassword(String password, String hashed) {
        if (hashed == null || !hashed.startsWith("$2a$")) {
            throw new IllegalArgumentException("Hash mật khẩu không đúng định dạng BCrypt");
        }
        return BCrypt.checkpw(password, hashed);
    }
}
