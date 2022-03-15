package magicsquare.quartumbackend.persistance.entity

import jakarta.persistence.*

@Entity
@Table(name = "article_files")
open class ArticleFile {
    @Id
    open var id: Long? = null

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "article_id")
    open var article: Article? = null

    @Column(name = "article_file_id", nullable = false)
    open var articleFileId: Long? = null

}