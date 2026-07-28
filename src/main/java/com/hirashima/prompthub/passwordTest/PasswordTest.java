package com.hirashima.prompthub.passwordTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordTest {

    public static void main(String[] args) {

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        String password = "password123";

        System.out.println(encoder.encode(password));
    }
}
//admin@example.com
//password123