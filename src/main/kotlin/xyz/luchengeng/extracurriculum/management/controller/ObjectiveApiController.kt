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
        val user = securityService.auth("objective::post",apiKey)
        objectiveService.new(objective)
        return ResponseEntity.ok(Unit)
    }

    override fun get(id: Long, apiKey: String): ResponseEntity<ObjectiveDto> {
        val user = securityService.auth("objective::get",apiKey)
        return ResponseEntity.ok(ObjectiveDto(objectiveService.get(id)))
    }

    override fun getPage(pageNo: Long, pageLen: Long, apiKey: String): ResponseEntity<PageDto<Objective>> {
        val user = securityService.auth("objective::page",apiKey)
        return ResponseEntity.ok(PageDto(objectiveService.get(pageNo.toInt(),pageLen.toInt())))
    }

    override fun del(id: Long, apiKey: String): ResponseEntity<Unit> {
        val user = securityService.auth("objective::del",apiKey)
        objectiveService.del(objectiveService.get(id))
        return ResponseEntity.ok(Unit)
    }

    override fun appendContent(id: Long, contentId: Long, apiKey: String): ResponseEntity<Unit> {
        val user = securityService.auth("objective::content::append",apiKey)
        objectiveService.appendContent(id,contentId)
        return ResponseEntity.ok(Unit)
    }

    override fun dropContent(id: Long, contentId: Long, apiKey: String): ResponseEntity<Unit> {
        val user = securityService.auth("objective::content::drop",apiKey)
        objectiveService.dropContent(id,contentId)
        return ResponseEntity.ok(Unit)
    }

}