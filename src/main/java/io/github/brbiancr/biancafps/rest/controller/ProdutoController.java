/*
	controlador Spring REST que gerencia operações relacionadas a produtos em uma aplicação. 
	Ela fornece uma interface para realizar operações CRUD (Create, Read, Update e Delete) em produtos.
*/

package io.github.brbiancr.biancafps.rest.controller;

import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import io.github.brbiancr.biancafps.domain.entity.Produto;
import io.github.brbiancr.biancafps.domain.repository.Produtos;

import java.util.List;

import static org.springframework.http.HttpStatus.*;

@RestController
@RequestMapping("/api/produtos")
public class ProdutoController {

    private Produtos repository;

    public ProdutoController(Produtos repository) {
        this.repository = repository;
    }

    @PostMapping
    @ResponseStatus(CREATED)
    public Produto save( @RequestBody Produto produto ){
        return repository.save(produto);
    }

    @PutMapping("{id}")
    @ResponseStatus(NO_CONTENT)
    public void update( @PathVariable Integer id, @RequestBody Produto produto ){
        repository
                .findById(id)
                .map( p -> {
                   produto.setId(p.getId());
                   repository.save(produto);
                   return produto;
                }).orElseThrow( () ->
                new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Produto não encontrado."));
    }

    @DeleteMapping("{id}")
    @ResponseStatus(NO_CONTENT)
    public void delete(@PathVariable Integer id){
        repository
                .findById(id)
                .map( p -> {
                    repository.delete(p);
                    return Void.TYPE;
                }).orElseThrow( () ->
                new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Produto não encontrado."));
    }

    @GetMapping("{id}")
    public Produto getById(@PathVariable Integer id){
        return repository
                .findById(id)
                .orElseThrow( () ->
                new ResponseStatusException(HttpStatus.NOT_FOUND,
                        "Produto não encontrado."));
    }

    @GetMapping
    public List<Produto> find(Produto filtro ){
    	// Configura o exemplo de busca, definindo as regras de correspondência
        ExampleMatcher matcher = ExampleMatcher
                					.matching() // Inicia a construção do matcher
                					.withIgnoreCase() // Ignora diferenças entre maiúsculas e minúsculas
                					.withStringMatcher(
                							ExampleMatcher.StringMatcher.CONTAINING ); // Usa correspondência parcial nas strings
        
        // Cria um exemplo com base no filtro recebido e no matcher configurado
        Example<Produto> example = Example.of(filtro, matcher);
        
        return repository.findAll(example);
    }
}