package com.knu_library.main;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.knu_library.R;

public class KLMainLogInOutDialog {
	
	public static final String TAG = "KLMainLogInOutDialog";
	
	public static void showLogInDialog(final KLMainActivity context) {
		
		LayoutInflater inflate = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		View dialogView = inflate.inflate(R.layout.dialog_login, null);
		final EditText loginEditText = (EditText) dialogView.findViewById(R.id.dialog_login_id_editText);
		ImageView loginRemoveAllImageView = (ImageView) dialogView.findViewById(R.id.dialog_login_id_remove_all);
		final EditText passwordEditText = (EditText) dialogView.findViewById(R.id.dialog_login_password_editText);
		ImageView passwordRemoveAllImageView = (ImageView) dialogView.findViewById(R.id.dialog_login_password_remove_all);
		final CheckBox autoLoginCheckBox = (CheckBox)dialogView.findViewById(R.id.dialog_login_checkbox_autologin);
		
		SharedPreferences spf = context.getSharedPreferences(KLConstants.SHARED_PREFERENCE, Context.MODE_PRIVATE);
		boolean isAutoLoginChecked = spf.getBoolean(KLConstants.AUTO_LOGIN, false);
		
		// removeAll
		loginRemoveAllImageView.setOnTouchListener(new OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				loginEditText.setText("");
				return false;
			}
		});
		
		passwordRemoveAllImageView.setOnTouchListener(new OnTouchListener() {
			
			@Override
			public boolean onTouch(View v, MotionEvent event) {
				passwordEditText.setText("");
				return false;
			}
		});
		
		// check auto login
		if (isAutoLoginChecked) {
			Log.i(TAG, "자동 로그인 설정");
			loginEditText.setText(spf.getString(KLConstants.STUDENT_ID, ""));
			passwordEditText.setText(spf.getString(KLConstants.PASSWORD, ""));
			autoLoginCheckBox.setChecked(true);
		}
		
		new AlertDialog.Builder(context)
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
				Toast.makeText(context, "로그인 중...", Toast.LENGTH_SHORT).show();
				
				// save auto login settings
				SharedPreferences spf = context.getSharedPreferences(KLConstants.SHARED_PREFERENCE, Context.MODE_PRIVATE);
				Editor editor = spf.edit();
				
				if (autoLoginCheckBox.isChecked()) {
					Log.i(TAG, "자동 로그인 설정");
					editor.putBoolean(KLConstants.AUTO_LOGIN, autoLoginCheckBox.isChecked());
					editor.putString(KLConstants.STUDENT_ID, loginEditText.getText().toString());
					editor.putString(KLConstants.PASSWORD, passwordEditText.getText().toString());
					editor.commit();
				}else{
					Log.i(TAG, "자동 로그인 설정 취소");
					editor.putBoolean(KLConstants.AUTO_LOGIN, autoLoginCheckBox.isChecked());
					editor.putString(KLConstants.STUDENT_ID, "");
					editor.putString(KLConstants.PASSWORD, "");
					editor.commit();
				}
				
				// log in to library server
				context.loginSuccess();
			}
		})
		.show();
	}
	
	public static void showLogOutDialog(final KLMainActivity context) {
		new AlertDialog.Builder(context)
		.setTitle("경북대학교 도서관") 
		.setMessage("로그아웃 하시겠습니까?")
		.setCancelable(true)
		.setNegativeButton("취소", new OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				Log.i(TAG, "취소");
			}
		})
		.setPositiveButton("확인", new OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				Log.i(TAG, "취소");
				
				// log in to library server
				context.logoutSuccess();
			}
		})
		.show();
	}
}
