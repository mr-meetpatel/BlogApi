package users

import blogs.BlogController
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.*
import javax.inject.Inject

@Controller("/api/v1/users")
class CreateUserApi @Inject constructor(
    private val userController: UserController
){
    @Post("/")
    //{"userName":"name"}
    fun createUser(userName:String): HttpResponse<Any> {
        val user = userController.createUser(userName)
        return user.getHttpResponse()
    }
}

@Controller("/api/v1/users")
class fetchAllUserApi @Inject constructor(
    private val userController: UserController
){
    @Get("/")
    fun fetchAllUser():HttpResponse<Any>{
        val user =userController.getAllUsers()
        return user.getHttpResponse()
    }
}

@Controller("/api/v1/user/{userId}")
class fetchUserApi @Inject constructor(
    private val userController: UserController
){
    @Get("/")
    fun fetchUser(userId: Long):HttpResponse<Any>{
        val user =userController.getUser(userId)
        return user.getHttpResponse()
    }
}
