package magicsquare.quartumbackend.web.controllers

import javax.validation.Valid
import magicsquare.quartumbackend.security.payload.LoginRequest
import magicsquare.quartumbackend.security.payload.SignupRequest
import magicsquare.quartumbackend.services.AuthService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.CrossOrigin
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import org.springframework.web.bind.annotation.RequestBody

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
class AuthController(
    private val authService: AuthService,
) {
    @PostMapping("/signin")
    fun authenticateUser(@RequestBody loginRequest: @Valid LoginRequest): ResponseEntity<*>? {
        val jwt = authService.signIn(loginRequest)
        return ResponseEntity.ok<Any>(jwt)
    }

    @PostMapping("/signup")
    fun registerUser(@RequestBody signUpRequest: @Valid SignupRequest): ResponseEntity<*>? {
        authService.signUp(signUpRequest)
        return ResponseEntity.ok<Any>("User registered successfully!")
    }
}
