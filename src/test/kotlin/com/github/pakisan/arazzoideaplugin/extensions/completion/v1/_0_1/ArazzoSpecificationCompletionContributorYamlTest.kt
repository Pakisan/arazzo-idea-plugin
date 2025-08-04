package com.github.pakisan.arazzoideaplugin.extensions.completion.v1._0_1

import com.github.pakisan.arazzoideaplugin.extensions.completion.AbstractArazzoSpecificationCompletionContributorTest
import com.github.pakisan.arazzoideaplugin.extensions.completion.ArazzoSpecificationCompletionContributor

/**
 * Abstract [ArazzoSpecificationCompletionContributor] test
 *
 * @author Pavel Bodiachevskii
 * @since 1.1.0
 */
class ArazzoSpecificationCompletionContributorYamlTest: AbstractArazzoSpecificationCompletionContributorTest() {

    override fun fileExtension(): String = "yaml"

    override fun arazzoVersion(): String = "1.0.1"

    override val `$ - i`: List<String> = super.`$ - i`.sorted().map { it.replace("\"", "") }

    override val `$ - info - i`: List<String> = listOf("description", "info", "title", "version")

    override val `$ - sourceDescriptions - e`: List<String> = listOf("name", "sourceDescriptions", "type")

    override val `$ - workflows - workflow - s` = super.`$ - workflows - workflow - s`.map { it.replace("\"", "") }

    override val `$ - components - s`: List<String> = super.`$ - components - s`.sorted().map { it.replace("\"", "") }

}