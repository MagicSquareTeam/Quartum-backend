package magicsquare.quartumbackend.persistance.repository

import magicsquare.quartumbackend.persistance.entity.Role
import magicsquare.quartumbackend.persistance.enums.ERole
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface RoleRepository : JpaRepository<Role, Int> {
    fun findByRoleName(eRole: ERole) : Role
}