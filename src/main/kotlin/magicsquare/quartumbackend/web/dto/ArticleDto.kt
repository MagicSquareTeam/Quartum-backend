package magicsquare.quartumbackend.web.dto

import java.io.Serializable

data class ArticleDto(
    val id: Long? = null,
    val authorId: Long? = null,
    val username: String? = null,
    val rating: Int? = null,
    val name: String? = null,
    val text: String? = null,
    val creationDate: String? = null,
    val edited: Boolean? = null,
    val editTime: String? = null,
    val tagName: String? = null,
    val archived: Boolean? = null,
    val starred_userIds: List<Long>?,
    val articlePictureIds: List<Long>?,
    val articleVideoIds: List<Long>?,
    val articleRatingIds: List<Long>?,
    val articleFileIds: List<Long>?
) : Serializable
