package magicsquare.quartumbackend.web.controllers

import magicsquare.quartumbackend.web.dto.UserCredentialDto
import magicsquare.quartumbackend.persistance.mapper.UserCredentialsMapper
import magicsquare.quartumbackend.services.UserCredentialService
import org.springframework.http.MediaType
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/test")
class TestController(
    private val service: UserCredentialService,
    private val mapper: UserCredentialsMapper
) {


    @GetMapping(
        path = ["/nikitaCredential/{email}"],
        produces = [MediaType.APPLICATION_JSON_VALUE]
    )
    fun getNikitaCredential(@PathVariable email: String): UserCredentialDto {
        return mapper.toDto(service.findByEmail(email))
    }
}