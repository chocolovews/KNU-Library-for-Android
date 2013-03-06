package com.knu_library.main.seats_info;

import com.knu_library.R;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

public class KLSeatsInfoListAdapter extends BaseAdapter {

	private static final String TAG = "KLSeatsInfoListAdapter";
	protected LayoutInflater layoutInflater;
	static final int numberOfRooms = 13; // 열람실 총 갯수 
	private Context m_context;
	
	static final int STORE = 0;
	static final int HELP = 1;
	static final int ABOUT = 2;
	static final int MORE_APPS = 3;

	public KLSeatsInfoListAdapter(Context context) {
		Log.i("MNAlarmListAdapter", "Constructor");
		layoutInflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		m_context = context;
	}
	@Override
	public int getCount() {
		return numberOfRooms;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		convertView = layoutInflater.inflate(R.layout.seats_info_item, parent, false);
		
		// 파싱을 완료한 후 필요한 정보를 받아서 보여주기
		
		return convertView;
	}

}
