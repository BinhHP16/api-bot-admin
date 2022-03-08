package com.saltlux.botadmin.controller;

import com.saltlux.botadmin.service.IEmailService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;
import java.io.File;
import java.util.Properties;


@Api(tags = "Email")
@RestController
@RequestMapping("/api/email")
public class SendEmailController {
    @Autowired
    IEmailService emailService;

    @ResponseBody
    @GetMapping("/")
    public String sendSimpleEmail(){
    return emailService.sendSimpleEmail();

    }

    @GetMapping("/email_gop_y")

    public void sendHTML(int anDanh, String hoten, String noiDung) throws AddressException, MessagingException {
        Properties mailServerProperties;
        Session getMailSession;
        MimeMessage mailMessage;

        // Step1: setup Mail Server
        mailServerProperties = System.getProperties();
        mailServerProperties.put("mail.smtp.port", "587");
        mailServerProperties.put("mail.smtp.auth", "true");
        mailServerProperties.put("mail.smtp.starttls.enable", "true");

        // Step2: get Mail Session
        getMailSession = Session.getDefaultInstance(mailServerProperties, null);
        mailMessage = new MimeMessage(getMailSession);

        mailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress("nvb5899@gmail.com")); //Thay abc bằng địa chỉ người nhận

        // Bạn có thể chọn CC, BCC
//    generateMailMessage.addRecipient(Message.RecipientType.CC, new InternetAddress("cc@gmail.com")); //Địa chỉ cc gmail
        String trangThaiAnDanh ="Không ẩn danh ";
        if(anDanh==1){
            trangThaiAnDanh="Ẩn danh";
            hoten="Ẩn danh";
        }


        mailMessage.setSubject("Hòm thư góp ý Công đoàn");
        String emailBody = "<p></p>\n" +
                "<p>Thân gửi bộ phận công đoàn Saltlux Technology.</p>\n" +
                "<p>BOT ADMIN thay mặt cán bộ nhân viên gửi thư góp ý tới bộ phận công đoàn.</p>\n" +
                "<p>Hình thức gửi "+trangThaiAnDanh+"</p>\n" +
                "<p>Nhân viên góp ý : "+hoten+"</p>\n" +
                "<p>Nội dung: "+noiDung+"</p>\n" +
                "<p>&nbsp;</p>\n" +
                "<p>Thân gửi</p>";
        mailMessage.setContent(emailBody, "text/html; charset=utf-8");

        // Step3: Send mail
        Transport transport = getMailSession.getTransport("smtp");

        // Thay your_gmail thành gmail của bạn, thay your_password thành mật khẩu gmail của bạn
        transport.connect("smtp.gmail.com", "bintorres05@gmail.com", "@AZxy199");
        transport.sendMessage(mailMessage, mailMessage.getAllRecipients());
        transport.close();
    }


    @GetMapping("/email_gop_y_file")
    public static void sendFile() throws AddressException, MessagingException {
        Properties mailServerProperties;
        Session getMailSession;
        MimeMessage mailMessage;

        // Step1: setup Mail Server
        mailServerProperties = System.getProperties();
        mailServerProperties.put("mail.smtp.port", "587");
        mailServerProperties.put("mail.smtp.auth", "true");
        mailServerProperties.put("mail.smtp.starttls.enable", "true");

        // Step2: get Mail Session
        getMailSession = Session.getDefaultInstance(mailServerProperties, null);
        mailMessage = new MimeMessage(getMailSession);

        mailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress("abc@gmail.com")); //Thay abc bằng địa chỉ người nhận

        // Bạn có thể chọn CC, BCC
//    generateMailMessage.addRecipient(Message.RecipientType.CC, new InternetAddress("cc@gmail.com")); //Địa chỉ cc gmail
        mailMessage.setSubject("Demo send file by Gmail from Java");

        // Tạo phần gửi message
        BodyPart messagePart = new MimeBodyPart();
        messagePart.setText("This is message body");

        // Tạo phần gửi file
        BodyPart filePart = new MimeBodyPart();
        File file = new File("C://a.txt");
        DataSource source = new FileDataSource(file);
        filePart.setDataHandler(new DataHandler(source));
        filePart.setFileName(file.getName());

        // Gộp message và file vào để gửi đi
        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(messagePart);
        multipart.addBodyPart(filePart);
        mailMessage.setContent(multipart );


        // Step3: Send mail
        Transport transport = getMailSession.getTransport("smtp");

        // Thay your_gmail thành gmail của bạn, thay your_password thành mật khẩu gmail của bạn
        transport.connect("smtp.gmail.com", "your_gmail", "your_password");
        transport.sendMessage(mailMessage, mailMessage.getAllRecipients());
        transport.close();
    }

}
