package com.kaioferreira.pagamento.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kaioferreira.pagamento.entity.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long>{

}
