package magicsquare.quartumbackend.security.payload

import javax.validation.constraints.NotBlank

data class LoginRequest(
    val username: String,
    val email: String,
    val password: String)
