import io.github.detekt.psi.absolutePath
import io.github.detekt.psi.relativePath
import io.gitlab.arturbosch.detekt.api.*
import org.jetbrains.kotlin.psi.*

class NoInternalImportRule(
    config: Config = Config.empty
) : Rule(config) {
    override val issue = Issue(
        "NoInternalImport",
        Severity.Maintainability, "Don’t import from an internal "
                + "package as they are subject to change.", Debt.TWENTY_MINS
    )

    override fun visitAnnotationEntry(annotationEntry: KtAnnotationEntry) {
        super.visitAnnotationEntry(annotationEntry)

        // ReferenceSearchとかはIntelliJのpluginの中じゃないと使えないっぽい
//        ReferenceSearch

        val n2 = annotationEntry.shortName
        if (n2.toString() == "Deprecated") {
            report(
                CodeSmell(
                    issue, Entity.from(annotationEntry),
                    "Deprecated class found at ${annotationEntry.containingFile.absolutePath()}"
                )
            )
        }
    }
}
