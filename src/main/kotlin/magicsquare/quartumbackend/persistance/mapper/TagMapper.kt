package magicsquare.quartumbackend.persistance.mapper

import magicsquare.quartumbackend.web.dto.TagDto
import magicsquare.quartumbackend.persistance.entity.Article
import magicsquare.quartumbackend.persistance.entity.Tag
import magicsquare.quartumbackend.persistance.entity.User
import org.springframework.stereotype.Component

@Component
class TagMapper : CommonMapper<TagDto, Tag> {
    override fun toDto(entity: Tag) = TagDto (
        id = entity.id,
        name = entity.name,
        userIds = entity.users.map { it.id!! },
        articleIds = entity.articles.map { it.id!! }
    )

    override fun update(dto: TagDto, entity: Tag) {
        entity.id = dto.id
        entity.name = dto.name
        entity.users = dto.userIds?.map { User(it) }!!.toMutableSet()
        entity.articles = dto.articleIds?.map { Article(it) }!!.toMutableSet()
    }

    override fun toEntity(dto: TagDto) = Tag (
        id = dto.id,
        name = dto.name,
        users = dto.userIds?.map { User(it) }!!.toMutableSet(),
        articles = dto.articleIds?.map { Article(it) }!!.toMutableSet()
    )
}
