package com.demo.phonebook.controller

import com.demo.phonebook.domain.ErrorResponse
import com.demo.phonebook.exception.DifferentBusinessCardException
import com.demo.phonebook.exception.NoSuchBusinessCardException
import org.hibernate.JDBCException
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.http.HttpStatus
import org.springframework.validation.FieldError
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.ResponseStatus

@ControllerAdvice(basePackageClasses = [PhoneBookController::class])
class PhoneBookControllerAdvice {

    private val logger: Logger = LoggerFactory.getLogger(PhoneBookControllerAdvice::class.java)

    @ExceptionHandler(NoSuchBusinessCardException::class)
    @ResponseBody
    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    fun handleNoSuchBusinessCardException(exception: Exception): ErrorResponse {
        logger.error("Business card not found: $exception")
        return ErrorResponse("Business card not found: ${exception.message}")
    }

    @ExceptionHandler(JDBCException::class)
    @ResponseBody
    @ResponseStatus(code = HttpStatus.UNPROCESSABLE_ENTITY)
    fun handleJDBCException(exception: Exception): ErrorResponse {
        logger.error("JDBC error: $exception")
        return ErrorResponse("JDBC error: ${exception.message}")
    }

    @ExceptionHandler(DifferentBusinessCardException::class)
    @ResponseBody
    @ResponseStatus(code = HttpStatus.CONFLICT)
    fun handleDifferentBusinessCardException(exception: Exception): ErrorResponse {
        logger.error("Conflict error: ", exception)
        return ErrorResponse("Conflict error: " + exception.message)
    }
    @ExceptionHandler(MethodArgumentNotValidException::class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    fun handleValidationExceptions(exception: MethodArgumentNotValidException): ErrorResponse {
        val message = exception.bindingResult.fieldErrors
            .map { error: FieldError -> error.field + ": " + error.defaultMessage }
            .joinToString { it }
        logger.error("Validation error: $exception")
        return ErrorResponse("Validation error: $message")
    }
}