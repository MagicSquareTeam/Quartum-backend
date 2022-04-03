package magicsquare.quartumbackend.controllers

import magicsquare.quartumbackend.dto.UserCredentialDto
import magicsquare.quartumbackend.service.UserCredentialService
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/test")
class TestController(
    private val service: UserCredentialService
) {


    @GetMapping(
        path = ["/nikitaCredential/{email}"],
        produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    fun getNikitaCredential(@PathVariable email: String): UserCredentialDto {
        return service.findByEmail(email)
    }
}