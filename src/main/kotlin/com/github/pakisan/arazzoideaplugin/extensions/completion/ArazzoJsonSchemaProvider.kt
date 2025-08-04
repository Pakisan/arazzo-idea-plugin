package com.github.pakisan.arazzoideaplugin.extensions.completion

import com.github.pakisan.arazzoideaplugin.ArazzoSpecificationRecognizer
import com.intellij.openapi.components.Service
import com.intellij.openapi.components.service
import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.VfsUtil
import com.intellij.psi.PsiFile
import com.intellij.util.ResourceUtil
import com.jetbrains.jsonSchema.ide.JsonSchemaService
import com.jetbrains.jsonSchema.impl.JsonSchemaObject
import kotlin.jvm.javaClass

/**
 * Provides Arazzo JSON Schema
 *
 * @author Pavel Bodiachevskii
 * @since 1.1.0
 */
@Service
class ArazzoJsonSchemaProvider {

    private val arazzoSpecificationRecognizer = service<ArazzoSpecificationRecognizer>()

    fun provide(file: PsiFile, project: Project): JsonSchemaObject? {
        val arazzoVersion = arazzoSpecificationRecognizer.extractArazzoKey(file)
        arazzoVersion ?: return null

        if (!arazzoSpecificationRecognizer.isSupported(arazzoVersion)) {
            return null
        }

        val arazzoJsonSchemaURL = ResourceUtil.getResource(javaClass.classLoader, "schema", "arazzo-1.0.0.json")
        val arazzoJsonSchemaFile = VfsUtil.findFileByURL(arazzoJsonSchemaURL)
        arazzoJsonSchemaFile ?: return null

        val jsonSchemaService = JsonSchemaService.Impl.get(project)
        val arazzoJsonSchemaObject = jsonSchemaService.getSchemaObjectForSchemaFile(arazzoJsonSchemaFile)
        arazzoJsonSchemaObject ?: return null

        return arazzoJsonSchemaObject
    }

}