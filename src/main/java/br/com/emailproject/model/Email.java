package br.com.emailproject.model;

public class Email {

	private String destino;
	private String assunto;
	private String texto;

	public Email(String destino, String assunto, String texto) {
		this.destino = destino;
		this.assunto = assunto;
		this.texto = texto;
	}

	public String getDestino() {
		return destino;
	}

	public void setDestino(String destino) {
		this.destino = destino;
	}

	public String getAssunto() {
		return assunto;
	}

	public void setAssunto(String assunto) {
		this.assunto = assunto;
	}

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

}
