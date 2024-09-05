package com.example.scuba

import com.example.scuba.config.ConfigProperties
import com.example.scuba.persistence.Fish
import com.example.scuba.persistence.FishRepository
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.core.env.Environment
import org.springframework.stereotype.Service
import org.springframework.jdbc.core.JdbcTemplate
import org.springframework.beans.factory.annotation.Autowired
import java.util.UUID


@ConfigurationPropertiesScan
@SpringBootApplication
class ScubaApplication

fun main(args: Array<String>) {
	runApplication<ScubaApplication>(*args)
}

@RestController
class TestController(val service: FishService, val config: ConfigProperties, val environment: Environment) {
	
	@GetMapping("/")
	fun index() = listOf(Fish(1, "Grouper"), Fish(2, "Blue Tang"), Fish(3, "${config.hello}"), Fish(4, "${environment.getProperty("ENV_HELLO")}"))

	@GetMapping("/fish")
    fun getAllFish(): List<Fish> = service.findFish()

	@PostMapping("/")
	fun post(@RequestBody fish: Fish) {
		service.save(fish)
	}
}

@Service
class FishService(@Autowired private val fishRepository: FishRepository) {
    fun findFish(): List<Fish> = fishRepository.findAll().toList()

    fun save(fish: Fish) {
        fishRepository.save(fish)
    }
}

