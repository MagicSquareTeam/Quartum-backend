package magicsquare.quartumbackend.persistance.entity

import magicsquare.quartumbackend.persistance.enums.ERole
import javax.persistence.*

@Entity
@Table(name = "role")
open class Role(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "role_id", nullable = false)
    open var id: Int? = null,

    @Column(name = "role_name", length = 50)
    @Enumerated(EnumType.STRING)
    open var roleName: ERole? = null,

    @ManyToMany(mappedBy = "roles")
    open var users: MutableSet<User> = mutableSetOf()
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Role

        if (id != other.id) return false
        if (roleName != other.roleName) return false
        if (users != other.users) return false

        return true
    }

    override fun hashCode(): Int {
        var result = id ?: 0
        result = 31 * result + (roleName?.hashCode() ?: 0)
//        result = 31 * result + users.hashCode()
        return result
    }


}
