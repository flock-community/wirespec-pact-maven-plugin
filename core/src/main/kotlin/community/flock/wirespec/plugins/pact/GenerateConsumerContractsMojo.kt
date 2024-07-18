package community.flock.wirespec.plugins.pact

import community.flock.wirespec.compiler.core.parse.Endpoint
import community.flock.wirespec.compiler.core.parse.Node
import community.flock.wirespec.generator.Generator.generate
import community.flock.wirespec.plugin.FileExtension
import community.flock.wirespec.plugin.PackageName
import community.flock.wirespec.plugin.maven.BaseMojo
import community.flock.wirespec.plugin.parse
import community.flock.wirespec.plugin.toDirectory
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import org.apache.maven.plugins.annotations.Mojo
import org.apache.maven.plugins.annotations.Parameter
import java.io.File

@Mojo(name = "generate-consumer-pact-contracts")
class GenerateConsumerContractsMojo : BaseMojo() {
    @Parameter(required = true)
    private lateinit var producer: String

    @Parameter
    private var strict: Boolean = true

    override fun execute() {
        log.info("generating Producer Pact Contracts")

        val outputFile = File(output)
        val packageNameValue = PackageName(packageName)
        val fileContents: List<Pair<String, String>> = getFilesContent()

        val ast: List<Pair<String, List<Node>>> = fileContents.parse(logger)

        ast.forEach { (filename, nodes: List<Node>) ->
            log.info("Processing $filename, outputFile: $outputFile")

            val pactDtos: List<PactDto> =
                nodes
                    .filterIsInstance<Endpoint>()
                    .map {
                        val generate = it.responses.first().content?.let { c -> nodes.generate(c.reference) }
                        PactDto(
                            consumer = NameDTO(project.name), // improve
                            producer = NameDTO(producer),
                            interactions =
                                setOf(
                                    InteractionDto(
                                        "description",
                                        request =
                                            RequestDto(
                                                it.method.name,
                                                it.path.toString(),
                                                it.query.toString(),
                                                it.headers.associate { h -> h.identifier.value to "randomValue" },
                                            ),
                                        response = generate,
                                    ),
                                ),
                            messages = emptySet(),
                        )
                    }
                    .also { println(it) }

            writeFile(
                output = outputFile,
                packageName = PackageName(""),
                fileName = "${outputFile.path}/$producer-${project.name}--$filename",
                ext = FileExtension.Json,
            )
                .writeText(Json.encodeToString(pactDtos))
        }

        log.info("Done generating consumer pact contracts")
    }
}

private fun writeFile(
    output: File,
    packageName: PackageName?,
    fileName: String,
    ext: FileExtension,
) = output
    .resolve(packageName.toDirectory())
    .apply { mkdirs() }
    .resolve("$fileName.${ext.value}")
