package magicsquare.quartumbackend.exceptions

import org.springframework.http.HttpStatus

data class InventoryServiceException(
    val errorMessage: String,
    val httpStatus: HttpStatus = HttpStatus.INTERNAL_SERVER_ERROR
) : RuntimeException()
