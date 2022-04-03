package magicsquare.quartumbackend.security

import magicsquare.quartumbackend.security.jwt.AuthEntryPointJwt
import magicsquare.quartumbackend.security.services.UserDetailsServiceImpl
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.core.userdetails.UserDetailsService

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
class WebSecurityConfig(
    val userDetailsService: UserDetailsServiceImpl,
    val unauthorizedHandler : AuthEntryPointJwt,
) : WebSecurityConfigurerAdapter() {
}