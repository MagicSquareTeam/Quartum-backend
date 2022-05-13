package magicsquare.quartumbackend.security.jwt

import io.jsonwebtoken.*
import magicsquare.quartumbackend.properties.QuartumProperties
import magicsquare.quartumbackend.security.services.UserDetailsImpl
import mu.KotlinLogging
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Component
import java.util.*


/**
 * Jwt utils - предоставляет методы для генерации, анализа, проверки JWT
 *
 * @property properties
 * @constructor Create empty Jwt utils
 */
@Component
class JwtUtils(
    private val properties: QuartumProperties,
) {
    private val logger = KotlinLogging.logger {}

    /**
     * Метод для генерации Jwt токена
     *
     * @param auth
     * @return JWT токен
     */
    fun generateJwtToken(auth: Authentication) : String{
        val userPrincipal = auth.principal as UserDetailsImpl

        return Jwts.builder()
            .setSubject(userPrincipal.username)
            .setIssuedAt(Date())
            .setExpiration(Date((Date()).time + properties.jwtExpirationMs))
            .signWith(SignatureAlgorithm.HS512, properties.jwtSecret)
            .compact()
    }

    /**
     * Получить имя пользователя из токена jwt
     *
     * @param token
     * @return имя токена из jwt
     */
    fun getUserNameFromJwtToken(token: String) : String {
        return Jwts.parser().setSigningKey(properties.jwtSecret).parseClaimsJws(token).body.subject
    }

    /**
     * Проверка токена jwt
     *
     * @param authToken
     * @return true - если jwt токен проходит валидацию, иначе - false
     */
    fun validateJwtToken(authToken: String): Boolean {
        try {
            Jwts.parser().setSigningKey(properties.jwtSecret).parseClaimsJws(authToken)
            return true
        } catch (e: SignatureException) {
            logger.error("Invalid JWT signature: {}", e.message)
        } catch (e: MalformedJwtException) {
            logger.error("Invalid JWT token: {}", e.message)
        } catch (e: ExpiredJwtException) {
            logger.error("JWT token is expired: {}", e.message)
        } catch (e: UnsupportedJwtException) {
            logger.error("JWT token is unsupported: {}", e.message)
        } catch (e: IllegalArgumentException) {
            logger.error("JWT claims string is empty: {}", e.message)
        }
        return false
    }

}
