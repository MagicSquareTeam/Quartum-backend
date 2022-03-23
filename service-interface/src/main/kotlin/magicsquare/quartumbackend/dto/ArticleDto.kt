package magicsquare.quartumbackend.dto

import java.io.Serializable
import java.time.Instant

data class ArticleDto(
    val id: Long? = null,
    val authorId: Long? = null,
    val rating: Int? = null,
    val name: String? = null,
    val text: String? = null,
    val creationDate: Instant? = null,
    val edited: Boolean? = null,
    val editTime: Instant? = null,
    val tagId: Int? = null,
    val archived: Boolean? = null,
    val starred_userIds: MutableSet<Long>?,
    val articlePictureIds: MutableSet<Long>?,
    val articleVideoIds: MutableSet<Long>?,
    val articleRatingIds: MutableSet<Long>?,
    val articleFileIds: MutableSet<Long>?
) : Serializable
