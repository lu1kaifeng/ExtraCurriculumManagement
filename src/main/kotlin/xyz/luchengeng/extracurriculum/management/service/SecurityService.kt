package xyz.luchengeng.extracurriculum.management.service

import com.auth0.jwt.JWT
import com.auth0.jwt.JWTVerifier
import com.auth0.jwt.algorithms.Algorithm
import com.auth0.jwt.exceptions.JWTCreationException
import com.auth0.jwt.exceptions.JWTVerificationException
import com.auth0.jwt.interfaces.DecodedJWT
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.env.Environment
import org.springframework.data.domain.Example
import org.springframework.stereotype.Service
import xyz.luchengeng.extracurriculum.management.entity.Auth
import xyz.luchengeng.extracurriculum.management.entity.User
import xyz.luchengeng.extracurriculum.management.exception.NotAuthorizedException
import xyz.luchengeng.extracurriculum.management.exception.NotFoundException
import xyz.luchengeng.extracurriculum.management.repository.AuthRepo
import xyz.luchengeng.extracurriculum.management.repository.UserRepo
import java.time.Instant
import java.util.*


@Service
class SecurityService @Autowired constructor(val userRepo: UserRepo,val authRepo: AuthRepo,env : Environment) {

    private val secret = env.getProperty("token.secret")

    private val issuer = env.getProperty("token.issuer")

    fun newUser(user : User) : User = userRepo.save(user)

    fun getUser(id : Long) : User{
        val userOpt = userRepo.findById(id)
        if(userOpt.isPresent){
            return userOpt.get()
        }else{
            throw NotFoundException()
        }
    }

    fun newAuth(auth: Auth) : Auth = authRepo.save(auth)

    fun delAuth(id : Long) : Unit = authRepo.deleteById(id)

    fun getAuth() : MutableList<Auth> = authRepo.findAll()

    fun auth(target : String,user : User) : Boolean{
        authRepo.findAll(Example.of(Auth(null,target,null,null,true))).map {
            if(it.role == null && it.user == null) return false
        }
        authRepo.findAll(Example.of(Auth(null,target,null,null,false))).map {
            if(it.role == null && it.user == null) return true
        }
        if(authRepo.findOne(Example.of(Auth(null,target,user.role,null,false))).isPresent){
            if(!authRepo.findOne(Example.of(Auth(null,target,null,user,true))).isPresent) return true
        }
        return false
    }

    fun auth(target : String,token : String) : User{
        try {
            val algorithm = Algorithm.HMAC256(this.secret)
            val verifier: JWTVerifier = JWT.require(algorithm)
                    .withIssuer(this.issuer)
                    .build() //Reusable verifier instance
            val jwt: DecodedJWT = verifier.verify(token)
            val user = userRepo.findById(jwt.subject.toLong()).orElseThrow{throw NotAuthorizedException()}
            if(!this.auth(target,user)) throw NotAuthorizedException()
            return user
        } catch (exception: JWTVerificationException) { //Invalid signature/claims
            throw NotAuthorizedException()
        }
    }

    fun logIn(email : String,password : String) : String {
        val user = userRepo.findOne(Example.of(User(null,email,password,null,null))).orElseThrow { throw NotAuthorizedException() }
        try {
            val algorithm: Algorithm = Algorithm.HMAC256(this.secret)
            return JWT.create()
                    .withIssuer(this.issuer)
                    .withIssuedAt(Date.from(Instant.now()))
                    .withSubject(user.id.toString())
                    .sign(algorithm)
        } catch (exception: JWTCreationException) { //Invalid Signing configuration / Couldn't convert Claims.
            throw NotAuthorizedException()
        }
    }
}