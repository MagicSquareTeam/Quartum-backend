package magicsquare.quartumbackend.persistance.entity

import javax.persistence.*

@Entity
@Table(name = "article_files")
open class ArticleFile(
    @Id
    open var id: Long? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "article_id")
    open var article: Article? = null,

    @Column(name = "article_file_id", nullable = false)
    open var articleFileId: Long? = null
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as ArticleFile

        if (id != other.id) return false
        if (article != other.article) return false
        if (articleFileId != other.articleFileId) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id?.hashCode() ?: 0
        result = 31 * result + (article?.hashCode() ?: 0)
        result = 31 * result + (articleFileId?.hashCode() ?: 0)
        return result
    }

}