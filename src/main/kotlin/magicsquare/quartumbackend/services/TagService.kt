package magicsquare.quartumbackend.services

import magicsquare.quartumbackend.persistance.entity.Tag
import magicsquare.quartumbackend.persistance.mapper.TagMapper
import magicsquare.quartumbackend.persistance.repository.jpa.TagRepository
import magicsquare.quartumbackend.web.dto.TagDto
import org.springframework.stereotype.Service

@Service
/**
 * Класс сервиса для работы с тегами
 */
class TagService(
    /** Поле репозитория с тегами */
    private val tagRepository: TagRepository,
    /** Поле маппера тегов */
    private val tagMapper: TagMapper
) {

    /**
     * Метод для поиска по имени тега
     * @param name Название тега
     * @return Тег
     * @see Tag
     */
    fun getTagByName(name: String): Tag {
        return tagRepository.findByName(name).get()
    }

    /**
     * Метод для поиска всех тегов
     * @return Список всех тегов
     * @see TagDto
     */
    fun findAll(): MutableSet<TagDto> {
        return tagRepository.findAll().map { tag -> tagMapper.toDto(tag) }.toMutableSet()
    }
}
