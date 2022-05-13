package magicsquare.quartumbackend.web.controllers

import magicsquare.quartumbackend.persistance.entity.Article
import magicsquare.quartumbackend.services.ArticleService
import magicsquare.quartumbackend.services.TagService
import magicsquare.quartumbackend.web.dto.ArticleDto
import magicsquare.quartumbackend.web.dto.TagDto
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/api/public")
/**
 * Класс контроллера дла работы с публичными данными
 */
class PublicController(
    /** Поле сервиса статей */
    private val articleService: ArticleService,
    /** Поле сервиса тегов */
    private val tagService: TagService
) {

    /**
     * Метод для получения всех статей
     * @return Список статей
     * @see ArticleDto
     */
    @GetMapping("/getAllArticles")
    fun getAllArticles(): MutableSet<ArticleDto> {
        return articleService.findAll()
    }

    /**
     * Метод для получения всех тегов
     * @return Список тегов
     * @see TagDto
     */
    @GetMapping("/getAllTags")
    fun getAllTags(): MutableSet<TagDto> {
        return tagService.findAll()
    }
}
