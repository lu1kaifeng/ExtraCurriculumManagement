package xyz.luchengeng.extracurriculum.management.entity

import org.springframework.http.MediaType
import javax.persistence.*

enum class Role{
    STUDENT,
    TEACHER,
    ADMIN
}

@Entity
data class User(@Id @GeneratedValue(strategy = GenerationType.AUTO)val id : Long?,
                var email : String,
                var password : String,
                val role : Role?,
                @OneToOne val content : Content?)

@Entity
data class Auth(@Id @GeneratedValue(strategy = GenerationType.AUTO) val id : Long?,
                val target : String,
                val role : Role?,
                @OneToOne val user :User?,
                val isBan : Boolean)

@Entity
data class Objective(@Id @GeneratedValue(strategy = GenerationType.AUTO) val id : Long?,
                     var title : String,
                     var description : String,
                     @OneToOne val owner : User,
                     @OneToOne val objectiveInfo : Content,
                     @OneToMany val contents : MutableList<Content>)

@Entity
data class Content(@Id @GeneratedValue(strategy = GenerationType.AUTO) val id : Long?,
                   val mime : MediaType?,
                   @OneToOne val owner : User)

@Entity
data class Project(@Id @GeneratedValue(strategy = GenerationType.AUTO) val id : Long?,
                   var title : String,
                   var description : String, @OneToOne var owner : User,
                   @OneToMany val participants : MutableList<User>,
                   @OneToOne val projectInfo : Content?,
                   @OneToMany val objectives : MutableList<Objective>,
                   @OneToMany val contents : MutableList<Content>)