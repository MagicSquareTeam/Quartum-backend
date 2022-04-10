package magicsquare.quartumbackend.persistance.document

import org.springframework.data.mongodb.core.mapping.Document
import java.time.Instant

@Document(collation = "profile_photo")
class ProfilePhoto(
    name: String? = null,
    description: String? = null,
    creationDate: Instant? = null,
    file: ByteArray? = null
) : AbstractDocument(name, description, creationDate, file)