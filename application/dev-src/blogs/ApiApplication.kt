package blogs

import io.micronaut.runtime.Micronaut
import io.swagger.v3.oas.annotations.OpenAPIDefinition
import io.swagger.v3.oas.annotations.info.Info

@OpenAPIDefinition(
    info = Info(
        title = "Blog App",
        version="1.0",
        description = "Blog Api"
    )
)
object ApiApplication {
    @JvmStatic
    fun main(args: Array<String>) {
        Micronaut.build(*args).start()
    }
}
