package com.example.scuba

import com.example.scuba.config.ConfigProperties
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController
import org.springframework.boot.context.properties.ConfigurationPropertiesScan

@ConfigurationPropertiesScan
@SpringBootApplication
class ScubaApplication

fun main(args: Array<String>) {
	runApplication<ScubaApplication>(*args)
}

data class Fish(val id: String?, val name: String)

@RestController
class TestController(val config: ConfigProperties) {
	
	@GetMapping("/")
	fun index(@RequestParam("name") name: String?) = listOf(Fish("1", "Grouper"), Fish("2", "Blue Tang"), Fish("3", "${config.hello}"))
}