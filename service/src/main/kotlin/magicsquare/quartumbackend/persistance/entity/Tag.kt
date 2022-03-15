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

}