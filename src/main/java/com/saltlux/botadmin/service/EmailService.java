package com.saltlux.botadmin.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@Service
public class EmailService implements IEmailService{

    @Value("${email.to}")
    public  String FRIEND_EMAIL ;

    @Value("${email.from}")
    public  String MY_EMAIL ;


    @Value("${email.password}")
    public  String PASSWORD ;


    @Autowired
    public JavaMailSender emailSender;

    public String sendSimpleEmail(){

        // Create a Simple MailMessage.
        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo(FRIEND_EMAIL);
        message.setSubject("Thông báo có ứng viên ứng tuyển");
        message.setText("Email thông báo ứng tuyển");

        // Send Message!
        this.emailSender.send(message);

        return "Email Sent!";

    }

    @Override
    public void sendSimpleEmailGopY() {


        // Create a Simple MailMessage.
        SimpleMailMessage message = new SimpleMailMessage();

        message.setTo(FRIEND_EMAIL);
        message.setSubject("Email góp ý");
        message.setText("Có 1 góp ý mới trong hòm thư góp ý");

        // Send Message!
        this.emailSender.send(message);

    }

    @Override
    public void sendMailHTML(String tieuDe, int anDanh, String hoTen, String noiDung) throws MessagingException {
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

        mailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(FRIEND_EMAIL)); //Thay abc bằng địa chỉ người nhận

        // Bạn có thể chọn CC, BCC
//    generateMailMessage.addRecipient(Message.RecipientType.CC, new InternetAddress("cc@gmail.com")); //Địa chỉ cc gmail
        String trangThaiAnDanh ="Không ẩn danh ";
        if(anDanh==1){
            trangThaiAnDanh="Ẩn danh";
            hoTen="Ẩn danh";
        }


        mailMessage.setSubject(tieuDe);
        String emailBody = "<p></p>\n" +
                "<p>Thân gửi bộ phận công đoàn Saltlux Technology.</p>\n" +
                "<p>BOT ADMIN thay mặt cán bộ nhân viên gửi thư góp ý tới bộ phận công đoàn.</p>\n" +
                "<p>Hình thức gửi: "+trangThaiAnDanh+"</p>\n" +
                "<p>Nhân viên góp ý: "+hoTen+"</p>\n" +
                "<p>Nội dung: "+noiDung+"</p>\n" +
                "<p>&nbsp;</p>\n" +
                "<p>Thân gửi</p>";
        mailMessage.setContent(emailBody, "text/html; charset=utf-8");

        // Step3: Send mail
        Transport transport = getMailSession.getTransport("smtp");

        // Thay your_gmail thành gmail của bạn, thay your_password thành mật khẩu gmail của bạn
        transport.connect("smtp.gmail.com", MY_EMAIL, PASSWORD);
        transport.sendMessage(mailMessage, mailMessage.getAllRecipients());
        transport.close();
    }

    @Override
    public void sendEmailAttach(String link, String hoTen, String viTri) throws MessagingException {
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

        mailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(FRIEND_EMAIL)); //Thay abc bằng địa chỉ người nhận

        // Bạn có thể chọn CC, BCC
//    generateMailMessage.addRecipient(Message.RecipientType.CC, new InternetAddress("cc@gmail.com")); //Địa chỉ cc gmail


        mailMessage.setSubject(hoTen+"_"+viTri);
        String emailBody = "<p>Thân gửi BP Nhân sự Saltlux Technology.</p>\n" +
                "<p>BOT ADMIN thay mặt ứng viên "+hoTen+" gửi thông tin ứng tuyển.</p>\n" +
                "<ul>\n" +
                "<li>Họ tên: "+hoTen+"</li>\n" +
                "<li>Vị trí: "+viTri+"</li>\n" +
                "<li> <a href=\""+link+"\" >Link CV</a></a></li>\n" +
                "</ul>\n" +
                "<p>Thân gửi!</p>";
        mailMessage.setContent(emailBody, "text/html; charset=utf-8");

        // Step3: Send mail
        Transport transport = getMailSession.getTransport("smtp");

        // Thay your_gmail thành gmail của bạn, thay your_password thành mật khẩu gmail của bạn
        transport.connect("smtp.gmail.com", MY_EMAIL, PASSWORD);
        transport.sendMessage(mailMessage, mailMessage.getAllRecipients());
        transport.close();
    }

//    @Override
//    public void sendEmailAttach(BodyPart filePart, String hoTen, String viTri) throws MessagingException {
//
//        Properties mailServerProperties;
//        Session getMailSession;
//        MimeMessage mailMessage;
//
//        // Step1: setup Mail Server
//        mailServerProperties = System.getProperties();
//        mailServerProperties.put("mail.smtp.port", "587");
//        mailServerProperties.put("mail.smtp.auth", "true");
//        mailServerProperties.put("mail.smtp.starttls.enable", "true");
//
//        // Step2: get Mail Session
//        getMailSession = Session.getDefaultInstance(mailServerProperties, null);
//        mailMessage = new MimeMessage(getMailSession);
//
//        mailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(FRIEND_EMAIL)); //Thay abc bằng địa chỉ người nhận
//
//        // Bạn có thể chọn CC, BCC
////    generateMailMessage.addRecipient(Message.RecipientType.CC, new InternetAddress("cc@gmail.com")); //Địa chỉ cc gmail
//
//        mailMessage.setSubject(hoTen+"_"+viTri);
//
//        // Tạo phần gửi message
//        BodyPart messagePart = new MimeBodyPart();
//        messagePart.setText("Nội dung:\n" +
//                "Thân gửi BP Nhân sự Saltlux Technology.\n" +
//                "BOT ADMIN thay mặt ứng viên "+hoTen+"gửi thông tin ứng tuyển.\n" +
//                "- Họ tên ứng viên : "+hoTen+"\n" +
//                "- Vị trí : "+viTri+"\n" +
//                "- CV như file đính kèm\n" +
//                "Thân gửi!");
//
//        // Gộp message và file vào để gửi đi
//        Multipart multipart = new MimeMultipart();
//        multipart.addBodyPart(messagePart);
//        multipart.addBodyPart(filePart);
//        mailMessage.setContent(multipart,"text/html; charset=utf-8");
//
//        // Step3: Send mail
//        Transport transport = getMailSession.getTransport("smtp");
//
//        // Thay your_gmail thành gmail của bạn, thay your_password thành mật khẩu gmail của bạn
//        transport.connect("smtp.gmail.com", MY_EMAIL, PASSWORD);
//        transport.sendMessage(mailMessage, mailMessage.getAllRecipients());
//        transport.close();
//    }


}
