package com.example.MavenDemand;

import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;



public class MailReport {
	public static void sendMail()
	{
		 String from = "OPEN-TEXT-CHECK@loreal.com"; 
	        String to = "nilargha.ghosh@accenture.com"; 
	        String host = "smtprelay1-emea.loreal.wans"; // or IP address
	        String fileName="./ok.PNG";
	        Properties properties = System.getProperties(); 
	 
	        properties.setProperty("mail.smtp.host", host); 
	 
	        Session session = Session.getDefaultInstance(properties); 
	 
	        try {
	        	Message message = new MimeMessage(session);
	            message.setFrom(new InternetAddress(from));
	            message.setRecipients(Message.RecipientType.TO,
	                    InternetAddress.parse(to));	
	            message.setSubject("Daily Open Text Health Check");
	            message.setText("Hello Team,\n Please find the attached Report for Open Text Health Check");
	            message.setDescription("DAILY CHECK FOR BETA TESTING");
	            MimeBodyPart messageBodyPart = new MimeBodyPart();
	            Multipart multipart = new MimeMultipart();
	            messageBodyPart = new MimeBodyPart();
	           // String file = "path of file to be attached";
	            String file = "attachmentName";
	            DataSource source = new FileDataSource(fileName);
	            messageBodyPart.setDataHandler(new DataHandler(source));
	            messageBodyPart.setFileName(file);
	            multipart.addBodyPart(messageBodyPart);
	            message.setContent(multipart);
	            System.out.println("Sending");
	            Transport.send(message);
	            System.out.println("Done");
	        }
	        catch (MessagingException mex) {
	            mex.printStackTrace();
	        }
	   
	}
}