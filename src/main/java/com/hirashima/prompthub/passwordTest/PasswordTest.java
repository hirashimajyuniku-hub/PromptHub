package com.hirashima.prompthub.passwordTest;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordTest {

    public static void main(String[] args) {

        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

        String password = "password123";
        String password1 = "1234";

        System.out.println(encoder.encode(password));
        System.out.println(encoder.encode(password1));
    }
}
//admin@example.com
//password123

//test@test.com
//1234