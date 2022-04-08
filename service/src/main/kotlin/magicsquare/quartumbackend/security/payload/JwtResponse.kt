package magicsquare.quartumbackend.security.payload

import javax.validation.constraints.NotBlank

data class JwtResponse(
    @NotBlank val token: String,
    @NotBlank val type: String,
    @NotBlank val userId: Long,
    @NotBlank val username: String,
    @NotBlank val email: String,
    @NotBlank val roles: List<String>
)
