package odon.drools.poc.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import odon.drools.poc.constants.TipoFormaPagamento;


public class Compra implements RecebeDesconto {

	protected Usuario usuario;
	protected Carrinho carrinho;
	protected Endereco enderecoEntrega;
	protected BigDecimal valorFrete;
	protected String cupom;
	protected Calendar data = GregorianCalendar.getInstance();
	protected TipoFormaPagamento tipoFormaPagamento;	
	protected BigDecimal valorTotal = BigDecimal.valueOf(0.0);
	protected List<Desconto> descontos = new ArrayList<Desconto>();

	public Compra(Usuario usuario, Carrinho carrinho, Endereco enderecoEntrega) {
		this.usuario = usuario;
		this.carrinho = carrinho;
		this.enderecoEntrega = enderecoEntrega;
	}

	public Usuario getUsuario() {
		return usuario;
	}

	public Carrinho getCarrinho() {
		return carrinho;
	}

	public Endereco getEnderecoEntrega() {
		return enderecoEntrega;
	}

	public BigDecimal getValorProdutos() {
		return this.carrinho.getValorTotal();
	}

	public BigDecimal getValorFrete() {
		return valorFrete;
	}

	public void setValorFrete(BigDecimal valorFrete) {
		this.valorFrete = valorFrete;
	}

	public String getCupom() {
		return cupom;
	}

	public void setCupom(String cupom) {
		this.cupom = cupom;
	}

	public TipoFormaPagamento getTipoFormaPagamento() {
		return tipoFormaPagamento;
	}

	public void setTipoFormaPagamento(TipoFormaPagamento tipoFormaPagamento) {
		this.tipoFormaPagamento = tipoFormaPagamento;
	}

	public Calendar getData() {
		return data;
	}

	public void setData(Calendar data) {
		this.data = data;
	}

	public BigDecimal getValorTotal() {
		BigDecimal desconto = BigDecimal.valueOf(1).subtract(getPercentualDesconto().divide(BigDecimal.valueOf(100)));		
		BigDecimal valor = BigDecimal.valueOf(0);
		valor = valor.add(carrinho.getValorTotal());
		if(desconto != null){
			valor = valor.multiply(desconto);
		}
		if(valorFrete != null){
			valor = valor.add(valorFrete);		
		}
		return valor;
	}

	public BigDecimal getPercentualDesconto() {
		return CalculadoraDeDesconto.consolidarDescontos(this);
	}
	
	public void registrarDesconto(Desconto desconto){
		descontos.add(desconto);
	}
	
	public List<Desconto> getDescontos() {
		return descontos;
	}
}
