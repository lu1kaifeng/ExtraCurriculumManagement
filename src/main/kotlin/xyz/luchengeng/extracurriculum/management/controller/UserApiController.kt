package xyz.luchengeng.extracurriculum.management.controller

import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import xyz.luchengeng.extracurriculum.management.dto.UserDto
import xyz.luchengeng.extracurriculum.management.entity.User

@Controller
class UserApiController : UserApi {
    override fun postUser(user: User): ResponseEntity<Unit> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getUser(id: Long, apiKey: String): ResponseEntity<UserDto> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }
}