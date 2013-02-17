package com.example.knu_library_for_android.intro;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;

import com.example.knu_library_for_android.R;
import com.example.knu_library_for_android.main.KLMainActivity;

/**
 * 인트로 화면. 자동으로 메인으로 넘어간다 
 * @author DeepSea
 *
 */
public class KLIntroActivity extends Activity {

//	Button introButton;
	long loadingTime = 1500;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);
        
//        introButton = (Button)findViewById(R.id.intro_introButton);
//        introButton.setOnClickListener(this);
        
        new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				try {
					Thread.sleep(loadingTime);
					startMainActivity();
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}).start();
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.activity_intro, menu);
//        return true;
//    }
    
//    @Override
//    public void onClick(View v) {
//        if (v == introButton) {
//            startMainActivity();
//        }
//    }

	public void startMainActivity() {
		Intent intent = new Intent(KLIntroActivity.this, KLMainActivity.class);
		intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
		intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
		startActivity(intent);
		finish();
	
		runOnUiThread(new Runnable() {
			
			@Override
			public void run() {
				getWindow().setFlags(WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN, WindowManager.LayoutParams.FLAG_FORCE_NOT_FULLSCREEN);		
			}
		});
		overridePendingTransition(R.anim.fade, R.anim.hold);
	}
}
