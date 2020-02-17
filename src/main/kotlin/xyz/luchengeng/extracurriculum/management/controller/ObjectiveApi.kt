package xyz.luchengeng.extracurriculum.management.controller

import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import xyz.luchengeng.extracurriculum.management.dto.ObjectiveDto
import xyz.luchengeng.extracurriculum.management.dto.PageDto
import xyz.luchengeng.extracurriculum.management.entity.Objective

interface ObjectiveApi {

    @RequestMapping(value = ["/objective"], method = [RequestMethod.POST], consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun post(@RequestBody objective : Objective,@RequestHeader("x-api-key") apiKey : String) : ResponseEntity<Unit>

    @RequestMapping(value = ["/objective/{id}"], method = [RequestMethod.GET])
    fun get(@PathVariable("id")id : Long,@RequestHeader("x-api-key") apiKey : String) : ResponseEntity<Objective>

    @RequestMapping(value = ["/objective/page/{pageLen}/{no}"], method = [RequestMethod.GET])
    fun getPage(@PathVariable("no") pageNo : Long,@PathVariable("pageLen") pageLen : Long,@RequestHeader("x-api-key") apiKey : String) : ResponseEntity<PageDto<Objective>>

    @RequestMapping(value = ["/objective/{id}"], method = [RequestMethod.DELETE])
    fun del(@PathVariable("id")id : Long,@RequestHeader("x-api-key") apiKey : String) : ResponseEntity<Unit>

    @RequestMapping(value = ["/objective/{id}/content/{contentId}"], method = [RequestMethod.POST])
    fun appendContent(@PathVariable("id") id : Long,@PathVariable("contentId") contentId : Long,@RequestHeader("x-api-key") apiKey : String) : ResponseEntity<Unit>

    @RequestMapping(value = ["/objective/{id}/content/{contentId}"], method = [RequestMethod.DELETE])
    fun dropContent(@PathVariable("id") id : Long,@PathVariable("contentId") contentId : Long,@RequestHeader("x-api-key") apiKey : String) : ResponseEntity<Unit>
}