/*
	estrutura que encapsula e organiza os erros retornados por uma API em formato de lista. 
	Ela é frequentemente usada para padronizar as respostas de erro no desenvolvimento de APIs REST, 
	permitindo que os clientes (front-end ou outras aplicações) recebam mensagens de erro de forma estruturada.
*/

package io.github.brbiancr.biancafps.rest;

import lombok.Getter;

import java.util.Arrays;
import java.util.List;

public class ApiErrors {

    @Getter
    private List<String> errors;

    public ApiErrors(String mensagemErro){
        this.errors = Arrays.asList(mensagemErro);
    }
}