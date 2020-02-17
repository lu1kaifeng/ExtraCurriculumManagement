package xyz.luchengeng.extracurriculum.management.controller

import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import xyz.luchengeng.extracurriculum.management.entity.Content
import xyz.luchengeng.extracurriculum.management.repository.ContentStore
import xyz.luchengeng.extracurriculum.management.service.SecurityService

class ContentApiController constructor(private val contentStore: ContentStore,private val securityService: SecurityService) : ContentApi {

    override fun post(bytes: Array<Byte>, apiKey: String, contentType: String): ResponseEntity<Content> {
        val user = securityService.auth("content::post",apiKey)
        return ResponseEntity.ok(contentStore.serialize(user,bytes, MediaType.parseMediaType(contentType)))
    }

    override fun get(id: Long, apiKey: String): ResponseEntity<Array<Byte>> {
        val user = securityService.auth("content::get",apiKey)
        return ResponseEntity.ok()
                .header("Content-Type",contentStore.findHandleById(id).mime.toString())
                .body(contentStore.deserialize<Array<Byte>>(id))
    }

    override fun del(id: Long, apiKey: String): ResponseEntity<Unit> {
        val user = securityService.auth("content::del",apiKey)
        contentStore.delete(id)
        return ResponseEntity.ok(Unit)
    }
}