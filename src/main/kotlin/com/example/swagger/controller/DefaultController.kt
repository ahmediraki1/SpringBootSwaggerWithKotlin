package com.example.swagger.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import springfox.documentation.spring.web.json.Json
import java.util.HashMap
import io.swagger.annotations.Api
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiResponses
import io.swagger.annotations.ApiResponse

@Api(value = "DefaultController", description = "Restful APIs related to User")
@RestController
@RequestMapping("/")
class DefaultController {
	@ApiOperation(
		value = "say hello service is up and running !",
		response = Map::class,
		tags = ["sayHello"]
	)
	@ApiResponses(
		ApiResponse(code = 200, message = "Success|OK")
	)
	@GetMapping
	fun sayHello(): Map<String, String> {
		val map = HashMap<String, String>()
		map.put("message", "Welcome to user service")
		return map
	}

}
