package de.rholambdapi.mrcl.intellij.language

import com.intellij.lang.Commenter

class MrclCommenter : Commenter {
    override fun getLineCommentPrefix(): String {
        return "//"
    }

    override fun getBlockCommentPrefix(): String {
        return "/*"
    }

    override fun getBlockCommentSuffix(): String {
        return "*/"
    }

    override fun getCommentedBlockCommentPrefix(): String {
        return "/*"
    }

    override fun getCommentedBlockCommentSuffix(): String {
        return "*/"
    }
}