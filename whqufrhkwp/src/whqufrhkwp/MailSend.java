package whqufrhkwp;

import java.io.IOException;
import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class MailSend {
	
	public static void gmailSender() throws IOException {
        final String user = "leeys9423@naver.com"; 	// 발신메일 설정
        final String password = "";   				// 패스워드 설정

        // SMTP 서버 정보를 설정한다.
        Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.naver.com"); 		// STMP서버
		props.put("mail.smtp.port", "587");					// SSL 포트 설정
		props.put("mail.smtp.auth", "true"); 				// 인증 설정
		
		/*
		 * Properties 속성값(SMTP서버 정보와 사용자 정보)을 이용해 세션을 생성한다.
		 * Session 객체는 사용자를 인증하고 메시지 저장과 전송에 대한 접근을 제어한다. 
		 */
        Session session = Session.getDefaultInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(user, password);
            }
        });
		
		/*
		 * 메시지 보내기
		 * 메시지 작성은 javax.mail.Message클래스를 통해서 이루어진다.
		 * 오늘날 사용되는 대부분의 이메일들은 다양한 MIME타입을 지원하기 때문에 MimeMessage클래스를 사용한다.
		 * 메일과 관련된 내용을 지정할 수 있도록 해준다.
		 */
		try {
			MimeMessage message = new MimeMessage(session);
			
			// InternetAddress : 이메일 주소를 나타낼 때 사용되는 클래스로서 수신자, 발신자를 지정할 때 사용한다.
			// 수신 메일 설정
			String toUser = "wkdtjdqh17@naver.com";
			
			// 수신자 설정
			message.addRecipient(Message.RecipientType.TO, new InternetAddress(toUser));
			
			// 발신자 설정
			message.setFrom(new InternetAddress(user));

			// 메일 제목을 입력
			message.setSubject("이클립스에서 보내봅니다."); 
			
			// 글 내용을 담을 바디파트
			MimeBodyPart mbp1 = new MimeBodyPart();
			// HTML을 담을 바디파트
			MimeBodyPart mbp2 = new MimeBodyPart();
			// 첨부파일을 담을 바디파트
			MimeBodyPart mbp3 = new MimeBodyPart();

			// Text
			mbp1.setText("안녕하세요~~"); // 메일 내용을 입력
			
			// HTML img 설정
			mbp2.setContent("<p>안녕하세요?</p><br><img src=\"http://www.apache.org/images/feather.gif\">", "text/html; charset=utf-8;");
			
			// 파일을 첨부한다.
			String filename = "D:/d_other/고양이.jpg";
			mbp3.attachFile(filename);
			
			// 여러 Mime타입을 전송하기 위한 Multipart 생성
			Multipart mp = new MimeMultipart();

			// 글 내용과 첨부파일을 멀티파트에 추가
			mp.addBodyPart(mbp1);
			mp.addBodyPart(mbp2);
			mp.addBodyPart(mbp3);

			// 메시지 내용을 멀티파트에 담긴 데이터로 설정
			message.setContent(mp);

			// 메시지 내용을 전송
			Transport.send(message); //// 전송
			System.out.println("message sent successfully...");
        } catch (AddressException e) {
            e.printStackTrace();
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
	
	public static void main(String[] args) throws IOException {
		gmailSender();
	}
}




