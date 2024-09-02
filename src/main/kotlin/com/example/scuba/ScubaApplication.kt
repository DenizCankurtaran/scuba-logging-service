package com.example.scuba

import com.example.scuba.config.ConfigProperties
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
import java.util.UUID


@ConfigurationPropertiesScan
@SpringBootApplication
class ScubaApplication

fun main(args: Array<String>) {
	runApplication<ScubaApplication>(*args)
}

data class Fish(val id: String?, val name: String)

@RestController
class TestController(val service: FishService, val config: ConfigProperties, val environment: Environment) {
	
	@GetMapping("/")
	fun index() = listOf(Fish("1", "Grouper"), Fish("2", "Blue Tang"), Fish("3", "${config.hello}"), Fish("4", "${environment.getProperty("ENV_HELLO")}"))

	@GetMapping("/fish")
    fun getAllFish(): List<Fish> = service.findFish()

	@PostMapping("/")
	fun post(@RequestBody fish: Fish) {
		service.save(fish)
	}
}

@Service
class FishService(val db: JdbcTemplate) {
    fun findFish(): List<Fish> = db.query("select * from fish") { response, _ ->
        Fish(response.getString("id"), response.getString("name"))
    }

    fun save(fish: Fish) {
		val id = fish.id ?: UUID.randomUUID().toString()
        db.update(
            "insert into fish values ( ?, ? )",
            id, fish.name
        )
    }
}