package users

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.micronaut.test.annotation.MicronautTest
import utility.ResponseType

@MicronautTest
class UserApiTest(
    private val userControllerImpl: UserControllerImpl
):BehaviorSpec( {
//    given("Creating User") {
//        `when`("the user API in call with /api/v1/users") {
//
//            val result = userControllerImpl.createUser("Shivam")
//            then("it should return status success") {
//                result.status shouldBe ResponseType.SUCCESS
//            }
//        }
//    }
    given("fetching all users") {
        `when`("the blog API in call with /api/v1/user/{userId}/blog") {

            val result = userControllerImpl.getAllUsers()
            then("it should return status success") {
                result.status shouldBe ResponseType.SUCCESS
            }
        }
    }
    given("fetching user with correct data") {
        `when`("the blog API in call with /api/v1/user/{userId}") {

            val result = userControllerImpl.getUser(1)
            then("it should return status success") {
                result.status shouldBe ResponseType.SUCCESS
            }
        }
    }
    given("fetching user with incorrect data") {
        `when`("the blog API in call with /api/v1/user/{userId}") {

            val result = userControllerImpl.getUser(100)
            then("it should return status not found") {
                result.status shouldBe ResponseType.NOT_FOUND
            }
        }
    }
})
