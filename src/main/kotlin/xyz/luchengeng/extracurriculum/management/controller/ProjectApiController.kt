package xyz.luchengeng.extracurriculum.management.controller

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import xyz.luchengeng.extracurriculum.management.dto.PageDto
import xyz.luchengeng.extracurriculum.management.dto.ProjectDto
import xyz.luchengeng.extracurriculum.management.entity.Project
import xyz.luchengeng.extracurriculum.management.service.ProjectService
import xyz.luchengeng.extracurriculum.management.service.SecurityService

@Controller
class ProjectApiController @Autowired constructor(private val securityService: SecurityService,private val projectService: ProjectService) : ProjectApi {
    override fun post(project: Project, apiKey: String): ResponseEntity<Project> {
        val user = securityService.auth("project::post",apiKey)
        project.owner = user
        return ResponseEntity.ok(projectService.new(project))
    }

    override fun get(id: Long, apiKey: String): ResponseEntity<Project> {
        val user = securityService.auth("project::get",apiKey)
        return ResponseEntity.ok(projectService.get(id))
    }

    override fun getPage(pageNo: Long, pageLen: Long, apiKey: String): ResponseEntity<PageDto<Project>> {
        val user = securityService.auth("project::page",apiKey)
        return ResponseEntity.ok(PageDto(projectService.get(pageNo.toInt(),pageLen.toInt())))
    }

    override fun del(id: Long, apiKey: String): ResponseEntity<Unit> {
        val user = securityService.auth("project::del",apiKey)
        return ResponseEntity.ok(projectService.del(projectService.get(id)))
    }

    override fun appendContent(id: Long, contentId: Long, apiKey: String): ResponseEntity<Unit> {
        val user = securityService.auth("project::content::append",apiKey)
        projectService.appendContent(id,contentId)
        return ResponseEntity.ok(Unit)
    }

    override fun dropContent(id: Long, contentId: Long, apiKey: String): ResponseEntity<Unit> {
        val user = securityService.auth("project::content::drop",apiKey)
        projectService.dropContent(id,contentId)
        return ResponseEntity.ok(Unit)
    }

}