/*
	A classe InformacaoItemPedidoDTO serve para transferir dados relacionados a informações dos itens dos pedidos de maneira organizada 
	e controlada entre as diferentes camadas da aplicação. Ao utilizar DTOs, você melhora a 
	modularidade, segurança e clareza da aplicação, além de facilitar a manutenção e a evolução 
	do código ao longo do tempo.
*/

package io.github.brbiancr.biancafps.rest.dto;

import java.math.BigDecimal;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class InformacaoItemPedidoDTO {
	private String descricaoProduto;
	private BigDecimal precoUnitario;
	private Integer quantidade;	
}
