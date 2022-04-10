package magicsquare.quartumbackend.web.dto

import java.io.Serializable

data class ArticleFileDto(val id: Long? = null, val articleId: Long? = null, val articleFileId: Long? = null) :
    Serializable
