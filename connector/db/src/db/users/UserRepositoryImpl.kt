package db.users

import blogs.core.entity.User
import blogs.core.services.UserRepository
import java.sql.DriverManager
import java.sql.Timestamp
import javax.inject.Inject
import javax.inject.Singleton
import javax.sql.DataSource

@Singleton
class UserRepositoryImpl :UserRepository{
    val jdbcUrl = "jdbc:postgresql://localhost:5432/blogs_dev"

    // get the connection
    val connection = DriverManager
        .getConnection(jdbcUrl, "postgres", "password")
    override fun createUser(user_name: String): List<User> {
        val insertQuery =connection.prepareStatement("INSERT INTO users(name,created_at,updated_at) VALUES (?,?,?)")

        insertQuery.setString(1,user_name)

        insertQuery.setTimestamp(2, Timestamp(System.currentTimeMillis()))
        insertQuery.setTimestamp(3, Timestamp(System.currentTimeMillis()))
        insertQuery.executeUpdate()
        //Fetching Last Insert Data
        val fetchInsertedData= connection.prepareStatement("SELECT * FROM users ORDER BY id DESC LIMIT 1")
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

    override fun getAllUsers(): List<User> {
        val fetchUser=connection.prepareStatement("SELECT * FROM users")
        val result=fetchUser.executeQuery()
        val user= mutableListOf<User>()
        while(result.next()){
            val id = result.getInt("id").toLong()
            val name = result.getString("name") as String
            val createdAt=result.getTimestamp("created_at") as Timestamp
            val updatedAt=result.getTimestamp("updated_at") as Timestamp

            user.add(User(id,name,createdAt,updatedAt))

        }
        return user
    }

    override fun getUser(userId: Long): List<User> {
        val fetchUser=connection.prepareStatement("SELECT * FROM users WHERE id=${userId}")
        val result=fetchUser.executeQuery()
        val user= mutableListOf<User>()
        while(result.next()){
            val id = result.getInt("id").toLong()
            val name = result.getString("name") as String

            val createdAt=result.getTimestamp("created_at") as Timestamp
            val updatedAt=result.getTimestamp("updated_at") as Timestamp

            user.add(User(id,name,createdAt,updatedAt))

        }
        return user
    }
}
