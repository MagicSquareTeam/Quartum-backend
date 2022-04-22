package magicsquare.quartumbackend.services

import magicsquare.quartumbackend.web.dto.UserCredentialDto
import magicsquare.quartumbackend.exceptions.InventoryServiceException
import magicsquare.quartumbackend.persistance.entity.UserCredential
import magicsquare.quartumbackend.persistance.mapper.CommonMapper
import magicsquare.quartumbackend.persistance.repository.UserCredentialRepository
import org.springframework.stereotype.Service

@Service
/**
 * Класс сервиса для работы с данными пользователя
 */
class UserCredentialService(
    /** Поле репозитория с данными о пользователях */
    private val repository: UserCredentialRepository,
    /** Поле маппера классов с данными пользователя */
    val mapper: CommonMapper<UserCredentialDto, UserCredential>
) {

    /**
     * Метод для поиска по почте
     * @param email Почта пользователя
     * @return Пользователь с данной почтой
     * @see UserCredential
     */
    fun findByEmail(email: String): UserCredential {
        val userCredential = repository.findByEmail(email)

        return userCredential ?: throw InventoryServiceException("Credentials for $email not found")
    }

    /**
     * Метод для поиска по имени пользователя
     * @param username Имя пользователя
     * @return Пользователь с данным именем
     * @see UserCredential
     */
    fun findByUsername(username: String): UserCredential {
        val userCredential = repository.findByUsername(username)

        return userCredential ?: throw InventoryServiceException("Credentials for $username not found")
    }

    /**
     * Метод для проверки существования почты
     * @param email Почта пользователя
     * @return Boolean - наличие (true) или отсутствие (false)
     * @see UserCredentialRepository.existsByEmail
     */
    fun existByEmail(email: String) = repository.existsByEmail(email)

    /**
     * Метод для сохранения данных о пользователе
     * @param userCredential Данные пользователя
     * @return Boolean - успешное сохранение (true) или ошибка (false)
     * @see UserCredentialRepository.save
     */
    fun save(userCredential: UserCredential) = repository.save(userCredential)
}
