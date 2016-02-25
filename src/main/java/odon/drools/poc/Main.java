package odon.drools.poc;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.GregorianCalendar;

import odon.drools.poc.constants.TipoFormaPagamento;
import odon.drools.poc.constants.UF;
import odon.drools.poc.model.Carrinho;
import odon.drools.poc.model.Compra;
import odon.drools.poc.model.Endereco;
import odon.drools.poc.model.ItemCarrinho;
import odon.drools.poc.model.Usuario;

import org.kie.api.KieServices;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.StatelessKieSession;
import org.slf4j.LoggerFactory;


public class Main {

	public static final void main(String[] args) {

		long tempoInicio=0;
		
		tempoInicio = System.currentTimeMillis();
		
		KieServices ks = KieServices.Factory.get();
		KieContainer kcontainer = ks.getKieClasspathContainer();				
		StatelessKieSession ksession = kcontainer.newStatelessKieSession();
		ksession.setGlobal("logger", LoggerFactory.getLogger("Regras"));
		
		System.out.println("Tempo gasto: "+(System.currentTimeMillis() - tempoInicio)+" ms.");
		
		tempoInicio = System.currentTimeMillis(); 

		System.out.println("\n\n*********** COMPRA #1 **************");		
		Compra c1 = criarCompra1();
				
		ksession.execute(criarFatosDaCompra(c1));
		imprimirCompra(c1);
		
		System.out.println("Tempo gasto: "+(System.currentTimeMillis() - tempoInicio)+" ms.");
		
		tempoInicio = System.currentTimeMillis();
		
		System.out.println("\n\n*********** COMPRA #2 **************");
		Compra c2 = criarCompra2();
		ksession.execute(criarFatosDaCompra(c2));
		imprimirCompra(c2);
		
		System.out.println("Tempo gasto: "+(System.currentTimeMillis() - tempoInicio)+" ms.");
		
		tempoInicio = System.currentTimeMillis();
		
		System.out.println("\n\n*********** COMPRA #3 **************");
		Compra c3 = criarCompra3();
		ksession.execute(criarFatosDaCompra(c3));		
		imprimirCompra(c3);
		
		System.out.println("Tempo gasto: "+(System.currentTimeMillis() - tempoInicio)+" ms.");
		
		tempoInicio = System.currentTimeMillis();
		
	}

	private static Compra criarCompra3() {
		Usuario usuario = new Usuario();
		usuario.setPrimeiraCompra(false);
		usuario.setEnviarNewsletter(true);

		Carrinho carrinho = new Carrinho();		
		carrinho.adicionarItem(criarItemMeiaAdidas());				

		Endereco enderecoEntrega = new Endereco();
		enderecoEntrega.setUf(UF.AC);

		Compra compra = new Compra(usuario, carrinho, enderecoEntrega);		
		compra.setTipoFormaPagamento(TipoFormaPagamento.CREDITO);
		
		return compra;
	}

	private static ItemCarrinho criarItemTenisNike() {
		return new ItemCarrinho(25L, "Tênis da Nike", BigDecimal.valueOf(200.00));
	}

	private static Compra criarCompra2() {
		Usuario usuario = new Usuario();
		usuario.setPrimeiraCompra(false);
		usuario.setEnviarNewsletter(false);

		Carrinho carrinho = new Carrinho();		
		carrinho.adicionarItem(criarItemTenisNike());				

		Endereco enderecoEntrega = new Endereco();
		enderecoEntrega.setUf(UF.MG);

		Compra compra = new Compra(usuario, carrinho, enderecoEntrega);		
		compra.setTipoFormaPagamento(TipoFormaPagamento.CREDITO);
		
		return compra;
	}

	private static ArrayList<Object> criarFatosDaCompra(Compra c1) {
		ArrayList<Object> fatos = new ArrayList<Object>();
		fatos.add(c1);
		fatos.add(c1.getEnderecoEntrega());
		fatos.add(c1.getUsuario());
		fatos.add(c1.getCarrinho());
		for(ItemCarrinho item:c1.getCarrinho().getItens()){
			fatos.add(item);	
		}
		return fatos;
	}

	private static void imprimirCompra(Compra c1) {
		System.out.println("==== Carrinho ======");
		System.out.println("Cod\tPreço\t\tDescrição");
		for(ItemCarrinho item:c1.getCarrinho().getItens()){
			System.out.printf("%d\t%f\t%s\n",item.getCodigoProduto(),item.getValorComDesconto(),item.getNomeProduto());
		}
		System.out.println("* Valor total do carrinho: "+c1.getCarrinho().getValorTotal());
		System.out.println("* Desconto: "+c1.getPercentualDesconto()+"%");
		System.out.println("* Valor do frete: "+c1.getValorFrete());
		System.out.println("* Valor final: "+c1.getValorTotal());
	}

	private static Compra criarCompra1() {
		Usuario usuario = new Usuario();
		usuario.setPrimeiraCompra(true);
		usuario.setEnviarNewsletter(true);

		Carrinho carrinho = new Carrinho();
		carrinho.adicionarItem(criarItemTenisNike());
		carrinho.adicionarItem(criarItemTenisNike());
		carrinho.adicionarItem(criarItemMeiaAdidas());
		carrinho.adicionarItem(criarItemMeiaAdidas());
		carrinho.adicionarItem(criarItemMeiaAdidas());
		carrinho.adicionarItem(criarItemCamisaRebook());

		Endereco enderecoEntrega = new Endereco();
		enderecoEntrega.setUf(UF.SP);

		Compra compra = new Compra(usuario, carrinho, enderecoEntrega);
		compra.setCupom("BLACKFRIDAY2015");
		compra.setData(new GregorianCalendar(2015, 10, 27));
		compra.setTipoFormaPagamento(TipoFormaPagamento.BOLETO);
		
		return compra;
	}

	private static ItemCarrinho criarItemCamisaRebook() {
		return new ItemCarrinho(16L, "Camiseta Rebook", BigDecimal.valueOf(35.00));
	}

	private static ItemCarrinho criarItemMeiaAdidas() {
		return new ItemCarrinho(45L, "Par de Meias Adidas", BigDecimal.valueOf(10.00));
	}
}