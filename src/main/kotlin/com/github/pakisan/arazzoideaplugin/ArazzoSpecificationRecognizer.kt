package com.github.pakisan.arazzoideaplugin

import com.github.pakisan.arazzoideaplugin.xpath.JsonFileXPath
import com.github.pakisan.arazzoideaplugin.xpath.YamlFileXPath
import com.intellij.json.JsonFileType
import com.intellij.json.psi.JsonFile
import com.intellij.openapi.components.Service
import com.intellij.psi.PsiFile
import org.jetbrains.yaml.YAMLFileType
import org.jetbrains.yaml.psi.YAMLFile
import kotlin.collections.firstOrNull

/**
 * Analyzes the file and tries to find Arazzo keyword
 *
 * @author Pavel Bodiachevskii
 * @since 1.0.0
 */
@Service
class ArazzoSpecificationRecognizer {

    fun isSpecification(file: PsiFile?, useIndex: Boolean = false): Boolean {
        file ?: return false

        if (file.fileType !is YAMLFileType && file.fileType !is JsonFileType) {
            return false
        }

        return if (useIndex) {
            /*
                TODO: implement.
             */
            false
        } else {
            isSupported(extractArazzoKey(file))
        }
    }

    fun extractArazzoKey(file: PsiFile?): String? {
        file ?: return null

        val psiXPath = "$.arazzo"
        return when (file) {
            is JsonFile -> JsonFileXPath.findText(file, psiXPath).firstOrNull()
            is YAMLFile -> YamlFileXPath.findText(file, psiXPath).firstOrNull()
            else -> null
        }
    }

    fun isSupported(version: String?): Boolean {
        return when (version) {
            "1.0.0", "1.0.1" -> true
            else -> false
        }
    }

}