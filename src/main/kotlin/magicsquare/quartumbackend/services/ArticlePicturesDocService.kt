package magicsquare.quartumbackend.services

import magicsquare.quartumbackend.exceptions.InventoryServiceException
import magicsquare.quartumbackend.persistance.mapper.ArticlePicturesDocumentMapper
import magicsquare.quartumbackend.persistance.mapper.ProfilePhotoDocumentMapper
import magicsquare.quartumbackend.persistance.repository.ArticleProfilePhotoDocRepository
import magicsquare.quartumbackend.web.dto.DocumentDto
import org.springframework.http.HttpStatus
import org.springframework.stereotype.Service

@Service
class ArticlePicturesDocService(
    private val mapper: ProfilePhotoDocumentMapper,
    private val repository: ArticleProfilePhotoDocRepository
) {

    fun save(dto: DocumentDto){
        try {
            repository.save(mapper.toEntity(dto))
        } catch (e: IllegalArgumentException){
            throw InventoryServiceException("Picture entity is null: $dto", HttpStatus.UNPROCESSABLE_ENTITY)
        }
    }
}
