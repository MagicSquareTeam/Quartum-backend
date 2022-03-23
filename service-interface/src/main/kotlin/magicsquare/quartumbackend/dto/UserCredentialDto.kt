package magicsquare.quartumbackend.dto

import java.io.Serializable

data class UserCredentialDto(
    val id: Long? = null,
    val userId: Long? = null,
    val email: String? = null,
    val password: String? = null,
    val username: String? = null
) : Serializable
