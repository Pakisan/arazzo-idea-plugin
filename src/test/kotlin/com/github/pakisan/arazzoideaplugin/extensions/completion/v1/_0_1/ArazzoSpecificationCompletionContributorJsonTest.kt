package com.github.pakisan.arazzoideaplugin.extensions.completion.v1._0_1

import com.github.pakisan.arazzoideaplugin.extensions.completion.AbstractArazzoSpecificationCompletionContributorTest
import com.github.pakisan.arazzoideaplugin.extensions.completion.ArazzoSpecificationCompletionContributor

/**
 * Abstract [ArazzoSpecificationCompletionContributor] test
 *
 * @author Pavel Bodiachevskii
 * @since 1.1.0
 */
class ArazzoSpecificationCompletionContributorJsonTest: AbstractArazzoSpecificationCompletionContributorTest() {

    override fun fileExtension(): String = "json"

    override fun arazzoVersion(): String = "1.0.1"

}