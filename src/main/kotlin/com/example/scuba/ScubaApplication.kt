package com.example.scuba

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@SpringBootApplication
class ScubaApplication

fun main(args: Array<String>) {
	runApplication<ScubaApplication>(*args)
}

data class Fish(val id: String?, val name: String)

@RestController
class TestController {
	
	@GetMapping("/")
	fun index(@RequestParam("name") name: String?) = listOf(Fish("1", "Grouper"), Fish("2", "Blue Tang"), Fish("3", "Firefish"))
}