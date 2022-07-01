package blogs

import blogs.core.services.BlogService
import io.micronaut.http.HttpResponse
import utility.Response
import utility.ResponseType
import javax.inject.Singleton

@Singleton
class BlogControllerImpl(
    private val blogService: BlogService
) : BlogController {
    override fun getAll(): Response<Any> {
        val response = blogService.getAll()
        return Response(ResponseType.SUCCESS, body = response)
    }
    override fun createUser(user_name: String): Response<Any> {
        val response =blogService.createUser(user_name)
        return Response(ResponseType.SUCCESS, body = response)

    }
    override fun createUserBlog(userId: Long, title: String, content: String): Response<Any> {
        val response =blogService.createUserBlog(userId,title,content)
        if(response.isNotEmpty()){

            return Response(ResponseType.SUCCESS, body = response)
        }
        else{
            return Response(ResponseType.NOT_FOUND, body = "{\"Error\":\"User Not Found\"}")
        }
    }

    override fun updateUserBlog(userId: Long, blogId: Long, title: String, content: String): Response<Any> {
        val response=blogService.updateUserBlog(userId,blogId,title,content)
        if(response.isEmpty()) {
            return Response(ResponseType.NOT_FOUND, body = "{\"Error\":\"No Data Found\"}")
        }

            return Response(ResponseType.SUCCESS,body="{\"Success\":\"Blog Updated Successfully\"}")

    }

    override fun deleteUserBlog(userId: Long, blogId: Long): Response<Any> {
        val response=blogService.deleteUserBlog(userId,blogId)
        if(response.isEmpty()){
            return Response(ResponseType.SUCCESS,body="{\"Success\":\"Blog Deleted Successfully\"}")
        }else{
            return Response(ResponseType.NOT_FOUND,body="{\"Error\":\"No Data Found\"}")
        }

    }
}
