package magicsquare.quartumbackend.persistance.document

import org.springframework.data.mongodb.core.mapping.Document
import java.time.Instant

@Document(collation = "profile_photo")
class ProfilePhoto(
    var name:  String? = null,
    var description: String? = null,
    var creationDate: Instant? = null,
    var file: ByteArray
    ) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as ProfilePhoto

        if (name != other.name) return false
        if (description != other.description) return false
        if (creationDate != other.creationDate) return false
        if (!file.contentEquals(other.file)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = name?.hashCode() ?: 0
        result = 31 * result + (description?.hashCode() ?: 0)
        result = 31 * result + (creationDate?.hashCode() ?: 0)
        result = 31 * result + file.contentHashCode()
        return result
    }
}