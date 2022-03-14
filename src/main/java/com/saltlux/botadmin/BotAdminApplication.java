package com.saltlux.botadmin;

import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;


@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class BotAdminApplication {

    public static void main(String[] args)  {
        SpringApplication.run(BotAdminApplication.class, args);
        String password = "DeftBlog";
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        md.update(password.getBytes());
        byte[] digest = md.digest();
        String myHash = DatatypeConverter
                .printHexBinary(digest).toUpperCase();
        System.out.println("My Hash: " + myHash);
        String md5Hex = DigestUtils
                .md5Hex(password).toUpperCase();
        System.out.println("Hash: " + md5Hex);
        System.out.println("Verify: " + myHash.equals(md5Hex));


    }


}
