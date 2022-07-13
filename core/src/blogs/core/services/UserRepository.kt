package blogs.core.services

import blogs.core.entity.User

interface UserRepository {
    fun createUser(user_name: String):List<User>
    fun getAllUsers():List<User>
    fun getUser(userId: Long):List<User>
}
