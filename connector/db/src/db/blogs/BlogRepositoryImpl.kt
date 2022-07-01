package db.blogs

import blogs.core.models.Blog
import blogs.core.models.User
import blogs.core.services.BlogRepository

import java.sql.Timestamp
import javax.inject.Inject
import javax.inject.Singleton
import javax.sql.DataSource

@Singleton
class BlogRepositoryImpl(
    @Inject private val dataSource: DataSource
) : BlogRepository {
    override fun getAll(): List<Blog> {
        val query = dataSource.connection.prepareStatement("SELECT * FROM blogs")
        val result = query.executeQuery()
        val blogs = mutableListOf<Blog>()

        while (result.next()) {
            val id = result.getInt("id").toLong()
            val name = result.getString("title") as String
            val content = result.getString("content") as String
            val createdAt=result.getTimestamp("created_at") as Timestamp
            val updatedAt=result.getTimestamp("updated_at") as Timestamp
            val userId=result.getLong("user_id")
            val isPublish=result.getBoolean("is_publish")
            blogs.add(Blog(id, name, content,createdAt,updatedAt,userId,isPublish))
        }
        return blogs
    }

    override fun getBlogById(blogId: Long): List<Blog> {
        val query = dataSource.connection.prepareStatement("SELECT * FROM blogs where id=${blogId}")
        val result = query.executeQuery()
        val blogs = mutableListOf<Blog>()

        while (result.next()) {
            val id = result.getInt("id").toLong()
            val name = result.getString("title") as String
            val content = result.getString("content") as String
            val createdAt=result.getTimestamp("created_at") as Timestamp
            val updatedAt=result.getTimestamp("updated_at") as Timestamp
            val userId=result.getLong("user_id")
            val isPublish=result.getBoolean("is_publish")
            blogs.add(Blog(id, name, content,createdAt,updatedAt,userId,isPublish))
        }
        return blogs
    }

    override fun createUser(user_name: String):List<User>{
        val insertQuery =dataSource.connection.prepareStatement("INSERT INTO users(name,created_at,updated_at) VALUES (?,?,?)")

        insertQuery.setString(1,user_name)

        insertQuery.setTimestamp(2,Timestamp(System.currentTimeMillis()))
        insertQuery.setTimestamp(3,Timestamp(System.currentTimeMillis()))
        insertQuery.executeUpdate()
        //Fetching Last Insert Data
        val fetchInsertedData= dataSource.connection.prepareStatement("SELECT * FROM users ORDER BY id DESC LIMIT 1")
        val result = fetchInsertedData.executeQuery()
        val userData = mutableListOf<User>()

        while (result.next()) {
            val id = result.getInt("id").toLong()
            val name = result.getString("name") as String
            val createdAt = result.getTimestamp("created_at") as Timestamp
            val updatedAt = result.getTimestamp("updated_at") as Timestamp
            userData.add(User(id, name, createdAt,updatedAt))
        }
        return userData
    }

    override fun createUserBlog(userId:Long,title:String,content:String):List<Blog>{
        // user is add or not
        val fetchUserQuery=dataSource.connection.prepareStatement("SELECT * FROM users where id=${userId}")
        val result=fetchUserQuery.executeQuery()
        val blog = mutableListOf<Blog>()

         if(result.next()){
             val insertQuery=dataSource.connection.prepareStatement("INSERT INTO blogs(title,content,created_at,updated_at,user_id) VALUES(?,?,?,?,?)")
             insertQuery.setString(1,title)
             insertQuery.setString(2,content)
             insertQuery.setTimestamp(3,Timestamp(System.currentTimeMillis()))
             insertQuery.setTimestamp(4,Timestamp(System.currentTimeMillis()))
             insertQuery.setLong(5,userId)
             insertQuery.executeUpdate()
             val updateUser=dataSource.connection.prepareStatement("UPDATE users SET updated_at=? where id=${userId}")
             updateUser.setTimestamp(1,Timestamp(System.currentTimeMillis()))
             updateUser.executeUpdate()
             val fetchInsertedData= dataSource.connection.prepareStatement("SELECT * FROM blogs ORDER BY id DESC LIMIT 1")
             val res= fetchInsertedData.executeQuery()


             while (res.next()) {
                 val id = res.getInt("id").toLong()
                 val isPublish=res.getBoolean("is_publish")
                 blog.add(Blog(id,title,content,Timestamp(System.currentTimeMillis()),Timestamp(System.currentTimeMillis()),userId ,isPublish))
             }
         }




        return blog
    }

    override fun updateUserBlog(userId: Long, blogId: Long, title: String, content: String): List<Blog>{
        // fetch data from db
        val fetchBlog=dataSource.connection.prepareStatement("SELECT * FROM blogs where user_id = $userId and id = $blogId")
        val result=fetchBlog.executeQuery()
        val blog= mutableListOf<Blog>()
        if(result.next()){
            val isPublish=true

            val updateQuery=dataSource.connection.prepareStatement("UPDATE blogs SET title=?,content=?,is_publish=?,updated_at=? where id=${blogId} and user_id=${userId}")
            updateQuery.setString(1,title)
            updateQuery.setString(2,content)
            updateQuery.setBoolean(3,isPublish)
            updateQuery.setTimestamp(4,Timestamp(System.currentTimeMillis()))

            val res=updateQuery.executeUpdate()

            return getBlogById(blogId)



        }
        return blog
    }

    override fun deleteUserBlog(userId: Long,blogId: Long):List<Blog>{
        val fetchBlog=dataSource.connection.prepareStatement("SELECT * FROM blogs where user_id = $userId and id = $blogId")
        val result=fetchBlog.executeQuery()
        if(result.next()){
            val updateQuery=dataSource.connection.prepareStatement("DELETE FROM blogs where id=? and user_id=?")
            updateQuery.setLong(1,blogId)
            updateQuery.setLong(2,userId)
            val res=updateQuery.executeUpdate()

            return getBlogById(blogId)
        }else{
            val blog = mutableListOf<Blog>()
            blog.add(Blog(0,"","",Timestamp(System.currentTimeMillis()),Timestamp(System.currentTimeMillis()),0,false))
            return blog
        }

    }
}
