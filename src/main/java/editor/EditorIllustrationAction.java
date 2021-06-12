package editor;

import com.intellij.openapi.actionSystem.AnAction;
import com.intellij.openapi.actionSystem.AnActionEvent;
import com.intellij.openapi.actionSystem.CommonDataKeys;
import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.editor.Caret;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.ui.popup.Balloon;
import com.intellij.openapi.ui.popup.JBPopupFactory;
import com.intellij.ui.Gray;
import com.intellij.ui.JBColor;
import org.jetbrains.annotations.NotNull;

public class EditorIllustrationAction extends AnAction {
    @Override
    public void actionPerformed(@NotNull AnActionEvent e) {
        final Editor editor = e.getRequiredData(CommonDataKeys.EDITOR);
        final Project project = e.getRequiredData(CommonDataKeys.PROJECT);
        final Document document = editor.getDocument();
        Caret primaryCaret = editor.getCaretModel().getPrimaryCaret();
        int start = primaryCaret.getSelectionStart();
        int end = primaryCaret.getSelectionEnd();
        JBPopupFactory jbPopupFactory = JBPopupFactory.getInstance();
        Balloon balloon = jbPopupFactory.createHtmlTextBalloonBuilder("Converted", null,
                new JBColor(Gray._242, Gray._0), null).setFadeoutTime(1000).createBalloon();
        WriteCommandAction.runWriteCommandAction(project, () -> {
            document.replaceString(start, end, PersianDigits.convert(primaryCaret.getSelectedText()));
            balloon.show(jbPopupFactory.guessBestPopupLocation(editor), Balloon.Position.below);
        });
        primaryCaret.removeSelection();
    }

    @Override
    public void update(@NotNull AnActionEvent e) {
        final Project project = e.getProject();
        final Editor editor = e.getData(CommonDataKeys.EDITOR);
        e.getPresentation().setEnabledAndVisible(
                project != null && editor != null && editor.getSelectionModel().hasSelection()
        );
    }
}
