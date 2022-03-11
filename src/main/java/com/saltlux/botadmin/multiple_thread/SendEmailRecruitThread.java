package com.saltlux.botadmin.multiple_thread;

import lombok.*;
import lombok.experimental.Accessors;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

@Data
@Getter
@Setter
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class SendEmailRecruitThread extends Thread {
    private Thread t;
    private String threadName;
    private String hoTen;
    private String viTri;
    private String link;
    private String FRIEND_EMAIL;
    private String MY_EMAIL;
    private String PASSWORD;

    public SendEmailRecruitThread(String name, String link, String hoTen, String viTri, String FRIEND_EMAIL,
                                  String MY_EMAIL, String PASSWORD) {
        threadName = name;
        this.link = link;
        this.hoTen = hoTen;
        this.viTri = viTri;
        this.FRIEND_EMAIL = FRIEND_EMAIL;
        this.MY_EMAIL = MY_EMAIL;
        this.PASSWORD = PASSWORD;
    }


    @Override
    public void run() {
        System.out.println("Running " + threadName);
        try {
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


            mailMessage.setSubject(hoTen + "_" + viTri);
            String emailBody = "<p>Thân gửi BP Nhân sự Saltlux Technology.</p>\n" +
                    "<p>BOT ADMIN thay mặt ứng viên " + hoTen + " gửi thông tin ứng tuyển.</p>\n" +
                    "<ul>\n" +
                    "<li>Họ tên: " + hoTen + "</li>\n" +
                    "<li>Vị trí: " + viTri + "</li>\n" +
                    "<li> <a href=\"" + link + "\" >Link CV</a></a></li>\n" +
                    "</ul>\n" +
                    "<p>Thân gửi!</p>";
            mailMessage.setContent(emailBody, "text/html; charset=utf-8");

            // Step3: Send mail
            Transport transport = getMailSession.getTransport("smtp");

            // Thay your_gmail thành gmail của bạn, thay your_password thành mật khẩu gmail của bạn
            transport.connect("smtp.gmail.com", MY_EMAIL, PASSWORD);
            transport.sendMessage(mailMessage, mailMessage.getAllRecipients());
            transport.close();

        } catch (MessagingException e) {
            System.out.println("Thread " + threadName + " interrupted.");
        }
        System.out.println("Thread " + threadName + " exiting.");
    }

    public void start() {
        System.out.println("Starting " + threadName);
        if (t == null) {
            t = new Thread(this, threadName);
            t.start();
            System.out.println("End Thread : " + threadName);
        }
    }

}
