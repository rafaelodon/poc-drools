package odon.drools.poc.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;


public class Carrinho {

	protected List<ItemCarrinho> itens = new ArrayList<ItemCarrinho>();	
	
	public void adicionarItem(ItemCarrinho item){
		itens.add(item);
	}
	
	public void removerItem(ItemCarrinho item){
		itens.remove(item);
	}
	
	public boolean contem(Long codigoProduto){		
		return itens.contains(new ItemCarrinho(codigoProduto, null, null));
	}
	
	public List<ItemCarrinho> getItens() {
		return itens;
	}
	
	public BigDecimal getValorTotal(){
		BigDecimal total = BigDecimal.valueOf(0.0);
		for(ItemCarrinho item:itens){
			total = total.add(item.getValor());
		}
		return total;
	}	
}
