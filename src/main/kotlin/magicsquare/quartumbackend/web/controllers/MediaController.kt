package magicsquare.quartumbackend.web.controllers

import magicsquare.quartumbackend.services.ArticlePicturesDocService
import magicsquare.quartumbackend.web.dto.DocumentDto
import org.springframework.web.bind.annotation.*
import org.springframework.web.multipart.MultipartFile

@CrossOrigin(maxAge = 3600)
@RequestMapping("/api/media")
@RestController
class MediaController(
    private val picturesDocService: ArticlePicturesDocService
) {

    @PostMapping(value =["/upload/profilePhoto"], consumes = ["multipart/form-data"])
    fun uploadPhoto( file: MultipartFile) {
        picturesDocService.save(file)
    }

    @GetMapping("/getProfilePhoto")
    fun getProfilePhoto(){
        return picturesDocService.getProfilePhotoById()
    }
}
