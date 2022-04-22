package magicsquare.quartumbackend.web.dto

import java.io.Serializable

/**
 * Модель DTO для данных пользователя
 */
data class UserCredentialDto(
    val id: Long? = null,
    val userId: Long? = null,
    val email: String? = null,
    val password: CharArray? = null,
    val username: String? = null
) : Serializable
