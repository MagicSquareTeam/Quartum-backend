package magicsquare.quartumbackend.persistance.mapper

import magicsquare.quartumbackend.dto.DocumentDto
import magicsquare.quartumbackend.persistance.document.ArticleFiles
import org.springframework.stereotype.Component
import java.time.Instant

@Component
class ArticleFIlesDocumentMapper : CommonMapper<DocumentDto, ArticleFiles> {
    override fun toDto(entity: ArticleFiles) = DocumentDto (
        name = entity.name,
        description = entity.description,
        creationDate = entity.creationDate.toString(),
        file = entity.file!!
    )

    override fun update(dto: DocumentDto, entity: ArticleFiles) {
        entity.name = dto.name
        entity.description = dto.description
        entity.creationDate = Instant.parse(dto.creationDate)
        entity.file = dto.file
    }

    override fun toEntity(dto: DocumentDto) = ArticleFiles (
        name = dto.name,
        description = dto.description,
        creationDate = Instant.parse(dto.creationDate),
        file = dto.file
    )
}