package magicsquare.quartumbackend.persistance.entity

import jakarta.persistence.*
import java.time.Instant

@Entity
@Table(
    name = "\"user\"", indexes = [
        Index(name = "user_name_surname", columnList = "name, surname"),
        Index(name = "user_phone_number_uindex", columnList = "phone_number", unique = true)
    ]
)
open class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false)
    open var id: Long? = null

    @Column(name = "name", nullable = false, length = 50)
    open var name: String? = null

    @Column(name = "surname", nullable = false, length = 50)
    open var surname: String? = null

    @Column(name = "patronymic", length = 50)
    open var patronymic: String? = null

    @Column(name = "birthday", nullable = false)
    open var birthday: Instant? = null

    @Column(name = "profile_status")
    open var profileStatus: String? = null

    @Column(name = "about_user")
    open var aboutUser: String? = null

    @Column(name = "profile_photo_id")
    open var profilePhotoId: Long? = null

    @Column(name = "phone_number", length = 11)
    open var phoneNumber: String? = null

    @OneToMany(mappedBy = "user")
    open var userCredentials: MutableSet<UserCredential> = mutableSetOf()

    @ManyToMany
    @JoinTable(
        name = "user_roles",
        joinColumns = [JoinColumn(name = "user_id")],
        inverseJoinColumns = [JoinColumn(name = "role_id")]
    )
    open var roles: MutableSet<Role> = mutableSetOf()

    @ManyToMany
    @JoinTable(
        name = "tag_subscriptions",
        joinColumns = [JoinColumn(name = "user_id")],
        inverseJoinColumns = [JoinColumn(name = "tag_id")]
    )
    open var tags: MutableSet<Tag> = mutableSetOf()

    @OneToMany(mappedBy = "author")
    open var articles: MutableSet<Article> = mutableSetOf()

    @ManyToMany
    @JoinTable(
        name = "starred_article",
        joinColumns = [JoinColumn(name = "author_id")],
        inverseJoinColumns = [JoinColumn(name = "article_id")]
    )
    open var starred_articles: MutableSet<Article> = mutableSetOf()

    @OneToMany(mappedBy = "ratedUser")
    open var articleRatings: MutableSet<ArticleRating> = mutableSetOf()

    @ManyToMany
    @JoinTable(
        name = "user_subscriptions",
        joinColumns = [JoinColumn(name = "user_id")],
        inverseJoinColumns = [JoinColumn(name = "user_subs_id")]
    )
    open var users_subscriptions: MutableSet<User> = mutableSetOf()

    @ManyToMany
    @JoinTable(
        name = "user_subscriptions",
        joinColumns = [JoinColumn(name = "user_subs_id")],
        inverseJoinColumns = [JoinColumn(name = "user_id")]
    )
    open var users: MutableSet<User> = mutableSetOf()
}