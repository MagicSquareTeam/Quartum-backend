package magicsquare.quartumbackend.service

import jakarta.persistence.EntityManager
import magicsquare.quartumbackend.dto.UserDto
import magicsquare.quartumbackend.persistance.entity.User
import magicsquare.quartumbackend.persistance.mapper.UserMapper
import magicsquare.quartumbackend.persistance.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class UserService(
    userRepository: UserRepository,
    userMapper: UserMapper,
) : AbstractInventoryService <UserDto, User, Long>(
    userRepository,
    userMapper
)