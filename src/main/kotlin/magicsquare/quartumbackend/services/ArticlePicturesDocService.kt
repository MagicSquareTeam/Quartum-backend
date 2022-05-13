package magicsquare.quartumbackend.services

import magicsquare.quartumbackend.exceptions.InventoryServiceException
import magicsquare.quartumbackend.persistance.document.ProfilePhoto
import magicsquare.quartumbackend.persistance.mapper.ProfilePhotoDocumentMapper
import magicsquare.quartumbackend.persistance.repository.mongo.ArticleProfilePhotoDocRepository
import magicsquare.quartumbackend.web.dto.DocumentDto
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service
import org.springframework.web.multipart.MultipartFile
import java.time.Instant

@Service
class ArticlePicturesDocService(
    private val mapper: ProfilePhotoDocumentMapper,
    private val repository: ArticleProfilePhotoDocRepository
) {

    fun save(file: MultipartFile){
        try {
            repository.save(ProfilePhoto("photo", "adfasdf", Instant.now(), file.bytes))
        } catch (e: IllegalArgumentException){
            throw InventoryServiceException("Picture entity is null", HttpStatus.UNPROCESSABLE_ENTITY)
        }
    }

    fun getProfilePhotoById() {
        TODO("Not yet implemented")
    }
}
