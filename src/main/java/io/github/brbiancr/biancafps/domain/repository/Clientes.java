/* 
 	A interface Clientes define um repositório específico para a entidade Cliente, estendendo a interface JpaRepository. 
 	Isso significa que ela herda todos os métodos padrão do Spring Data JPA para manipulação de dados, como salvar, 
 	atualizar, buscar por ID, excluir, etc., e adiciona consultas personalizadas para atender a requisitos específicos
  	do domínio.
*/

package io.github.brbiancr.biancafps.domain.repository;

import io.github.brbiancr.biancafps.domain.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface Clientes extends JpaRepository<Cliente, Integer > {

    @Query(value = " select * from cliente c where c.nome like '%:nome%' ", nativeQuery=true)
    List<Cliente> encontrarPorNome( @Param("nome") String nome );

    @Query(" delete from Cliente c where c.nome =:nome ")
    @Modifying //indica que essa operação modifica o estado do banco de dados
    void deleteByNome(String nome);

    boolean existsByNome(String nome);
    
    // Carrega um cliente pelo ID e também os pedidos associados ao cliente
    @Query(" select c from Cliente c left join fetch c.pedidos where c.id = :id  ")
    Cliente findClienteFetchPedidos( @Param("id") Integer id );


}