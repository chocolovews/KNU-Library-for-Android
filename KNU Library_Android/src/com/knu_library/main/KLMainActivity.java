package com.knu_library.main;

import android.app.ActionBar;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.knu_library.R;
import com.knu_library.main.seats_info.KLSeatsInfoListAdapter;
import com.knu_library.main.seats_info.KLSeatsInfoListFragment;

public class KLMainActivity extends FragmentActivity implements ActionBar.TabListener {

    private static final String STATE_SELECTED_NAVIGATION_ITEM = "selected_navigation_item";
    private static final String TAG = "KLMainActivity";
    
    private Menu m_menu;
    private int currenntTabPosition = -1;
    private boolean isLoginComplete = false;
    
    // Constants
    private static final int BOOK_SEARCH = 0;
    private static final int MY_BOOKS = 1;
    private static final int SEATS_INFO = 2;
    private static final int SETTINGS = 3;
    
    // Fragments
    KLSeatsInfoListFragment seatsInfoListFragment;
    
    @Override
    public void onCreate(Bundle savedInstanceState) {
    	
    	// 인트로 실행
//        Intent intent = new Intent(KLMainActivity.this, KLIntroActivity.class);
//		startActivity(intent);
        
//		intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//		intent.addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);

//		overridePendingTransition(0, 0);
		
        super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
        
		// 초기화
        initialize();
    }

	public void initialize() {
		// Set up the action bar.
        final ActionBar actionBar = getActionBar();
//        actionBar.setIcon(null); 
        actionBar.setLogo(R.drawable.main_logo_app_name);
        actionBar.setBackgroundDrawable(getResources().getDrawable(R.drawable.navigation_bar));
        
        // 탭 추가
//		getActionBar().setDisplayShowCustomEnabled(true);
		getActionBar().setDisplayShowTitleEnabled(false);
//		getActionBar().setDisplayShowHomeEnabled(false);
		getActionBar().setDisplayUseLogoEnabled(true);
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        // For each of the sections in the app, add a tab to the action bar.
//        actionBar.addTab(actionBar.newTab().setText(R.string.title_section1).setTabListener(this).setIcon(R.drawable.book_search));
//        actionBar.addTab(actionBar.newTab().setText(R.string.title_section2).setTabListener(this).setIcon(R.drawable.my_books));
//        actionBar.addTab(actionBar.newTab().setText(R.string.title_section3).setTabListener(this).setIcon(R.drawable.seats_info));
//        actionBar.addTab(actionBar.newTab().setText(R.string.title_section4).setTabListener(this).setIcon(R.drawable.settings));
        
        actionBar.addTab(actionBar.newTab().setText(R.string.title_section1).setTabListener(KLMainActivity.this));
        actionBar.addTab(actionBar.newTab().setText(R.string.title_section2).setTabListener(KLMainActivity.this));
        actionBar.addTab(actionBar.newTab().setText(R.string.title_section3).setTabListener(KLMainActivity.this));
        actionBar.addTab(actionBar.newTab().setText(R.string.title_section4).setTabListener(KLMainActivity.this));
        
        // 각 탭에 해당하는 프래그먼트를 생성
//        seatsInfoListFragment = new KLSeatsInfoListFragment();
        
//        m_menu.findItem(R.id.main_login_item).setVisible(false);
	}

    @Override
    public void onRestoreInstanceState(Bundle savedInstanceState) {
        if (savedInstanceState.containsKey(STATE_SELECTED_NAVIGATION_ITEM)) {
            getActionBar().setSelectedNavigationItem(
                    savedInstanceState.getInt(STATE_SELECTED_NAVIGATION_ITEM));
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putInt(STATE_SELECTED_NAVIGATION_ITEM,
                getActionBar().getSelectedNavigationIndex());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
//    	Log.i(TAG, "onCreateOptionsMenu");
        getMenuInflater().inflate(R.menu.activity_main_menu, menu);
        m_menu = menu;

    	// show log in icon only in the my books menu
        if (currenntTabPosition == 1) {
            m_menu.findItem(R.id.main_login_item).setVisible(true);	
        }else{
        	m_menu.findItem(R.id.main_login_item).setVisible(false);
        }
        return true;
    }    
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
    	switch (item.getItemId()) {
		case R.id.main_login_item:
//			Log.i(TAG, "login button clicked");
			if (isLoginComplete) {
				KLMainLogInOutDialog.showLogOutDialog(this);
			}else{
				KLMainLogInOutDialog.showLogInDialog(this);	
			}
			break;
		}
    	return true;
    }
    
    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
      
    	if (isLoginComplete) {
			menu.findItem(R.id.main_login_item).setIcon(R.drawable.main_after_login);
		}else{
			menu.findItem(R.id.main_login_item).setIcon(R.drawable.main_before_login);
		}
        return super.onPrepareOptionsMenu(menu);

    }

    @Override
    public void onTabUnselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
    }

    @Override
    public void onTabSelected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
    	Log.i(TAG, "onTabSelected");
    	
    	switch (tab.getPosition()) {
//		case BOOK_SEARCH:
//			
//			break;

//		case MY_BOOKS:
//			
//			break;
			
		case SEATS_INFO:
			if (seatsInfoListFragment == null) {
				seatsInfoListFragment = new KLSeatsInfoListFragment();
			}
			getSupportFragmentManager().beginTransaction().replace(R.id.container, seatsInfoListFragment).commit();
			break;
			
//		case SETTINGS:
//			
//			break;
			
		default:
			 // When the given tab is selected, show the tab contents in the container
	        Fragment fragment = new DummySectionFragment();
	        Bundle args = new Bundle();
	        args.putInt(DummySectionFragment.ARG_SECTION_NUMBER, tab.getPosition() + 1);
	        fragment.setArguments(args);
	        getSupportFragmentManager().beginTransaction()
	                .replace(R.id.container, fragment)
	                .commit();
			break;
		}

        currenntTabPosition = tab.getPosition();
        
        invalidateOptionsMenu();
    }

    @Override
    public void onTabReselected(ActionBar.Tab tab, FragmentTransaction fragmentTransaction) {
    
    }

    /**
     * A dummy fragment representing a section of the app, but that simply displays dummy text.
     */
    public static class DummySectionFragment extends Fragment {
        public DummySectionFragment() {
        }

        public static final String ARG_SECTION_NUMBER = "section_number";

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            TextView textView = new TextView(getActivity());
            textView.setGravity(Gravity.CENTER);
            Bundle args = getArguments();
            textView.setText(Integer.toString(args.getInt(ARG_SECTION_NUMBER)));
            return textView;
        }
    }
    
    public void loginSuccess() {
    	isLoginComplete = true;
    	invalidateOptionsMenu();
    }
    
    public void logoutSuccess() {
    	isLoginComplete = false;
    	invalidateOptionsMenu();
    }
}
