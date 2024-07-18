package community.flock.wirespec.plugins.pact

import org.apache.maven.plugin.AbstractMojo
import org.apache.maven.plugins.annotations.Mojo
import org.apache.maven.plugins.annotations.Parameter

@Mojo(name = "generate-producer-pact-contracts")
class GenerateProducerPactContractsMojo : AbstractMojo() {
    @Parameter(property = "greeting", defaultValue = "Hello, World!")
    private val greeting: String? = null

    override fun execute() {
        log.info("generating Producer Pact Contracts")
        log.info("Done generating producer pact contracts")
    }
}
