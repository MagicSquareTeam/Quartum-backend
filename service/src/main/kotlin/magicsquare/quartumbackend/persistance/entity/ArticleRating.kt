package magicsquare.quartumbackend.persistance.entity

import jakarta.persistence.*

@Entity
@Table(name = "article_rating")
open class ArticleRating(
    @Id
    open var id: Long? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rated_user_id")
    open var ratedUser: User? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "article_id")
    open var article: Article? = null,

    @Column(name = "rating", nullable = false)
    open var rating: Boolean? = null
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as ArticleRating

        if (id != other.id) return false
        if (ratedUser != other.ratedUser) return false
        if (article != other.article) return false
        if (rating != other.rating) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id?.hashCode() ?: 0
        result = 31 * result + (ratedUser?.hashCode() ?: 0)
        result = 31 * result + (article?.hashCode() ?: 0)
        result = 31 * result + (rating?.hashCode() ?: 0)
        return result
    }


}