/*
	exceção personalizada que estende a classe RuntimeException. Ela foi criada para ser lançada 
 	quando um pedido específico não é encontrado no sistema, permitindo o tratamento de erros de 
 	maneira mais expressiva e adequada ao domínio da aplicação.
*/

package io.github.brbiancr.biancafps.exception;

public class PedidoNaoEncontradoException extends RuntimeException{

	public PedidoNaoEncontradoException() {
		super("Pedido não encontrado");
	}
}
