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


package modder.hub.dexeditor.utils;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Environment;
import android.provider.Settings;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import android.Manifest;
import modder.hub.dexeditor.MainActivity;

public class FilePermissionManager {
	
	/*
	Author @developer-krushna
	*/
	
	public static final int REQUEST_STORAGE_PERMISSION = 1001;
	private static boolean showingDialog = false;
	
	public interface PermissionCallback {
		void onPermissionGranted();
		void onPermissionDenied();
	}
	
	public static void checkStoragePermission(Context context, MainActivity activity, PermissionCallback callback) {
		if (hasStoragePermission(context)) {
			callback.onPermissionGranted();
			return;
		}
		if (showingDialog) {
			return; 
		}
		showingDialog = true;
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
			showManageAllFilesDialog(context, activity, callback);
		} else {
			showLegacyPermissionDialog(context, activity, callback);
		}
	}
	
	private static boolean hasStoragePermission(Context context) {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
			return Environment.isExternalStorageManager();
		} else {
			return ContextCompat.checkSelfPermission(context, Manifest.permission.READ_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED &&
			ContextCompat.checkSelfPermission(context, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED;
		}
	}
	
	private static void showLegacyPermissionDialog(final Context context, final MainActivity activity, final PermissionCallback callback) {
		new AlertDialog.Builder(context)
		.setTitle("Storage Permission Required")
		.setMessage("This app needs access to your storage to function properly.")
		.setCancelable(false)
		.setPositiveButton("Grant", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				ActivityCompat.requestPermissions(activity, 
				new String[]{
					Manifest.permission.READ_EXTERNAL_STORAGE,
					Manifest.permission.WRITE_EXTERNAL_STORAGE
				}, 
				REQUEST_STORAGE_PERMISSION);
				showingDialog = false;
			}
		})
		.setNegativeButton("Exit", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				showingDialog = false;
				callback.onPermissionDenied();
				activity.finish();
			}
		})
		.setOnDismissListener(new DialogInterface.OnDismissListener() {
			@Override
			public void onDismiss(DialogInterface dialog) {
				showingDialog = false;
			}
		})
		.show();
	}
	
	private static void showManageAllFilesDialog(final Context context, final MainActivity activity, final PermissionCallback callback) {
		new AlertDialog.Builder(context)
		.setTitle("Full Storage Access Required")
		.setMessage("On Android 11+, this app needs special permission to manage all files.")
		.setCancelable(false)
		.setPositiveButton("Open Settings", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				try {
					Intent intent = new Intent(Settings.ACTION_MANAGE_APP_ALL_FILES_ACCESS_PERMISSION);
					intent.setData(Uri.parse("package:" + context.getPackageName()));
					activity.startActivityForResult(intent, REQUEST_STORAGE_PERMISSION);
				} catch (Exception e) {
					Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
					intent.setData(Uri.parse("package:" + context.getPackageName()));
					activity.startActivityForResult(intent, REQUEST_STORAGE_PERMISSION);
				}
				showingDialog = false;
			}
		})
		.setNegativeButton("Exit", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				showingDialog = false;
				callback.onPermissionDenied();
				activity.finish();
			}
		})
		.setOnDismissListener(new DialogInterface.OnDismissListener() {
			@Override
			public void onDismiss(DialogInterface dialog) {
				showingDialog = false;
			}
		})
		.show();
	}
	
	public static void onRequestPermissionsResult(int requestCode, int[] grantResults, PermissionCallback callback) {
		if (requestCode == REQUEST_STORAGE_PERMISSION) {
			if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
				callback.onPermissionGranted();
			} else {
				callback.onPermissionDenied();
			}
		}
	}
	
	public static void onActivityResult(int requestCode, PermissionCallback callback, Context context) {
		if (requestCode == REQUEST_STORAGE_PERMISSION) {
			if (hasStoragePermission(context)) {
				callback.onPermissionGranted();
			} else {
				callback.onPermissionDenied();
			}
		}
	}
}
