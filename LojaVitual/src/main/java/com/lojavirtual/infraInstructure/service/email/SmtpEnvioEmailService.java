package com.lojavirtual.infraInstructure.service.email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import com.lojavirtual.core.email.EmailProperties;
import com.lojavirtual.domain.service.EnvioEmailService;

import freemarker.template.Configuration;
import freemarker.template.Template;
import jakarta.mail.internet.MimeMessage;

public class SmtpEnvioEmailService implements EnvioEmailService{

	@Autowired
	private JavaMailSender mailsander;
	
	@Autowired
	private EmailProperties emailProperties;
	
	@Autowired
	private Configuration configuration;
	
	@Override
	public void enviar(Mensagem mensagem) {
		try {
			String corpo = processarTemplate(mensagem);
		MimeMessage mimeMessage = mailsander.createMimeMessage();
		
		MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "UTF-8");
		helper.setFrom(emailProperties.getRemetente());
		helper.setTo(mensagem.getDestinatarios().toArray(new String[0]));
		helper.setSubject(mensagem.getAssunto());
		helper.setText(corpo, true);
		
		mailsander.send(mimeMessage);
		
		}catch (Exception e) {
			throw new EmailException("Email não pode ser enviado", e);
		}
	}
	
	
	protected String processarTemplate(Mensagem mensagem) {
		try {
		Template template =	configuration.getTemplate(mensagem.getCorpo());
		
		return FreeMarkerTemplateUtils.processTemplateIntoString(template,
				mensagem.getVariaveis());
				
		}catch (Exception e) {
			throw new EmailException("Não foi possível montar template do e-mail.",e);
		}
	}
}
