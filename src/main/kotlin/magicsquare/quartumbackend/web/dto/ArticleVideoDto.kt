package magicsquare.quartumbackend.web.dto

import java.io.Serializable

data class ArticleVideoDto(val id: Long? = null, val articleId: Long? = null, val articleVideoId: Long? = null) :
    Serializable
