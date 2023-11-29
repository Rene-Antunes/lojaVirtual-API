package com.lojavirtual.infraInstructure.service.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import com.lojavirtual.core.email.EmailProperties;
import com.lojavirtual.domain.service.EnvioEmailService;

import jakarta.mail.internet.MimeMessage;

@Service
public class SmtpEnvioEmailService implements EnvioEmailService{

	@Autowired
	private JavaMailSender mailsander;
	
	@Autowired
	private EmailProperties emailProperties;
		
	@Override
	public void enviar(Mensagem mensagem) {
		try {
		MimeMessage mimeMessage = mailsander.createMimeMessage();
		
		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "UTF-8");
		helper.setFrom(emailProperties.getRemetente());
		helper.setTo(mensagem.getDestinatarios().toArray(new String[0]));
		helper.setSubject(mensagem.getAssunto());
		helper.setText(mensagem.getCorpo(), true);
		
		
		
		mailsander.send(mimeMessage);
		}catch (Exception e) {
			throw new EmailException("Email não pode ser enviado", e);
		}
	}

}
