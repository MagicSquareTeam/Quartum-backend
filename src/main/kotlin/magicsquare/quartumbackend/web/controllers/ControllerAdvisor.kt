package magicsquare.quartumbackend.web.controllers

import magicsquare.quartumbackend.exceptions.AuthException
import magicsquare.quartumbackend.exceptions.ErrorResponse
import magicsquare.quartumbackend.exceptions.InventoryServiceException
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.ControllerAdvice
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler
import java.time.LocalDateTime

@ControllerAdvice
/**
 * Класс для перехвата ошибок в контроллерах
 */
class ControllerAdvisor : ResponseEntityExceptionHandler() {

    /**
     * Метод обработки ошибок класса InventoryService
     * @param ex Объект ошибки
     * @return ResponseEntity с данными об ошибке
     * @see InventoryServiceException
     * @see ResponseEntity
     */
    @ExceptionHandler(InventoryServiceException::class)
    fun handleInventoryServiceException(ex: InventoryServiceException): ResponseEntity<Any> {
        return ResponseEntity(
            ErrorResponse(LocalDateTime.now(), ex::class.qualifiedName, ex.errorMessage),
            ex.httpStatus
        )
    }

    /**
     * Метод обработки ошибок класса AuthException
     * @param ex Объект ошибки
     * @return ResponseEntity с данными об ошибке
     * @see AuthException
     * @see ResponseEntity
     */
    @ExceptionHandler(AuthException::class)
    fun handleAuthException(ex: AuthException): ResponseEntity<Any> {
        return ResponseEntity(
            ErrorResponse(LocalDateTime.now(), ex::class.qualifiedName, ex.errorMessage),
            ex.httpStatus
        )
    }
}
