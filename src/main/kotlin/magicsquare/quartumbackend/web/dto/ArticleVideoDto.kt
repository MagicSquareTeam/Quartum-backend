package magicsquare.quartumbackend.web.dto

import java.io.Serializable

/**
 * Модель DTO для видео из статьи
 */
data class ArticleVideoDto(val id: Long? = null, val articleId: Long? = null, val articleVideoId: Long? = null) :
    Serializable
