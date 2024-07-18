package community.flock.wirespec.plugins.pact

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonElement

@Serializable
data class PactDto(
    val consumer: NameDTO,
    val producer: NameDTO,
    val interactions: Set<InteractionDto>,
    val messages: Set<MessagesDto>,
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
    val headers: Map<String, String>,
)

@Serializable
data class InteractionDto(
    val description: String,
    val request: RequestDto,
    val response: JsonElement?,
)
