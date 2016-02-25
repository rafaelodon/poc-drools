package odon.drools.poc.model;

import java.math.BigDecimal;

public class CalculadoraDeDesconto {

	public static BigDecimal consolidarDescontos(RecebeDesconto recebeDesconto) {
		BigDecimal percentual = BigDecimal.valueOf(0.0);
		BigDecimal maior = BigDecimal.valueOf(0.0);
		for(Desconto desconto:recebeDesconto.getDescontos()){
			if(desconto.getAcumulativo()){
				percentual = percentual.add(desconto.getPercentual());				
			}else{
				if(desconto.getPercentual().compareTo(maior) > 0){
					maior = desconto.getPercentual();					
				}
			}
		}
		percentual = percentual.add(maior);
		if(percentual.compareTo(BigDecimal.valueOf(100)) > 0){
			percentual = BigDecimal.valueOf(100);
		}
		return percentual;
	}
}
