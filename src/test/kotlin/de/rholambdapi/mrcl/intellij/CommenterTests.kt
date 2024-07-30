package de.rholambdapi.mrcl.intellij

import com.intellij.codeInsight.actions.MultiCaretCodeInsightAction
import com.intellij.codeInsight.generation.actions.CommentByLineCommentAction
import com.intellij.openapi.editor.Editor
import com.intellij.openapi.project.Project
import com.intellij.testFramework.fixtures.LightPlatformCodeInsightFixture4TestCase
import de.rholambdapi.mrcl.intellij.language.MrclFileType
import org.junit.Test

class CommenterTests : LightPlatformCodeInsightFixture4TestCase() {
    @Test
    fun shouldCommentLineByAction() {
        myFixture.configureByText(MrclFileType.INSTANCE, "<caret>module test")
        performAction(CommentByLineCommentAction(), project, myFixture.editor)
        myFixture.checkResult("//module test")

        performAction(CommentByLineCommentAction(), project, myFixture.editor)
        myFixture.checkResult("module test")
    }
}

private fun performAction(action: MultiCaretCodeInsightAction, project: Project, editor: Editor) {
    action.actionPerformedImpl(project, editor)
}
