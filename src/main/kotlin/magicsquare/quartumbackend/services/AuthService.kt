package magicsquare.quartumbackend.services

import magicsquare.quartumbackend.exceptions.AuthException
import magicsquare.quartumbackend.persistance.entity.Role
import magicsquare.quartumbackend.persistance.entity.User
import magicsquare.quartumbackend.persistance.entity.UserCredential
import magicsquare.quartumbackend.persistance.enums.ERole
import magicsquare.quartumbackend.security.jwt.JwtUtils
import magicsquare.quartumbackend.security.payload.JwtResponse
import magicsquare.quartumbackend.security.payload.LoginRequest
import magicsquare.quartumbackend.security.payload.SignupRequest
import magicsquare.quartumbackend.security.services.UserDetailsImpl
import org.springframework.http.HttpStatus
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.stereotype.Service
import java.time.LocalDate
import java.util.function.Consumer
import java.util.stream.Collectors

@Service
class AuthService(
    private val authenticationManager: AuthenticationManager,
    private val jwtUtils: JwtUtils,
    private val userCredentialService: UserCredentialService,
    private val roleService: RoleService,
    private val encoder: PasswordEncoder
) {

    private companion object {
        const val BEARER = "Bearer"
    }

    fun signIn(loginRequest: LoginRequest): JwtResponse {
        val authentication: Authentication = authenticationManager.authenticate(
            UsernamePasswordAuthenticationToken(loginRequest.username, loginRequest.password)
        )
        SecurityContextHolder.getContext().authentication = authentication
        val jwt: String = jwtUtils.generateJwtToken(authentication)
        val userDetails = authentication.principal as UserDetailsImpl
        val roles = userDetails.authorities.stream()
            .map { item: GrantedAuthority? -> item!!.authority }
            .collect(Collectors.toList())
        return JwtResponse(
            jwt,
            BEARER,
            userDetails.getUserId(),
            userDetails.username,
            userDetails.email,
            roles
        )
    }

    fun signUp(signupRequest: SignupRequest) {
        if (userCredentialService.existByEmail(signupRequest.email)) {
            throw AuthException("Error: Username ${signupRequest.email} is already taken!", HttpStatus.BAD_REQUEST)
        }

        val userCredential = UserCredential(
            signupRequest.username,
            signupRequest.email,
            encoder.encode(signupRequest.password)
        )
        val strRoles = signupRequest.role
        val roles: MutableSet<Role> = HashSet<Role>()
        if (strRoles == null) {
            val userRole: Role = roleService.findByRoleName(ERole.USER)
            roles.add(userRole)
        } else {
            strRoles.forEach(
                Consumer { role: String? ->
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
                }
            )
        }
        val user = User(
            signupRequest.name,
            signupRequest.surname,
            signupRequest.phoneNumber,
            LocalDate.parse(signupRequest.birthday),
            roles
        )

        userCredential.user = user
        user.userCredentials = userCredential

        userCredentialService.save(userCredential)
    }
}
