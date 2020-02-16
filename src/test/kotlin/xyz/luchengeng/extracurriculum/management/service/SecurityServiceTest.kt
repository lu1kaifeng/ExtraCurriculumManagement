package xyz.luchengeng.extracurriculum.management.service

import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.BDDMockito.given
import org.mockito.InjectMocks
import org.mockito.Mock

import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import org.springframework.boot.test.context.SpringBootTest
import org.springframework.core.env.Environment
import org.springframework.data.domain.Example
import xyz.luchengeng.extracurriculum.management.entity.Auth
import xyz.luchengeng.extracurriculum.management.entity.Role
import xyz.luchengeng.extracurriculum.management.entity.User
import xyz.luchengeng.extracurriculum.management.exception.NotAuthorizedException
import xyz.luchengeng.extracurriculum.management.repository.AuthRepo
import xyz.luchengeng.extracurriculum.management.repository.UserRepo
import java.util.*

@SpringBootTest
@RunWith(MockitoJUnitRunner::class)
class SecurityServiceTest {
    @Mock
    private lateinit var userRepo: UserRepo
    @Mock
    private lateinit var authRepo: AuthRepo
    @Mock
    private lateinit var env : Environment
    private lateinit var securityService : SecurityService

    @Before
    fun setUp(){
        given(env.getProperty("token.secret")).willReturn("secret")
        given(env.getProperty("token.issuer")).willReturn("henu")
        given(userRepo.findOne(Example.of(User(null,"mock","mock",null,null)))).willReturn(Optional.of(User(1,"mock","mock", Role.ADMIN,null)))
        given(userRepo.findById(1)).willReturn(Optional.of(User(1,"mock","mock", Role.ADMIN,null)))
        given(authRepo.findAll(Example.of(Auth(null,"one::all",null,null,false)))).willReturn(mutableListOf(Auth(1,"one::all",null,null,false)))
        given(authRepo.findAll(Example.of(Auth(null,"two::all",null,null,true)))).willReturn(mutableListOf(Auth(2,"two::all",null,null,true)))
        securityService = SecurityService(userRepo,authRepo,env)
    }

    @Test
    fun test(){
        val token = securityService.logIn("mock","mock")
        securityService.auth("one::all",token)
        try {
            securityService.auth("two::all", token)
        }catch (e : NotAuthorizedException){
            return
        }
        //exception should have been thrown
        assert(false)
    }
}