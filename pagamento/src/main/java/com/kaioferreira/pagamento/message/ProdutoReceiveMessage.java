package com.kaioferreira.pagamento.message;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.kaioferreira.pagamento.data.vo.ProdutoVO;
import com.kaioferreira.pagamento.entity.Produto;
import com.kaioferreira.pagamento.exception.ResourceNotFoundException;
import com.kaioferreira.pagamento.repository.ProdutoRepository;

@Component
public class ProdutoReceiveMessage {
	
	private final ProdutoRepository produtoRepository;
	
	@Autowired
	public ProdutoReceiveMessage(ProdutoRepository produtoRepository) {
		this.produtoRepository = produtoRepository;
	}
	
	@RabbitListener(queues={"${crud.rabbitmq.queue}"})
	public void receiveMessageCreate(@Payload ProdutoVO produtoVO) {
		produtoRepository.save(Produto.create(produtoVO));
	}
	
	@RabbitListener(queues={"${crud.rabbitmq.queue-delete}"})
	public void receiveMessageCreate(@Payload Long id) {
		Produto produto = produtoRepository.findById(id)
				.orElseThrow(() -> new ResourceNotFoundException("No records found for this ID"));
		
		produtoRepository.delete(produto);
	}
}
