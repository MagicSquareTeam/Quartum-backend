package magicsquare.quartumbackend.persistance.mapper

import magicsquare.quartumbackend.web.dto.ArticleRatingDto
import magicsquare.quartumbackend.persistance.entity.ArticleRating

/**
 * Article rating mapper - маппер для преобразованя модели в dto и наоборот
 *
 */
class ArticleRatingMapper: CommonMapper <ArticleRatingDto, ArticleRating> {
    override fun toDto(entity: ArticleRating) = ArticleRatingDto (
        id = entity.id,
    )

    override fun update(dto: ArticleRatingDto, entity: ArticleRating) {
        entity.id = dto.id
    }

    override fun toEntity(dto: ArticleRatingDto) = ArticleRating (
        id = dto.id,
    )
}