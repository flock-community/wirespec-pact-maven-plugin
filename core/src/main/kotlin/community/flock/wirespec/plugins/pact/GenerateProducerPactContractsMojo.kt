package community.flock.wirespec.plugins.pact

import org.apache.maven.plugins.annotations.LifecyclePhase
import org.apache.maven.plugins.annotations.Mojo
import org.apache.maven.plugins.annotations.Parameter

@Mojo(name = "generate-producer-pact-contracts", defaultPhase = LifecyclePhase.GENERATE_RESOURCES, threadSafe = true)
class GenerateProducerPactContractsMojo : GeneratePactContractsMojoBase() {
    @Parameter(required = true)
    private lateinit var consumer: String

    @Parameter(defaultValue = "\${project.name}", readonly = true, required = true)
    private lateinit var producer: String

    override fun execute() {
        log.info("generating Producer Pact Contracts (seed $seed)")
        generatePactContract(NameDTO(consumer), NameDTO(producer))
        log.info("Done generating producer pact contracts (seed $seed)")
    }
}
