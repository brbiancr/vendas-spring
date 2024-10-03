/*
	A classe PedidoDTO serve para transferir dados relacionados a pedidos de maneira organizada 
	e controlada entre as diferentes camadas da aplicação. Ao utilizar DTOs, você melhora a 
	modularidade, segurança e clareza da aplicação, além de facilitar a manutenção e a evolução 
	do código ao longo do tempo.
*/

package io.github.brbiancr.biancafps.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PedidoDTO {
    private Integer cliente;
    private BigDecimal total;
    private List<ItemPedidoDTO> itens;
}