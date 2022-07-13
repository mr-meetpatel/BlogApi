package blogs.core.services

import blogs.core.entity.Blog

interface BlogRepository {
    fun getAll(): List<Blog>
    fun getBlogById(blogId: Long): List<Blog>

    fun createUserBlog(userId: Long, title: String, content: String): List<Blog>
    fun updateUserBlog(userId: Long, blogId: Long, title: String, content: String): List<Blog>
    fun deleteUserBlog(userId: Long, blog: Long): List<Blog>
    fun fetchUserBlog(userId: Long): List<Blog>
}
