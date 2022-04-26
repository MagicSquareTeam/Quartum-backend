package magicsquare.quartumbackend.services

import magicsquare.quartumbackend.exceptions.InventoryServiceException
import magicsquare.quartumbackend.persistance.entity.Article
import magicsquare.quartumbackend.persistance.entity.User
import magicsquare.quartumbackend.persistance.mapper.ArticleMapper
import magicsquare.quartumbackend.persistance.mapper.UserMapper
import magicsquare.quartumbackend.persistance.repository.UserRepository
import magicsquare.quartumbackend.web.dto.ArticleDto
import magicsquare.quartumbackend.web.dto.UserDto
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service

@Service
/**
 * Класс сервиса для работы с пользователями
 */
class UserService(
    private val repository: UserRepository,
    private val userMapper: UserMapper,
    private val articleMapper: ArticleMapper
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
    fun save(userDto: UserDto) {
        repository.save(userMapper.toEntity(userDto))
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

    fun addUserSub(userId: Long, userSubId: Long) {
        val user: User = repository.findById(userId).get()
        user.users_subscriptions.add(repository.findById(userSubId).get())
        repository.save(user)
    }

    fun getUserSubs(userId: Long): MutableSet<User> {
        return repository.findById(userId).get().users_subscriptions
    }

    fun getUserData(userId: Long): UserDto {
        return userMapper.toDto(repository.findById(userId).get())
    }

    fun getSubsArticles(userId: Long): MutableSet<ArticleDto> {
        val articles: MutableSet<ArticleDto> = mutableSetOf()
        repository.findById(userId).get().users_subscriptions.forEach {
            articles.addAll(it.articles.map { article -> articleMapper.toDto(article) }.toMutableSet())
        }
        return articles
    }
}
