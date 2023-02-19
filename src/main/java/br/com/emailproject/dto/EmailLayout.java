package br.com.emailproject.dto;

import br.com.emailproject.model.Email;

public class EmailLayout {

	private static final String ESPACO_DUPLO = "<br><br>";
	private static final String ESPACO_SIMPLES = "<br>";

	/**
	 * Modelo de Email para Administracao
	 * @param destino
	 * @param assunto
	 * @return String
	 */
	public Email montarEmailAdm(String destino, String assunto) {

		StringBuilder texto = new StringBuilder();

		texto
			.append("A/C Administrador")
			.append(ESPACO_DUPLO)
			.append("Solicitacao de Alteração de Senha")
			.append(ESPACO_DUPLO);

		gerarAssinatura(texto);
		rodape(texto);

		return new Email(destino, assunto, texto.toString());
	}
	/**
	 * Modelo de Email para Secretario
	 * @param destino
	 * @param assunto
	 * @return String
	 */
	public Email montarEmailSecret(String destino, String assunto) {

		StringBuilder texto = new StringBuilder();

		texto
			.append("A/C Secretario")
			.append(ESPACO_DUPLO)
			.append("Solicitacao de Novos Cadastros")
			.append(ESPACO_DUPLO);

		gerarAssinatura(texto);
		rodape(texto);

		return new Email(destino, assunto, texto.toString());
	}

	/**
	 * Rodape de Email Estatico
	 * @param texto
	 * @return String
	 */
	private String gerarAssinatura(StringBuilder texto) {
		return texto
				.append("Atenciosamente:")
				.append(ESPACO_SIMPLES)
				.append("Fco. Antonio")
				.append(ESPACO_SIMPLES)
				.append("Supervisor Temp")
				.toString();
	}

	/**
	 * Rodape dos textos para Email
	 * @param texto
	 * @return String
	 */
	private String rodape(StringBuilder texto) {
		return texto.append(ESPACO_SIMPLES +"Email automatica, favor nao responder").toString();
	}

}
