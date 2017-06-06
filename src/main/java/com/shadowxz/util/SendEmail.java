package com.shadowxz.util;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Properties;

/**
 * Created by xz on 2017/5/8.
 */


public class SendEmail {

    public static String email = null;
    public static String password = null;

    private static Session getSession() {
        Properties props = new Properties();
        InputStream is = null;
        try {
            is = Thread.currentThread().getContextClassLoader().getResourceAsStream("mail.properties");
            if(is == null){
                System.out.println("mail.properties not found");
            }
            props.load(is);
        } catch (IOException e) {
            e.printStackTrace();
        }

        email = props.getProperty("email");
        password = props.getProperty("password");

        Authenticator authenticator = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(email,password);
            }

        };
        Session session = Session.getDefaultInstance(props , authenticator);
        return session;
    }

    public static void send(String toEmail , String content) {
        Session session = getSession();
        try {
            System.out.println("--send--"+content);
            // Instantiate a message
            Message msg = new MimeMessage(session);

            //Set message attributes
            msg.setFrom(new InternetAddress(email));
            InternetAddress[] address = {new InternetAddress(toEmail)};
            msg.setRecipients(Message.RecipientType.TO, address);
            msg.setSubject("账号激活邮件");
            msg.setSentDate(new Date());
            msg.setContent(content , "text/html;charset=utf-8");

            //Send the message
            Transport.send(msg);
        }
        catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }
}