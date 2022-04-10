package magicsquare.quartumbackend.persistance.mapper

import magicsquare.quartumbackend.web.dto.DocumentDto
import magicsquare.quartumbackend.persistance.document.ArticlePictures
import java.time.Instant

class ArticlePicturesDocumentMapper : CommonMapper<DocumentDto, ArticlePictures> {
    override fun toDto(entity: ArticlePictures) = DocumentDto (
        name = entity.name,
        description = entity.description,
        creationDate = entity.creationDate.toString(),
        file = entity.file!!
    )

    override fun update(dto: DocumentDto, entity: ArticlePictures) {
        entity.name = dto.name
        entity.description = dto.description
        entity.creationDate = Instant.parse(dto.creationDate)
        entity.file = dto.file
    }

    override fun toEntity(dto: DocumentDto) = ArticlePictures (
        name = dto.name,
        description = dto.description,
        creationDate = Instant.parse(dto.creationDate),
        file = dto.file
    )
}