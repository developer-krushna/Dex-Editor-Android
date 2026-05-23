/*
 * Dex-Editor-Android an Advanced Dex Editor for Android
 * Copyright 2024-26, developer-krushna
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions are
 * met:
 *
 *     * Redistributions of source code must retain the above copyright
 * notice, this list of conditions and the following disclaimer.
 *     * Redistributions in binary form must reproduce the above
 * copyright notice, this list of conditions and the following disclaimer
 * in the documentation and/or other materials provided with the
 * distribution.
 *     * Neither the name of developer-krushna nor the names of its
 * contributors may be used to endorse or promote products derived from
 * this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS
 * "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT
 * LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR
 * A PARTICULAR PURPOSE ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT
 * OWNER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
 * SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT
 * LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR SERVICES; LOSS OF USE,
 * DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY
 * THEORY OF LIABILITY, WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT
 * (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
 * OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.


 *     Please contact Krushna by email mt.modder.hub@gmail.com if you need
 *     additional information or have any questions
 */


package modder.hub.dexeditor.views;

import android.app.Activity;
import android.content.DialogInterface;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.progressindicator.LinearProgressIndicator;

import modder.hub.dexeditor.R;
import modder.hub.dexeditor.utils.SketchwareUtil;

/* 
Author @developer-krushna
*/

public class AlertProgress {

    private static final long DOUBLE_PRESS_INTERVAL = 2000;
    Activity activity;
    MaterialAlertDialogBuilder process;
    AlertDialog alert;
    TextView textview_mesage;
    TextView textview_title;
    LinearProgressIndicator progress;
    private OnCancelListener cancelListener;
    private long lastBackPressTime = 0;

    public AlertProgress(Activity activity) {
        this.activity = activity;
        process = new MaterialAlertDialogBuilder(activity);
        View view = View.inflate(activity, R.layout.progress_dlg, null);
        textview_mesage = view.findViewById(R.id.message);
        progress = view.findViewById(R.id.progress);
        textview_title = view.findViewById(R.id.title);

        process.setCancelable(false);
        process.setView(view);

        alert = process.create();
        setupBackPressCancellation();
    }

    private void setupBackPressCancellation() {
        alert.setOnKeyListener(new DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                if (keyCode == KeyEvent.KEYCODE_BACK && event.getAction() == KeyEvent.ACTION_UP) {
                    long currentTime = System.currentTimeMillis();
                    if (currentTime - lastBackPressTime < DOUBLE_PRESS_INTERVAL) {
                        if (cancelListener != null) {
                            cancelListener.onCancel();
                        }
                        dismiss();
                    } else {
                        lastBackPressTime = currentTime;
                        SketchwareUtil.showMessage(activity, activity.getString(R.string.press_again_msg));
                    }
                    return true;
                }
                return false;
            }
        });
    }

    public void setOnCancelListener(OnCancelListener listener) {
        this.cancelListener = listener;
    }

    public void setCancelable(boolean cancelable) {
        alert.setCancelable(cancelable);
    }

    public void setCanceledOnTouchOutside(boolean cancelable) {
        alert.setCanceledOnTouchOutside(cancelable);
    }

    public void setProgress(final int value) {
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                progress.setVisibility(View.VISIBLE);
                progress.setProgress(value);
            }
        });
    }

    public void setMax(final int max) {
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                progress.setMax(max);
            }
        });
    }

    public boolean isShowing() {
        return alert != null && alert.isShowing();
    }

    public void setTitle(final String title) {
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (title == null || title.isEmpty()) {
                    textview_title.setVisibility(View.GONE);
                } else {
                    textview_title.setVisibility(View.VISIBLE);
                    textview_title.setText(title);
                }
            }
        });
    }

    public void setMessage(final String message) {
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                if (message == null || message.isEmpty()) {
                    textview_mesage.setVisibility(View.GONE);
                } else {
                    textview_mesage.setVisibility(View.VISIBLE);
                    textview_mesage.setText(message);
                }
            }
        });
    }

    public void setProgress(final int value, final int max) {

        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                progress.setVisibility(View.VISIBLE);
                progress.setProgress(value);
                progress.setMax(max);
            }
        });
    }

    public void setIndeterminate(final boolean bool) {

        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                progress.setIndeterminate(bool);
            }
        });

    }

    public void show() {
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                alert.show();
            }
        });
    }

    public void dismiss() {
        activity.runOnUiThread(new Runnable() {
            @Override
            public void run() {
                alert.dismiss();
            }
        });
    }

    public void setButton(int whichButton, CharSequence text, android.content.DialogInterface.OnClickListener listener) {
        process.setPositiveButton(text, listener);
        // Re-create the alert to apply the button
        alert = process.create();
    }

    public interface OnCancelListener {
        void onCancel();
    }

}
