package xyz.luchengeng.extracurriculum.management.controller

import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import xyz.luchengeng.extracurriculum.management.dto.PageDto
import xyz.luchengeng.extracurriculum.management.dto.ProjectDto
import xyz.luchengeng.extracurriculum.management.entity.Project

@Controller
class ProjectApiController : ProjectApi {
    override fun post(project: Project, apiKey: String): ResponseEntity<Unit> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun get(id: Long, apiKey: String): ResponseEntity<ProjectDto> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getPage(pageNo: Long, pageLen: Long, apiKey: String): PageDto<Project> {
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