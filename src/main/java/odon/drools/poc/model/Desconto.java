package odon.drools.poc.model;

import java.math.BigDecimal;

public class Desconto {

	protected String identificador;
	protected RecebeDesconto alvo;
	protected BigDecimal percentual;
	protected Boolean acumulativo = false;

	public Desconto(String identificador, RecebeDesconto alvo) {
		this.identificador = identificador;
		this.alvo = alvo;
		this.alvo.registrarDesconto(this);
	}

	public Desconto(String identificador, RecebeDesconto alvo, BigDecimal percentual) {
		this(identificador, alvo);
		this.percentual = percentual;
	}

	public Desconto(String identificador, RecebeDesconto alvo, BigDecimal percentual, Boolean acumulativo) {
		this(identificador, alvo,percentual);
		this.acumulativo = acumulativo;
	}
	
	public String getIdentificador() {
		return identificador;
	}
	
	public void setIdentificador(String identificador) {
		this.identificador = identificador;
	}

	public RecebeDesconto getAlvo() {
		return alvo;
	}
	
	public Boolean getAcumulativo() {
		return acumulativo;
	}

	public BigDecimal getPercentual() {
		return percentual;
	}

	public void setAcumulativo(Boolean acumulativo) {
		this.acumulativo = acumulativo;
	}

	public void setPercentual(BigDecimal percentual) {
		this.percentual = percentual;
	}	
}
