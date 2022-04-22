package magicsquare.quartumbackend.persistance.document

import org.springframework.data.mongodb.core.mapping.Document
import java.time.Instant

/**
 * Profile photo  - профильное фото пользователя
 *
 * @constructor
 *
 * @param name
 * @param description
 * @param creationDate
 * @param file
 */
@Document(collation = "profile_photo")
class ProfilePhoto(
    name: String? = null,
    description: String? = null,
    creationDate: Instant? = null,
    file: ByteArray? = null
) : AbstractDocument(name, description, creationDate, file)