/*
	define um contrato para os serviços relacionados ao gerenciamento de pedidos
*/

package io.github.brbiancr.biancafps.service;

import java.util.Optional;

import io.github.brbiancr.biancafps.domain.entity.Pedido;
import io.github.brbiancr.biancafps.domain.enums.StatusPedido;
import io.github.brbiancr.biancafps.rest.dto.PedidoDTO;

public interface PedidoService {
    Pedido salvar( PedidoDTO dto );
    Optional<Pedido> obterPedidoCompleto(Integer id);
    void atualizaStatus(Integer id, StatusPedido statusPedido);
}