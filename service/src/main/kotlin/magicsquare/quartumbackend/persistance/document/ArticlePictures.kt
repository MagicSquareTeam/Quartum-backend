package magicsquare.quartumbackend.persistance.document

import org.springframework.data.mongodb.core.mapping.Document

@Document(collection = "article_pictures")
class ArticlePictures: AbstractDocument()