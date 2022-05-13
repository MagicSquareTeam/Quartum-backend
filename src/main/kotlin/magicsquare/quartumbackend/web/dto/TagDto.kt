package magicsquare.quartumbackend.web.dto

import java.io.Serializable

/**
 * Модель DTO для тега
 */
data class TagDto(
    val id: Int? = null,
    val name: String? = null,
    val userIds: List<Long>?,
    val articleIds: List<Long>?
) : Serializable
