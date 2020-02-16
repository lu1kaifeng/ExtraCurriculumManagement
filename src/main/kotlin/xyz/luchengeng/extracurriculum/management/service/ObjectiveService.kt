package xyz.luchengeng.extracurriculum.management.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import xyz.luchengeng.extracurriculum.management.entity.Objective
import xyz.luchengeng.extracurriculum.management.repository.ContentStore
import xyz.luchengeng.extracurriculum.management.repository.ObjectiveRepo

@Service
class ObjectiveService @Autowired constructor(objectiveRepo: ObjectiveRepo,val contentStore: ContentStore) : BaseEntryService<Objective>(objectiveRepo){
    override fun appendContent(id: Long, contentId: Long) {
        val content = contentStore.findHandleById(contentId)
        val objective = super.get(id)
        objective.contents.add(content)
        super.new(objective)
    }

    override fun dropContent(id: Long, contentId: Long) {
        val content = contentStore.findHandleById(contentId)
        val objective = super.get(id)
        objective.contents.remove(content)
        super.new(objective)
    }

}