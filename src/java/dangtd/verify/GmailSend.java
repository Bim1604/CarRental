/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dangtd.verify;

import java.io.Serializable;
import java.util.Properties;
import java.util.Random;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

/**
 *
 * @author Admin
 */
public class GmailSend implements Serializable{
    public String getRandomCode(){
        Random rn = new Random();
        int number = rn.nextInt(999999);
        return String.valueOf(number);
    }
    
    public String sendEmail(String username){
        String code = getRandomCode();
        String from = "dangvipro3@gmail.com";
        String password = "dang1234567890";
        Properties props = System.getProperties();
        String host = "smtp.gmail.com";
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.user", from);
        props.put("mail.smtp.password", password);
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        
        Session sesionMail = Session.getDefaultInstance(props);
        MimeMessage message = new MimeMessage(sesionMail);

        try {
            message.setFrom(new InternetAddress(from));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(username));

            message.setSubject("Code Verification");
            message.setText("Your code using to verify your account is: " + code);
            try (Transport transport = sesionMail.getTransport("smtp")) {
                transport.connect(host, from, password);
                transport.sendMessage(message, message.getAllRecipients());
            }
        } catch (AddressException ex) {
//            LOGGER.error("CreateRecordServlet_AddressException: " + ex.getMessage());
        } catch (MessagingException ex) {
//            LOGGER.error("CreateRecordServlet_MessagingException: " + ex.getMessage());
        }
        return code;
    }
}
