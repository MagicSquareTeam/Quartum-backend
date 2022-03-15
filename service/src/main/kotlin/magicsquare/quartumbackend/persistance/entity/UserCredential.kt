package magicsquare.quartumbackend.persistance.entity

import jakarta.persistence.*

@Entity
@Table(name = "user_credentials")
open class UserCredential {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false)
    open var id: Long? = null

    @MapsId
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    open var user: User? = null

    @Column(name = "email", nullable = false, length = 60)
    open var email: String? = null

    @Column(name = "password", nullable = false, length = 60)
    open var password: String? = null

    @Column(name = "username", nullable = false, length = 40)
    open var username: String? = null

}