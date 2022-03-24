package magicsquare.quartumbackend.dto

import java.io.Serializable
import java.time.Instant

class DocumentDto(
    var name: String? = null,
    var description: String? = null,
    var creationDate: Instant? = null,
    var file: ByteArray
) : Serializable
