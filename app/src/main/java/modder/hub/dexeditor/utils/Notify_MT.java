/*
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



package modder.hub.dexeditor.utils;

import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface.*;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.graphics.Color;
import android.text.Html;
import android.widget.TextView;
import android.app.DialogFragment;
import android.widget.LinearLayout;
import android.widget.EditText;
import android.widget.ScrollView;
import android.view.Gravity;
import android.text.SpannableString;
import android.view.ViewGroup;
import android.util.Base64;
import android.widget.ListView;
import android.view.View;
import android.widget.RelativeLayout.LayoutParams;
import android.view.WindowManager;
import android.animation.*;
import android.app.*;
import android.app.Activity;
import android.app.DialogFragment;
import android.app.Fragment;
import android.app.FragmentManager;
import android.content.*;
import android.content.res.*;
import android.graphics.*;
import android.graphics.drawable.*;
import android.media.*;
import android.net.*;
import android.os.*;
import android.text.*;
import android.text.style.*;
import android.util.*;
import android.view.*;
import android.view.View.*;
import android.view.animation.*;
import android.webkit.*;
import android.widget.*;
import java.io.*;
import java.text.*;
import java.util.*;
import java.util.regex.*;
import org.json.*;
import android.content.ClipData;
import android.content.ClipboardManager;
import modder.hub.dexeditor.views.*;
import modder.hub.dexeditor.utils.*;
import modder.hub.dexeditor.*;

/*
Author @developer-krushna
*/

public class Notify_MT {
	
	public static int cornerRadius = 20;
	
	
	public static void Notify(Context context, String title_mt, String message_mt, String cancel_mt) {
		try {
			final Builder builder = new Builder(context);
			builder.setTitle(title_mt);
			builder.setMessage(message_mt);
			builder.setPositiveButton(cancel_mt, new DialogInterface.OnClickListener() {
				@Override
				public void onClick(DialogInterface dia, int which) {
					// nothing
				}
			});
			
			// Setting custom background
			final AlertDialog alert = builder.create();
			alert.getWindow().setBackgroundDrawable(createDrawable(cornerRadius, 0xFFFFFFFF));
			alert.show();
			final TextView message = alert.findViewById(android.R.id.message);
			message.setTextIsSelectable(true);
		} catch (WindowManager.BadTokenException e) {
			e.printStackTrace();
		}
	}
	
	
	public static void Dlg_Style(AlertDialog.Builder dialog){
		final AlertDialog alert = dialog.create();
		alert.getWindow().setBackgroundDrawable(createDrawable(cornerRadius, 0xFFFFFFFF));
		try{
			alert.show();
		} catch (WindowManager.BadTokenException e){
			
		}
		
	}
	public static GradientDrawable createDrawable(int cornerRadiuss, int color) {
		GradientDrawable drawable = new GradientDrawable();
		drawable.setCornerRadius(cornerRadiuss);
		drawable.setColor(color);
		return drawable;
	}
	
	
}
