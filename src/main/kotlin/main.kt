import java.io.File

fun main() {
    val deprecated = DeprecatedClass()

    val a = (1..3).min()

    val projectDirPath = "/Users/ishida_n/github/with_android"
    val projectDir = File(projectDirPath)
    val deprecatedFiles =
        findKotlinFiles(projectDir)
            .filter { it.containsAnnotation("Deprecated") }

    deprecatedFiles.forEach { println(it.toRelativeString(projectDir)) }
    println("${deprecatedFiles.count()}個のDeprecatedファイル")
}

private fun File.containsAnnotation(annotationName: String): Boolean {
    return readLines().joinToString("").contains("@${annotationName}")
}

private fun findKotlinFiles(dir: File) = findFilesWithExtension(dir, extension = "kt")

private fun findFilesWithExtension(dir: File, extension: String): Sequence<File> =
    dir.walkTopDown().filter { it.extension == extension }
