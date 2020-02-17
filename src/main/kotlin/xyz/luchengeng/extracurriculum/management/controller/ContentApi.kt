package xyz.luchengeng.extracurriculum.management.controller

import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import xyz.luchengeng.extracurriculum.management.entity.Content
import xyz.luchengeng.extracurriculum.management.entity.Objective

interface ContentApi {
    @RequestMapping(value = ["/content"], method = [RequestMethod.POST])
    fun post(@RequestBody b64 : String, @RequestHeader("x-api-key") apiKey : String,@RequestHeader("mime") mime : String) : ResponseEntity<Content>

    @RequestMapping(value = ["/content/{id}"], method = [RequestMethod.GET])
    fun get(@PathVariable("id") id : Long, @RequestHeader("x-api-key") apiKey : String) : ResponseEntity<Array<Byte>>

    @RequestMapping(value = ["/content/{id}"], method = [RequestMethod.DELETE])
    fun del(@PathVariable("id") id : Long, @RequestHeader("x-api-key") apiKey : String) : ResponseEntity<Unit>
}