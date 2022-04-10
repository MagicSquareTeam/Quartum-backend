package magicsquare.quartumbackend.persistance.repository;

import magicsquare.quartumbackend.persistance.entity.Article
import org.springframework.data.jpa.repository.JpaRepository

interface ArticleRepository : JpaRepository<Article, Long> {
}
