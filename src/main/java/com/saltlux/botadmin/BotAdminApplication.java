package com.saltlux.botadmin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

import javax.xml.bind.DatatypeConverter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


@SpringBootApplication(exclude = { SecurityAutoConfiguration.class })
public class BotAdminApplication {

    public static void main(String[] args)  {
        SpringApplication.run(BotAdminApplication.class, args);

    }


}
