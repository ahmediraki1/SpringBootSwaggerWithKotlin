package com.example.swagger.repo

import org.springframework.stereotype.Repository
import com.example.swagger.models.User
import org.springframework.data.jpa.repository.JpaRepository

@Repository
interface UserRepository : JpaRepository<User, Long> {
	fun findByEmailAddressOrPhoneNumber(emailAddress: String?, phoneNumber: String?): List<User>
	fun findByActiveTrueOrderByUserNameDesc(): List<User>
	fun findByActiveFalseOrderByUserNameAsc(): List<User>
	fun findByPhoneNumberContaining(searchForPhone:String):List<User>
}
