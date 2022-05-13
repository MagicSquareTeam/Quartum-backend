package magicsquare.quartumbackend.security.jwt

import mu.KotlinLogging
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.AuthenticationEntryPoint
import org.springframework.stereotype.Component
import javax.servlet.http.HttpServletRequest
import javax.servlet.http.HttpServletResponse

/**
 * Auth entry point jwt
 *
 * @constructor Создаёт пустую точку входа для авторизации jwt
 */
@Component
class AuthEntryPointJwt : AuthenticationEntryPoint {

    private val logger = KotlinLogging.logger {}

    /**
     * Этот метод будет срабатывать в любое время, когда не прошедший проверку подлинности пользователь
     * запрашивает защищенный HTTP-ресурс, и возникает исключение AuthenticationException
     */
    override fun commence(
        request: HttpServletRequest,
        response: HttpServletResponse,
        authException: AuthenticationException
    ) {
        logger.error { "Unauthorized error: ".plus(authException.message)  }
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Error: Unauthorized");
    }
}