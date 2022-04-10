package magicsquare.quartumbackend.exceptions

import java.time.LocalDateTime

data class ErrorResponse(
    val time: LocalDateTime,
    val exceptionClassName: String?,
    val message: String
) {
}