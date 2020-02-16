package xyz.luchengeng.extracurriculum.management.controller

import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RequestMethod
import xyz.luchengeng.extracurriculum.management.dto.ObjectiveDto
import xyz.luchengeng.extracurriculum.management.dto.PageDto
import xyz.luchengeng.extracurriculum.management.entity.Objective

interface ObjectiveApi {

    @RequestMapping(value = ["/objective"], method = [RequestMethod.POST], consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun post(@RequestBody objective : Objective) : ResponseEntity<Unit>

    @RequestMapping(value = ["/objective/{id}"], method = [RequestMethod.GET], consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun get(@PathVariable("id")id : Long) : ResponseEntity<ObjectiveDto>

    @RequestMapping(value = ["/objective/page/{pageLen}/{no}"], method = [RequestMethod.GET], consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun getPage(@PathVariable("no") pageNo : Long,@PathVariable("pageLen") pageLen : Long) : PageDto<Objective>

    @RequestMapping(value = ["/objective/{id}"], method = [RequestMethod.DELETE], consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun del(@PathVariable("id")id : Long) : ResponseEntity<Unit>

    @RequestMapping(value = ["/objective/{id}/content/{contentId}"], method = [RequestMethod.POST], consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun appendContent(@PathVariable("id") id : Long,@PathVariable("contentId") contentId : Long) : ResponseEntity<Unit>

    @RequestMapping(value = ["/objective/{id}/content/{contentId}"], method = [RequestMethod.DELETE], consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun dropContent(@PathVariable("id") id : Long,@PathVariable("contentId") contentId : Long) : ResponseEntity<Unit>
}