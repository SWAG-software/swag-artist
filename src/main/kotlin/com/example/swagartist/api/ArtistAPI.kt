package com.example.swagartist.api

import com.example.swagartist.bl.ArtistBL
import com.example.swagartist.dao.Artist
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RestController
import java.util.*

@RestController
class ArtistAPI {
    // Business Logic
    private var artistBL: ArtistBL? = null
    @Autowired
    fun ArtistAPI(artistBL: ArtistBL?) {
        this.artistBL = artistBL
    }

    // Logger
    companion object {
        private val LOGGER: Logger = LoggerFactory.getLogger(ArtistAPI::class.java)
    }

    // Get All Artists
    @GetMapping("/artist")
    fun getAllArtists(): ResponseEntity<List<Artist>> {
        // Logger
        LOGGER.info("Get All Artists API working")
        // BL
        val bl = artistBL?.findAllArtists()
        // Custom or Default values
        return if (bl != null) {
            ResponseEntity.ok(bl)
        } else {
            ResponseEntity.notFound().build()
        }
    }

    // Send Notification by RabbitMQ
    @GetMapping("/artist/like/{idUser}/{idArtist}/{state}")
    fun getNotification(
            @PathVariable("idUser") idUser: String,
            @PathVariable("idArtist") idArtist: String,
            @PathVariable("state") state: String
    ): String? {
        // Logger
        LOGGER.info("Send Notification by RabbitMQ API working")
        return artistBL?.sendNotification(idUser, idArtist, state)
    }
}