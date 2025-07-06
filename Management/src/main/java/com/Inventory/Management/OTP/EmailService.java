package com.Inventory.Management.OTP;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {
        @Autowired
        private JavaMailSender mailSender;

        public void sendOtp(String toEmail, String otp) {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(toEmail);
            message.setSubject("Your OTP Code");
            message.setText("Your OTP is: " + otp);
            message.setFrom("your-email@gmail.com");

            mailSender.send(message);
        }
}
