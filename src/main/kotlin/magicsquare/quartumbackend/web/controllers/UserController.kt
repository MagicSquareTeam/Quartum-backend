package magicsquare.quartumbackend.web.controllers

import magicsquare.quartumbackend.persistance.entity.User
import magicsquare.quartumbackend.services.UserService
import magicsquare.quartumbackend.web.dto.ArticleDto
import magicsquare.quartumbackend.web.dto.UserDto
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@CrossOrigin(maxAge = 3600)
@RestController
@RequestMapping("/api/user")
class UserController(
    private val userService: UserService
) {

    @PostMapping("/addUserSub/{userId}/{userSubId}")
    fun addUserSub(@PathVariable userId: Long, @PathVariable userSubId: Long): ResponseEntity<*> {
        userService.addUserSub(userId, userSubId)
        return ResponseEntity.ok<Any>("Subscr added successfully")
    }

    @GetMapping("/getUserSubs/{userId}")
    fun getUserSubs(@PathVariable userId: Long): MutableSet<User> {
        return userService.getUserSubs(userId)
    }

    @GetMapping("/getUserData/{userId}")
    fun getUserData(@PathVariable userId: Long): UserDto {
        return userService.getUserData(userId)
    }

    @PostMapping("updateUserData")
    fun saveUserData(dto: UserDto) {
        return userService.save(dto)
    }

    @GetMapping("/getSubsArticles/{userId}")
    fun getSubsArticles(@PathVariable userId: Long): MutableSet<ArticleDto> {
        return userService.getSubsArticles(userId)
    }
}
