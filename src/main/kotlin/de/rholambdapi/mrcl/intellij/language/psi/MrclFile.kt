package de.rholambdapi.mrcl.intellij.language.psi

import com.intellij.extapi.psi.PsiFileBase
import com.intellij.openapi.fileTypes.FileType
import com.intellij.psi.FileViewProvider
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiNamedElement
import de.rholambdapi.mrcl.intellij.language.MrclFileType
import de.rholambdapi.mrcl.intellij.language.MrclIcons
import de.rholambdapi.mrcl.intellij.language.MrclLanguage
import org.antlr.intellij.adaptor.SymtabUtils
import org.antlr.intellij.adaptor.psi.ScopeNode
import javax.swing.Icon

class MrclFile(viewProvider: FileViewProvider) : PsiFileBase(viewProvider, MrclLanguage.INSTANCE), ScopeNode {
    override fun getFileType(): FileType {
        return MrclFileType.INSTANCE
    }

    override fun toString(): String {
        return "MRCL File"
    }

    override fun getIcon(flags: Int): Icon {
        return MrclIcons.ICON
    }

    override fun getContext(): ScopeNode? {
        return null
    }

    override fun resolve(element: PsiNamedElement?): PsiElement? {
        //		System.out.println(getClass().getSimpleName()+
//		                   ".resolve("+element.getName()+
//		                   " at "+Integer.toHexString(element.hashCode())+")");
//        if ( element?.parent is CallSubtree ) {
//            return SymtabUtils.resolve(this, SampleLanguage.INSTANCE,
//                element, "/script/function/ID");
//        }
//        return SymtabUtils.resolve(this, SampleLanguage.INSTANCE,
//            element, "/script/vardef/ID");
        return SymtabUtils.resolve(this, MrclLanguage.INSTANCE, element, "/TODO")
    }
}