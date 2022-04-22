package magicsquare.quartumbackend.properties

import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConstructorBinding
import org.springframework.validation.annotation.Validated

@Validated
@ConstructorBinding
@ConfigurationProperties(prefix = "magicsquare.quartum")
/**
 * Класс с параметрами приложения
 */
data class QuartumProperties(
    /** Поле JWT Secret */
    val jwtSecret : String,

    /** Поле длительности JWT */
    val jwtExpirationMs: Int
)
