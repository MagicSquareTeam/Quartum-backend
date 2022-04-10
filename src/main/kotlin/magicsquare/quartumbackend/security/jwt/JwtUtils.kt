package magicsquare.quartumbackend.security.jwt

import io.jsonwebtoken.*
import magicsquare.quartumbackend.properties.QuartumProperties
import magicsquare.quartumbackend.security.services.UserDetailsImpl
import mu.KotlinLogging
import org.springframework.security.core.Authentication
import org.springframework.stereotype.Component
import java.util.*


@Component
class JwtUtils(
    private val properties: QuartumProperties,
) {
    private val logger = KotlinLogging.logger {}

    fun generateJwtToken(auth: Authentication) : String{
        val userPrincipal = auth.principal as UserDetailsImpl

        return Jwts.builder()
            .setSubject(userPrincipal.username)
            .setIssuedAt(Date())
            .setExpiration(Date((Date()).time + properties.jwtExpirationMs))
            .signWith(SignatureAlgorithm.HS512, properties.jwtSecret)
            .compact()
    }

    fun getUserNameFromJwtToken(token: String) : String {
        return Jwts.parser().setSigningKey(properties.jwtSecret).parseClaimsJws(token).body.subject
    }

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
