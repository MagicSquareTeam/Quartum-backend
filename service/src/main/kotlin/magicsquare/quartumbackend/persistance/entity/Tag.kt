package magicsquare.quartumbackend.persistance.entity

import jakarta.persistence.*

@Entity
@Table(name = "tag")
open class Tag {
    @Id
    @Column(name = "tag_id", nullable = false)
    open var id: Int? = null

    @Column(name = "name", nullable = false, length = 30)
    open var name: String? = null

    @ManyToMany(mappedBy = "tags")
    open var users: MutableSet<User> = mutableSetOf()

    @OneToMany(mappedBy = "tag")
    open var articles: MutableSet<Article> = mutableSetOf()
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Tag

        if (id != other.id) return false
        if (name != other.name) return false
        if (users != other.users) return false
        if (articles != other.articles) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id ?: 0
        result = 31 * result + (name?.hashCode() ?: 0)
        result = 31 * result + users.hashCode()
        result = 31 * result + articles.hashCode()
        return result
    }


}