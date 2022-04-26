package magicsquare.quartumbackend.persistance.entity

import com.fasterxml.jackson.annotation.JsonIgnore
import org.hibernate.annotations.GenericGenerator
import javax.persistence.*
import java.time.LocalDate

/**
 * User - модель представляет сущность пользователя
 *
 * @property id
 * @property name
 * @property surname
 * @property patronymic
 * @property birthday
 * @property profileStatus
 * @property aboutUser
 * @property profilePhotoId
 * @property phoneNumber
 * @property userCredentials
 * @property roles
 * @property tags
 * @property articles
 * @property starred_articles
 * @property articleRatings
 * @property users_subscribers
 * @property users_subscriptions
 */
@Entity
@Table(
    name = "\"user\"",
    indexes = [
        Index(name = "user_name_surname", columnList = "name, surname"),
    ]
)
open class User(
    @Id
    @GeneratedValue(generator = "gen")
    @GenericGenerator(name="gen", strategy="foreign",
        parameters=[org.hibernate.annotations.Parameter(name="property", value="userCredentials")])
    @Column(name = "user_id", nullable = false)
    open var id: Long? = null,

    @Column(name = "name", nullable = false, length = 50)
    open var name: String? = null,

    @Column(name = "surname", nullable = false, length = 50)
    open var surname: String? = null,

    @Column(name = "patronymic", length = 50)
    open var patronymic: String? = null,

    @Column(name = "birthday", nullable = false)
    open var birthday: LocalDate? = null,

    @Column(name = "profile_status")
    open var profileStatus: String? = null,

    @Column(name = "about_user")
    open var aboutUser: String? = null,

    @Column(name = "profile_photo_id")
    open var profilePhotoId: Long? = null,

    @Column(name = "phone_number", length = 11)
    open var phoneNumber: String? = null,

//    @OneToOne(mappedBy = "user", cascade = [CascadeType.ALL])
    @OneToOne
    @PrimaryKeyJoinColumn
    open var userCredentials: UserCredential? = null,

    @ManyToMany
    @JoinTable(
        name = "user_roles",
        joinColumns = [JoinColumn(name = "user_id")],
        inverseJoinColumns = [JoinColumn(name = "role_id")]
    )
    open var roles: MutableSet<Role> = mutableSetOf(),

    @ManyToMany
    @JoinTable(
        name = "tag_subscriptions",
        joinColumns = [JoinColumn(name = "user_id")],
        inverseJoinColumns = [JoinColumn(name = "tag_id")]
    )
    open var tags: MutableSet<Tag> = mutableSetOf(),

    @OneToMany(mappedBy = "author")
    open var articles: MutableSet<Article> = mutableSetOf(),

    @ManyToMany
    @JoinTable(
        name = "starred_article",
        joinColumns = [JoinColumn(name = "author_id")],
        inverseJoinColumns = [JoinColumn(name = "article_id")]
    )
    open var starred_articles: MutableSet<Article> = mutableSetOf(),

    @OneToMany(mappedBy = "ratedUser")
    open var articleRatings: MutableSet<ArticleRating> = mutableSetOf(),

    @ManyToMany
    @JoinTable(
        name = "user_subscriptions",
        joinColumns = [JoinColumn(name = "user_id")],
        inverseJoinColumns = [JoinColumn(name = "user_subs_id")]
    )
    open var users_subscribers: MutableSet<User> = mutableSetOf(),//люди, которые подписаны на пользователя

    @ManyToMany
    @JoinTable(
        name = "user_subscriptions",
        joinColumns = [JoinColumn(name = "user_subs_id")],
        inverseJoinColumns = [JoinColumn(name = "user_id")]
    )
    open var users_subscriptions: MutableSet<User> = mutableSetOf()// люди на кого подписан наш пользователь
) {

    constructor(
        name: String,
        surname: String,
        phoneNumber: String,
        birthday: LocalDate,
        roles: MutableSet<Role>
    ) : this() {
        this.name = name
        this.surname = surname
        this.phoneNumber = phoneNumber
        this.birthday = birthday
        this.roles = roles
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as User

        if (id != other.id) return false
        if (name != other.name) return false
        if (surname != other.surname) return false
        if (patronymic != other.patronymic) return false
        if (birthday != other.birthday) return false
        if (profileStatus != other.profileStatus) return false
        if (aboutUser != other.aboutUser) return false
        if (profilePhotoId != other.profilePhotoId) return false
        if (phoneNumber != other.phoneNumber) return false
        if (userCredentials != other.userCredentials) return false
        if (roles != other.roles) return false
        if (tags != other.tags) return false
        if (articles != other.articles) return false
        if (starred_articles != other.starred_articles) return false
        if (articleRatings != other.articleRatings) return false
        if (users_subscribers != other.users_subscribers) return false
        if (users_subscriptions != other.users_subscriptions) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id?.hashCode() ?: 0
        result = 31 * result + (name?.hashCode() ?: 0)
        result = 31 * result + (surname?.hashCode() ?: 0)
        result = 31 * result + (patronymic?.hashCode() ?: 0)
        result = 31 * result + (birthday?.hashCode() ?: 0)
        result = 31 * result + (profileStatus?.hashCode() ?: 0)
        result = 31 * result + (aboutUser?.hashCode() ?: 0)
        result = 31 * result + (profilePhotoId?.hashCode() ?: 0)
        result = 31 * result + (phoneNumber?.hashCode() ?: 0)
        return result
    }


}
