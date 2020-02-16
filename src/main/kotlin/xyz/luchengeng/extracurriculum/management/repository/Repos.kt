package xyz.luchengeng.extracurriculum.management.repository

import io.minio.MinioClient
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.env.Environment
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.http.MediaType
import org.springframework.stereotype.Repository
import xyz.luchengeng.extracurriculum.management.entity.*
import xyz.luchengeng.extracurriculum.management.exception.NotFoundException
import java.awt.PageAttributes
import java.io.*


@Repository
interface UserRepo : JpaRepository<User,Long?>

@Repository
interface AuthRepo : JpaRepository<Auth,Long?>

@Repository
interface ObjectiveRepo : JpaRepository<Objective,Long?>

@Repository
interface ContentRepo : JpaRepository<Content,Long?>

@Repository
interface ProjectRepo : JpaRepository<Project,Long?>

@Repository
class ContentStore @Autowired constructor(private val contentRepo: ContentRepo, private val storageClient : MinioClient, env : Environment){
    private val bucket = env.getProperty("min.io.bucket")
    init{
        if(!storageClient.bucketExists(bucket)) storageClient.makeBucket(bucket)
    }
    fun <T> deserialize(id : Long) : T{
        return this.deserialize(contentRepo.findById(id).orElseThrow { throw NotFoundException() })
    }
    fun <T> deserialize(content : Content) : T{
         return ObjectInputStream(storageClient.getObject(bucket,content.id?.toString())).readObject() as T
    }
    fun delete(id : Long){
        contentRepo.deleteById(id)
        storageClient.removeObject(bucket,id.toString())
    }
    fun findHandleById(id : Long) : Content{
        return contentRepo.findById(id).orElseThrow { throw NotFoundException() }
    }
    fun delete(content : Content){
        contentRepo.delete(content)
        storageClient.removeObject(bucket,content.id.toString())
    }
    fun <T> serialize(user : User,content : T,mime : MediaType) : Content {
        val contentEntry = contentRepo.save(Content(null, mime, user))
        val baos = ByteArrayOutputStream()
        val oos = ObjectOutputStream(baos)
        oos.writeObject(content)
        oos.flush()
        oos.close()
        val inputStream = ByteArrayInputStream(baos.toByteArray())
        storageClient.putObject(bucket,contentEntry.id?.toString(),inputStream,contentEntry.mime?.toString())
        return contentEntry
    }
}
