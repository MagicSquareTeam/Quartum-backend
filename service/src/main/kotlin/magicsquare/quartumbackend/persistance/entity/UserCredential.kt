package magicsquare.quartumbackend.persistance.entity

import jakarta.persistence.*

@Entity
@Table(name = "user_credentials")
open class UserCredential(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false)
    open var id: Long? = null,

    @MapsId
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    open var user: User? = null,

    @Column(name = "email", nullable = false, length = 60)
    open var email: String? = null,

    @Column(name = "password", nullable = false, length = 60)
    open var password: String? = null,

    @Column(name = "username", nullable = false, length = 40)
    open var username: String? = null,
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as UserCredential

        if (id != other.id) return false
        if (user != other.user) return false
        if (email != other.email) return false
        if (password != other.password) return false
        if (username != other.username) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id?.hashCode() ?: 0
        result = 31 * result + (user?.hashCode() ?: 0)
        result = 31 * result + (email?.hashCode() ?: 0)
        result = 31 * result + (password?.hashCode() ?: 0)
        result = 31 * result + (username?.hashCode() ?: 0)
        return result
    }


}