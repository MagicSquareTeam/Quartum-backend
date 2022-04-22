package magicsquare.quartumbackend.web.dto

import java.io.Serializable

/**
 * Модель DTO для документа
 */
class DocumentDto(
    var name: String? = null,
    var description: String? = null,
    var creationDate: String? = null,
    var file: ByteArray
) : Serializable
