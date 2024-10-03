/*
	A classe ItemPedidoDTO serve para transferir dados relacionados a itens dos pedidos de maneira organizada 
	e controlada entre as diferentes camadas da aplicação. Ao utilizar DTOs, você melhora a 
	modularidade, segurança e clareza da aplicação, além de facilitar a manutenção e a evolução 
	do código ao longo do tempo.
*/

package io.github.brbiancr.biancafps.rest.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ItemPedidoDTO {
    private Integer produto;
    private Integer quantidade;
}