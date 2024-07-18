package community.flock.wirespec.plugins.pact

import community.flock.wirespec.compiler.core.parse.Endpoint
import community.flock.wirespec.compiler.core.parse.Field.Reference
import community.flock.wirespec.compiler.core.parse.Node
import community.flock.wirespec.plugin.FileExtension
import community.flock.wirespec.plugin.PackageName
import community.flock.wirespec.plugin.maven.BaseMojo
import community.flock.wirespec.plugin.parse
import community.flock.wirespec.plugin.toDirectory
import kotlinx.serialization.encodeToString
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonElement
import org.apache.maven.plugins.annotations.Mojo
import org.apache.maven.plugins.annotations.Parameter
import java.io.File
import kotlin.random.Random

private fun List<Endpoint.Segment>.produce(): String =
    joinToString(separator = "/") {
        when (it) {
            is Endpoint.Segment.Literal -> it.value
            is Endpoint.Segment.Param -> "{${it.identifier.value}}"
        }
    }

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

        try {
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
                                            "${it.method.name} ${it.path.produce()}",
                                            request =
                                                RequestDto(
                                                    method = it.method.name,
                                                    path = it.path.produce(),
                                                    query = it.query.toString(),
                                                    headers =
                                                        it.headers.associate { h ->
                                                            h.identifier.value to
                                                                nodes.generate(h.reference)
                                                        },
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
        } catch (e: Exception) {
            log.error(e.message, e)
            throw e
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

// TODO: fix me for generating primitives
fun List<Node>.generate(
    type: Reference,
    random: Random = kotlin.random.Random.Default,
): JsonElement {
    if (type is Reference.Primitive) {
        return TODO("return a primitive")
    } else {
        return this.(community.flock.wirespec.generator.Generator.generate)(type, random)
    }
}
