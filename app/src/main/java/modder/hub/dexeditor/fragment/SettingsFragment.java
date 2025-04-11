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


package modder.hub.dexeditor.fragment;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.preference.EditTextPreference;
import androidx.preference.ListPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;
import modder.hub.dexeditor.R;
import modder.hub.dexeditor.activity.EditFloatingMenusActivity;

/*
Author @developer-krushna
Code fixed/ideas/comments by ChatGPT
*/

public class SettingsFragment extends PreferenceFragmentCompat 
implements SharedPreferences.OnSharedPreferenceChangeListener {
	
	// Preference keys
	private static final String PREFS_NAME = "editor_prefs";
	private static final String KEY_FONT_TYPE = "font_type";
	private static final String KEY_FONT_SIZE = "font_size";
	private static final String KEY_SHOW_INDENT = "show_indent_guide";
	private static final String KEY_SHOW_LINE_NUM = "show_line_numbers";
	private static final String KEY_FLOATING_MENU = "floating_menu";
	private static final String KEY_GEMINI_API = "gemini_api_key";
	private static final String KEY_EDIT_AI_PROMPT = "edit_ai_prompt";
	private static final String KEY_GEMINI_WEBSITE = "gemini_website";
	
	@Override
	public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
		// Initialize preferences
		getPreferenceManager().setSharedPreferencesName(PREFS_NAME);
		setPreferencesFromResource(R.xml.preferences, rootKey);
		
		setupFontPreferences();
		setupFloatingMenuPreference();
		setupAiPreferences();
	}
	
	private void setupFontPreferences() {
		updateSummary(findPreference(KEY_FONT_TYPE));
		updateSummary(findPreference(KEY_FONT_SIZE));
	}
	
	
	private void setupAiPreferences() {
		// API Key preference
		EditTextPreference apiKeyPref = findPreference(KEY_GEMINI_API);
		if (apiKeyPref != null) {
			apiKeyPref.setOnPreferenceChangeListener(new Preference.OnPreferenceChangeListener() {
				@Override
				public boolean onPreferenceChange(Preference preference, Object newValue) {
					return validateAndSetApiKey(preference, (String) newValue);
				}
			});
			
			// Set initial summary
			String currentApiKey = apiKeyPref.getText();
			apiKeyPref.setSummary(formatApiKeySummary(currentApiKey));
		}
		
		// Website preference
		Preference websitePref = findPreference(KEY_GEMINI_WEBSITE);
		if (websitePref != null) {
			websitePref.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
				@Override
				public boolean onPreferenceClick(Preference preference) {
					openChatGptWebsite();
					return true;
				}
			});
		}
	}
	
	private boolean validateAndSetApiKey(Preference preference, String apiKey) {
		apiKey = apiKey.trim();
		if (apiKey.isEmpty()) {
			Toast.makeText(getActivity(), "Input key", Toast.LENGTH_SHORT).show();
			return false;
		}
		preference.setSummary(formatApiKeySummary(apiKey));
		return true;
	}
	
	private String formatApiKeySummary(String apiKey) {
		return apiKey.isEmpty() ? "API Key not set" : 
		"••••••••" + apiKey.substring(Math.max(0, apiKey.length() - 4));
	}
	
	private void openChatGptWebsite() {
		try {
			startActivity(new Intent(Intent.ACTION_VIEW, 
			Uri.parse("https://aistudio.google.com/app/u/1/apikey")));
		} catch (Exception e) {
			Toast.makeText(getActivity(), "No browser found", Toast.LENGTH_SHORT).show();
		}
	}
	
	private void setupFloatingMenuPreference() {
		Preference floatingMenuPref = findPreference(KEY_FLOATING_MENU);
		if (floatingMenuPref != null) {
			floatingMenuPref.setOnPreferenceClickListener(new Preference.OnPreferenceClickListener() {
				@Override
				public boolean onPreferenceClick(Preference preference) {
					startActivity(new Intent(getActivity(), EditFloatingMenusActivity.class));
					return true;
				}
			});
		}
	}
	
	
	
	private void updateSummary(Preference preference) {
		if (preference instanceof ListPreference) {
			preference.setSummary(((ListPreference) preference).getEntry());
		}
	}
	
	@Override
	public void onResume() {
		super.onResume();
		getPreferenceManager().getSharedPreferences().registerOnSharedPreferenceChangeListener(this);
	}
	
	@Override
	public void onPause() {
		super.onPause();
		getPreferenceManager().getSharedPreferences().unregisterOnSharedPreferenceChangeListener(this);
	}
	
	@Override
	public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
		updateSummary(findPreference(key));
	}
	
	
	public static String getFontType(Context context) {
		return getPrefs(context).getString(KEY_FONT_TYPE, "normal");
	}
	
	public static int getFontSize(Context context) {
		try {
			return Integer.parseInt(getPrefs(context).getString(KEY_FONT_SIZE, "14"));
		} catch (NumberFormatException e) {
			return 14;
		}
	}
	
	public static boolean showIndentGuide(Context context) {
		return getPrefs(context).getBoolean(KEY_SHOW_INDENT, true);
	}
	
	public static boolean showLineNumbers(Context context) {
		return getPrefs(context).getBoolean(KEY_SHOW_LINE_NUM, true);
	}
	
	public static boolean shouldEditAiPrompt(Context context) {
		return getPrefs(context).getBoolean(KEY_EDIT_AI_PROMPT, false);
	}
	
	public static String getChatGptApiKey(Context context) {
		return getPrefs(context).getString(KEY_GEMINI_API, "");
	}
	
	private static SharedPreferences getPrefs(Context context) {
		return context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
	}
}
