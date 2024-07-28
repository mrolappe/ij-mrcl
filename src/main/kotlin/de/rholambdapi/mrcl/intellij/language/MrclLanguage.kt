package de.rholambdapi.mrcl.intellij.language

import com.intellij.lang.Language


class MrclLanguage : Language("MRCL") {
    companion object {
        @JvmStatic
        val INSTANCE = MrclLanguage()
    }
}
