package magicsquare.quartumbackend.persistance.repository.jpa

import magicsquare.quartumbackend.persistance.entity.UserCredential
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

/**
 * Репозиторий User credential
 *
 */
@Repository
interface UserCredentialRepository : JpaRepository<UserCredential, Long>{

    /**
     * Ищет по почте
     *
     * @param email
     * @return данные пользователя для идентификации
     */
    fun findByEmail(email: String) : UserCredential?

    /**
     * Ищет по имени пользователя
     *
     * @param username
     * @return данные пользователя для идентификации
     */
    fun findByUsername(username: String): UserCredential?

    /**
     * Проверяет есть ли пользователь по email
     *
     * @param email
     * @return
     */
    fun existsByEmail(email: String): Boolean
}
