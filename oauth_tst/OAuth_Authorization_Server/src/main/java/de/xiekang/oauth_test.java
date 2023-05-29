package de.xiekang;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class oauth_test {
    public static void main(String[] args) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        String encodedPassword = passwordEncoder.encode("password");
        System.out.println(encodedPassword);
    }
}
