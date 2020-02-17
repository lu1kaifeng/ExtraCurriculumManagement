package xyz.luchengeng.extracurriculum.management.controller

import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import xyz.luchengeng.extracurriculum.management.entity.User

interface UserApi {
    @RequestMapping(value = ["/user"], method = [RequestMethod.POST], consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun postUser(@RequestBody user : User):ResponseEntity<Unit>

    @RequestMapping(value = ["/user/login"], method = [RequestMethod.GET], consumes = [MediaType.APPLICATION_JSON_VALUE])
    fun logIn(@RequestBody user : User):ResponseEntity<String>

    @RequestMapping(value = ["/user/{id}"], method = [RequestMethod.GET])
    fun getUser(@PathVariable("id") id : Long,@RequestHeader("x-api-key") apiKey : String) : ResponseEntity<User>
}