package xyz.luchengeng.extracurriculum.management.service

import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.repository.findByIdOrNull
import xyz.luchengeng.extracurriculum.management.exception.NotFoundException

abstract class BaseEntryService <T> constructor(private val repo : JpaRepository<T,Long?>){
    fun save(entry : T) : T = repo.save(entry)
    fun del(entry : T) = repo.delete(entry)
    open fun get(id : Long) : T = repo.findByIdOrNull(id)?: throw NotFoundException()
    open fun get(page : Int, pageSize : Int, sort: Sort = Sort.unsorted()) : Page<T> =
            repo.findAll(PageRequest.of(page,pageSize,sort))
    abstract fun appendContent(id : Long,contentId : Long)
    abstract fun dropContent(id : Long,contentId : Long)
}