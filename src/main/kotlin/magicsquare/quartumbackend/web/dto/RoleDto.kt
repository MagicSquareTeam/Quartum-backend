package magicsquare.quartumbackend.web.dto

import java.io.Serializable

data class RoleDto(val id: Int? = null, val roleName: String? = null, val userIds: List<Long>?) : Serializable
