package io.github.brbiancr.biancafps.domain.repository;

import io.github.brbiancr.biancafps.domain.entity.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface Produtos extends JpaRepository<Produto,Integer> {
	
}