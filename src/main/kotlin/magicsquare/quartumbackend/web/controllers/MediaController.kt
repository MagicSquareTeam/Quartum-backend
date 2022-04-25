package magicsquare.quartumbackend.web.controllers

import magicsquare.quartumbackend.services.ArticlePicturesDocService
import magicsquare.quartumbackend.web.dto.DocumentDto
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@CrossOrigin(maxAge = 3600)
@RequestMapping("/api/media")
@RestController
class MediaController(
    private val picturesDocService: ArticlePicturesDocService
) {

    @PostMapping("/profilePhoto")
    fun uploadPhoto(dto: DocumentDto) {
        picturesDocService.save(dto)
    }
}
