package blogs



import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.shouldBe
import io.micronaut.test.annotation.MicronautTest
import utility.ResponseType


@MicronautTest
class BlogApiTest(
    private val blogControllerImpl: BlogControllerImpl
):BehaviorSpec( {
    given("fetching all Blogs") {
        `when`("the blog API in call with /api/v1/blogs") {

            val result = blogControllerImpl.getAll()
            then("it should return status success") {
                result.status shouldBe ResponseType.SUCCESS
            }
        }
    }
    given("creating blog with correct data") {
        `when`("the blog API in call with /api/v1/user/{userId}/blog") {

            val result = blogControllerImpl.createUserBlog(1,"Kotlin","Kotlin")
            then("it should return status success") {
                result.status shouldBe ResponseType.SUCCESS
            }
        }
    }
    given("creating blog with incorrect data") {
        `when`("the blog API in call with /api/v1/user/{userId}/blog") {

            val result = blogControllerImpl.createUserBlog(100,"Kotlin","Kotlin")
            then("it should return status not found") {
                result.status shouldBe ResponseType.NOT_FOUND
            }
        }
    }
    given("updating blog with correct data") {
        `when`("the blog API in call with /api/v1/user/{userId}/blog/{blogId}") {

            val result = blogControllerImpl.updateUserBlog(1,23,"Kotlin","Kotlin Content")
            then("it should return status success") {
                result.status shouldBe ResponseType.SUCCESS
            }
        }
    }
    given("updating blog with incorrect data") {
        `when`("the blog API in call with /api/v1/user/{userId}/blog/{blogId}") {

            val result = blogControllerImpl.updateUserBlog(1,2,"Kotlin","Kotlin Content")
            then("it should return status not found") {
                result.status shouldBe ResponseType.NOT_FOUND
            }
        }
    }
//    given("deleting blog with correct data") {
//        `when`("the blog API in call with /api/v1/user/{userId}/blog/{blogId}") {
//
//            val result = blogControllerImpl.deleteUserBlog(1,19)
//            then("it should return status success") {
//                result.status shouldBe ResponseType.SUCCESS
//            }
//        }
//    }

    given("deleting blog with incorrect data") {
        `when`("the blog API in call with /api/v1/user/{userId}/blog/{blogId}") {

            val result = blogControllerImpl.deleteUserBlog(1,19)
            then("it should return status not found") {
                result.status shouldBe ResponseType.NOT_FOUND
            }
        }
    }
    given("fetching user blogs with correct data") {
        `when`("the blog API in call with /api/v1/user/{userId}/blogs") {

            val result = blogControllerImpl.fetchUserBlog(1)
            then("it should return status success") {
                result.status shouldBe ResponseType.SUCCESS
            }
        }
    }

    given("fetching user blogs with incorrect data") {
        `when`("the blog API in call with /api/v1/user/{userId}/blogs") {

            val result = blogControllerImpl.fetchUserBlog(2)
            then("it should return status not found") {
                result.status shouldBe ResponseType.NOT_FOUND
            }
        }
    }

})
