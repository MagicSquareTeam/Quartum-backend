package magicsquare.quartumbackend.properties

import org.springframework.boot.context.properties.ConfigurationProperties

@ConfigurationProperties(prefix = "magicsquare.quartum")
data class QuartumProperties(
    private val jwtSecret : String,
    private val jwtExpirationMs: String
)