package magicsquare.quartumbackend.persistance.mapper

import magicsquare.quartumbackend.dto.ArticleFileDto
import magicsquare.quartumbackend.persistance.entity.Article
import magicsquare.quartumbackend.persistance.entity.ArticleFile

class ArticleFileMapper : CommonMapper <ArticleFileDto, ArticleFile> {
    override fun toDto(entity: ArticleFile) = ArticleFileDto (
        id = entity.id,
        articleId = entity.article!!.id,
        articleFileId = entity.articleFileId
    )

    override fun update(dto: ArticleFileDto, entity: ArticleFile) {
        entity.id = dto.id
        entity.article = Article(dto.articleId)
        entity.articleFileId = dto.articleFileId
    }

    override fun toEntity(dto: ArticleFileDto) = ArticleFile (
        id = dto.id,
        article = Article(dto.articleId),
        articleFileId = dto.articleFileId
    )
}