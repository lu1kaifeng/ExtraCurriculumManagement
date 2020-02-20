package xyz.luchengeng.extracurriculum.management.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.Sort
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import xyz.luchengeng.extracurriculum.management.entity.Objective
import xyz.luchengeng.extracurriculum.management.entity.Project
import xyz.luchengeng.extracurriculum.management.entity.User
import xyz.luchengeng.extracurriculum.management.exception.NotFoundException
import xyz.luchengeng.extracurriculum.management.repository.ContentStore
import xyz.luchengeng.extracurriculum.management.repository.ObjectiveRepo
import xyz.luchengeng.extracurriculum.management.repository.ProjectRepo

@Service
class ProjectService @Autowired constructor(val projectRepo: ProjectRepo,val contentStore: ContentStore,val objectiveRepo: ObjectiveRepo) : BaseEntryService<Project>(projectRepo) {
    override fun appendContent(id: Long, contentId: Long) {
        val content = contentStore.findHandleById(contentId)
        val project = super.get(id)
        project.contents.add(content)
        super.new(project)
    }

    override fun dropContent(id: Long, contentId: Long) {
        val content = contentStore.findHandleById(contentId)
        val project = super.get(id)
        project.contents.remove(content)
        super.new(project)
    }

    fun participate(id : Long,user : User){
        val projectOpt = projectRepo.findById(id)
        if(!projectOpt.isPresent) throw xyz.luchengeng.extracurriculum.management.exception.NotFoundException()
        projectOpt.get().participants.add(user)
        projectRepo.save(projectOpt.get())
    }

    override fun get(id: Long): Project {
        val list =  super.get(id)
        list.owner.password = ""
        list.participants.forEach {
            it.password = ""
        }
        list.objectives.forEach {
            it.owner.password = ""
        }
        return list
    }

    override fun get(page: Int, pageSize: Int, sort: Sort): Page<Project> {
        val pageObj =  super.get(page, pageSize, sort)
        pageObj.content.forEach { project ->
            project.owner.password = ""
            project.participants.forEach {
                it.password = ""
            }
            project.objectives.forEach{
                it.owner.password = ""
            }
        }
        return pageObj
    }

    fun leave(id : Long,user : User){
        val projectOpt = projectRepo.findById(id)
        if(!projectOpt.isPresent) throw xyz.luchengeng.extracurriculum.management.exception.NotFoundException()
        projectOpt.get().participants.filter {
            it.id != user.id
        }
        projectRepo.save(projectOpt.get())
    }

    fun appendObjective(id: Long, objectiveId: Long){
        val objective = objectiveRepo.findByIdOrNull(objectiveId)?: throw NotFoundException()
        val project = super.get(id)
        project.objectives.add(objective as Objective)
        super.new(project)
    }

    fun dropObjective(id: Long, objectiveId: Long){
        val objective = objectiveRepo.findByIdOrNull(objectiveId)?: throw NotFoundException()
        val project = super.get(id)
        project.objectives.remove(objective)
        super.new(project)
    }

}