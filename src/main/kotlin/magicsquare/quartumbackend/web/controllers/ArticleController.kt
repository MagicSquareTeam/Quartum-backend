package magicsquare.quartumbackend.web.controllers

import magicsquare.quartumbackend.services.ArticleService
import magicsquare.quartumbackend.web.dto.ArticleDto
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@CrossOrigin(maxAge = 3600)
@RequestMapping("/api/articles")
class ArticleController(
    private val articleService: ArticleService
) {

    @PostMapping("/addNewArticle")
    fun addNewArticle(@RequestBody articleDto: ArticleDto): ResponseEntity<*> {
        articleService.addNewArticle(articleDto)
        return ResponseEntity.ok<Any>("New article added successfully")
    }
}
