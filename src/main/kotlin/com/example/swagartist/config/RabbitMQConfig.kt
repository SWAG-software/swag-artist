package com.example.swagartist.config

import org.springframework.amqp.core.*
import org.springframework.amqp.rabbit.connection.ConnectionFactory
import org.springframework.amqp.rabbit.core.RabbitTemplate
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class RabbitMQConfig {
    @Bean
    fun notification2ExchangeArtist(): DirectExchange {
        return DirectExchange("notification2ExchangeArtist")
    }

    @Bean
    fun notification2QueueArtist(): Queue {
        return QueueBuilder.durable("notification2QueueArtist").build()
    }

    @Bean
    fun notification2BindingArtist(notification2QueueArtist: Queue, notification2ExchangeArtist: DirectExchange): Binding {
        return BindingBuilder.bind(notification2QueueArtist).to(notification2ExchangeArtist).with("notification2RoutingKeyArtist")
    }

    @Bean
    fun converter(): Jackson2JsonMessageConverter {
        return Jackson2JsonMessageConverter()
    }

    @Bean
    fun amqpTemplate(connectionFactory: ConnectionFactory): AmqpTemplate {
        val rabbitTemplate = RabbitTemplate(connectionFactory)
        rabbitTemplate.messageConverter = converter()
        return rabbitTemplate
    }
}