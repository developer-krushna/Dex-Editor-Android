package modder.hub.dexeditor.utils;

import io.github.rosemoe.sora.text.Content;
import io.github.rosemoe.sora.text.Cursor;
import io.github.rosemoe.sora.widget.CodeEditor;

/**
 * EditorHelper: Shared logic for CodeEditor operations like line manipulation, indentation, etc.
 * Refactored to keep DexEditorActivity clean.
 */
public class EditorHelper {

    public static void copyLine(CodeEditor editor) {
        int line = editor.getCursor().getLeftLine();
        String text = editor.getText().getLineString(line);
        UIHelper.copyToClipboard(editor.getContext(), text);
    }

    public static void cutLine(CodeEditor editor) {
        int line = editor.getCursor().getLeftLine();
        String text = editor.getText().getLineString(line);
        UIHelper.copyToClipboard(editor.getContext(), text);
        editor.getText().delete(line, 0, line, editor.getText().getColumnCount(line));
        if (line < editor.getText().getLineCount() - 1) {
            editor.getText().delete(line, editor.getText().getColumnCount(line), line + 1, 0);
        }
    }

    public static void deleteLine(CodeEditor editor) {
        int line = editor.getCursor().getLeftLine();
        editor.getText().delete(line, 0, line, editor.getText().getColumnCount(line));
        if (line < editor.getText().getLineCount() - 1) {
            editor.getText().delete(line, editor.getText().getColumnCount(line), line + 1, 0);
        }
    }

    public static void emptyLine(CodeEditor editor) {
        int line = editor.getCursor().getLeftLine();
        editor.getText().delete(line, 0, line, editor.getText().getColumnCount(line));
    }

    public static void duplicateLine(CodeEditor editor) {
        int line = editor.getCursor().getLeftLine();
        String text = editor.getText().getLineString(line);
        editor.getText().insert(line, editor.getText().getColumnCount(line), "\n" + text);
    }

    public static void convertSelectedTextCase(CodeEditor editor, boolean toUpper) {
        if (editor.getCursor().isSelected()) {
            int start = editor.getCursor().getLeft();
            int end = editor.getCursor().getRight();
            String selectedText = editor.getText().substring(start, end);
            editor.getText().replace(start, end, toUpper ? selectedText.toUpperCase() : selectedText.toLowerCase());
        }
    }

    public static void indent(CodeEditor editor, boolean indent) {
        if (editor.getCursor().isSelected()) {
            int startLine = editor.getCursor().getLeftLine();
            int endLine = editor.getCursor().getRightLine();
            editor.getText().beginBatchEdit();
            for (int i = startLine; i <= endLine; i++) {
                if (indent) {
                    editor.getText().insert(i, 0, "\t");
                } else {
                    String line = editor.getText().getLineString(i);
                    if (line.startsWith("\t")) {
                        editor.getText().delete(i, 0, i, 1);
                    } else if (line.startsWith("    ")) {
                        editor.getText().delete(i, 0, i, 4);
                    }
                }
            }
            editor.getText().endBatchEdit();
        } else {
            int line = editor.getCursor().getLeftLine();
            if (indent) {
                editor.getText().insert(line, 0, "\t");
            } else {
                String lineStr = editor.getText().getLineString(line);
                if (lineStr.startsWith("\t")) {
                    editor.getText().delete(line, 0, line, 1);
                } else if (lineStr.startsWith("    ")) {
                    editor.getText().delete(line, 0, line, 4);
                }
            }
        }
    }
}
