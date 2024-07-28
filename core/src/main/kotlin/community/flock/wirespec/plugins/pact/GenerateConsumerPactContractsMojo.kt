package community.flock.wirespec.plugins.pact

import org.apache.maven.plugins.annotations.LifecyclePhase
import org.apache.maven.plugins.annotations.Mojo
import org.apache.maven.plugins.annotations.Parameter

@Mojo(name = "generate-consumer-pact-contracts", defaultPhase = LifecyclePhase.GENERATE_RESOURCES, threadSafe = true)
class GenerateConsumerPactContractsMojo() : GeneratePactContractsMojoBase() {
    @Parameter(required = true)
    private lateinit var producer: String

    @Parameter(defaultValue = "\${project.name}", readonly = true, required = true)
    private lateinit var consumer: String

    override fun execute() {
        log.info("Generating Consumer Pact Contracts (seed $seed)")
        generatePactContract(NameDTO(consumer), NameDTO(producer))
        log.info("Done generating consumer pact contracts (seed $seed)")
    }
}
