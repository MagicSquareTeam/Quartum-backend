package magicsquare.quartumbackend.services

import magicsquare.quartumbackend.persistance.entity.Article
import magicsquare.quartumbackend.persistance.mapper.ArticleMapper
import magicsquare.quartumbackend.persistance.repository.ArticleRepository
import magicsquare.quartumbackend.web.dto.ArticleDto
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Service

@Service
/**
 * Класс сервиса для работы со статьями
 */
class ArticleService(
    /** Поле с маппером статей */
    private val articleMapper: ArticleMapper,

    /** Поле с репозиторием статей */
    private val articleRepository: ArticleRepository
) {
    /**
     * Метод для добавления новой статьи
     * @param articleDto Модель статьи
     * @see ArticleDto
     */
    fun addNewArticle(articleDto: ArticleDto) {
        val article: Article = articleMapper.toEntity(articleDto)
        articleRepository.save(article)
    }

    /**
     * Метод для возвращения всех статей
     * @return Возвращает список всех статей
     */
    fun findAll(): MutableSet<ArticleDto> {
        return articleRepository.findAll().map { article -> articleMapper.toDto(article) }.toMutableSet()
    }

    /**
     * Метод для обновления существующей статьи
     * @param articleDto Модель статьи
     * @see ArticleDto
     */
    fun updateArticle(articleDto: ArticleDto) {
        val article: Article = articleMapper.toEntity(articleDto)
        articleRepository.save(article)
    }
}
