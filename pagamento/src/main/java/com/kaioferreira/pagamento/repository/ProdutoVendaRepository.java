package com.kaioferreira.pagamento.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.kaioferreira.pagamento.entity.ProdutoVenda;

public interface ProdutoVendaRepository extends JpaRepository<ProdutoVenda, Long> {

}
