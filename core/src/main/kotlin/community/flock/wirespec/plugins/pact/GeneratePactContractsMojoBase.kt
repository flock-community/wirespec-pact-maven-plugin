package community.flock.wirespec.plugins.pact

import community.flock.wirespec.compiler.core.parse.AST
import community.flock.wirespec.compiler.core.parse.Channel
import community.flock.wirespec.compiler.core.parse.Endpoint
import community.flock.wirespec.compiler.core.parse.Node
import community.flock.wirespec.compiler.utils.Logger
import community.flock.wirespec.plugin.FileExtension
import community.flock.wirespec.plugin.PackageName
import community.flock.wirespec.plugin.maven.BaseMojo
import community.flock.wirespec.plugin.parse
import kotlinx.serialization.encodeToString
import org.apache.maven.plugins.annotations.Parameter
import java.io.File
import kotlin.random.Random

abstract class GeneratePactContractsMojoBase : BaseMojo() {
    @Parameter(required = false)
    protected var seed: Long = Random.nextLong()

    protected fun generatePactContract(
        consumer: NameDTO,
        producer: NameDTO,
    ) {
        val random = Random(seed)
        val outputFile = File(output)
        val fileContents: FilesContents = getFilesContent()

        try {
            val ast: List<Pair<String, AST>> = fileContents.parse(Logger(false))
            ast.forEach { (filename, nodes: List<Node>) ->
                log.info("Processing $filename, outputFile: $outputFile")

                val pactDto =
                    PactDto(
                        consumer = consumer,
                        producer = producer,
                        interactions =
                            nodes
                                .filterIsInstance<Endpoint>()
                                .map { it.mapInteractions(nodes, random) },
                        messages =
                            nodes
                                .filterIsInstance<Channel>()
                                .map { it.mapMessage(nodes, random) },
                        metadata =
                            MetadataDto(
                                pactSpecification = PACT_SPECIFICATION,
                                pactJvm = PACT_JVM,
                            ),
                    )

                writeFile(
                    output = outputFile,
                    packageName = PackageName(""),
                    fileName = "${outputFile.path}/${producer.name}-${consumer.name}--$filename",
                    ext = FileExtension.Json,
                )
                    .writeText(jsonEncoder.encodeToString(pactDto))
            }
        } catch (e: Exception) {
            log.error(e.message, e)
            throw e
        }
    }
}
