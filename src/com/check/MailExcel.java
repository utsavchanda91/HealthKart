package com.check;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import java.util.Properties;
import javax.mail.internet.*;
import javax.activation.*;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: root
 * Date: 26/11/13
 * Time: 3:47 PM
 * To change this template use File | Settings | File Templates.
 */
public class MailExcel {
    public static final String SMTP_HOST_NAME = "smtp.gmail.com";
    public static final String SMTP_HOST_PORT = "587";
    //public static final String SMTP_AUTH_USER = "utsavchanda@outlook.com";
    //public static final String SMTP_AUTH_PWD  = "!UditChanda1";
    public static final String SMTP_AUTH_USER = "healthkarttest@gmail.com";
    public static final String SMTP_AUTH_PWD  = "healthkart";
    public static final String emailToAddress = "utsav.chanda@healthkart.com";
    public static String[] emailIdList = {"utsav.chanda@healthkart.com"};

    public void mailExcel(String fileName){

        Properties  properties = System.getProperties();
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.EnableSSL.enable","true");
        properties.put("mail.smtp.host", SMTP_HOST_NAME);
        properties.put("mail.smtp.auth", "true");
        properties.setProperty("mail.user", SMTP_AUTH_USER);
        properties.setProperty("mail.password", SMTP_AUTH_PWD);
        properties.setProperty("mail.smtp.port", SMTP_HOST_PORT);
        properties.put("mail.smtp.starttls.enable", "true");
        //properties.setProperty("mail.transport.protocol", "smtp");
        Authenticator auth = new SMTPAuthenticator();
        Session session = Session.getDefaultInstance(properties, auth);
        try{
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(SMTP_AUTH_USER));
            message.addRecipient(Message.RecipientType.TO,new InternetAddress(emailToAddress));
            message.setSubject("Excel");

            BodyPart messageBodyPart = new MimeBodyPart();
            messageBodyPart.setText("This is message body");
            MimeMultipart multipart = new MimeMultipart();
            multipart.addBodyPart(messageBodyPart);
            messageBodyPart = new MimeBodyPart();
            DataSource source = new FileDataSource(fileName);
            messageBodyPart.setDataHandler(new DataHandler(source));
            messageBodyPart.setFileName(fileName);
            multipart.addBodyPart(messageBodyPart);
            message.setContent(multipart);
            Transport transport = session.getTransport("smtp");
            transport.connect(SMTP_HOST_NAME, SMTP_AUTH_USER, SMTP_AUTH_PWD);
            transport.send(message);
            message.saveChanges();
            transport.close();

        }catch (MessagingException mex) {
            mex.printStackTrace();
        }

    }
    private class SMTPAuthenticator extends javax.mail.Authenticator
    {

        public PasswordAuthentication getPasswordAuthentication()
        {
            String username = SMTP_AUTH_USER;
            String password = SMTP_AUTH_PWD;
            return new PasswordAuthentication(username, password);
        }
    }

}
