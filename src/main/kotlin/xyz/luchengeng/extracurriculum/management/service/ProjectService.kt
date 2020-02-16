package xyz.luchengeng.extracurriculum.management.service

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import xyz.luchengeng.extracurriculum.management.entity.Project
import xyz.luchengeng.extracurriculum.management.repository.ContentStore
import xyz.luchengeng.extracurriculum.management.repository.ProjectRepo

@Service
class ProjectService @Autowired constructor(projectRepo: ProjectRepo,val contentStore: ContentStore) : BaseEntryService<Project>(projectRepo) {
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
}