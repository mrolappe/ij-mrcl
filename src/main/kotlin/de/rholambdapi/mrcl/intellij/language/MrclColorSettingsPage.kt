package de.rholambdapi.mrcl.intellij.language

import com.intellij.openapi.editor.colors.TextAttributesKey
import com.intellij.openapi.fileTypes.SyntaxHighlighter
import com.intellij.openapi.options.colors.AttributesDescriptor
import com.intellij.openapi.options.colors.ColorDescriptor
import com.intellij.openapi.options.colors.ColorSettingsPage
import javax.swing.Icon

class MrclColorSettingsPage : ColorSettingsPage {
    companion object {
        val DESCRIPTORS = arrayOf(
            AttributesDescriptor("Identifier", MrclSyntaxHighlighter.IDENTIFIER),
            AttributesDescriptor("Keyword", MrclSyntaxHighlighter.KEYWORD),
            AttributesDescriptor("String", MrclSyntaxHighlighter.STRING),
            AttributesDescriptor("Line comment", MrclSyntaxHighlighter.LINE_COMMENT),
        )
    }

    override fun getAttributeDescriptors(): Array<AttributesDescriptor> {
        return DESCRIPTORS
    }

    override fun getColorDescriptors(): Array<ColorDescriptor> {
        return ColorDescriptor.EMPTY_ARRAY
    }

    override fun getDisplayName(): String {
        return "MRCL"
    }

    override fun getIcon(): Icon {
        return MrclIcons.ICON
    }

    override fun getHighlighter(): SyntaxHighlighter {
        return MrclSyntaxHighlighter()
    }

    override fun getDemoText(): String {
        return """
            module test
            import something
            
            const MSG = "doit"
            
            proc doit {
                println MSG
            }
            """.trimIndent()
    }

    override fun getAdditionalHighlightingTagToDescriptorMap(): MutableMap<String, TextAttributesKey>? {
        return null
    }
}