package magicsquare.quartumbackend

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.context.properties.ConfigurationPropertiesScan
import org.springframework.boot.runApplication
import org.springframework.data.jpa.repository.config.EnableJpaRepositories
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories

@SpringBootApplication
@ConfigurationPropertiesScan
@EnableMongoRepositories(basePackages = ["magicsquare.quartumbackend.persistance.repository.mongo"])
@EnableJpaRepositories(basePackages = ["magicsquare.quartumbackend.persistance.repository.jpa"])
class QuartumBackendApplication

fun main(args: Array<String>) {


    runApplication<QuartumBackendApplication>(*args)
}
