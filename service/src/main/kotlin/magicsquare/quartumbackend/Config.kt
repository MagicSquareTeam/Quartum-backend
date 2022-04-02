package magicsquare.quartumbackend

import jakarta.persistence.EntityManager
import org.hibernate.internal.SessionImpl
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration

@Configuration
class Config {
    @Bean
     fun entityManager() : EntityManager {
        val sessionImpl: SessionImpl
        val d = sessionImpl.session.criteriaBuilder
         return SessionImpl
     }
}