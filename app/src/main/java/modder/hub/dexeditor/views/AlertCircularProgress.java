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
import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.TextView;

import modder.hub.dexeditor.R;

import android.view.KeyEvent;
import modder.hub.dexeditor.utils.SketchwareUtil;

// Author - @developer-krushna
public class AlertCircularProgress {
    private final Context context;
    private final Activity activity;
    private final androidx.appcompat.app.AlertDialog alertDialog;
    private final TextView progressMessage;
    private final TextView progressTitle;
    private final Handler uiHandler;
    private OnCancelListener cancelListener;
    private long lastBackPressTime = 0;
    private static final long DOUBLE_PRESS_INTERVAL = 2000;

    public interface OnCancelListener {
        void onCancel();
    }

    public AlertCircularProgress(Context mContext) {
        this.context = mContext;
        this.activity = (Activity) context;
        this.uiHandler = new Handler(Looper.getMainLooper());

        View view = View.inflate(context, R.layout.circular_progress, null);
        progressMessage = view.findViewById(R.id.progress_message);
        progressTitle = view.findViewById(R.id.progress_title);
        alertDialog = new com.google.android.material.dialog.MaterialAlertDialogBuilder(activity)
                .setCancelable(false)
                .setView(view)
                .create();
        setupBackPressCancellation();
    }

    private void setupBackPressCancellation() {
        alertDialog.setOnKeyListener(new android.content.DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(android.content.DialogInterface dialog, int keyCode, KeyEvent event) {
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

    public void setMessage(final String message) {
        uiHandler.post(new MessageRunnable(this, message));
    }

    public void setTitle(final String title) {
        uiHandler.post(new TitleRunnable(this, title));
    }

    public void show() {
        uiHandler.post(new ShowRunnable(this));
    }

    public void dismiss() {
        uiHandler.post(new DismissRunnable(this));
    }

    public static class MessageRunnable implements Runnable {
        private final AlertCircularProgress dialog;
        private final String message;

        public MessageRunnable(AlertCircularProgress dialog, String message) {
            this.dialog = dialog;
            this.message = message;
        }

        @Override
        public void run() {
            if (message == null || message.isEmpty()) {
                dialog.progressMessage.setVisibility(View.GONE);
            } else {
                dialog.progressMessage.setVisibility(View.VISIBLE);
                dialog.progressMessage.setText(message);
            }
        }
    }

    public static class TitleRunnable implements Runnable {
        private final AlertCircularProgress dialog;
        private final String title;

        public TitleRunnable(AlertCircularProgress dialog, String title) {
            this.dialog = dialog;
            this.title = title;
        }

        @Override
        public void run() {
            if (title == null || title.isEmpty()) {
                dialog.progressTitle.setVisibility(View.GONE);
            } else {
                dialog.progressTitle.setVisibility(View.VISIBLE);
                dialog.progressTitle.setText(title);
            }
        }
    }

    public static class ShowRunnable implements Runnable {
        private final AlertCircularProgress dialog;

        public ShowRunnable(AlertCircularProgress dialog) {
            this.dialog = dialog;
        }

        @Override
        public void run() {
            if (dialog.activity != null && !dialog.activity.isFinishing() && !dialog.activity.isDestroyed()) {
                dialog.alertDialog.show();
            }
        }
    }

    public static class DismissRunnable implements Runnable {
        private final AlertCircularProgress dialog;

        public DismissRunnable(AlertCircularProgress dialog) {
            this.dialog = dialog;
        }

        @Override
        public void run() {
            if (dialog.alertDialog != null && dialog.alertDialog.isShowing()) {
                dialog.alertDialog.dismiss();
            }
        }
    }
}
