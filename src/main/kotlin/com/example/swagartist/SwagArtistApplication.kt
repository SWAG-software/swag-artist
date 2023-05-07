package com.example.swagartist

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.cloud.client.discovery.EnableDiscoveryClient
import org.springframework.cloud.openfeign.EnableFeignClients

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
class SwagArtistApplication {
//	@Bean
//	fun converter(): Jackson2JsonMessageConverter {
//		return Jackson2JsonMessageConverter()
//	}
}

fun main(args: Array<String>) {
	runApplication<SwagArtistApplication>(*args)
}
