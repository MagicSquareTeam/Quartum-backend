package magicsquare.quartumbackend.web.controllers

import magicsquare.quartumbackend.persistance.entity.Article
import magicsquare.quartumbackend.persistance.mapper.ArticleMapper
import magicsquare.quartumbackend.services.ArticleService
import magicsquare.quartumbackend.services.UserService
import magicsquare.quartumbackend.web.dto.ArticleDto
import org.springframework.data.repository.query.Param
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
@CrossOrigin(maxAge = 3600)
@RequestMapping("/api/articles")
/**
 * Класс контроллера для работы со статьями
 */
class ArticleController(
    /** Поле сервиса статей */
    private val articleService: ArticleService,
    /** Поле сервиса пользователей */
    private val userService: UserService,
    /** Поле маппера статей */
    private val articleMapper: ArticleMapper
) {

    /**
     * Метод для добавления новой статьи
     * @param articleDto Модель статьи
     * @return ResponseEntity с ответом
     * @see ArticleDto
     * @see ResponseEntity
     */
    @PostMapping("/addNewArticle")
    fun addNewArticle(@RequestBody articleDto: ArticleDto): ResponseEntity<*> {
        articleService.addNewArticle(articleDto)
        return ResponseEntity.ok<Any>("New article added successfully")
    }

    /**
     * Метод для получения статей пользователя
     * @param id ID пользователя
     * @return Список статей пользователя
     * @see ArticleDto
     */
    @GetMapping("/getUserArticles/{id}")
    fun getUserArticles(@PathVariable id: Long): MutableSet<ArticleDto> {
        return userService.getUserArticles(id).map { article -> articleMapper.toDto(article) }.toMutableSet()
    }

    /**
     * Метод для обновления статьи
     * @param articleDto Модель статьи
     * @return ResponseEntity с ответом
     * @see ArticleDto
     * @see ResponseEntity
     */
    @PostMapping("/updateArticle")
    fun updateArticle(@RequestBody articleDto: ArticleDto): ResponseEntity<*>{
        articleService.updateArticle(articleDto)
        return ResponseEntity.ok<Any>("Article updated successfully")
    }
}
