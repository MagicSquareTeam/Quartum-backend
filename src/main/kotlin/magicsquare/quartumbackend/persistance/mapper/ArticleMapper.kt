package magicsquare.quartumbackend.persistance.mapper

import magicsquare.quartumbackend.web.dto.ArticleDto
import magicsquare.quartumbackend.persistance.entity.*
import magicsquare.quartumbackend.persistance.repository.TagRepository
import magicsquare.quartumbackend.persistance.repository.UserRepository
import magicsquare.quartumbackend.services.TagService
import magicsquare.quartumbackend.services.UserService
import org.springframework.stereotype.Component
import java.time.Instant
import java.time.ZoneOffset
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeFormatterBuilder

/**
 * Article mapper - маппер для преобразованя модели в dto и наоборот
 *
 * @property tagRepository - репозиторий для поиска тега в БД
 */
@Component
class ArticleMapper(
    private val tagRepository: TagRepository
) : CommonMapper<ArticleDto, Article> {
    private val formatter: DateTimeFormatter = DateTimeFormatterBuilder()
        .appendPattern("yyyy-MM-dd HH:mm")
        .toFormatter()
        .withZone(ZoneOffset.UTC)

    override fun toDto(entity: Article) = ArticleDto(
        id = entity.id,
        authorId = entity.author!!.id,
        username = entity.author!!.userCredentials?.username,
        rating = entity.rating,
        name = entity.name,
        text = entity.text,
        creationDate = Instant.parse(entity.creationDate.toString()).atOffset(ZoneOffset.UTC).format(formatter),
        edited = entity.edited,
        editTime = entity.editTime.toString(),
        tagName = entity.tag?.name,
        archived = entity.archived,
        starred_userIds = if (entity.starred_users.size != 0) entity.starred_users.map { it.id!! } else listOf(),
        articlePictureIds = if (entity.articlePictures.size != 0) entity.articlePictures.map { it.id!! } else listOf(),
        articleVideoIds = if (entity.articleVideos.size != 0) entity.articleVideos.map { it.id!! } else listOf(),
        articleRatingIds = if (entity.articleRatings.size != 0) entity.articleRatings.map { it.id!! } else listOf(),
        articleFileIds = if (entity.articleFiles.size != 0) entity.articleFiles.map { it.id!! } else listOf()
    )

    override fun update(dto: ArticleDto, entity: Article) {
        entity.id = dto.id
        entity.author = User(dto.authorId)
        entity.rating = dto.rating
        entity.name = dto.name
        entity.text = dto.text
        entity.creationDate = formatter.parse(dto.creationDate, Instant::from)
        entity.edited = dto.edited
        entity.editTime = if (dto.edited == true) formatter.parse(dto.editTime, Instant::from) else null
        entity.tag = dto.tagName?.let { tagRepository.findByName(it).get() }
        entity.archived = dto.archived
        entity.starred_users = dto.starred_userIds?.map { User(it) }!!.toMutableSet()
        entity.articlePictures = dto.articlePictureIds?.map { ArticlePicture(it) }!!.toMutableSet()
        entity.articleVideos = dto.articleVideoIds?.map { ArticleVideo(it) }!!.toMutableSet()
        entity.articleRatings = dto.articleRatingIds?.map { ArticleRating(it) }!!.toMutableSet()
        entity.articleFiles = dto.articleFileIds?.map { ArticleFile(it) }!!.toMutableSet()
    }

    override fun toEntity(dto: ArticleDto) = Article(
        id = dto.id,
        author = User(dto.authorId),
        rating = dto.rating,
        name = dto.name,
        text = dto.text,
        creationDate = formatter.parse(dto.creationDate, Instant::from),
        edited = dto.edited,
        editTime = if (dto.edited == true) formatter.parse(dto.editTime, Instant::from) else null,
        tag = dto.tagName?.let { tagRepository.findByName(it).get() },
        archived = dto.archived,
        starred_users = if (dto.starred_userIds != null) (dto.starred_userIds.map { User(it) }
            .toMutableSet()) else mutableSetOf(),
        articlePictures = if (dto.articlePictureIds != null) (dto.articlePictureIds.map { ArticlePicture(it) }
            .toMutableSet()) else mutableSetOf(),
        articleVideos = if (dto.articleVideoIds != null) (dto.articleVideoIds.map { ArticleVideo(it) }
            .toMutableSet()) else mutableSetOf(),
        articleRatings = if (dto.articleRatingIds != null) (dto.articleRatingIds.map { ArticleRating(it) }
            .toMutableSet()) else mutableSetOf(),
        articleFiles = if (dto.articleFileIds != null) (dto.articleFileIds.map { ArticleFile(it) }
            .toMutableSet()) else mutableSetOf(),
    )
}
