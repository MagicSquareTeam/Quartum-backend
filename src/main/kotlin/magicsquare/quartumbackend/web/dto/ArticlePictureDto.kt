package magicsquare.quartumbackend.web.dto

import java.io.Serializable

/**
 * Модель DTO для картинок статьи
 */
data class ArticlePictureDto(val id: Long? = null, val articleId: Long? = null, val articlePicturesId: Long? = null) :
    Serializable
