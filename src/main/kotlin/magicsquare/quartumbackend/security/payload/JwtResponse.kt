package magicsquare.quartumbackend.security.payload

import javax.validation.constraints.NotBlank

/**
 * Jwt response - ответ при успешной аутентификации
 *
 * @property token
 * @property type
 * @property userId
 * @property username
 * @property email
 * @property roles
 * @constructor Создаёт пустой ответ Jwt
 */
data class JwtResponse(
    @NotBlank val token: String,
    @NotBlank val type: String,
    @NotBlank val userId: Long,
    @NotBlank val username: String,
    @NotBlank val email: String,
    @NotBlank val roles: List<String>
)
