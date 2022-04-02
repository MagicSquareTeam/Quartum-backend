package magicsquare.quartumbackend.persistance.mapper

import magicsquare.quartumbackend.dto.RoleDto
import magicsquare.quartumbackend.persistance.entity.Role
import magicsquare.quartumbackend.persistance.entity.User
import org.springframework.stereotype.Component

@Component
class RoleMapper : CommonMapper<RoleDto, Role> {
    override fun toDto(entity: Role) = RoleDto (
        id = entity.id,
        roleName = entity.roleName,
        userIds = entity.users.map { it.id!! }
    )

    override fun update(dto: RoleDto, entity: Role) {
        entity.id = dto.id
        entity.roleName = dto.roleName
        entity.users = dto.userIds?.map { User(it) }!!.toMutableSet()
    }

    override fun toEntity(dto: RoleDto) = Role (
        id = dto.id,
        roleName = dto.roleName,
        users = dto.userIds?.map { User(it) }!!.toMutableSet()
    )
}