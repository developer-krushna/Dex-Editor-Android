/*
* Dex-Editor-Android an Advanced Dex Editor for Android 
* Copyright 2024-25, developer-krushna
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


import android.Manifest;
import android.animation.*;
import android.app.*;
import android.app.Activity;
import android.content.*;
import android.content.ClipData;
import android.content.ClipboardManager;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.res.*;
import android.graphics.*;
import android.graphics.drawable.*;
import android.media.*;
import android.net.*;
import android.os.*;
import android.text.*;
import android.text.method.LinkMovementMethod;
import android.text.style.*;
import android.util.*;
import android.view.*;
import android.view.View.*;
import android.view.animation.*;
import android.webkit.*;
import android.widget.*;
import android.app.AlertDialog;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import com.google.android.material.appbar.AppBarLayout;
import io.noties.markwon.Markwon;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.IOException;
import android.util.Log;
import java.net.SocketTimeoutException;
import java.net.UnknownHostException;
import java.util.concurrent.TimeUnit;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONArray;

import modder.hub.dexeditor.fragment.*;
import modder.hub.dexeditor.smali.Smali2Java;
import modder.hub.dexeditor.views.*;
import modder.hub.dexeditor.utils.*;
import modder.hub.dexeditor.R;

/*
Author @developer-krushna
Code fixed comments by ChatGPT
*/

public class AIOverViewActivity extends AppCompatActivity {
	private AppBarLayout appBarLayout;
	private CoordinatorLayout coordinatorLayout;
	private Toolbar toolbar;
	private TextView markdownText;
	private ScrollView scrollView;
	private String smaliCode = "";
	private String GEMINI_API_KEY = "";
	private ProgressDialog progressDialog;
	private String DefaultMsg = "Analyze this Smali method and explain the register usage:\n\nMethod:\n";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.ai_overview_activity);
		initialize(savedInstanceState);
		initializeLogic();
		
	}
	
	private void initialize(Bundle savedInstanceState) {
		appBarLayout = findViewById(R.id._app_bar);
		coordinatorLayout = findViewById(R.id._coordinator);
		toolbar = findViewById(R.id._toolbar);
		setSupportActionBar(toolbar);
		
		getSupportActionBar().setDisplayHomeAsUpEnabled(true);
		getSupportActionBar().setHomeButtonEnabled(true);
		
		toolbar.setNavigationOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				onBackPressed();
			}
		});
		
		markdownText = findViewById(R.id.markdownText);
		scrollView = findViewById(R.id.scrollView);
	}
	private void initializeLogic() {
		setTitle("AI Explanation");
		markdownText.setTextIsSelectable(true);
		smaliCode = getIntent().getStringExtra("smali");
		SharedPreferences pref = getSharedPreferences("editor_prefs", MODE_PRIVATE);
		if (pref.contains("gemini_api_key")) {
			GEMINI_API_KEY = pref.getString("gemini_api_key", "Key");
		} else {
			startActivity(new Intent(AIOverViewActivity.this, SettingsActivity.class));
		}
		boolean allowEditPrompt = SettingsFragment.shouldEditAiPrompt(this);
		if (allowEditPrompt) {
			showPromptEditorDialog();
		} else {
			startAnalysis(DefaultMsg + smaliCode);
		}
	}
	private void showPromptEditorDialog() {
		final EditText input = new EditText(this);
		input.setHint("Enter your custom prompt");
		input.setInputType(InputType.TYPE_CLASS_TEXT | InputType.TYPE_TEXT_FLAG_MULTI_LINE);
		
		FrameLayout container = new FrameLayout(this);
		int padding = (int) (20 * getResources().getDisplayMetrics().density);
		FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.WRAP_CONTENT);
		params.leftMargin = padding;
		params.topMargin = padding;
		params.rightMargin = padding;
		params.bottomMargin = padding;
		input.setLayoutParams(params);
		container.addView(input);
		
		AlertDialog dialog = new AlertDialog.Builder(this)
		.setTitle("Edit Prompt")
		.setView(container)
		.setPositiveButton("OK", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialogInterface, int i) {
				String customPrompt = input.getText().toString().trim();
				if (customPrompt.isEmpty()) {
					customPrompt = DefaultMsg + smaliCode;
				} else {
					customPrompt = customPrompt + "\n" + smaliCode;
				}
				startAnalysis(customPrompt);
			}
		})
		.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialogInterface, int i) {
				finish();
			}
		})
		.create();
		
		dialog.show();
	}
	
	private void startAnalysis(String prompt) {
		progressDialogShow(true);
		
		OkHttpClient client = new OkHttpClient.Builder()
		.connectTimeout(30, TimeUnit.SECONDS)
		.readTimeout(30, TimeUnit.SECONDS)
		.writeTimeout(30, TimeUnit.SECONDS)
		.build();
		
		try {
			// Build the request JSON
			JSONObject requestBody = new JSONObject();
			JSONArray contents = new JSONArray();
			JSONObject content = new JSONObject();
			JSONArray parts = new JSONArray();
			JSONObject part = new JSONObject();
			
			part.put("text", prompt);
			parts.put(part);
			content.put("parts", parts);
			contents.put(content);
			requestBody.put("contents", contents);
			
			Request request = new Request.Builder()
			.url("https://generativelanguage.googleapis.com/v1beta/models/gemini-2.0-flash:generateContent?key=" + GEMINI_API_KEY)
			.post(RequestBody.create(MediaType.parse("application/json"), requestBody.toString()))
			.addHeader("Content-Type", "application/json")
			.build();
			
			client.newCall(request).enqueue(new Callback() {
				@Override
				public void onFailure(Call call, IOException e) {
					runOnUiThread(new Runnable() {
						@Override
						public void run() {
							progressDialogShow(false);
							markdownText.setText("Network error: " + e.getMessage());
						}
					});
				}
				
				@Override
				public void onResponse(Call call, Response response) throws IOException {
					String responseBody = response.body().string();
					runOnUiThread(new Runnable() {
						@Override
						public void run() {
							try {
								JSONObject jsonResponse = new JSONObject(responseBody);
								if (jsonResponse.has("candidates")) {
									String generatedText = jsonResponse.getJSONArray("candidates")
									.getJSONObject(0)
									.getJSONObject("content")
									.getJSONArray("parts")
									.getJSONObject(0)
									.getString("text");
									
									renderMarkdown(generatedText);
								} else {
									markdownText.setText("Error: No candidates in response");
								}
							} catch (Exception e) {
								markdownText.setText("Error parsing response");
							}
							progressDialogShow(false);
						}
					});
				}
			});
		} catch (JSONException e) {
			progressDialogShow(false);
			markdownText.setText("Error creating request");
		}
	}
	
	private void renderMarkdown(String content) {
		Markwon markwon = Markwon.create(AIOverViewActivity.this);
		markwon.setMarkdown(markdownText, content);
	}
	
	
	private void progressDialogShow(boolean show) {
		if (show) {
			if (progressDialog == null) {
				progressDialog = new ProgressDialog(this);
				progressDialog.setCancelable(false);
				progressDialog.setCanceledOnTouchOutside(false);
				progressDialog.requestWindowFeature(1);
				progressDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
			}
			progressDialog.setMessage(null);
			try {
				runOnUiThread(new Runnable() {
					@Override
					public void run() {
						progressDialog.show();
					}
				});
			} catch (WindowManager.BadTokenException e) {
				// Ignore
			}
			progressDialog.setContentView(R.layout.loading);
			LinearLayout backgroundLayout = progressDialog.findViewById(R.id.background);
			GradientDrawable gradientDrawable = new GradientDrawable();
			gradientDrawable.setColor(Color.parseColor("#E0E0E0"));
			gradientDrawable.setCornerRadius(40.0f);
			gradientDrawable.setStroke(0, Color.WHITE);
			progressDialog.findViewById(R.id.linear2).setBackground(gradientDrawable);
			((LinearLayout) progressDialog.findViewById(R.id.layout_progress)).addView(new RadialProgressView(this));
		} else if (progressDialog != null) {
			runOnUiThread(new Runnable() {
				@Override
				public void run() {
					progressDialog.dismiss();
				}
			});
		}
	}
}
