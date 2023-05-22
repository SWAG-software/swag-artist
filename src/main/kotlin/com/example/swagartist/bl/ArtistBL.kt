package com.example.swagartist.bl

import com.example.swagartist.dao.Artist
import com.example.swagartist.dao.Notification
import com.example.swagartist.dto.ArtistDTO
import com.example.swagartist.producer.NotificationProducer
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class ArtistBL @Autowired constructor(private val artistDTO: ArtistDTO, private val notificationProducer: NotificationProducer){
    // Logger
    companion object {
        private val LOGGER: Logger = LoggerFactory.getLogger(ArtistBL::class.java)
    }
    // Get All Artists
    fun findAllArtists(): List<Artist>? {
        // Logger
        LOGGER.info("Artist BL working")
        // DTO
        return artistDTO.findAll()
    }

    // Send Notification by RabbitMQ
    fun sendNotification(idUser: String, idArtist: String, state: String): String {
        //Logger
        LOGGER.info("RabbitMQ working")
        return notificationProducer.sendNotification(Notification(idUser, idArtist, state))
    }
}