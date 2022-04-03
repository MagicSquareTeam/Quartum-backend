package magicsquare.quartumbackend.exceptions

import org.springframework.http.HttpStatus

data class InventoryServiceException(
    val errorMessage: String,
    val httpStatus: HttpStatus = HttpStatus.NOT_FOUND
) : RuntimeException()
