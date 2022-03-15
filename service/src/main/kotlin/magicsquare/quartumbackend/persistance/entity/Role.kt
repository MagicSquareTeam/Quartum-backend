package magicsquare.quartumbackend.persistance.entity

import jakarta.persistence.*

@Entity
@Table(name = "role")
open class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id", nullable = false)
    open var id: Int? = null

    @Column(name = "role_name", length = 50)
    open var roleName: String? = null

    @ManyToMany(mappedBy = "roles")
    open var users: MutableSet<User> = mutableSetOf()

}