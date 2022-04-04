package magicsquare.quartumbackend.services

import magicsquare.quartumbackend.exceptions.InventoryServiceException
import magicsquare.quartumbackend.persistance.entity.Role
import magicsquare.quartumbackend.persistance.enums.ERole
import magicsquare.quartumbackend.persistance.repository.RoleRepository
import org.springframework.dao.EmptyResultDataAccessException
import org.springframework.stereotype.Service

@Service
class RoleService(
    private val repository: RoleRepository
) {
    fun findByRoleName(role: String): Role {
        try{
            val eRole = ERole.valueOf(role)
            return repository.findByRoleName(eRole)
        } catch (ex: IllegalArgumentException){
            throw InventoryServiceException("Role $role doesn't exists")
        }
    }

    fun findByRoleName(eRole: ERole): Role {
        try {
            return repository.findByRoleName(eRole)
        } catch (ex: EmptyResultDataAccessException){
            throw InventoryServiceException("Role $eRole doesn't exists")
        }
    }
}