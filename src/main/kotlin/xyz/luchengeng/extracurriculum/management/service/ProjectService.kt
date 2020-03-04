package xyz.luchengeng.extracurriculum.management.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.Sort
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import xyz.luchengeng.extracurriculum.management.entity.Project
import xyz.luchengeng.extracurriculum.management.entity.User
import xyz.luchengeng.extracurriculum.management.exception.NotFoundException
import xyz.luchengeng.extracurriculum.management.repository.ContentStore
import xyz.luchengeng.extracurriculum.management.repository.ObjectiveRepo
import xyz.luchengeng.extracurriculum.management.repository.ProjectRepo

@Service
class ProjectService @Autowired constructor(val projectRepo: ProjectRepo,val contentStore: ContentStore,val objectiveRepo: ObjectiveRepo) : BaseEntryService<Project>(projectRepo) {
    override fun appendContent(id: Long, contentId: Long) =
        contentStore.findHandleById(contentId).run {
            val project = super.get(id)
            project.contents.add(this)
            super.save(project)
            Unit
        }


    override fun dropContent(id: Long, contentId: Long) =
        contentStore.findHandleById(contentId).run{
            val project = super.get(id)
            project.contents.remove(this)
            super.save(project)
            Unit
        }


    fun participate(id : Long,user : User)=
        projectRepo.save(projectRepo.findByIdOrNull(id)?.apply{
            this.participants.add(user)
        }?:throw NotFoundException())

    override fun get(id: Long): Project =
            super.get(id).apply {
            this.owner?.password = ""
            this.participants.forEach {
                it.password = ""
            }
            this.objectives.forEach {
                it.owner?.password = ""
            }
        }


    override fun get(page: Int, pageSize: Int, sort: Sort): Page<Project> =
        super.get(page, pageSize, sort).apply {
            this.content.forEach { project ->
                project.owner?.password = ""
                project.participants.forEach {
                    it.password = ""
                }
                project.objectives.forEach{
                    it.owner?.password = ""
                }
            }
        }

    fun leave(id : Long,user : User) =
        projectRepo.save(projectRepo.findByIdOrNull(id)?.apply {
            this.participants.filter {
                it.id != user.id
            }
        }?:throw NotFoundException())


    fun appendObjective(id: Long, objectiveId: Long) =
        objectiveRepo.findByIdOrNull(objectiveId)?.run{
            val project = super.get(id)
            project.objectives.add(this)
            super.save(project)
        }?: throw NotFoundException()

    fun dropObjective(id: Long, objectiveId: Long) =
        objectiveRepo.findByIdOrNull(objectiveId)?.run {
            val project = super.get(id)
            project.objectives.remove(this)
            super.save(project)
        }?: throw NotFoundException()

}