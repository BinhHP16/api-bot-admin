package com.saltlux.botadmin.service;

import javax.mail.MessagingException;

public interface IEmailService {
    public String sendSimpleEmail();

    void sendSimpleEmailGopY();

    void sendMailHTML(String tieuDe,int anDanh, String hoTen, String noiDung) throws MessagingException;
}
