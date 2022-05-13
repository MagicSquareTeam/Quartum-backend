package magicsquare.quartumbackend.persistance.entity

import javax.persistence.*

/**
 * Article video - видео статьи, содержит id этого же объекта в mongo db
 *
 * @property id
 * @property article
 * @property articleVideoId
 */
@Entity
@Table(name = "article_videos")
open class ArticleVideo (
    @Id
    open var id: Long? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "article_id")
    open var article: Article? = null,

    @Column(name = "article_video_id", nullable = false)
    open var articleVideoId: Long? = null,
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as ArticleVideo

        if (id != other.id) return false
        if (article != other.article) return false
        if (articleVideoId != other.articleVideoId) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id?.hashCode() ?: 0
        result = 31 * result + (article?.hashCode() ?: 0)
        result = 31 * result + (articleVideoId?.hashCode() ?: 0)
        return result
    }


}