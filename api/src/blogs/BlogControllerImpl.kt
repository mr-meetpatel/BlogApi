package blogs

import blogs.core.entity.Message
import blogs.core.services.BlogService
import utility.Response
import utility.ResponseType
import javax.inject.Singleton

@Singleton
class BlogControllerImpl(
    private val blogService: BlogService
) : BlogController {
    override fun getAll(): Response<Any> {
        val response = blogService.getAll()
        if(response.isEmpty()){
            val error= mutableListOf<Message>()
            error.add(Message("Blog Not Found"))
            return Response(ResponseType.NOT_FOUND, body = error)
        }

        return Response(ResponseType.SUCCESS, body = response)
    }

    override fun createUserBlog(userId: Long, title: String, content: String): Response<Any> {
        val response =blogService.createUserBlog(userId,title,content)
        if(response.isNotEmpty()){

            return Response(ResponseType.SUCCESS, body = response)
        }
        else{
            val error= mutableListOf<Message>()
            error.add(Message("User Not Found"))
            return Response(ResponseType.NOT_FOUND, body = error)
        }
    }

    override fun updateUserBlog(userId: Long, blogId: Long, title: String, content: String): Response<Any> {
        val response=blogService.updateUserBlog(userId,blogId,title,content)
        if(response.isEmpty()) {
            val error= mutableListOf<Message>()
            error.add(Message("No Data Found"))
            return Response(ResponseType.NOT_FOUND, body = error)
        }
            val success= mutableListOf<Message>()
            success.add(Message("Blog Update Successfully"))
            return Response(ResponseType.SUCCESS,body=success)

    }

    override fun deleteUserBlog(userId: Long, blogId: Long): Response<Any> {
        val response=blogService.deleteUserBlog(userId,blogId)
        if(response.isEmpty()){
            val success= mutableListOf<Message>()
            success.add(Message("Blog Deleted Successfully"))
            return Response(ResponseType.SUCCESS,body=success)
        }else{
            val error= mutableListOf<Message>()
            error.add(Message("No Data Found"))
            return Response(ResponseType.NOT_FOUND,body=error)
        }

    }

    override fun fetchUserBlog(user_id: Long): Response<Any> {
        val response=blogService.fetchUserBlog(user_id)
        if(response.isEmpty()){
            val error= mutableListOf<Message>()
            error.add(Message("Blog Not Found"))
            return Response(ResponseType.NOT_FOUND,body=error)
        }else{
            return Response(ResponseType.SUCCESS,body=response)
        }
    }



}
