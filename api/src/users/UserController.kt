package users

import utility.Response

interface UserController {
    fun createUser(user_name: String): Response<Any>
    fun getAllUsers():Response<Any>
    fun getUser(userId: Long):Response<Any>
}
