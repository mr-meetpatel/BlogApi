package blogs.core.services

import blogs.core.entity.Blog
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BlogService @Inject constructor(
    private val blogRepository: BlogRepository
) {
    fun getAll(): List<Blog> = blogRepository.getAll()

    fun createUserBlog(userId:Long,title:String,content:String):List<Blog> = blogRepository.createUserBlog(userId,title,content)
    fun updateUserBlog(userId: Long, blogId: Long, title: String, content: String): List<Blog> = blogRepository.updateUserBlog(userId,blogId,title,content)
    fun deleteUserBlog(userId: Long,blogId: Long):List<Blog> = blogRepository.deleteUserBlog(userId,blogId)
    fun fetchUserBlog(userId:Long):List<Blog> = blogRepository.fetchUserBlog(userId)

}
