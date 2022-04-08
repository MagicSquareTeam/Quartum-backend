package magicsquare.quartumbackend.exceptions

import org.springframework.http.HttpStatus

class AuthException(
    val errorMessage: String,
    val httpStatus: HttpStatus = HttpStatus.NOT_FOUND
) : RuntimeException(){
}