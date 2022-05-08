package magicsquare.quartumbackend.persistance.repository.jpa

import magicsquare.quartumbackend.persistance.entity.Role
import magicsquare.quartumbackend.persistance.enums.ERole
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

/**
 * Репозиторий Role
 *
 */
@Repository
interface RoleRepository : JpaRepository<Role, Int> {
    /**
     * Ищет запись в БД по роли
     *
     * @param eRole
     * @return роль
     */
    fun findByRoleName(eRole: ERole) : Role
}