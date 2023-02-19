package br.com.emailproject.bean;

import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import br.com.emailproject.dto.EmailLayout;
import br.com.emailproject.model.Email;
import br.com.emailproject.service.EmailService;

@Named
@RequestScoped
public class EmailBean implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private static final String DESTINATARIO = "josivan@gmail.com";
	
	private static final String ASSUNTO = "Mudança de Senha Temp";

	private EmailService emailService;

	@Inject
	public String enviarEmail() {
		emailService.enviar(montarEmail());
		return "";
	}

	private Email montarEmail() {
		EmailLayout layout = new EmailLayout();
		return layout.montarEmailAdm(DESTINATARIO, ASSUNTO);
	}
}
