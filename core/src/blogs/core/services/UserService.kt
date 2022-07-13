package blogs.core.services

import blogs.core.entity.User
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class UserService @Inject constructor(
    private val userRepository: UserRepository
) {
    fun createUser(user_name: String):List<User> = userRepository.createUser(user_name)
    fun getAllUsers():List<User> = userRepository.getAllUsers()
    fun getUser(userId: Long):List<User> = userRepository.getUser(userId)
}
