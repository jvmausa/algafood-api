package com.jvmausa.algafood.core.storage;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.jvmausa.algafood.core.email.EmailProperties;
import com.jvmausa.algafood.domain.service.EnvioEmailService;
import com.jvmausa.algafood.infrastructure.serivce.email.FakeEnvioEmailService;
import com.jvmausa.algafood.infrastructure.serivce.email.SandBoxEnvioEmailService;
import com.jvmausa.algafood.infrastructure.serivce.email.SmtpEnvioEmailService;

@Configuration
public class EmailConfig {

	@Autowired
	private EmailProperties emailProperties;

	@Bean
	public EnvioEmailService envioEmailService() {
		// Acho melhor usar switch aqui do que if/else if
		switch (emailProperties.getImpl()) {
		case FAKE:
			return new FakeEnvioEmailService();
		case SMTP:
			return new SmtpEnvioEmailService();
		case SANDBOX:
			return new SandBoxEnvioEmailService();
		default:
			return null;
		}
	}

}
