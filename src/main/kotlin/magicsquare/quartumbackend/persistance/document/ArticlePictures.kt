package magicsquare.quartumbackend.persistance.document

import org.springframework.data.mongodb.core.mapping.Document
import java.time.Instant

/**
 * Article pictures - изображении статьи
 *
 * @param name
 * @param description
 * @param creationDate
 * @param file
 */
@Document(collection = "article_pictures")
class ArticlePictures(
    name: String? = null,
    description: String? = null,
    creationDate: Instant? = null,
    file: ByteArray? = null
) : AbstractDocument(name, description, creationDate, file)