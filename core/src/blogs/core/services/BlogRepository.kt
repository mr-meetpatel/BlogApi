package blogs.core.services

import blogs.core.models.Blog
import blogs.core.models.User

interface BlogRepository {
    fun getAll(): List<Blog>
    fun getBlogById(blogId: Long):List<Blog>
    fun createUser(user_name: String):List<User>
    fun createUserBlog(userId:Long,title:String,content:String):List<Blog>
    fun updateUserBlog(userId: Long, blogId: Long, title: String, content: String): List<Blog>
    fun deleteUserBlog(userId: Long,blog: Long):List<Blog>
    fun fetchUserBlog(userId:Long):List<Blog>
    fun getAllUsers():List<User>
    fun getUser(userId: Long):List<User>
}
