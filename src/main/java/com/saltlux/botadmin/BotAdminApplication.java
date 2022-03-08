package com.saltlux.botadmin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;


@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class BotAdminApplication {

    public static void main(String[] args) throws NoSuchAlgorithmException {
        SpringApplication.run(BotAdminApplication.class, args);
//
//        String password = "123456";
//        MessageDigest md = MessageDigest.getInstance("MD5");
//        md.update(password.getBytes());
//        byte[] digest = md.digest();
//        String myHash = DatatypeConverter
//                .printHexBinary(digest).toUpperCase();
//        System.out.println("My Hash: " + myHash);

        System.out.println(new Date());
        System.out.println("Verify: " + verify("123456", "E10ADC3949BA59ABBE56E057F20F883E"));

    }

    public static boolean verify(String inputPassword, String hashPassWord)
            throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(inputPassword.getBytes());
        byte[] digest = md.digest();
        String myChecksum = DatatypeConverter
                .printHexBinary(digest).toUpperCase();
        return hashPassWord.equals(myChecksum);
    }

}
