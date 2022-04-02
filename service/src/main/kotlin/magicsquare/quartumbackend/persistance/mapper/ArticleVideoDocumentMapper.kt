package magicsquare.quartumbackend.persistance.mapper

import magicsquare.quartumbackend.dto.ArticleVideoDto
import magicsquare.quartumbackend.persistance.entity.Article
import magicsquare.quartumbackend.persistance.entity.ArticleVideo
import org.springframework.stereotype.Component

@Component
class ArticleVideoDocumentMapper : CommonMapper <ArticleVideoDto, ArticleVideo> {
    override fun toDto(entity: ArticleVideo) = ArticleVideoDto (
        id = entity.id,
        articleId = entity.article!!.id,
        articleVideoId = entity.articleVideoId
    )

    override fun update(dto: ArticleVideoDto, entity: ArticleVideo) {
        entity.id = dto.id
        entity.article = Article(dto.articleId)
        entity.articleVideoId = dto.articleVideoId
    }

    override fun toEntity(dto: ArticleVideoDto) = ArticleVideo (
        id = dto.id,
        article = Article(dto.articleId),
        articleVideoId = dto.articleVideoId
    )
}