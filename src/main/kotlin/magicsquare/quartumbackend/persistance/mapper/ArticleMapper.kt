package magicsquare.quartumbackend.persistance.mapper

import magicsquare.quartumbackend.web.dto.ArticleDto
import magicsquare.quartumbackend.persistance.entity.*
import magicsquare.quartumbackend.services.UserService
import org.springframework.stereotype.Component
import java.time.Instant
import java.time.ZoneId
import java.time.format.DateTimeFormatter
import java.time.format.DateTimeFormatterBuilder
import java.time.temporal.ChronoField

@Component
class ArticleMapper(
    private val userService: UserService
) : CommonMapper<ArticleDto, Article> {
    private val formatter: DateTimeFormatter = DateTimeFormatterBuilder()
        .appendPattern("yyyy-MM-dd")
        .parseDefaulting(ChronoField.NANO_OF_DAY, 0)
        .toFormatter()
        .withZone(ZoneId.of("Europe/Paris"))

    override fun toDto(entity: Article) = ArticleDto(
        id = entity.id,
        authorId = entity.author!!.id,
        rating = entity.rating,
        name = entity.name,
        text = entity.text,
        creationDate = entity.creationDate.toString(),
        edited = entity.edited,
        editTime = entity.editTime.toString(),
        tagId = entity.tag?.id,
        archived = entity.archived,
        starred_userIds = if (entity.starred_users.size != 0) entity.starred_users.map { it.id!! } else listOf(),
        articlePictureIds = if
                (entity.articlePictures.size != 0)
            entity.articlePictures.map { it.id!! }
        else listOf(),
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
        entity.tag = Tag(dto.tagId)
        entity.archived = dto.archived
        entity.starred_users = dto.starred_userIds?.map { User(it) }!!.toMutableSet()
        entity.articlePictures = dto.articlePictureIds?.map { ArticlePicture(it) }!!.toMutableSet()
        entity.articleVideos = dto.articleVideoIds?.map { ArticleVideo(it) }!!.toMutableSet()
        entity.articleRatings = dto.articleRatingIds?.map { ArticleRating(it) }!!.toMutableSet()
        entity.articleFiles = dto.articleFileIds?.map { ArticleFile(it) }!!.toMutableSet()
    }

    override fun toEntity(dto: ArticleDto) = Article(
        id = dto.id,
        author = dto.authorId?.let { userService.findById(it) },
        rating = dto.rating,
        name = dto.name,
        text = dto.text,
        creationDate = formatter.parse(dto.creationDate, Instant::from),
        edited = dto.edited,
        editTime = if (dto.edited == true) formatter.parse(dto.editTime, Instant::from) else null,
        tag = Tag(dto.tagId),
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
