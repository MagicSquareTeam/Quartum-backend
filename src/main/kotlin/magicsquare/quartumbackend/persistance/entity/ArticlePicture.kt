package magicsquare.quartumbackend.persistance.entity

import javax.persistence.*

@Entity
@Table(name = "article_pictures")
open class ArticlePicture(
    @Id
    open var id: Long? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "article_id")
    open var article: Article? = null,

    @Column(name = "article_pictures_id", nullable = false)
    open var articlePicturesId: Long? = null
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as ArticlePicture

        if (id != other.id) return false
        if (article != other.article) return false
        if (articlePicturesId != other.articlePicturesId) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id?.hashCode() ?: 0
        result = 31 * result + (article?.hashCode() ?: 0)
        result = 31 * result + (articlePicturesId?.hashCode() ?: 0)
        return result
    }

}
