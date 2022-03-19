package magicsquare.quartumbackend.dto

import java.time.Instant

import java.io.Serializable;

class DocumentDto(
    var name:  String? = null,
    var description: String? = null,
    var creationDate: Instant? = null,
    var file: ByteArray
 ) : Serializable