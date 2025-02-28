/*
*
* Dex-Editor-Android an Advanced Dex Editor for Android 
* Copyright 2024, developer-krushna
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


package modder.hub.dexeditor.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import com.blogspot.atifsoftwares.animatoolib.Animatoo;
import com.google.android.material.appbar.AppBarLayout;
import io.github.rosemoe.sora.langs.java.JavaLanguage;
import io.github.rosemoe.sora.widget.CodeEditor;
import java.util.Timer;
import java.util.TimerTask;
import modder.hub.dexeditor.views.*;
import modder.hub.dexeditor.utils.*;
import modder.hub.dexeditor.R;

/*
Author @developer-krushna
Code fixed comments by ChatGPT
*/


public class JavaViewActivity extends AppCompatActivity {
	private AppBarLayout appBarLayout;
	private CoordinatorLayout coordinatorLayout;
	private Timer timer = new Timer();
	private Toolbar toolbar;
	private CodeEditor codeEditor;
	private TimerTask timerTask;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.java_view);
		initialize(savedInstanceState);
		
		// Check and request storage permission if not granted
		if (ContextCompat.checkSelfPermission(this, "android.permission.READ_EXTERNAL_STORAGE") == -1) {
			ActivityCompat.requestPermissions(this, new String[]{"android.permission.READ_EXTERNAL_STORAGE"}, 1000);
		} else {
			initializeLogic();
		}
	}
	
	@Override
	public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
		super.onRequestPermissionsResult(requestCode, permissions, grantResults);
		if (requestCode == 1000) {
			initializeLogic();
		}
	}
	
	// Initialize UI components
	private void initialize(Bundle savedInstanceState) {
		appBarLayout = findViewById(R.id._app_bar);
		coordinatorLayout = findViewById(R.id._coordinator);
		toolbar = findViewById(R.id._toolbar);
		setSupportActionBar(toolbar);
		
		// Enable back button in the toolbar
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setHomeButtonEnabled(true);
		
		// Set click listener for the back button
		toolbar.setNavigationOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				onBackPressed();
			}
		});
		
		codeEditor = findViewById(R.id.editor);
	}
	
	// Initialize logic for loading and displaying Java code
	private void initializeLogic() {
		codeEditor.setEditorLanguage(new JavaLanguage());
		codeEditor.setEditable(false);
		
		// Show a loading dialog
		final AlertProgress loadingDialog = new AlertProgress(this);
		loadingDialog.setTitle("Java View");
		loadingDialog.setMessage("Loading...");
		loadingDialog.setIndeterminate(true);
		loadingDialog.show();
		
		// Start a background thread to load the content
		new ContentLoaderThread(new Handler() {
			@Override
			public void handleMessage(Message message) {
				loadingDialog.dismiss();
			}
		}).start();
	}
	
	// Background thread to load content
	private class ContentLoaderThread extends Thread {
		private final Handler handler;
		
		ContentLoaderThread(Handler handler) {
			this.handler = handler;
		}
		
		@Override
		public void run() {
			Looper.prepare();
			try {
				timerTask = new TimerTask() {
					@Override
					public void run() {
						runOnUiThread(new Runnable() {
							@Override
							public void run() {
								// Load and display Java code based on the intent extras
								if (getIntent().hasExtra("Smali2Java")) {
									codeEditor.setText(FileUtil.readFile(getIntent().getStringExtra("Smali2Java")));
									getSupportActionBar().setSubtitle("Smali 2 Java");
									
									String fileName = getIntent().getStringExtra("Smali2JavaName");
									if (fileName.isEmpty()) {
										setTitle("Main.java");
									} else {
										setTitle(fileName);
									}
								} else if (getIntent().hasExtra("Method2JavaTitle")) {
									setTitle(getIntent().getStringExtra("Method2JavaTitle"));
									getSupportActionBar().setSubtitle(getIntent().getStringExtra("Method2JavaSubtitle"));
									codeEditor.setText(FileUtil.readFile(getIntent().getStringExtra("Method2JavaPath")));
								}
							}
						});
					}
				};
				timer.schedule(timerTask, 200L); // Schedule the task after 200ms
			} catch (Exception e) {
				// Handle exceptions silently
			}
			handler.sendEmptyMessage(0); // Notify the handler that the task is done
			Looper.loop();
		}
	}
	
	@Override
	public void onBackPressed() {
		finish();
		Animatoo.animateSlideLeft(this); // Apply slide animation when going back
	}
}
