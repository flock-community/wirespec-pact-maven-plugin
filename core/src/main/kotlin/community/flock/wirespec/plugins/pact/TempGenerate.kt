package community.flock.wirespec.plugins.pact

import community.flock.wirespec.compiler.core.parse.AST
import community.flock.wirespec.compiler.core.parse.Field.Reference
import community.flock.wirespec.generator.generate
import kotlinx.serialization.json.JsonElement
import kotlinx.serialization.json.JsonPrimitive
import kotlin.random.Random

internal fun AST.generateTemp(
    type: Reference,
    random: Random,
): JsonElement =
// TODO: this should be part of wirespec-generate
    // when fixed this should work --> ast.generate(type, random)
    when (type) {
        is Reference.Primitive -> generatePrimitive(type, random)
        else -> generate(type, random)
    }

// TODO: use `generate` function from wirespec-generate
// Copied / adapted from wirespec-generate
private fun generatePrimitive(
    type: Reference.Primitive,
    random: Random,
) = when (type.type) {
    Reference.Primitive.Type.String -> random.nextString(50).let(::JsonPrimitive)
    Reference.Primitive.Type.Integer -> random.nextInt().let(::JsonPrimitive)
    Reference.Primitive.Type.Number -> random.nextDouble().let(::JsonPrimitive)
    Reference.Primitive.Type.Boolean -> random.nextBoolean().let(::JsonPrimitive)
}

// TODO: fix me for generating primitives
private val charPool: List<Char> = ('a'..'z') + ('A'..'Z') + ('0'..'9')

private fun Random.nextString(maxLength: Int) =
    nextInt(maxLength).let { length ->
        (1..length)
            .map { nextInt(0, charPool.size).let { charPool[it] } }
            .joinToString("")
    }
