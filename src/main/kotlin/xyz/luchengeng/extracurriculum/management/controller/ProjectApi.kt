package xyz.luchengeng.extracurriculum.management.controller

import org.springframework.data.domain.Page
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import xyz.luchengeng.extracurriculum.management.entity.Objective
import xyz.luchengeng.extracurriculum.management.entity.Project
import xyz.luchengeng.extracurriculum.management.entity.User

interface ProjectApi {

    @RequestMapping(value = ["/project"], method = [RequestMethod.POST], consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun post(@RequestBody project : Project,@RequestHeader("x-api-key") apiKey : String) : ResponseEntity<Project>

    @RequestMapping(value = ["/project/{id}"], method = [RequestMethod.GET])
    fun get(@PathVariable("id")id : Long,@RequestHeader("x-api-key") apiKey : String) : ResponseEntity<Project>

    @RequestMapping(value = ["/project/page/{pageLen}/{no}"], method = [RequestMethod.GET])
    fun getPage(@PathVariable("no") pageNo : Long, @PathVariable("pageLen") pageLen : Long,@RequestHeader("x-api-key") apiKey : String) : ResponseEntity<Page<Project>>

    @RequestMapping(value = ["/project/{id}"], method = [RequestMethod.DELETE])
    fun del(@PathVariable("id")id : Long,@RequestHeader("x-api-key") apiKey : String) : ResponseEntity<Unit>

    @RequestMapping(value = ["/project/{id}/content/{contentId}"], method = [RequestMethod.POST])
    fun appendContent(@PathVariable("id") id : Long, @PathVariable("contentId") contentId : Long,@RequestHeader("x-api-key") apiKey : String) : ResponseEntity<Unit>

    @RequestMapping(value = ["/project/{id}/content/{contentId}"], method = [RequestMethod.DELETE])
    fun dropContent(@PathVariable("id") id : Long, @PathVariable("contentId") contentId : Long,@RequestHeader("x-api-key") apiKey : String) : ResponseEntity<Unit>

    @RequestMapping(value = ["/project/{id}/participation"], method = [RequestMethod.POST])
    fun participate(@PathVariable("id") id : Long, @RequestHeader("x-api-key") apiKey : String) : ResponseEntity<Unit>

    @RequestMapping(value = ["/project/{id}/participation"], method = [RequestMethod.DELETE])
    fun leave(@PathVariable("id") id : Long, @RequestHeader("x-api-key") apiKey : String) : ResponseEntity<Unit>

    @RequestMapping(value = ["/project/{id}/objective/{objectiveId}"], method = [RequestMethod.POST])
    fun appendObjective(@PathVariable("id") id : Long, @PathVariable("objectiveId") objectiveId : Long, @RequestHeader("x-api-key") apiKey : String) : ResponseEntity<Unit>

    @RequestMapping(value = ["/project/{id}/objective/{objectiveId}"], method = [RequestMethod.DELETE])
    fun dropObjective(@PathVariable("id") id : Long, @PathVariable("objectiveId") objectiveId : Long, @RequestHeader("x-api-key") apiKey : String) : ResponseEntity<Unit>
}