package com.example.swagger.controller

import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.RequestMapping
import com.example.swagger.repo.UserRepository
import org.springframework.web.bind.annotation.GetMapping
import com.example.swagger.models.User
import com.example.swagger.exceptions.ResourceNotFoundException
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.PutMapping
import org.springframework.web.bind.annotation.DeleteMapping
import io.swagger.annotations.ApiOperation
import io.swagger.annotations.ApiResponses
import io.swagger.annotations.ApiResponse
import io.swagger.annotations.Api

@Api(value = "UserRestController", description = "Restful APIs related to User")
@RestController
@RequestMapping("/users")
class UserController internal constructor(
	private val repository: UserRepository
) {
	//getAllUsers
	@ApiOperation(
		value = "Get list of all users",
		response = User::class,
		tags = ["getAllUsers"]
	)
	@ApiResponses(
		ApiResponse(code = 200, message = "Success|OK")
	)
	@GetMapping
	fun getAllUsers(): List<User> {
		return repository.findAll()
	}


	//findUserId
	@ApiOperation(
		value = "Get user object by Id",
		response = User::class,
		tags = ["findUserId"]
	)
	@ApiResponses(
		ApiResponse(code = 200, message = "Success|OK"),
		ApiResponse(code = 404, message = "not found")
	)
	@GetMapping(path = ["/{id}"])
	@Throws(ResourceNotFoundException::class)
	fun findUserId(@PathVariable id: Long): ResponseEntity<User> {
		return repository.findById(id)
			.map { record -> ResponseEntity.ok().body(record) }
			.orElseThrow { ResourceNotFoundException("User doesn't exist : $id") }
	}


	//createUser
	@ApiOperation(
		value = "create new user",
		response = User::class,
		tags = ["createUser"]
	)
	@ApiResponses(
		ApiResponse(code = 200, message = "Success|OK")
	)
	@PostMapping
	fun createUser(@RequestBody userModel: User): User {
		return repository.save(userModel)
	}


	//updateUser
	@ApiOperation(
		value = "Update user object by Id",
		response = User::class,
		tags = ["updateUser"]
	)
	@ApiResponses(
		ApiResponse(code = 200, message = "Success|OK"),
		ApiResponse(code = 404, message = "not found")
	)
	@PutMapping(path = ["/{id}"])
	@Throws(ResourceNotFoundException::class)
	fun updateUser(
		@PathVariable("id") id: Long,
		@RequestBody userModel: User
	): ResponseEntity<User> {
		return repository.findById(id)
			.map { record ->
				record.userName = userModel.userName
				record.emailAddress = userModel.emailAddress
				record.phoneNumber = userModel.phoneNumber
				val updated = repository.save(record)
				ResponseEntity.ok().body(updated)
			}.orElseThrow { ResourceNotFoundException("User doesn't exist : $id") }
	}

	//updateUser
	@ApiOperation(
		value = "delete user object by Id",
		response = User::class,
		tags = ["deleteUser"]
	)
	@ApiResponses(
		ApiResponse(code = 200, message = "Success|OK"),
		ApiResponse(code = 404, message = "not found")
	)
	@DeleteMapping(path = ["/{id}"])
	@Throws(ResourceNotFoundException::class)
	fun deleteUser(@PathVariable("id") id: Long): ResponseEntity<*> {
		return repository.findById(id)
			.map { record ->
				repository.deleteById(id)
				ResponseEntity.ok().body(record)
			}.orElseThrow { ResourceNotFoundException("User doesn't exist : $id") }
	}


	//getAllNotActiveUsers
	@ApiOperation(
		value = "Get list of all not active users",
		response = User::class,
		tags = ["getAllNotActiveUsers"]
	)
	@ApiResponses(
		ApiResponse(code = 200, message = "Success|OK")
	)
	@GetMapping(path = ["/notActive"])
	fun getAllNotActiveUsers(): List<User> {
		return repository.findByActiveFalseOrderByUserNameAsc()
	}


	//getAllActiveUsers
	@ApiOperation(
		value = "Get list of all active users",
		response = User::class,
		tags = ["getAllActiveUsers"]
	)
	@ApiResponses(
		ApiResponse(code = 200, message = "Success|OK")
	)
	@GetMapping(path = ["/active"])
	fun getAllActiveUsers(): List<User> {
		return repository.findByActiveTrueOrderByUserNameDesc()
	}


	//searchWithPhone
	@ApiOperation(
		value = "Get list of users their phone matches the recieved data",
		response = User::class,
		tags = ["searchWithPhone"]
	)
	@ApiResponses(
		ApiResponse(code = 200, message = "Success|OK")
	)
	@GetMapping(path = ["/search/{phone}"])
	fun searchWithPhone(@PathVariable("phone") phone: String): List<User> {
		return repository.findByPhoneNumberContaining(phone)
	}


	//searchWithPhoneOrEmail
	@ApiOperation(
		value = "Get list of users their email or phone matches the recieved data",
		response = User::class,
		tags = ["searchWithPhoneOrEmail"]
	)
	@ApiResponses(
		ApiResponse(code = 200, message = "Success|OK")
	)
	@GetMapping(path = ["/search/{email}/{phone}"])
	fun searchWithPhoneOrEmail(@PathVariable("phone") phone: String?, @PathVariable("email") email: String?): List<User> {
		return repository.findByEmailAddressOrPhoneNumber(email, phone)
	}

}
