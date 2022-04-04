package magicsquare.quartumbackend.persistance.mapper

import magicsquare.quartumbackend.dto.UserDto
import magicsquare.quartumbackend.persistance.entity.*
import java.time.LocalDate
import java.time.LocalDateTime

class UserMapper : CommonMapper<UserDto, User> {
    override fun toDto(entity: User) = UserDto (
        id = entity.id,
        name = entity.name,
        surname = entity.surname,
        patronymic = entity.patronymic,
        birthday = entity.birthday.toString(),
        profileStatus = entity.profileStatus,
        aboutUser = entity.aboutUser,
        profilePhotoId = entity.profilePhotoId,
        phoneNumber = entity.phoneNumber,
        userCredentialsId = entity.userCredentials?.id,
        roleIds = entity.roles.map { it.id!! },
        tagIds = entity.tags.map { it.id!! },
        articleIds = entity.articles.map { it.id!! },
        starred_articleIds = entity.starred_articles.map { it.id!! },
        articleRatingIds = entity.articleRatings.map { it.id!! },
        users_subscriberIds = entity.users_subscribers.map { it.id!! },
        users_subscriptionIds = entity.users_subscriptions.map { it.id!! }
    )

    override fun update(dto: UserDto, entity: User) {
        entity.id = dto.id
        entity.name = dto.name
        entity.surname = dto.surname
        entity.patronymic = dto.patronymic
        entity.birthday = LocalDate.parse(dto.birthday)
        entity.profileStatus = dto.profileStatus
        entity.aboutUser =dto.aboutUser
        entity.profilePhotoId = dto.profilePhotoId
        entity.phoneNumber = dto.phoneNumber
        entity.userCredentials = UserCredential(dto.userCredentialsId)
        entity.roles = dto.roleIds?.map { Role(it) }!!.toMutableSet()
        entity.tags = dto.tagIds?.map { Tag(it) }!!.toMutableSet()
        entity.articles = dto.articleIds?.map { Article(it) }!!.toMutableSet()
        entity.starred_articles = dto.starred_articleIds?.map { Article(it) }!!.toMutableSet()
        entity.articleRatings = dto.articleRatingIds?.map { ArticleRating(it) }!!.toMutableSet()
        entity.users_subscribers = dto.users_subscriberIds?.map { User(it) }!!.toMutableSet()
        entity.users_subscriptions = dto.users_subscriptionIds?.map { User(it) }!!.toMutableSet()
    }

    override fun toEntity(dto: UserDto) = User (
        id = dto.id,
        name = dto.name,
        surname = dto.surname,
        patronymic = dto.patronymic,
        birthday = LocalDate.parse(dto.birthday),
        profileStatus = dto.profileStatus,
        aboutUser =dto.aboutUser,
        profilePhotoId = dto.profilePhotoId,
        phoneNumber = dto.phoneNumber,
        userCredentials = UserCredential(dto.userCredentialsId),
        roles = dto.roleIds?.map { Role(it) }!!.toMutableSet(),
        tags = dto.tagIds?.map { Tag(it) }!!.toMutableSet(),
        articles = dto.articleIds?.map { Article(it) }!!.toMutableSet(),
        starred_articles = dto.starred_articleIds?.map { Article(it) }!!.toMutableSet(),
        articleRatings = dto.articleRatingIds?.map { ArticleRating(it) }!!.toMutableSet(),
        users_subscribers = dto.users_subscriberIds?.map { User(it) }!!.toMutableSet(),
        users_subscriptions = dto.users_subscriptionIds?.map { User(it) }!!.toMutableSet()
    )
}