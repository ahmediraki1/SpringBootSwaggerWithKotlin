package com.example.swagger.exceptions

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.context.request.WebRequest

import java.util.Date

@ControllerAdvice
class GlobalExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException::class)
    fun resourceNotFoundException(ex: ResourceNotFoundException, request: WebRequest): ResponseEntity<*> {
        val errorModel = ErrorModel(Date(), ex.message!!, request.getDescription(false))
        return ResponseEntity(errorModel, HttpStatus.NOT_FOUND)
    }

    @ExceptionHandler(Exception::class)
    fun globleExcpetionHandler(ex: Exception, request: WebRequest): ResponseEntity<*> {
        val errorModel = ErrorModel(Date(), ex.message!!, request.getDescription(false))
        return ResponseEntity(errorModel, HttpStatus.INTERNAL_SERVER_ERROR)
    }
}
