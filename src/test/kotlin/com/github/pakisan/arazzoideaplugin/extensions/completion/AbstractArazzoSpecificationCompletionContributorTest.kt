package com.github.pakisan.arazzoideaplugin.extensions.completion

import com.intellij.codeInsight.completion.CompletionType
import com.intellij.testFramework.fixtures.BasePlatformTestCase
import kotlin.collections.sorted

/**
 * Abstract [ArazzoSpecificationCompletionContributor] test
 *
 * @author Pavel Bodiachevskii
 * @since 1.1.0
 */
abstract class AbstractArazzoSpecificationCompletionContributorTest: BasePlatformTestCase() {

    abstract fun fileExtension(): String

    abstract fun arazzoVersion(): String

    override fun getTestDataPath(): String = "src/test/testData/${fileExtension()}/completion/${arazzoVersion()}"

    protected open val `$ - i` = listOf(
        "\"info\"",
        "\"sourceDescriptions\"",
    )

    protected open val `$ - info - i` = listOf(
        "\"description\"",
        "\"version\"",
        "\"title\""
    )

    protected open val `$ - sourceDescriptions - e` = listOf(
        "\"name\"",
        "\"type\""
    )

    protected open val `$ - workflows - workflow - s` = listOf(
        "\"successActions\"",
        "\"failureActions\"",
        "\"parameters\"",
        "\"dependsOn\""
    )

    protected open val `$ - components - s` = listOf(
        "\"successActions\"",
        "\"failureActions\"",
        "\"parameters\"",
    )

    fun `test $ - (double quote)i(double quote)`() {
        configureMyFixture("double quote i double quote.${fileExtension()}")

        assertTrue((myFixture.lookupElementStrings ?: emptyList<String>()).containsAll(`$ - i`))
    }

    fun `test $ - i`() {
        configureMyFixture("i.${fileExtension()}")

        assertEquals(`$ - i`.sorted(), (myFixture.lookupElementStrings?.sorted() ?: emptyList<String>()))
    }

    fun `test $ - info - (double quote)i(double quote)`() {
        configureMyFixture("info - double quote i double quote.${fileExtension()}")

        assertTrue(
            (myFixture.lookupElementStrings ?: emptyList<String>())
                .containsAll(`$ - info - i`)
        )
    }

    fun `test $ - info - i`() {
        configureMyFixture("info - i.${fileExtension()}")

        assertEquals(`$ - info - i`.sorted(), (myFixture.lookupElementStrings?.sorted() ?: emptyList<String>()))
    }

    fun `test $ - sourceDescriptions - e`() {
        configureMyFixture("sourceDescriptions - e.${fileExtension()}")

        assertTrue(
            (myFixture.lookupElementStrings ?: emptyList<String>())
                .containsAll(`$ - sourceDescriptions - e`)
        )
    }

    fun `test $ - sourceDescriptions - (double quote)e(double quote)`() {
        configureMyFixture("sourceDescriptions - double quote e double quote.${fileExtension()}")

        assertEquals(
            (`$ - sourceDescriptions - e` + listOf("\"url\"")).sorted(), // Old bug - in IDEA url doesn't appear but in test does
            (myFixture.lookupElementStrings?.sorted() ?: emptyList<String>())
        )
    }

    fun `test $ - workflows - workflow - s`() {
        configureMyFixture("workflows - workflow - s.${fileExtension()}")

        assertTrue(
            (myFixture.lookupElementStrings ?: emptyList<String>())
                .containsAll(`$ - workflows - workflow - s`)
        )
    }

    fun `test $ - workflows - workflow - (double quote)s(double quote)`() {
        configureMyFixture("workflows - workflow - double quote s double quote.${fileExtension()}")

        assertEquals(
            (`$ - workflows - workflow - s`).sorted(),
            (myFixture.lookupElementStrings?.sorted() ?: emptyList<String>())
        )
    }

    fun `test $ - components - s`() {
        configureMyFixture("workflows - workflow - s.${fileExtension()}")

        assertTrue(
            (myFixture.lookupElementStrings ?: emptyList<String>())
                .containsAll(`$ - components - s`)
        )
    }

    fun `test $ - components - (double quote)s(double quote)`() {
        configureMyFixture("components - double quote s double quote.${fileExtension()}")

        assertEquals(
            (`$ - components - s`).sorted(),
            (myFixture.lookupElementStrings?.sorted() ?: emptyList<String>())
        )
    }

    private fun configureMyFixture(fileName: String) {
        myFixture.configureByFile(fileName)
        myFixture.complete(CompletionType.BASIC, 1)
    }

}