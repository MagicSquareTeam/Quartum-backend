package magicsquare.quartumbackend.persistance.mapper

import magicsquare.quartumbackend.web.dto.ArticleDto
import magicsquare.quartumbackend.persistance.entity.*
import java.time.Instant

class ArticleMapper : CommonMapper<ArticleDto, Article> {
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
        starred_userIds = entity.starred_users.map { it.id!! },
        articlePictureIds = entity.articlePictures.map { it.id!! },
        articleVideoIds = entity.articleVideos.map { it.id!! },
        articleRatingIds = entity.articleRatings.map { it.id!! },
        articleFileIds = entity.articleFiles.map { it.id!! }
    )

    override fun update(dto: ArticleDto, entity: Article) {
        entity.id = dto.id
        entity.author = User(dto.authorId)
        entity.rating = dto.rating
        entity.name = dto.name
        entity.text = dto.text
        entity.creationDate = Instant.parse(dto.creationDate)
        entity.edited = dto.edited
        entity.editTime = Instant.parse(dto.editTime)
        entity.tag = Tag(dto.tagId)
        entity.archived = dto.archived
        entity.starred_users = dto.starred_userIds?.map {User(it)}!!.toMutableSet()
        entity.articlePictures = dto.articlePictureIds?.map { ArticlePicture(it) }!!.toMutableSet()
        entity.articleVideos = dto.articleVideoIds?.map { ArticleVideo(it) }!!.toMutableSet()
        entity.articleRatings = dto.articleRatingIds?.map { ArticleRating(it) }!!.toMutableSet()
        entity.articleFiles = dto.articleFileIds?.map { ArticleFile(it) }!!.toMutableSet()
    }

    override fun toEntity(dto: ArticleDto) = Article (
        id = dto.id,
        author = User(dto.authorId),
        rating = dto.rating,
        name = dto.name,
        text = dto.text,
        creationDate = Instant.parse(dto.creationDate),
        edited = dto.edited,
        editTime = Instant.parse(dto.editTime),
        tag = Tag(dto.tagId),
        archived = dto.archived,
        starred_users = dto.starred_userIds?.map {User(it)}!!.toMutableSet(),
        articlePictures = dto.articlePictureIds?.map { ArticlePicture(it) }!!.toMutableSet(),
        articleVideos = dto.articleVideoIds?.map { ArticleVideo(it) }!!.toMutableSet(),
        articleRatings = dto.articleRatingIds?.map { ArticleRating(it) }!!.toMutableSet(),
        articleFiles = dto.articleFileIds?.map { ArticleFile(it) }!!.toMutableSet()
    )
}