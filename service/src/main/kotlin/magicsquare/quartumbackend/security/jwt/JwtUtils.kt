package magicsquare.quartumbackend.security.jwt

import magicsquare.quartumbackend.properties.QuartumProperties
import mu.KLogger
import org.springframework.stereotype.Component
import mu.KotlinLogging

@Component
class JwtUtils(
    private val properties: QuartumProperties,
) {
    private val logger = KotlinLogging.logger {}

    fun generateJwtToken(auth: Authentification)

}
