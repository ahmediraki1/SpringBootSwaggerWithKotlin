package com.example.swagger

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer
import org.springframework.boot.SpringApplication
import com.example.swagger.repo.UserRepository
import org.springframework.boot.CommandLineRunner
import java.util.stream.LongStream
import org.springframework.context.annotation.Bean
import com.example.swagger.models.User
import java.util.Random

@SpringBootApplication
open class SwaggerApplication : SpringBootServletInitializer() {
	@Bean
	open fun initActors(repository: UserRepository) = CommandLineRunner {
		repository.deleteAll()
		LongStream.range(1, 11)
			.mapToObj { i ->
				val c = User()
				c.userName = "user $i"
				c.emailAddress = c.userName + "@system.com"
				c.phoneNumber = "0100" + i * 100 + "100" + i
				c.active = Random().nextBoolean()
				c
			}
			.map { v ->
				repository.save(v)
			}
			.forEach({ println(it) })
	}

	companion object {
		@JvmStatic
		fun main(args: Array<String>) {
			SpringApplication.run(SwaggerApplication::class.java, *args)
		}
	}
}

