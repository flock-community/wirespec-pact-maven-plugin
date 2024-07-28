package community.flock.wirespec.plugins.pact

import community.flock.wirespec.compiler.core.parse.AST
import community.flock.wirespec.compiler.core.parse.Endpoint
import community.flock.wirespec.compiler.core.parse.Field
import kotlin.random.Random

// TODO: consider mapping multiple responses
// TODO: consider extending InteractionDTO to include pattern matching from actual schema
// https://raw.githubusercontent.com/pactflow/pact-schemas/main/dist/pact-schema-v3.json
internal fun Endpoint.mapInteractions(
    ast: AST,
    random: Random,
) = InteractionDto(
    description = "${method.name} ${path.produce()}",
    request = mapRequest(ast, random),
    response = responses.first().content?.let { ast.generateTemp(it.reference, random) },
)

private fun Endpoint.mapRequest(
    ast: AST,
    random: Random,
) = RequestDto(
    method = method.name,
    path = path.produce(),
    query = query.produce(ast, random),
    headers = headers.associate { it.identifier.value to ast.generateTemp(it.reference, random) },
)

private fun List<Endpoint.Segment>.produce(): String =
    joinToString(separator = "/") {
        when (it) {
            is Endpoint.Segment.Literal -> it.value
            is Endpoint.Segment.Param -> "{${it.identifier.value}}"
        }
    }

private fun List<Field>.produce(
    ast: AST,
    random: Random,
): String =
    joinToString(separator = "&") {
        val key = it.identifier.value
        val value = ast.generateTemp(it.reference, random)
        "$key=$value"
    }
