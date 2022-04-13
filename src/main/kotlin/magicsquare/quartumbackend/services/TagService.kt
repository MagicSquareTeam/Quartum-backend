package magicsquare.quartumbackend.services

import magicsquare.quartumbackend.persistance.entity.Tag
import magicsquare.quartumbackend.persistance.mapper.TagMapper
import magicsquare.quartumbackend.persistance.repository.TagRepository
import magicsquare.quartumbackend.web.dto.TagDto
import org.springframework.stereotype.Service

@Service
class TagService(
    private val tagRepository: TagRepository,
    private val tagMapper: TagMapper
) {

    fun getTagByName(name: String): Tag {
        return tagRepository.findByName(name).get()
    }

    fun findAll(): MutableSet<TagDto> {
        return tagRepository.findAll().map { tag -> tagMapper.toDto(tag) }.toMutableSet()
    }
}
