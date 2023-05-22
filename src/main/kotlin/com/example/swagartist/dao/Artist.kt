package com.example.swagartist.dao

import org.bson.types.ObjectId
import org.springframework.data.annotation.Id
import org.springframework.data.mongodb.core.mapping.Document

@Document("artist")
data class Artist (
    @Id
    var id: String? = ObjectId().toHexString(),
    val artist: String = "",
    val image: String = ""
)