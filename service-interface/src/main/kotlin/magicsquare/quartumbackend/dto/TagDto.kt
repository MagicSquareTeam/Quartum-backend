package magicsquare.quartumbackend.dto

import java.io.Serializable

data class TagDto(
    val id: Int? = null,
    val name: String? = null,
    val userIds: MutableSet<Long>?,
    val articleIds: MutableSet<Long>?
) : Serializable
