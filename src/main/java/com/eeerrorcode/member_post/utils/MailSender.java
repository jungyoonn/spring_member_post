package com.eeerrorcode.member_post.utils;

// import jakarta.mail.*;
// import jakarta.mail.internet.*;

public class MailSender {
	//private static final String ENCODE = "utf-8";

	// public static void main(String[] args) {
	// 	Session session = new MailSender().init();
	// 	String rndText = String.format("%08d", (int)(Math.random() * 100000000));
	// 	System.out.println(rndText);
	// 	send(session, "메일발송테스트", "내용내용" + rndText, "sophia76256@gmail.com", "sophia7625@naver.com", "khanman1208@gmail.com");
	// }
	
	// public Session init() {
	// 	Properties props = new Properties();
	// 	Properties authProps = new Properties();
		
	// 	try {
	// 		props.load(getClass().getClassLoader().getResourceAsStream("mail.properties"));
	// 		authProps.load(getClass().getClassLoader().getResourceAsStream("mail_auth.properties"));
	// 	} catch (IOException e) {
	// 		e.printStackTrace();
	// 	}
	// 	Session session = Session.getDefaultInstance(props, new Authenticator() {
			
	// 		@Override
	// 		protected PasswordAuthentication getPasswordAuthentication() {
	// 			return new PasswordAuthentication(authProps.getProperty("Username"), authProps.getProperty("Password"));
	// 		}
	// 	});
	// 	return session;
	// }
	
	// public static void send(Session session, String title, String content, String... to) {
	// 	Message msg = new MimeMessage(session);
	// 	try {
	// 		InternetAddress address = new InternetAddress("sophia76256@gmail.com", "관리자", ENCODE);
	// 		msg.setFrom(address);
	// 		msg.addRecipients(Message.RecipientType.TO, convertToInternetAddressArray(to));
	// 		msg.setSubject(title);
	// 		msg.setContent(content, "text/html; charset=" + ENCODE);
			
	// 		Transport.send(msg);
	// 	} catch (UnsupportedEncodingException | MessagingException e) {
	// 		e.printStackTrace();
	// 	}
	// }
	
	// public static Address[] convertToInternetAddressArray(String[] emailAddresses) {
	// 	InternetAddress[] internetAddresses = new InternetAddress[emailAddresses.length];
		
	// 	for(int i = 0; i < emailAddresses.length; i++) {
	// 		try {
	// 			internetAddresses[i] = new InternetAddress(emailAddresses[i]);
	// 		} catch (AddressException e) {
	// 			System.out.println("Invalid email address : " + emailAddresses[i]);
	// 			e.printStackTrace();
	// 		}
	// 	}
	// 	return internetAddresses;
	// }
}
