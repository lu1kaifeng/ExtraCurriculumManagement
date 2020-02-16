package xyz.luchengeng.extracurriculum.management.config

import io.minio.MinioClient
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.core.env.Environment

@Configuration
class ObjectStorageConfig @Autowired constructor(val env : Environment) {
    @Bean
    fun storageClient() : MinioClient{
        return MinioClient(env.getProperty("min.io.host"), env.getProperty("min.io.accessKey"), env.getProperty("min.io.secretKey"))
    }
}