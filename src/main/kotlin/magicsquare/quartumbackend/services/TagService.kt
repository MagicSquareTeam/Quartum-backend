package magicsquare.quartumbackend.services

import magicsquare.quartumbackend.persistance.entity.Tag
import magicsquare.quartumbackend.persistance.repository.TagRepository
import org.springframework.stereotype.Service

@Service
class TagService(
    private val tagRepository: TagRepository
) {

    fun getTagByName(name: String): Tag {
        return tagRepository.findByName(name).get()
    }
}
