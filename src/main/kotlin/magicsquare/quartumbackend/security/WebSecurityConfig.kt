package magicsquare.quartumbackend.security

import magicsquare.quartumbackend.security.jwt.AuthEntryPointJwt
import magicsquare.quartumbackend.security.jwt.AuthTokenFilter
import magicsquare.quartumbackend.security.services.UserDetailsServiceImpl
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.authentication.AuthenticationManager
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.annotation.web.builders.WebSecurity
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter


/**
 * Web security config - конфигурация безопасности
 *
 * @property userDetailsService
 * @property unauthorizedHandler
 * @property authTokenFilter
 * @constructor Создаёт пустую Web security config
 */
@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
class WebSecurityConfig(
    private val userDetailsService: UserDetailsServiceImpl,
    private val unauthorizedHandler: AuthEntryPointJwt,
    private val authTokenFilter: AuthTokenFilter
) : WebSecurityConfigurerAdapter() {

    override fun configure(auth: AuthenticationManagerBuilder) {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder())
    }

    @Bean
    @Throws(Exception::class)
    override fun authenticationManagerBean(): AuthenticationManager {
        return super.authenticationManagerBean()
    }

    /**
     * Password encoder - отвечает за шифрование паролей
     *
     * @return зашифрованный пароль
     */
    @Bean
    fun passwordEncoder(): PasswordEncoder? {
        return BCryptPasswordEncoder()
    }

    /**
     * Configure - метод отвечает за конфигурирования доступа к определённым маппингам системы
     *
     * @param http
     */
    @Throws(Exception::class)
    override fun configure(http: HttpSecurity) {
        http.cors().and().csrf().disable()
            .exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
            .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
            .authorizeRequests().antMatchers("/api/auth/**").permitAll().and()
            // для тестов
            .authorizeRequests().antMatchers("/api/media/**").permitAll().and()
            .authorizeRequests().antMatchers("/api/articles/**").authenticated().and()
            .authorizeRequests().antMatchers("/api/public/**").permitAll()
            .anyRequest().authenticated()
        http.addFilterBefore(authTokenFilter, UsernamePasswordAuthenticationFilter::class.java)
    }
}
