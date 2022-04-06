package magicsquare.quartumbackend.controllers

import magicsquare.quartumbackend.persistance.entity.Role
import magicsquare.quartumbackend.persistance.entity.User
import magicsquare.quartumbackend.persistance.entity.UserCredential
import magicsquare.quartumbackend.persistance.enums.ERole
import magicsquare.quartumbackend.security.jwt.JwtUtils
import magicsquare.quartumbackend.security.payload.JwtResponse
import magicsquare.quartumbackend.security.payload.LoginRequest
import magicsquare.quartumbackend.security.payload.SignupRequest
import magicsquare.quartumbackend.security.services.UserDetailsImpl
import magicsquare.quartumbackend.services.RoleService
import magicsquare.quartumbackend.services.UserCredentialService
import magicsquare.quartumbackend.services.UserService
import org.springframework.http.ResponseEntity
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.web.bind.annotation.*
import java.time.LocalDate
import java.util.function.Consumer
import java.util.stream.Collectors
import javax.validation.Valid


@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
class AuthController(
    private val authenticationManager: AuthenticationManager,
    private val userCredentialService: UserCredentialService,
    private val userService: UserService,
    private val roleService: RoleService,
    private val jwtUtils: JwtUtils,
    private val encoder: PasswordEncoder
) {
    @PostMapping("/signin")
    fun authenticateUser(@RequestBody loginRequest: @Valid LoginRequest): ResponseEntity<*>? {
        val authentication: Authentication = authenticationManager.authenticate(
            UsernamePasswordAuthenticationToken(loginRequest.username, loginRequest.password)
        )
        SecurityContextHolder.getContext().authentication = authentication
        val jwt: String = jwtUtils.generateJwtToken(authentication)
        val userDetails = authentication.principal as UserDetailsImpl
        val roles = userDetails.authorities.stream()
            .map { item: GrantedAuthority? -> item!!.authority }
            .collect(Collectors.toList())
        return ResponseEntity.ok<Any>(
            JwtResponse(
                jwt,
                "Bearer",
                userDetails.getUserId(),
                userDetails.username,
                roles
            )
        )
    }

    @PostMapping("/signup")
    fun registerUser(@RequestBody signUpRequest: @Valid SignupRequest?): ResponseEntity<*>? {
        if (userCredentialService.existByEmail(signUpRequest!!.email)) {
            return ResponseEntity
                .badRequest()
                .body<Any>("Error: Username ${signUpRequest.email} is already taken!")
        }
        // Create new user's account
        val userCredential = UserCredential(
            signUpRequest.username,
            signUpRequest.email,
            encoder.encode(signUpRequest.password)
        )
        val strRoles = signUpRequest.role
        val roles: MutableSet<Role> = HashSet<Role>()
        if (strRoles == null) {
            val userRole: Role = roleService.findByRoleName(ERole.USER)
            roles.add(userRole)
        } else {
            strRoles.forEach(Consumer { role: String? ->
                when (role) {
                    "admin" -> {
                        val adminRole: Role = roleService.findByRoleName(ERole.ADMIN)
                        roles.add(adminRole)
                    }
                    else -> {
                        val userRole: Role = roleService.findByRoleName(ERole.USER)
                        roles.add(userRole)
                    }
                }
            })
        }
        val user = User(
            signUpRequest.name,
            signUpRequest.surname,
            signUpRequest.phoneNumber,
            LocalDate.parse(signUpRequest.birthday),
            roles
        )

        userCredential.user = user
        user.userCredentials = userCredential

        userCredentialService.save(userCredential)

        return ResponseEntity.ok<Any>("User registered successfully!")
    }
}
