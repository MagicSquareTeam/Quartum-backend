package magicsquare.quartumbackend.persistance.repository.jpa;

import magicsquare.quartumbackend.persistance.entity.Article
import org.springframework.data.jpa.repository.JpaRepository

/**
 * Репозиторий Article
 *
 */
interface ArticleRepository : JpaRepository<Article, Long> {
}
