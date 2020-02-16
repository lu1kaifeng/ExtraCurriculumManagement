package xyz.luchengeng.extracurriculum.management.service

import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.data.jpa.repository.JpaRepository
import xyz.luchengeng.extracurriculum.management.exception.NotFoundException

abstract class BaseEntryService <T> constructor(private val repo : JpaRepository<T,Long?>){
    fun new(entry : T) : T = repo.save(entry)
    fun del(entry : T) = repo.delete(entry)
    fun get(id : Long) : T = repo.findById(id).orElseThrow { throw NotFoundException() }
    fun get(page : Int, pageSize : Int, sort: Sort = Sort.unsorted()) : Page<T> {
        return repo.findAll(PageRequest.of(page,pageSize,sort))
    }
    abstract fun appendContent(id : Long,contentId : Long)
    abstract fun dropContent(id : Long,contentId : Long)
}