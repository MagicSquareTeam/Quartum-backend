package magicsquare.quartumbackend.persistance.repository

import magicsquare.quartumbackend.persistance.document.ProfilePhoto
import org.springframework.data.mongodb.repository.MongoRepository
import org.springframework.stereotype.Repository

@Repository
interface ArticleProfilePhotoDocRepository : MongoRepository<ProfilePhoto, String>
