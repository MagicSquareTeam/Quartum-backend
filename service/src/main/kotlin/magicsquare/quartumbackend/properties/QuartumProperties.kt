package magicsquare.quartumbackend.properties

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding
import org.springframework.validation.annotation.Validated

@Validated
@ConstructorBinding
@ConfigurationProperties(prefix = "magicsquare.quartum")
data class QuartumProperties(
    val jwtSecret : String,
    val jwtExpirationMs: Int
)