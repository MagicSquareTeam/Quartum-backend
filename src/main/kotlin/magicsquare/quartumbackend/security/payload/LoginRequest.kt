package magicsquare.quartumbackend.security.payload


/**
 * Login request - запрос при попытке аутентификации
 *
 * @property username
 * @property email
 * @property password
 * @constructor Создаёт пустой запрос на вход в систему
 */
data class LoginRequest(
    val username: String,
    val email: String,
    val password: String)
