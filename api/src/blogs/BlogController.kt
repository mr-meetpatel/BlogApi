package blogs

import io.micronaut.http.HttpResponse
import utility.Response

interface BlogController {
    fun getAll(): Response<Any>

    fun createUserBlog(userId:Long,title:String,content:String):Response<Any>
    fun updateUserBlog(userId:Long,blogId:Long,title: String,content: String): Response<Any>
    fun deleteUserBlog(userId:Long,blogId:Long):Response<Any>
    fun fetchUserBlog(user_id:Long):Response<Any>

}
