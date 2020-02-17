package xyz.luchengeng.extracurriculum.management.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import xyz.luchengeng.extracurriculum.management.entity.User
import xyz.luchengeng.extracurriculum.management.service.SecurityService

@Controller
class UserApiController @Autowired constructor(private val securityService: SecurityService) : UserApi {
    override fun postUser(user: User): ResponseEntity<Unit> {
        securityService.newUser(user)
        return ResponseEntity.ok(Unit)
    }

    override fun logIn(user: User): ResponseEntity<String> {
        return ResponseEntity.ok(securityService.logIn(user.email,user.password))
    }

    override fun getUser(id: Long, apiKey: String): ResponseEntity<User> {
        val user = securityService.auth("user::get",apiKey)
        val requested = securityService.getUser(id)
        requested.password = ""
        return ResponseEntity.ok(requested)
    }
}