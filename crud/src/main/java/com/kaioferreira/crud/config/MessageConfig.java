package com.kaioferreira.crud.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MessageConfig {

	@Value("${crud.rabbitmq.exchange}")
	String exchange;
	
	@Value("${crud.rabbitmq.routingkey}")
	String routingkey;
	
	@Value("${crud.rabbitmq.routingKey-delete}")
	String routingKeyDelete;
	
	@Value("${crud.rabbitmq.queue}")
	String queue;
	
	@Value("${crud.rabbitmq.queue-delete}")
	String queueDelete;
	
	@Bean
	public Exchange declareExchage() {
		return ExchangeBuilder.directExchange(exchange).durable(true).build();
	}
	
	@Bean
	public Exchange declareExchangeDelete() {
		return ExchangeBuilder.directExchange(exchange).durable(true).build();
	}
	
	@Bean
	public Binding declareBinding() {
		return BindingBuilder.bind(declareQueue()).to(declareExchage())
				.with(routingkey).noargs();
	}
	
	@Bean
	public Binding declareBindingDelete() {
		return BindingBuilder.bind(declareQueueDelete()).to(declareExchage())
				.with(routingKeyDelete).noargs();
	}

	@Bean
	public MessageConverter jsonMessageConverter() {
		return new Jackson2JsonMessageConverter();
	}
	
	@Bean
	public Queue declareQueue() {
		return QueueBuilder.durable(queue).build();
	}
	
	@Bean
	public Queue declareQueueDelete() {
		return QueueBuilder.durable(queueDelete).build();
	}
}