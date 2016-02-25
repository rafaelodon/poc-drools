[keyword][]Configurar=
import odon.drools.poc.model.*
import odon.constants.*
import java.util.GregorianCalendar
import java.math.BigDecimal
global org.slf4j.Logger logger
expander regras_compras_dsl.dsl

[keyword][][Rr]egra=rule
[keyword][][Ss]e=when
[keyword][][Ee]ntão=then
[keyword][][Ff]im=end
[keyword][][Pp]rioridade=salience

[condition][Compra][Ee]xiste uma compra=$compra:Compra()
[condition][Compra]- Cujo frete não foi definido=$compra.valorFrete == null
[condition][Compra]- Cujo valor da compra é maior ou igual a {valor}=$compra.valorProdutos >= {valor}
[condition][Endereco]A UF do endereço é {valorUf}=Endereco( uf==UF.{valorUf} )
[condition][Endereco]A UF do endereço é {valorUf2} ou=Endereco( uf==UF.{valorUf2} ) or

[consequence][]Mude o frete para {valorFrete}=modify($compra)\{ setValorFrete(BigDecimal.valueOf({valorFrete})) \};
[consequence][]Registre o log {textoLog}=logger.debug({textoLog});