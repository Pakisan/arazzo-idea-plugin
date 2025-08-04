package com.github.pakisan.arazzoideaplugin.extensions.completion

import com.intellij.codeInsight.completion.CompletionContributor
import com.intellij.codeInsight.completion.CompletionParameters
import com.intellij.codeInsight.completion.CompletionResultSet
import com.intellij.openapi.components.service
import com.jetbrains.jsonSchema.impl.JsonSchemaCompletionContributor

/**
 * Provides JSON Schema completion contributor
 *
 * @author Pavel Bodiachevskii
 * @since 1.1.0
 */
class ArazzoSpecificationCompletionContributor: CompletionContributor() {

    private val arazzoJsonSchemaProvider = service<ArazzoJsonSchemaProvider>()

    override fun fillCompletionVariants(
        parameters: CompletionParameters,
        result: CompletionResultSet
    ) {
        val arazzoJsonSchemaObject = arazzoJsonSchemaProvider.provide(parameters.originalFile, parameters.position.project)
        arazzoJsonSchemaObject ?: return

        JsonSchemaCompletionContributor.doCompletion(parameters, result, arazzoJsonSchemaObject, false)
    }

}