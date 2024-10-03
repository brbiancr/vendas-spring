package io.github.brbiancr.biancafps.domain.repository;

import io.github.brbiancr.biancafps.domain.entity.ItemPedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItensPedido extends JpaRepository<ItemPedido, Integer> {
	
}