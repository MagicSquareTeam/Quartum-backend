package magicsquare.quartumbackend.persistance.mapper

import magicsquare.quartumbackend.dto.UserCredentialDto
import magicsquare.quartumbackend.persistance.entity.User
import magicsquare.quartumbackend.persistance.entity.UserCredential
import org.springframework.stereotype.Component

@Component
class UserCredentialsMapper : CommonMapper<UserCredentialDto ,UserCredential> {
    override fun toDto(entity: UserCredential) = UserCredentialDto (
        id = entity.id,
        userId = entity.id,
        email = entity.email,
        password = entity.password,
        username = entity.username
    )

    override fun update(dto: UserCredentialDto, entity: UserCredential) {
        entity.id = dto.id
        entity.user = User(dto.id)
        entity.email = dto.email
        entity.password = dto.password
        entity.username = dto.username
    }

    override fun toEntity(dto: UserCredentialDto) = UserCredential (
        id = dto.id,
        user = User(dto.id),
        email = dto.email,
        password = dto.password,
        username = dto.username
    )

}