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
/**
 * Класс сервиса для работы с пользователями
 */
class UserService(
    /** Поле репозитория с пользователями */
    private val repository: UserRepository
) {

    /**
     * Метод для поиска по id пользователя
     * @param id ID пользователя
     * @return Пользователь с заданным id
     * @see User
     */
    fun findById(id: Long): User {
        val user = repository.findByIdOrNull(id)

        return user ?: throw InventoryServiceException("Credentials for $id not found")
    }

    /**
     * Метод для сохранения пользователя
     * @param user Пользователь для сохранения
     * @return Boolean
     * @see UserRepository.save
     */
    fun save(user: User) {
        repository.save(user)
    }

    /**
     * Метод для получения всех статей пользователя
     * @param id ID пользователя
     * @return Список его статей
     * @see Article
     */
    fun getUserArticles(id: Long): MutableSet<Article> {
        return repository.findById(id).get().articles
    }
}
