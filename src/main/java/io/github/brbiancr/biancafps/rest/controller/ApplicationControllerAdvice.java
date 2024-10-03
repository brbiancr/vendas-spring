/*
	classe que gerencia o tratamento global de exceções em uma API utilizando o Spring Framework. 
	Ela é anotada com @RestControllerAdvice, o que indica que essa classe é um componente de tratamento 
	de erros que lida com exceções lançadas em todos os controladores da aplicação (controllers). 
	A principal função desta classe é interceptar exceções específicas e retornar respostas HTTP 
	apropriadas com mensagens de erro padronizadas, sem que cada controlador precise tratar essas 
	exceções manualmente.
 */

package io.github.brbiancr.biancafps.rest.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import io.github.brbiancr.biancafps.exception.PedidoNaoEncontradoException;
import io.github.brbiancr.biancafps.exception.RegraNegocioException;
import io.github.brbiancr.biancafps.rest.ApiErrors;


@RestControllerAdvice
public class ApplicationControllerAdvice {
	
	// Intercepta exceções do tipo RegraNegocioException, que representam violações de regras de negócio.
    @ExceptionHandler(RegraNegocioException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ApiErrors handleRegraNegocioException(RegraNegocioException ex){
        String mensagemErro = ex.getMessage();
        return new ApiErrors(mensagemErro);
    }
    
    // Lida com exceções do tipo PedidoNaoEncontradoException, que são lançadas quando um pedido não é encontrado no sistema.
    @ExceptionHandler(PedidoNaoEncontradoException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ApiErrors handlePedidoNotFoundException(PedidoNaoEncontradoException ex) {
    	return new ApiErrors(ex.getMessage());
    }
}