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
public class SendEmailFeedbackThread extends Thread {


    private Thread t;
    private String threadName;

    private String tieuDe;
    private int anDanh;
    private String hoTen;
    private String noiDung;
    private String FRIEND_EMAIL;
    private String MY_EMAIL;
    private String PASSWORD;

    public SendEmailFeedbackThread(String name, String tieuDe, int anDanh, String hoTen, String noiDung, String FRIEND_EMAIL,
                                   String MY_EMAIL, String PASSWORD) {
        threadName = name;
        this.tieuDe = tieuDe;
        this.anDanh = anDanh;
        this.hoTen = hoTen;
        this.noiDung = noiDung;
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
            mailServerProperties = System.getProperties();
            mailServerProperties.put("mail.smtp.port", "587");
            mailServerProperties.put("mail.smtp.auth", "true");
            mailServerProperties.put("mail.smtp.starttls.enable", "true");
            getMailSession = Session.getDefaultInstance(mailServerProperties, null);
            mailMessage = new MimeMessage(getMailSession);

            mailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(FRIEND_EMAIL)); //Thay abc bằng địa chỉ người nhận
            String trangThaiAnDanh = "Không ẩn danh ";
            if (anDanh == 1) {
                trangThaiAnDanh = "Ẩn danh";
                hoTen = "Ẩn danh";
            }
            mailMessage.setSubject(tieuDe);
            String emailBody = "<p></p>\n" +
                    "<p>Thân gửi bộ phận công đoàn Saltlux Technology.</p>\n" +
                    "<p>BOT ADMIN thay mặt cán bộ nhân viên gửi thư góp ý tới bộ phận công đoàn.</p>\n" +
                    "<p>Hình thức gửi: " + trangThaiAnDanh + "</p>\n" +
                    "<p>Nhân viên góp ý: " + hoTen + "</p>\n" +
                    "<p>Nội dung: " + noiDung + "</p>\n" +
                    "<p>&nbsp;</p>\n" +
                    "<p>Thân gửi</p>";
            mailMessage.setContent(emailBody, "text/html; charset=utf-8");
            Transport transport = getMailSession.getTransport("smtp");
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
