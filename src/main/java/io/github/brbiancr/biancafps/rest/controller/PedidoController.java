/*
	controlador Spring REST que gerencia operações relacionadas a pedidos em uma aplicação. 
	Ela define endpoints para criar, obter e atualizar pedidos.
	Utiliza DTO (Data Transfer Object), que geralmente contém apenas os dados necessários 
	para criar um pedido, sem a complexidade da entidade Pedido
 */

package io.github.brbiancr.biancafps.rest.controller;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import io.github.brbiancr.biancafps.domain.entity.ItemPedido;
import io.github.brbiancr.biancafps.domain.entity.Pedido;
import io.github.brbiancr.biancafps.domain.enums.StatusPedido;
import io.github.brbiancr.biancafps.rest.dto.AtualizacaoStatusPedidoDTO;
import io.github.brbiancr.biancafps.rest.dto.InformacaoItemPedidoDTO;
import io.github.brbiancr.biancafps.rest.dto.InformacoesPedidoDTO;
import io.github.brbiancr.biancafps.rest.dto.PedidoDTO;
import io.github.brbiancr.biancafps.service.PedidoService;

import static org.springframework.http.HttpStatus.*;

import java.time.format.DateTimeFormatter;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.util.CollectionUtils;

@RestController
@RequestMapping("/api/pedidos")
public class PedidoController {

    private PedidoService service;

    public PedidoController(PedidoService service) {
        this.service = service;
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public Integer save( @RequestBody PedidoDTO dto ){
        Pedido pedido = service.salvar(dto);
        return pedido.getId();
    }
    
    @GetMapping("{id}")
    public InformacoesPedidoDTO getById(@PathVariable Integer id) {
    	return service
    			.obterPedidoCompleto(id)
    			.map(p -> converter(p))
    			.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Pedido não encontrado!"));
    }
    
    private InformacoesPedidoDTO converter(Pedido pedido) {
    	return InformacoesPedidoDTO
    			.builder()
    			.codigo(pedido.getId())
    			.dataPedido(pedido.getDataPedido().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")))
    			.cpf(pedido.getCliente().getCpf())
    			.nomeCliente(pedido.getCliente().getNome())
    			.total(pedido.getTotal())
    			.status(pedido.getStatus().name())
    			.itens(converter(pedido.getItens()))
    			.build();
    }
    
    private List<InformacaoItemPedidoDTO> converter(List<ItemPedido> itens){
    	if(CollectionUtils.isEmpty(itens)) {
    		return Collections.emptyList();
    	}
    	
    	return itens.stream().map(
    			item -> InformacaoItemPedidoDTO
    			.builder().descricaoProduto(item.getProduto().getDescricao())
    			.precoUnitario(item.getProduto().getPreco())
    			.quantidade(item.getQuantidade())
    			.build()
    	).collect(Collectors.toList());
    }
    
    @PatchMapping("{id}")
    @ResponseStatus(NO_CONTENT)
    public void updateStatus(@PathVariable Integer id, @RequestBody AtualizacaoStatusPedidoDTO dto) {
    	String novoStatus = dto.getNovoStatus();
    	service.atualizaStatus(id, StatusPedido.valueOf(novoStatus));
    }
}