package de.rholambdapi.mrcl.intellij.language

import com.intellij.openapi.fileTypes.LanguageFileType
import javax.swing.Icon

class MrclFileType : LanguageFileType(MrclLanguage.INSTANCE) {
    companion object {
        val INSTANCE = MrclFileType()
    }

    override fun getName(): String {
        return "MRCL"
    }

    override fun getDescription(): String {
        return "MRCL file"
    }

    override fun getDefaultExtension(): String {
        return "mrcl"
    }

    override fun getIcon(): Icon {
        return MrclIcons.ICON
    }
}