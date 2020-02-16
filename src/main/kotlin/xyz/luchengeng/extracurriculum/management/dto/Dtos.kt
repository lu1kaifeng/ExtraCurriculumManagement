package xyz.luchengeng.extracurriculum.management.dto

import org.springframework.data.domain.Page
import xyz.luchengeng.extracurriculum.management.entity.*

inline class UserDto(private val user: User){
    val email : String
        get() = user.email
    val role : Role
        get() = user.role as Role
    val userObjId : Long?
        get() = user.content?.id
}

inline class ObjectiveDto(private val objective: Objective){
    val title : String
        get() = objective.title
    val description : String
        get() = objective.description
    val ownerId : Long
        get() = objective.owner.id as Long
    val contentList : List<Long>
    get() = objective.contents.map {
        it.id as Long
    }
}

inline class ProjectDto(private val project: Project){
    val title : String
        get() = project.title
    val description : String
        get() = project.description
    val ownerId : Long
        get() = project.owner.id as Long
    val participantList : List<Long>
        get() = project.participants.map {
            it.id as Long
        }
    val contentList : List<Long>
        get() = project.contents.map {
            it.id as Long
        }
}

inline class PageDto<T>(private val page : Page<T>){
    val numElement : Int
        get()=page.numberOfElements
    val pageNo : Int
        get() = page.number
    val totalPage : Int
        get() = page.totalPages
    val content : MutableList<T>
        get()=page.content
}