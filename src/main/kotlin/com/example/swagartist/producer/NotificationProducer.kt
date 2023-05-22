package com.example.swagartist.producer

import com.example.swagartist.dao.Notification
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.amqp.core.AmqpTemplate
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class NotificationProducer @Autowired constructor(private val amqpTemplate: AmqpTemplate)  {
    // Logger
    companion object {
        private val LOGGER: Logger = LoggerFactory.getLogger(NotificationProducer::class.java)
    }

    fun sendNotification(notification: Notification): String {
        val response = amqpTemplate.convertAndSend("notification2ExchangeArtist", "notification2RoutingKeyArtist", notification)
        LOGGER.info("Notification sent: $notification")
        return "Mensaje enviado: ${notification.toString()}";
    }
}