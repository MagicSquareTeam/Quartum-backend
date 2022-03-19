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
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Article

        if (id != other.id) return false
        if (author != other.author) return false
        if (rating != other.rating) return false
        if (name != other.name) return false
        if (text != other.text) return false
        if (creationDate != other.creationDate) return false
        if (edited != other.edited) return false
        if (editTime != other.editTime) return false
        if (tag != other.tag) return false
        if (archived != other.archived) return false
        if (starred_users != other.starred_users) return false
        if (articlePictures != other.articlePictures) return false
        if (articleVideos != other.articleVideos) return false
        if (articleRatings != other.articleRatings) return false
        if (articleFiles != other.articleFiles) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id?.hashCode() ?: 0
        result = 31 * result + (author?.hashCode() ?: 0)
        result = 31 * result + (rating ?: 0)
        result = 31 * result + (name?.hashCode() ?: 0)
        result = 31 * result + (text?.hashCode() ?: 0)
        result = 31 * result + (creationDate?.hashCode() ?: 0)
        result = 31 * result + (edited?.hashCode() ?: 0)
        result = 31 * result + (editTime?.hashCode() ?: 0)
        result = 31 * result + (tag?.hashCode() ?: 0)
        result = 31 * result + (archived?.hashCode() ?: 0)
        result = 31 * result + starred_users.hashCode()
        result = 31 * result + articlePictures.hashCode()
        result = 31 * result + articleVideos.hashCode()
        result = 31 * result + articleRatings.hashCode()
        result = 31 * result + articleFiles.hashCode()
        return result
    }


}