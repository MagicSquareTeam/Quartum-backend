package magicsquare.quartumbackend.persistance.document

import org.springframework.data.mongodb.core.mapping.Document
import java.time.Instant

@Document(collation = "profile_photo")
class ProfilePhoto : AbstractDocument()