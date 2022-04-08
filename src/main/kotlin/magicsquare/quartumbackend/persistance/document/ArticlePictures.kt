package magicsquare.quartumbackend.persistance.document

import org.springframework.data.mongodb.core.mapping.Document
import java.time.Instant

@Document(collection = "article_pictures")
class ArticlePictures(
    name: String? = null,
    description: String? = null,
    creationDate: Instant? = null,
    file: ByteArray? = null
) : AbstractDocument(name, description, creationDate, file)