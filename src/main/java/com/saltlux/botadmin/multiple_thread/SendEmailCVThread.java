package com.saltlux.botadmin.multiple_thread;

import lombok.*;
import lombok.experimental.Accessors;
import org.springframework.util.ResourceUtils;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Properties;

@Data
@Getter
@Setter
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
public class SendEmailCVThread extends Thread {
    private Thread t;
    private String threadName;
    private String hoTen;
    private String viTri;
    private String filePath;
    private String FRIEND_EMAIL;
    private String MY_EMAIL;
    private String PASSWORD;

    public SendEmailCVThread(String name, String filePath, String hoTen, String viTri, String FRIEND_EMAIL,
                             String MY_EMAIL, String PASSWORD) {
        threadName = name;
        this.filePath = filePath;
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
            File file = ResourceUtils.getFile(filePath);

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

            // Tạo phần gửi message
            mailMessage.setSubject(hoTen + "_" + viTri);
            BodyPart messagePart = new MimeBodyPart();
            messagePart.setText("Thân gửi BP Nhân sự Saltlux Technology.\n" +
                    "BOT ADMIN thay mặt ứng viên " + hoTen + " gửi thông tin ứng tuyển.\n" +
                    "Họ tên: " + hoTen + "\n" +
                    "Vị trí: " + viTri + "\n" +
                    "CV như file đính kèm \n" +
                    "Thân gửi!");

            // Tạo phần gửi file
            BodyPart filePart = new MimeBodyPart();
            File file2 = new File(filePath);
            DataSource source = new FileDataSource(file);
            filePart.setDataHandler(new DataHandler(source));
            filePart.setFileName(file.getName());

            // Gộp message và file vào để gửi đi
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(messagePart);
            multipart.addBodyPart(filePart);
            mailMessage.setContent(multipart, "text/html; charset=utf-8");

            // Step3: Send mail
            Transport transport = getMailSession.getTransport("smtp");

            // Thay your_gmail thành gmail của bạn, thay your_password thành mật khẩu gmail của bạn
            transport.connect("smtp.gmail.com", MY_EMAIL, PASSWORD);
            transport.sendMessage(mailMessage, mailMessage.getAllRecipients());
            transport.close();

        } catch (MessagingException | FileNotFoundException e) {
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
