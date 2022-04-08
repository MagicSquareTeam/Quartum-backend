package magicsquare.quartumbackend.web.dto

import java.io.Serializable

data class ArticlePictureDto(val id: Long? = null, val articleId: Long? = null, val articlePicturesId: Long? = null) :
    Serializable
