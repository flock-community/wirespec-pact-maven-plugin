package community.flock.wirespec.plugins.pact

import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugins.annotations.Mojo
import org.apache.maven.plugins.annotations.Parameter

@Mojo(name = "sayhello")
class MyMojo : AbstractMojo() {
    @Parameter(property = "greeting", defaultValue = "Hello, World!")
    private val greeting: String? = null

    override fun execute() {
        getLog().info(greeting)
    }
}
