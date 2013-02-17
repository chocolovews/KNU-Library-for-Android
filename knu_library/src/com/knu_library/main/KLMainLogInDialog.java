package com.knu_library.main;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;

import com.knu_library.R;

public class KLMainLogInDialog {
	
	public static final String TAG = "KLMainLogInDialog";
	
	public static void showLogInDialog(Context context) {
		
		LayoutInflater inflate = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View dialogView = inflate.inflate(R.layout.dialog_login, null);
		
		AlertDialog alertDialog = new AlertDialog.Builder(context)
		.setView(dialogView)
		.setTitle("경북대학교 도서관 로그인") 
		.setCancelable(true)
		.setNegativeButton("취소", new OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				Log.i(TAG, "취소");
			}
		})
		.setPositiveButton("로그인", new OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				Log.i(TAG, "확인");
			}
		})
		.show();
	}
}
