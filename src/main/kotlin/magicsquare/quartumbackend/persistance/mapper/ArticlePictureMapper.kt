package magicsquare.quartumbackend.persistance.mapper

import magicsquare.quartumbackend.web.dto.ArticlePictureDto
import magicsquare.quartumbackend.persistance.entity.Article
import magicsquare.quartumbackend.persistance.entity.ArticlePicture

/**
 * Article picture mapper - маппер для преобразованя модели в dto и наоборот
 *
 */
class ArticlePictureMapper : CommonMapper <ArticlePictureDto, ArticlePicture> {
    override fun toDto(entity: ArticlePicture) = ArticlePictureDto (
        id = entity.id,
        articleId = entity.article!!.id,
        articlePicturesId = entity.articlePicturesId
    )

    override fun update(dto: ArticlePictureDto, entity: ArticlePicture) {
        entity.id = dto.id
        entity.article = Article(dto.articleId)
        entity.articlePicturesId = dto.articlePicturesId
    }

    override fun toEntity(dto: ArticlePictureDto) = ArticlePicture (
        id = dto.id,
        article = Article(dto.articleId),
        articlePicturesId = dto.articlePicturesId
    )
}