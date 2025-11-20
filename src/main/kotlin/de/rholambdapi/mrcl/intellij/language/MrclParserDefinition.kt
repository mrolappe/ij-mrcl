package de.rholambdapi.mrcl.intellij.language

import com.intellij.lang.ASTNode
import com.intellij.lang.ParserDefinition
import com.intellij.lang.PsiParser
import com.intellij.lexer.Lexer
import com.intellij.openapi.diagnostic.thisLogger
import com.intellij.openapi.project.Project
import com.intellij.psi.FileViewProvider
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiFile
import com.intellij.psi.tree.IElementType
import com.intellij.psi.tree.IFileElementType
import com.intellij.psi.tree.TokenSet
import de.rholambdapi.mrcl.intellij.language.psi.MrclFile
import de.rholambdapi.mrcl.parser.antlr.MrclLexer
import de.rholambdapi.mrcl.parser.antlr.MrclParser
import org.antlr.intellij.adaptor.lexer.ANTLRLexerAdaptor
import org.antlr.intellij.adaptor.lexer.PSIElementTypeFactory
import org.antlr.intellij.adaptor.lexer.RuleIElementType
import org.antlr.intellij.adaptor.lexer.TokenIElementType
import org.antlr.intellij.adaptor.parser.ANTLRParserAdaptor
import org.antlr.intellij.adaptor.psi.ANTLRPsiNode
import org.antlr.v4.runtime.Parser
import org.antlr.v4.runtime.tree.ParseTree

class MrclParserDefinition : ParserDefinition {
    private val log = thisLogger()

    companion object {
        val INSTANCE = MrclParserDefinition()
        val FILE = IFileElementType(MrclLanguage.INSTANCE)
        val ID: TokenIElementType
        val COMMENT_TOKENS: TokenSet
        val WS_TOKENS: TokenSet
        val STRING_TOKENS: TokenSet

        init {
            PSIElementTypeFactory.defineLanguageIElementTypes(MrclLanguage.INSTANCE, MrclParser.tokenNames, MrclParser.ruleNames)
            val tokenIElementTypes = PSIElementTypeFactory.getTokenIElementTypes(MrclLanguage.INSTANCE)
            ID = tokenIElementTypes[MrclLexer.ID]
            COMMENT_TOKENS = PSIElementTypeFactory.createTokenSet(
                MrclLanguage.INSTANCE,
                MrclLexer.LINE_COMMENT,
                MrclLexer.BLOCK_COMMENT
            )
            WS_TOKENS = PSIElementTypeFactory.createTokenSet(MrclLanguage.INSTANCE, MrclLexer.WS, MrclLexer.EOL)
            STRING_TOKENS = PSIElementTypeFactory.createTokenSet(MrclLanguage.INSTANCE, MrclLexer.STRING_LITERAL)
        }
    }

    override fun createLexer(project: Project?): Lexer {
        val lexer = MrclLexer(null)
        return ANTLRLexerAdaptor(MrclLanguage.INSTANCE, lexer)
    }

    override fun createParser(project: Project?): PsiParser {
        val parser = MrclParser(null)
        return object : ANTLRParserAdaptor(MrclLanguage.INSTANCE, parser) {
            override fun parse(parser: Parser?, root: IElementType?): ParseTree {
                log.debug("createParser ANTLRParserAdaptor::parse, parser: $parser, root: $root")

                return (parser!! as MrclParser).mrclFile()
            }

        }
    }

    override fun getFileNodeType(): IFileElementType {
        return FILE
    }

    override fun getCommentTokens(): TokenSet {
        return COMMENT_TOKENS
    }

    override fun getStringLiteralElements(): TokenSet {
        return STRING_TOKENS
    }

    override fun getWhitespaceTokens(): TokenSet {
       return WS_TOKENS
    }

    override fun spaceExistenceTypeBetweenTokens(left: ASTNode?, right: ASTNode?): ParserDefinition.SpaceRequirements {
        return ParserDefinition.SpaceRequirements.MAY
    }

    override fun createElement(node: ASTNode?): PsiElement {
        val elementType = node!!.elementType

        if (elementType is TokenIElementType || elementType !is RuleIElementType) {
            return ANTLRPsiNode(node)
        }

        log.debug("createElement node: $node")

        when (elementType.ruleIndex) {
            // TODO
            else -> return ANTLRPsiNode(node)
        }
    }

    override fun createFile(viewProvider: FileViewProvider): PsiFile {
        return MrclFile(viewProvider)
    }
}