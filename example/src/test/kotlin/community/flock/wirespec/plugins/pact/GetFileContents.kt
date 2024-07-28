package community.flock.wirespec.plugins.pact

import java.io.File

typealias FileContent = Pair<String, String>

internal fun getFileContents(fileName: String): FileContent =
    (File(fileName))
        .let { it.name.take(it.name.lastIndexOf(".")) to it }
        .let { (name, reader) -> name to reader.readText(Charsets.UTF_8) }
