package blogs

import io.micronaut.http.HttpResponse
import utility.Response

interface BlogController {
    fun getAll(): Response<Any>
    fun createUser(user_name: String):Response<Any>
    fun createUserBlog(userId:Long,title:String,content:String):Response<Any>
    fun updateUserBlog(userId:Long,blogId:Long,title: String,content: String): Response<Any>
    fun deleteUserBlog(userId:Long,blogId:Long):Response<Any>
    fun fetchUserBlog(user_id:Long):Response<Any>
    fun getAllUsers():Response<Any>
    fun getUser(userId: Long):Response<Any>
}
