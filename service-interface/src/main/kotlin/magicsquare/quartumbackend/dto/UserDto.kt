package magicsquare.quartumbackend.dto

import java.io.Serializable
import java.time.LocalDateTime

data class UserDto(
    val id: Long? = null,
    val name: String? = null,
    val surname: String? = null,
    val patronymic: String? = null,
    val birthday: String? = null,
    val profileStatus: String? = null,
    val aboutUser: String? = null,
    val profilePhotoId: Long? = null,
    val phoneNumber: String? = null,
    val userCredentialsId: Long? = null,
    val roleIds: List<Int>?,
    val tagIds: List<Int>?,
    val articleIds: List<Long>?,
    val starred_articleIds: List<Long>?,
    val articleRatingIds: List<Long>?,
    val users_subscriberIds: List<Long>?,
    val users_subscriptionIds: List<Long>?
) : Serializable
