package blogs
import io.micronaut.http.HttpResponse
import io.micronaut.http.annotation.*
import javax.inject.Inject
import javax.sql.DataSource


@Controller("/api/v1/blogs")
class BlogApi @Inject constructor(
    private val blogController: BlogController
) {
    @Get("/")
    fun getAll(): HttpResponse<Any> {
        val blogs = blogController.getAll()
        return blogs.getHttpResponse()
    }
}


@Controller("/api/v1/user/{userId}/blog")
class createUserBlogApi @Inject constructor(
    private val blogController: BlogController
){
    @Post("/")
    fun createUserBlog(userId:Long,title:String,content:String):HttpResponse<Any>{
        val blog = blogController.createUserBlog(userId,title,content)
        return blog.getHttpResponse()
    }

}

@Controller("/api/v1/user/{userId}/blog/{blogId}")
class updateUserBlogApi @Inject constructor(
    private val blogController: BlogController
){
    @Put("/")
    fun updateUserBlog(userId: Long, blogId: Long, title: String, content: String): HttpResponse<Any> {
        val blog = blogController.updateUserBlog(userId,blogId,title,content)
        return blog.getHttpResponse()
    }
}

@Controller("/api/v1/user/{userId}/blog/{blogId}")
class deleteUserBlogApi @Inject constructor(
    private val blogController: BlogController
){
    @Delete("/")
    fun deleteUserBlog(userId: Long, blogId: Long): HttpResponse<Any> {
        val blog = blogController.deleteUserBlog(userId,blogId)
        return blog.getHttpResponse()
    }
}

@Controller("/api/v1/user/{userId}/blogs")
class fetchUserBlogApi @Inject constructor(
    private val blogController: BlogController
){
    @Get("/")
    fun fetchUserBlog(userId: Long):HttpResponse<Any>{
        val blog =blogController.fetchUserBlog(userId)
        return blog.getHttpResponse()
    }
}







