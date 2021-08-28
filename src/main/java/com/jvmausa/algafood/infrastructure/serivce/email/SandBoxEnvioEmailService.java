package com.jvmausa.algafood.infrastructure.serivce.email;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.MimeMessageHelper;

import com.jvmausa.algafood.core.email.EmailProperties;

public class SandBoxEnvioEmailService extends SmtpEnvioEmailService{

	@Autowired
	private EmailProperties emailProperties;

	@Override
	protected MimeMessage criarMimeMessage(Mensagem mensagem) throws MessagingException {
		MimeMessage mimeMessage = super.criarMimeMessage(mensagem);
		MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, "UTF-8");
		
		mimeMessageHelper.setTo(emailProperties.getSandbox().getDestinatario());
		
		return mimeMessage;
	}


	
	


}
