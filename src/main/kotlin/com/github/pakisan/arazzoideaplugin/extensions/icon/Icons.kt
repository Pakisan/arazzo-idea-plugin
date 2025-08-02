package com.github.pakisan.arazzoideaplugin.extensions.icon

import com.intellij.openapi.util.IconLoader
import javax.swing.Icon

/**
 * Object with plugin icons
 *
 * @author Pavel Bodiachevskii
 * @since 1.0.0
 */
object Icons {

    @JvmField
    val ARAZZO_ICON: Icon = IconLoader.getIcon("/icons/arazzo.svg", Icons::class.java)

}