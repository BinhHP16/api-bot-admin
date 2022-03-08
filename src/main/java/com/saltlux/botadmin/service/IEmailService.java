package com.saltlux.botadmin.service;

import javax.mail.MessagingException;

public interface IEmailService {
    public String sendSimpleEmail();

    void sendSimpleEmailGopY();

    void sendMailHTML(String tieuDe,int anDanh, String hoTen, String noiDung) throws MessagingException;


//    void sendEmailAttach(BodyPart filePart, String hoTen, String viTri) throws MessagingException;
    void sendEmailAttach(String link, String hoTen, String viTri) throws MessagingException;
}
