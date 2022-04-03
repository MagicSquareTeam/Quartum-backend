package magicsquare.quartumbackend

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication

@SpringBootApplication
@ConfigurationPropertiesScan
class QuartumBackendApplication

fun main(args: Array<String>) {


    runApplication<QuartumBackendApplication>(*args)
}
