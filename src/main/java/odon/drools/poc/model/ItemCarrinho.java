package odon.drools.poc.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ItemCarrinho implements RecebeDesconto {

	protected Long codigoProduto;
	protected String nomeProduto;
	protected BigDecimal valor;
	protected List<Desconto> descontos = new ArrayList<Desconto>();

	public ItemCarrinho(Long codigoProduto, String nomeProduto, BigDecimal valor) {
		super();
		this.codigoProduto = codigoProduto;
		this.nomeProduto = nomeProduto;
		this.valor = valor;
	}

	public Long getCodigoProduto() {
		return codigoProduto;
	}

	public void setCodigoProduto(Long codigoProduto) {
		this.codigoProduto = codigoProduto;
	}

	public String getNomeProduto() {
		return nomeProduto;
	}

	public void setNomeProduto(String nomeProduto) {
		this.nomeProduto = nomeProduto;
	}

	public BigDecimal getValor() {
		return valor;
	}

	public void setValor(BigDecimal valor) {
		this.valor = valor;
	}

	public BigDecimal getValorComDesconto() {
		return getValor().multiply(
				BigDecimal.valueOf(1)
						.subtract(
								getPercentualDesconto().divide(
										BigDecimal.valueOf(100))));
	}

	private BigDecimal getPercentualDesconto() {
		return CalculadoraDeDesconto.consolidarDescontos(this);
	}	

	@Override
	public void registrarDesconto(Desconto desconto) {
		this.descontos.add(desconto);
	}
	
	public List<Desconto> getDescontos() {
		return descontos;
	}

}
