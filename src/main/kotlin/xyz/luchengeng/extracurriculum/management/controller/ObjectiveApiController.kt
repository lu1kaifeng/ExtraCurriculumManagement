package xyz.luchengeng.extracurriculum.management.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import xyz.luchengeng.extracurriculum.management.dto.ObjectiveDto
import xyz.luchengeng.extracurriculum.management.dto.PageDto
import xyz.luchengeng.extracurriculum.management.entity.Objective
import xyz.luchengeng.extracurriculum.management.service.ObjectiveService
import xyz.luchengeng.extracurriculum.management.service.SecurityService

@Controller
class ObjectiveApiController @Autowired constructor(private val objectiveService: ObjectiveService,private val securityService: SecurityService) : ObjectiveApi {
    override fun post(objective: Objective, apiKey: String): ResponseEntity<Unit> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun get(id: Long, apiKey: String): ResponseEntity<ObjectiveDto> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getPage(pageNo: Long, pageLen: Long, apiKey: String): PageDto<Objective> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun del(id: Long, apiKey: String): ResponseEntity<Unit> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun appendContent(id: Long, contentId: Long, apiKey: String): ResponseEntity<Unit> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun dropContent(id: Long, contentId: Long, apiKey: String): ResponseEntity<Unit> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

}