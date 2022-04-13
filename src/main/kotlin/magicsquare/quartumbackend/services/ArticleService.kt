package magicsquare.quartumbackend.services

import magicsquare.quartumbackend.persistance.entity.Article
import magicsquare.quartumbackend.persistance.mapper.ArticleMapper
import magicsquare.quartumbackend.persistance.repository.ArticleRepository
import magicsquare.quartumbackend.web.dto.ArticleDto
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
class ArticleService(
    private val articleMapper: ArticleMapper,
    private val articleRepository: ArticleRepository
) {
    fun addNewArticle(articleDto: ArticleDto) {
        val article: Article = articleMapper.toEntity(articleDto)
        articleRepository.save(article)
    }

    fun findAll(): MutableSet<ArticleDto> {
        return articleRepository.findAll().map { article -> articleMapper.toDto(article) }.toMutableSet()
    }

    fun updateArticle(articleDto: ArticleDto){
        val article: Article = articleMapper.toEntity(articleDto)
        articleRepository.save(article)
    }
}
