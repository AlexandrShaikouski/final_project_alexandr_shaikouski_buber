package com.alexshay.buber.email;

import com.alexshay.buber.service.exception.ServiceException;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

public class MailGenerator {
    public void sendMessage(String subject, String message, List<String> email ) throws ServiceException {
        final Properties properties = new Properties();
        try {
            String path = File.separator + "email.properties";
            ClassLoader classLoader =  MailGenerator.class.getClassLoader();
            InputStream in = classLoader.getResourceAsStream(path);
            properties.load(in);
            in.close();
            Session mailSession = Session.getDefaultInstance(properties);
            MimeMessage mimeMessage = new MimeMessage(mailSession);
            mimeMessage.setFrom(new InternetAddress("taxibuber"));
            for(String em: email){
                mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(em));
            }
            mimeMessage.setSubject(subject);
            mimeMessage.setText(message);

            Transport transport = mailSession.getTransport();
            transport.connect(null, "medivh88");
            transport.sendMessage(mimeMessage,mimeMessage.getAllRecipients());
            transport.close();

        } catch (IOException | MessagingException e) {
            throw new ServiceException("Failed send mail",e);
        }
    }
}
