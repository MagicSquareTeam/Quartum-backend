package magicsquare.quartumbackend.security.payload

import javax.validation.constraints.Email
import javax.validation.constraints.NotBlank
import javax.validation.constraints.Size

/**
 * Signup request - запрос при попытке регистрациии
 *
 * @property email
 * @property role
 * @property password
 * @property username
 * @property name
 * @property surname
 * @property phoneNumber
 * @property birthday
 * @constructor Создаёт пустой запрос на регистрацию
 */
data class SignupRequest (
    @NotBlank @Size(max = 60) @Email val email: String,
    val role: Set<String>?,
    @NotBlank @Size(max = 60) val password: String,
    @NotBlank val username: String,
    @NotBlank val name: String,
    @NotBlank val surname: String,
    @NotBlank val phoneNumber: String,
    @NotBlank val birthday: String
    )
