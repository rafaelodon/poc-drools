package odon.drools.poc.model;

import java.util.List;


public interface RecebeDesconto {
	
	void registrarDesconto(Desconto desconto);
	List<Desconto> getDescontos();

}
