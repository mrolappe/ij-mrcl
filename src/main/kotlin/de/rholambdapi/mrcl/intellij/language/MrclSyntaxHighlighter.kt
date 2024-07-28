package de.rholambdapi.mrcl.intellij.language

import com.intellij.lexer.Lexer
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors
import com.intellij.openapi.editor.colors.TextAttributesKey
import com.intellij.openapi.editor.colors.TextAttributesKey.createTextAttributesKey
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase
import com.intellij.psi.tree.IElementType
import de.rholambdapi.mrcl.parser.antlr.MrclLexer
import de.rholambdapi.mrcl.parser.antlr.MrclParser
import org.antlr.intellij.adaptor.lexer.ANTLRLexerAdaptor
import org.antlr.intellij.adaptor.lexer.PSIElementTypeFactory
import org.antlr.intellij.adaptor.lexer.TokenIElementType


class MrclSyntaxHighlighter : SyntaxHighlighterBase() {
    companion object {
        val IDENTIFIER = createTextAttributesKey("MRCL_IDENTIFIER", DefaultLanguageHighlighterColors.IDENTIFIER)
        val KEYWORD = createTextAttributesKey("MRCL_KEYWORD", DefaultLanguageHighlighterColors.KEYWORD)
        val STRING = createTextAttributesKey("MRCL_STRING", DefaultLanguageHighlighterColors.STRING)
        val LINE_COMMENT = createTextAttributesKey("MRCL_LINE_COMMENT", DefaultLanguageHighlighterColors.LINE_COMMENT)
        val BLOCK_COMMENT = createTextAttributesKey("MRCL_BLOCK_COMMENT", DefaultLanguageHighlighterColors.BLOCK_COMMENT)

        init {
            PSIElementTypeFactory.defineLanguageIElementTypes(
                MrclLanguage.INSTANCE,
                MrclParser.tokenNames,
                MrclParser.ruleNames
            )
        }
    }

    override fun getHighlightingLexer(): Lexer {
        val lexer = MrclLexer(null)
        return ANTLRLexerAdaptor(MrclLanguage.INSTANCE, lexer)
    }

    override fun getTokenHighlights(tokenType: IElementType?): Array<TextAttributesKey> {
        if (tokenType !is TokenIElementType) return TextAttributesKey.EMPTY_ARRAY

        val key = when (tokenType.antlrTokenType) {
            MrclLexer.ID -> IDENTIFIER
            MrclLexer.CONST, MrclLexer.IMPORT, MrclLexer.MODULE, MrclLexer.PRINTLN, MrclLexer.PROC -> KEYWORD
            else -> return TextAttributesKey.EMPTY_ARRAY
        }

        return arrayOf(key)
    }
}