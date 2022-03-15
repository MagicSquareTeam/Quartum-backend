package magicsquare.quartumbackend.persistance.entity

import jakarta.persistence.*

@Entity
@Table(name = "article_videos")
open class ArticleVideo {
    @Id

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "article_id")
    open var article: Article? = null

    @Column(name = "article_video_id", nullable = false)
    open var articleVideoId: Long? = null

}