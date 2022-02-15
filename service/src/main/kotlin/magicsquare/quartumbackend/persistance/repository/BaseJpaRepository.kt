package magicsquare.quartumbackend.persistance.repository

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.NoRepositoryBean
import java.io.Serializable
import java.util.*

@NoRepositoryBean
interface BaseJpaRepository<T, ID : Serializable> : JpaRepository<T, ID> {}