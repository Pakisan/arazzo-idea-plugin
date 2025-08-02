package com.github.pakisan.arazzoideaplugin.extensions.icon

import com.github.pakisan.arazzoideaplugin.ArazzoSpecificationRecognizer
import com.intellij.ide.IconProvider
import com.intellij.json.psi.JsonFile
import com.intellij.openapi.components.service
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiFile
import org.jetbrains.yaml.psi.YAMLFile
import javax.swing.Icon

/**
 * Maps Arazzo icon to Arazzo specification files
 *
 * @author Pavel Bodiachevskii
 * @since 1.0.0
 */
class ArazzoIconProvider : IconProvider() {

    private val arazzoSpecificationRecognizer = service<ArazzoSpecificationRecognizer>()

    override fun getIcon(element: PsiElement, flags: Int): Icon? {
        if (element is JsonFile || element is YAMLFile) {
            if (arazzoSpecificationRecognizer.isSpecification(element)) {
                return Icons.ARAZZO_ICON
            }
        }

        return null
    }

}