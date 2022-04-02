package magicsquare.quartumbackend.persistance.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.NoRepositoryBean
import java.io.Serializable

@NoRepositoryBean
interface BaseJpaRepository<T, ID : Serializable> : JpaRepository<T, ID>{
    fun findByCriteria(criteria: String)
}