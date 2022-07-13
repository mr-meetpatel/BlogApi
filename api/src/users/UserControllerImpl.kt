package users


import blogs.core.entity.Message
import blogs.core.services.UserService
import utility.Response
import utility.ResponseType
import javax.inject.Singleton

@Singleton
class UserControllerImpl (
    private val userService: UserService
    ):UserController{
    override fun createUser(user_name: String): Response<Any> {
        val response =userService.createUser(user_name)
        return Response(ResponseType.SUCCESS, body = response)
    }

    override fun getAllUsers(): Response<Any> {
        val response=userService.getAllUsers()
        if(response.isEmpty()){
            val error= mutableListOf<Message>()
            error.add(Message("User Not Found"))
            return Response(ResponseType.NOT_FOUND,body=error)
        }else{
            return Response(ResponseType.SUCCESS,body=response)
        }

    }

    override fun getUser(userId: Long): Response<Any> {
        val response=userService.getUser(userId)
        if(response.isEmpty()){
            val error= mutableListOf<Message>()
            error.add(Message("User Not Found"))
            return Response(ResponseType.NOT_FOUND,body=error)
        }else{
            return Response(ResponseType.SUCCESS,body=response)
        }
    }

}

