package magicsquare.quartumbackend.persistance.document

import org.springframework.data.mongodb.core.mapping.Document
import java.time.Instant

/**
 * Article files - файлы статьи
 *
 * @param name
 * @param description
 * @param creationDate
 * @param file
 */
@Document(collection = "article_files")
class ArticleFiles(
    name: String? = null,
    description: String? = null,
    creationDate: Instant? = null,
    file: ByteArray? = null
) : AbstractDocument(name, description, creationDate, file)