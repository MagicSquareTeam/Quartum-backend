package magicsquare.quartumbackend.dto

import java.io.Serializable
import java.time.Instant

data class UserDto(
    val id: Long? = null,
    val name: String? = null,
    val surname: String? = null,
    val patronymic: String? = null,
    val birthday: Instant? = null,
    val profileStatus: String? = null,
    val aboutUser: String? = null,
    val profilePhotoId: Long? = null,
    val phoneNumber: String? = null,
    val userCredentialsId: Long? = null,
    val roleIds: MutableSet<Int>?,
    val tagIds: MutableSet<Int>?,
    val articleIds: MutableSet<Long>?,
    val starred_articleIds: MutableSet<Long>?,
    val articleRatingIds: MutableSet<Long>?,
    val users_subscriberIds: MutableSet<Long>?,
    val users_subscriptionIds: MutableSet<Long>?
) : Serializable
