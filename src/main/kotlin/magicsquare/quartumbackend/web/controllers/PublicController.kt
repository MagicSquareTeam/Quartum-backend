package magicsquare.quartumbackend.web.controllers

import magicsquare.quartumbackend.persistance.entity.Article
import magicsquare.quartumbackend.services.ArticleService
import magicsquare.quartumbackend.web.dto.ArticleDto
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/api/public")
class PublicController(
    private val articleService: ArticleService
) {

    @GetMapping("/getAllArticles")
    fun getAllArticles(): MutableSet<ArticleDto> {
        return articleService.findAll()
    }
}
