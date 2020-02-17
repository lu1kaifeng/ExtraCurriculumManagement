package xyz.luchengeng.extracurriculum.management.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import xyz.luchengeng.extracurriculum.management.dto.UserDto
import xyz.luchengeng.extracurriculum.management.entity.User
import xyz.luchengeng.extracurriculum.management.service.SecurityService

@Controller
class UserApiController @Autowired constructor(private val securityService: SecurityService) : UserApi {
    override fun postUser(user: User): ResponseEntity<Unit> {
        securityService.newUser(user)
        return ResponseEntity.ok(Unit)
    }

    override fun getUser(id: Long, apiKey: String): ResponseEntity<UserDto> {
        val user = securityService.auth("user::get",apiKey)
        return ResponseEntity.ok(UserDto(securityService.getUser(id)))
    }
}