/*
	exceção personalizada que estende a classe RuntimeException. Ela foi criada para representar erros 
	ou violações de regras de negócio na aplicação. Quando uma regra de negócio é desrespeitada, essa 
	exceção pode ser lançada para sinalizar o erro, com uma mensagem personalizada que descreve o problema.
*/

package io.github.brbiancr.biancafps.exception;

public class RegraNegocioException extends RuntimeException {

    public RegraNegocioException(String message) {
        super(message);
    }
}	