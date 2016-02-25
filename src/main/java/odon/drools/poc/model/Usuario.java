package odon.drools.poc.model;

public class Usuario {

	protected String cpf;
	protected Boolean primeiraCompra;
	protected Boolean enviarNewsletter;

	public String getCpf() {
		return cpf;
	}

	public void setCpf(String cpf) {
		this.cpf = cpf;
	}

	public Boolean getPrimeiraCompra() {
		return primeiraCompra;
	}

	public void setPrimeiraCompra(Boolean primeiraCompra) {
		this.primeiraCompra = primeiraCompra;
	}

	public Boolean getEnviarNewsletter() {
		return enviarNewsletter;
	}

	public void setEnviarNewsletter(Boolean enviarNewsletter) {
		this.enviarNewsletter = enviarNewsletter;
	}

}
