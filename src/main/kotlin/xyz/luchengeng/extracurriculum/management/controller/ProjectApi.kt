package xyz.luchengeng.extracurriculum.management.controller

import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import xyz.luchengeng.extracurriculum.management.dto.ObjectiveDto
import xyz.luchengeng.extracurriculum.management.dto.PageDto
import xyz.luchengeng.extracurriculum.management.dto.ProjectDto
import xyz.luchengeng.extracurriculum.management.entity.Objective
import xyz.luchengeng.extracurriculum.management.entity.Project

interface ProjectApi {

    @RequestMapping(value = ["/project"], method = [RequestMethod.POST], consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun post(@RequestBody project : Project,@RequestHeader("x-api-key") apiKey : String) : ResponseEntity<Unit>

    @RequestMapping(value = ["/project/{id}"], method = [RequestMethod.GET], consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun get(@PathVariable("id")id : Long,@RequestHeader("x-api-key") apiKey : String) : ResponseEntity<ProjectDto>

    @RequestMapping(value = ["/project/page/{pageLen}/{no}"], method = [RequestMethod.GET], consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun getPage(@PathVariable("no") pageNo : Long, @PathVariable("pageLen") pageLen : Long,@RequestHeader("x-api-key") apiKey : String) : PageDto<Project>

    @RequestMapping(value = ["/project/{id}"], method = [RequestMethod.DELETE], consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun del(@PathVariable("id")id : Long,@RequestHeader("x-api-key") apiKey : String) : ResponseEntity<Unit>

    @RequestMapping(value = ["/project/{id}/content/{contentId}"], method = [RequestMethod.POST], consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun appendContent(@PathVariable("id") id : Long, @PathVariable("contentId") contentId : Long,@RequestHeader("x-api-key") apiKey : String) : ResponseEntity<Unit>

    @RequestMapping(value = ["/project/{id}/content/{contentId}"], method = [RequestMethod.DELETE], consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun dropContent(@PathVariable("id") id : Long, @PathVariable("contentId") contentId : Long,@RequestHeader("x-api-key") apiKey : String) : ResponseEntity<Unit>
}