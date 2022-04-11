package magicsquare.quartumbackend.persistance.repository;

import magicsquare.quartumbackend.persistance.entity.Tag
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface TagRepository : JpaRepository<Tag, Int> {
    fun findByName(name: String): Optional<Tag>
}
