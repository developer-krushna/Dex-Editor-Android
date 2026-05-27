package modder.hub.dexeditor.utils;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.Context;
import android.text.SpannableString;
import android.text.style.ForegroundColorSpan;
import android.view.MenuItem;
import android.widget.Toast;

/**
 * UIHelper: Common UI utilities to avoid code duplication across activities and fragments.
 */
public class UIHelper {

    public static void copyToClipboard(Context context, String text) {
        ClipboardManager clipboard = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
        ClipData clip = ClipData.newPlainText("Copied Text", text);
        if (clipboard != null) {
            clipboard.setPrimaryClip(clip);
            Toast.makeText(context, "Text has been copied to clipboard", Toast.LENGTH_SHORT).show();
        }
    }

    public static void setMenuItemColor(MenuItem item, int color) {
        SpannableString s = new SpannableString(item.getTitle());
        s.setSpan(new ForegroundColorSpan(color), 0, s.length(), 0);
        item.setTitle(s);
    }
}
