package xyz.luchengeng.extracurriculum.management.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.data.domain.Page
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service
import xyz.luchengeng.extracurriculum.management.entity.Objective
import xyz.luchengeng.extracurriculum.management.repository.ContentStore
import xyz.luchengeng.extracurriculum.management.repository.ObjectiveRepo

@Service
class ObjectiveService @Autowired constructor(objectiveRepo: ObjectiveRepo,val contentStore: ContentStore) : BaseEntryService<Objective>(objectiveRepo){

    override fun get(id: Long): Objective =
         super.get(id).apply {
            this.owner?.password = ""
        }


    override fun get(page: Int, pageSize: Int, sort: Sort): Page<Objective> =
        super.get(page, pageSize, sort).apply {
            this.content.forEach {
                it.owner?.password = ""
            }
        }

    override fun appendContent(id: Long, contentId: Long) =
        contentStore.findHandleById(contentId).run {
            val objective = super.get(id)
            objective.contents.add(this)
            super.save(objective)
            Unit
        }


    override fun dropContent(id: Long, contentId: Long) =
        contentStore.findHandleById(contentId).run {
            val objective = super.get(id)
            objective.contents.remove(this)
            super.save(objective)
            Unit
        }
}