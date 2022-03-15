package magicsquare.quartumbackend.persistance.entity

import jakarta.persistence.*

@Entity
@Table(name = "article_pictures")
open class ArticlePicture {
    @Id
    open var id: Long? = null

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "article_id")
    open var article: Article? = null

    @Column(name = "article_pictures_id", nullable = false)
    open var articlePicturesId: Long? = null

}