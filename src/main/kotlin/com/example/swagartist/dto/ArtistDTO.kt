package com.example.swagartist.dto

import com.example.swagartist.dao.Artist
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface ArtistDTO: MongoRepository<Artist, String> {
    override fun findAll(): MutableList<Artist>
}