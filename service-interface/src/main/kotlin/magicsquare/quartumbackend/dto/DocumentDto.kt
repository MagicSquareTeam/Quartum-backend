package magicsquare.quartumbackend.dto

import java.io.Serializable

class DocumentDto(
    var name: String? = null,
    var description: String? = null,
    var creationDate: String? = null,
    var file: ByteArray
) : Serializable
