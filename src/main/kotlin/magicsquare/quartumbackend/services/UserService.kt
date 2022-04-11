package magicsquare.quartumbackend.services

import magicsquare.quartumbackend.exceptions.InventoryServiceException
import magicsquare.quartumbackend.persistance.entity.Article
import magicsquare.quartumbackend.persistance.entity.User
import magicsquare.quartumbackend.persistance.mapper.ArticleMapper
import magicsquare.quartumbackend.persistance.repository.UserRepository
import magicsquare.quartumbackend.web.dto.ArticleDto
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
class UserService(
    private val repository: UserRepository
) {

    fun findById(id: Long): User {
        val user = repository.findByIdOrNull(id)

        return user ?: throw InventoryServiceException("Credentials for $id not found")
    }

    fun save(user: User) {
        repository.save(user)
    }

    fun getUserArticles(id: Long): MutableSet<Article> {
        return repository.findById(id).get().articles
    }
}
