/*
	A interface Pedidos é um repositório que gerencia a entidade Pedido, estendendo a interface JpaRepository. 
	Com isso, ela herda todos os métodos padrão do Spring Data JPA para manipulação de dados relacionados à 
	entidade Pedido, como salvar, atualizar, buscar por ID, excluir, etc. Além disso, essa interface também 
	define consultas específicas para atender a requisitos de negócios.
*/

package io.github.brbiancr.biancafps.domain.repository;

import io.github.brbiancr.biancafps.domain.entity.Cliente;
import io.github.brbiancr.biancafps.domain.entity.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface Pedidos extends JpaRepository<Pedido, Integer> {

    List<Pedido> findByCliente(Cliente cliente);
    
    // Carrega um pedido pelo ID e também os itens associados ao pedido
    @Query("SELECT p FROM Pedido p JOIN FETCH p.itens WHERE p.id = :id")
    Optional<Pedido> findByIdFetchItens(@Param("id") Integer id);
}