package magicsquare.quartumbackend.persistance.entity

import jakarta.persistence.*

@Entity
@Table(name = "article_rating")
open class ArticleRating {
    @Id

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rated_user_id")
    open var ratedUser: User? = null

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "article_id")
    open var article: Article? = null

    @Column(name = "rating", nullable = false)
    open var rating: Int? = null

}