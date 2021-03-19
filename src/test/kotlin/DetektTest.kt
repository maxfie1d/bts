import io.gitlab.arturbosch.detekt.test.lint
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Test

class NoInternalImportRuleTest {
    @Test
    fun testFindDeprecatedClass() {
        val findings = NoInternalImportRule().lint(
            """
        @Deprecated("もう使わない")
        data class DeprecatedClass
        """.trimIndent()
        )

        assertEquals(1, findings.size)
        assertEquals(findings[0].message, "Deprecated class found at Test.kt")
    }


    @Test
    fun add() {
        assertEquals(2, 1 + 1)
    }
}
