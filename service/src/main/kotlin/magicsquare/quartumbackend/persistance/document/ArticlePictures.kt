package magicsquare.quartumbackend.persistance.document

import org.springframework.data.mongodb.core.mapping.Document
import java.time.Instant

@Document(collection = "article_pictures")
class ArticlePictures(
    var name:  String? = null,
    var description: String? = null,
    var creationDate: Instant? = null,
    var file: ByteArray
)