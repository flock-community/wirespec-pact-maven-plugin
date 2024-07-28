package community.flock.wirespec.plugins.pact

import community.flock.wirespec.plugin.FileExtension
import community.flock.wirespec.plugin.PackageName
import community.flock.wirespec.plugin.toDirectory
import kotlinx.serialization.json.Json
import java.io.File

typealias FilesContents = List<Pair<String, String>>

internal val jsonEncoder = Json { prettyPrint = true }

internal val PACT_SPECIFICATION = PactSpecification("3.0.0")
internal val PACT_JVM = PactJvm("3.3.3")

internal fun writeFile(
    output: File,
    packageName: PackageName?,
    fileName: String,
    ext: FileExtension,
) = output
    .resolve(packageName.toDirectory())
    .apply { mkdirs() }
    .resolve("$fileName.${ext.value}")
