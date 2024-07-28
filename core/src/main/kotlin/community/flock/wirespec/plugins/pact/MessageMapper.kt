package community.flock.wirespec.plugins.pact

import community.flock.wirespec.compiler.core.parse.AST
import community.flock.wirespec.compiler.core.parse.Channel
import kotlin.random.Random

fun Channel.mapMessage(
    ast: AST,
    random: Random,
) = MessagesDto(
    description = reference.value,
    contents = ast.generateTemp(reference, random),
)
