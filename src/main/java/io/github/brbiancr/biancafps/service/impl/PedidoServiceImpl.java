/*
	implementa a interface PedidoService e contém a lógica de negócios relacionada ao gerenciamento de pedidos
*/

package io.github.brbiancr.biancafps.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import io.github.brbiancr.biancafps.domain.entity.Cliente;
import io.github.brbiancr.biancafps.domain.entity.ItemPedido;
import io.github.brbiancr.biancafps.domain.entity.Pedido;
import io.github.brbiancr.biancafps.domain.entity.Produto;
import io.github.brbiancr.biancafps.domain.enums.StatusPedido;
import io.github.brbiancr.biancafps.domain.repository.Clientes;
import io.github.brbiancr.biancafps.domain.repository.ItensPedido;
import io.github.brbiancr.biancafps.domain.repository.Pedidos;
import io.github.brbiancr.biancafps.domain.repository.Produtos;
import io.github.brbiancr.biancafps.exception.PedidoNaoEncontradoException;
import io.github.brbiancr.biancafps.exception.RegraNegocioException;
import io.github.brbiancr.biancafps.rest.dto.ItemPedidoDTO;
import io.github.brbiancr.biancafps.rest.dto.PedidoDTO;
import io.github.brbiancr.biancafps.service.PedidoService;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PedidoServiceImpl implements PedidoService {

    private final Pedidos repository;
    private final Clientes clientesRepository;
    private final Produtos produtosRepository;
    private final ItensPedido itensPedidoRepository;

    @Override
    @Transactional
    public Pedido salvar( PedidoDTO dto ) {
        Integer idCliente = dto.getCliente();
        // Valida o ID do cliente contido no DTO, lançando uma exceção se não for encontrado.
        Cliente cliente = clientesRepository
                .findById(idCliente)
                .orElseThrow(() -> new RegraNegocioException("Código de cliente inválido."));

        Pedido pedido = new Pedido();
        pedido.setTotal(dto.getTotal());
        pedido.setDataPedido(LocalDate.now());
        pedido.setCliente(cliente);
        pedido.setStatus(StatusPedido.REALIZADO);

        List<ItemPedido> itemsPedido = converterItems(pedido, dto.getItens()); // Converte os itens do pedido (de ItemPedidoDTO para ItemPedido).
        repository.save(pedido);
        itensPedidoRepository.saveAll(itemsPedido);
        pedido.setItens(itemsPedido);
        return pedido;
    }

    // Converte uma lista de ItemPedidoDTO em uma lista de ItemPedido.
    private List<ItemPedido> converterItems(Pedido pedido, List<ItemPedidoDTO> items){
        if(items.isEmpty()){
            throw new RegraNegocioException("Não é possível realizar um pedido sem items.");
        }

        return items
                .stream()
                .map( dto -> {
                    Integer idProduto = dto.getProduto();
                    Produto produto = produtosRepository
                            .findById(idProduto)
                            .orElseThrow(
                                    () -> new RegraNegocioException(
                                            "Código de produto inválido: "+ idProduto
                                    ));

                    ItemPedido itemPedido = new ItemPedido();
                    itemPedido.setQuantidade(dto.getQuantidade());
                    itemPedido.setPedido(pedido);
                    itemPedido.setProduto(produto);
                    return itemPedido;
                }).collect(Collectors.toList());

    }

	@Override
	public Optional<Pedido> obterPedidoCompleto(Integer id) {
		// TODO Auto-generated method stub
		return repository.findByIdFetchItens(id);
	}

	@Override
	@Transactional
	public void atualizaStatus(Integer id, StatusPedido statusPedido) {
	    repository
	        .findById(id)
	        .map(pedido -> {
	            pedido.setStatus(statusPedido); // Atualiza o status do pedido
	            return repository.save(pedido); // Salva o pedido atualizado
	        }).orElseThrow(() -> new PedidoNaoEncontradoException()); // Lança exceção se o pedido não for encontrado
	}

}