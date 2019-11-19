package com.example.swagger.models

import lombok.AllArgsConstructor
import lombok.Data
import lombok.NoArgsConstructor

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id
import io.swagger.annotations.ApiModelProperty

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
class User() {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	var userId: Long? = null
	@ApiModelProperty(notes = "User full name", name = "userName", required = true, value = "userName")
	var userName: String? = null
	@ApiModelProperty(notes = "User email address", name = "emailAddress", required = true, value = "name@email.com")
	var emailAddress: String? = null
	@ApiModelProperty(notes = "User email phone number", name = "phoneNumber", required = true, value = "0020100100100")
	var phoneNumber: String? = null
	@ApiModelProperty(notes = "User status in the system", name = "active", required = false, value = "false")
	var active: Boolean = false
}