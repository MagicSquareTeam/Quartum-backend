package magicsquare.quartumbackend.persistance.entity

import jakarta.persistence.*
import java.time.Instant

@Entity
@Table(name = "article")
open class Article {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "article_id", nullable = false)
    open var id: Long? = null

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "author_id")
    open var author: User? = null

    @Column(name = "rating", nullable = false)
    open var rating: Int? = null

    @Column(name = "name", nullable = false)
    open var name: String? = null

    @Lob
    @Column(name = "text", nullable = false)
    open var text: String? = null

    @Column(name = "creation_date", nullable = false)
    open var creationDate: Instant? = null

    @Column(name = "edited")
    open var edited: Boolean? = null

    @Column(name = "edit_time")
    open var editTime: Instant? = null

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "tag_id")
    open var tag: Tag? = null

    @Column(name = "archived")
    open var archived: Boolean? = null

    @ManyToMany(mappedBy = "starred_articles")
    open var starred_users: MutableSet<User> = mutableSetOf()

    @OneToMany(mappedBy = "article")
    open var articlePictures: MutableSet<ArticlePicture> = mutableSetOf()

    @OneToMany(mappedBy = "article")
    open var articleVideos: MutableSet<ArticleVideo> = mutableSetOf()

    @OneToMany(mappedBy = "article")
    open var articleRatings: MutableSet<ArticleRating> = mutableSetOf()

    @OneToMany(mappedBy = "article")
    open var articleFiles: MutableSet<ArticleFile> = mutableSetOf()

}