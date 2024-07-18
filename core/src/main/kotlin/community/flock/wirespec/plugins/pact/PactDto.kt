package community.flock.wirespec.plugins.pact

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonElement

@Serializable
data class PactDto(
    val consumer: NameDTO,
    val producer: NameDTO,
    val interactions: Set<InteractionDto>,
    val messages: Set<MessagesDto>,
    val metadata: MetadataDto = MetadataDto(),
)

@Serializable
data class NameDTO(
    val name: String,
)

@Serializable
data class MessagesDto(
    val description: String,
    val contents: JsonElement?,
)

@Serializable
data class RequestDto(
    val method: String,
    val path: String,
    val query: String,
    val headers: Map<String, JsonElement>,
)

@Serializable
data class InteractionDto(
    val description: String,
    val request: RequestDto,
    val response: JsonElement?,
)

@Serializable
data class MetadataDto(
    val pactSpecification: PactSpecification = PactSpecification(),
    val pactJvm: PactJvm = PactJvm(),
)

@Serializable
data class PactSpecification(
    val version: String = "3.0.0",
)

@Serializable
data class PactJvm(
    val version: String = "3.3.3",
)
