package magicsquare.quartumbackend.controllers

import magicsquare.quartumbackend.exceptions.ErrorResponse
import magicsquare.quartumbackend.exceptions.InventoryServiceException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import java.time.LocalDateTime

@ControllerAdvice
class ControllerAdvisor : ResponseEntityExceptionHandler() {

    @ExceptionHandler(InventoryServiceException::class)
    fun handleInventoryServiceException(ex : InventoryServiceException): ResponseEntity<Any>{
        return ResponseEntity(
            ErrorResponse(LocalDateTime.now(), ex::class.qualifiedName, ex.errorMessage),
            ex.httpStatus
        )
    }
}