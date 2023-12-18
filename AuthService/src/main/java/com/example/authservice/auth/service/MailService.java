package com.example.authservice.auth.service;

import com.example.authservice.auth.constant.EmailConstant;
import com.example.authservice.auth.vo.EmailBoards;
import com.example.authservice.common.exception.custom.SendMessageException;
import jakarta.mail.Message.RecipientType;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Objects;
import java.util.Random;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class MailService {

  private final JavaMailSender emailSender;

  private final ResourceLoader resourceLoader;

  private final EmailBoards emailBoards;

  public void sendCodeToEmail(String toEmail) {
    try {
      String authCode = createKey();
      String emailText = getEmailText(authCode);
      MimeMessage mail = createMailContent(toEmail, emailText);
      emailSender.send(mail);
      emailBoards.putEmailBoard(toEmail, authCode);
    } catch (Exception e) {
      e.printStackTrace();
      throw new SendMessageException("인증번호 전송에 실패하였습니다.");
    }
  }

  public boolean isEqual(String email, String inputCode){
    boolean isEqual = Objects.equals(emailBoards.getValue(email), inputCode);
    if(isEqual){
      emailBoards.removeEmailBoard(email);
    }
    return isEqual;
  }

  private MimeMessage createMailContent(String to, String emailText) throws MessagingException, IOException {
    MimeMessage message = emailSender.createMimeMessage();
    message.addRecipients(RecipientType.TO, to);
    message.setSubject(EmailConstant.MAIL_SUBJECT);
    message.setText(emailText, EmailConstant.CHARSET, EmailConstant.TEXT_TYPE);
    message.setFrom(new InternetAddress(EmailConstant.ADDRESS_EMAIL, EmailConstant.ADDRESS_NAME));

    return message;
  }

  private String getEmailText(String authCode) throws IOException {
    Resource resource = resourceLoader.getResource(EmailConstant.FORM_LOCATION);
    String content = Files.readString(Path.of(resource.getURI()));
    return content.replace(EmailConstant.AUTH_CODE_PLACEHOLDER, authCode);
  }

  private String createKey() {
    StringBuilder key = new StringBuilder();
    Random random = new Random();

    for (int i = 0; i < 8; i++) {
      int index = random.nextInt(3); // 0~2 까지 랜덤

      switch (index) {
        case 0 -> key.append((char) ((random.nextInt(26)) + 97));
        //  a~z  (ex. 1+97=98 => (char)98 = 'b')
        case 1 -> key.append((char) ((random.nextInt(26)) + 65));
        //  A~Z
        case 2 -> key.append(random.nextInt(10));
        // 0~9
      }
    }
    return key.toString();
  }
}
