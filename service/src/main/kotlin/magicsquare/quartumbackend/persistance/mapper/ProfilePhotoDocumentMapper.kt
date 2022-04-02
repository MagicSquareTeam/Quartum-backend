package magicsquare.quartumbackend.persistance.mapper

import magicsquare.quartumbackend.dto.DocumentDto
import magicsquare.quartumbackend.persistance.document.ProfilePhoto
import org.springframework.stereotype.Component

import java.time.Instant

@Component
class ProfilePhotoDocumentMapper : CommonMapper<DocumentDto, ProfilePhoto> {
    override fun toDto(entity: ProfilePhoto) = DocumentDto (
        name = entity.name,
        description = entity.description,
        creationDate = entity.creationDate.toString(),
        file = entity.file!!
    )

    override fun update(dto: DocumentDto, entity: ProfilePhoto) {
        entity.name = dto.name
        entity.description = dto.description
        entity.creationDate = Instant.parse(dto.creationDate)
        entity.file = dto.file
    }

    override fun toEntity(dto: DocumentDto) = ProfilePhoto (
        name = dto.name,
        description = dto.description,
        creationDate = Instant.parse(dto.creationDate),
        file = dto.file
    )
}