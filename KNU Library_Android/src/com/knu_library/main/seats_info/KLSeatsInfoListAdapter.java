package com.knu_library.main.seats_info;

import java.util.ArrayList;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.knu_library.R;

public class KLSeatsInfoListAdapter extends BaseAdapter {

	private static final String TAG = "KLSeatsInfoListAdapter";
	protected LayoutInflater layoutInflater;
	static final int numberOfRooms = 13; // 열람실 총 갯수 
	private Context m_context;
	private ArrayList<KLSeatInfo> m_seatInfoModelList;
	
	static final int STORE = 0;
	static final int HELP = 1;
	static final int ABOUT = 2;
	static final int MORE_APPS = 3;

	public KLSeatsInfoListAdapter(Context context, ArrayList<KLSeatInfo> seatInfoModelList) {
		Log.i("MNAlarmListAdapter", "Constructor");
		layoutInflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		m_context = context;
		m_seatInfoModelList = seatInfoModelList;
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
		
		TextView upperTextView = (TextView)convertView.findViewById(R.id.seats_info_upperTextView);
		TextView bottomTextView = (TextView)convertView.findViewById(R.id.seats_info_bottomTextView);
		
		KLSeatInfo seatInfo = m_seatInfoModelList.get(position);
		
		// 파싱을 완료한 후 필요한 정보를 받아서 보여주기
		// 첫번째 줄 지하열람A(7좌석 남음) - 94.7%
		String upperString = String.format("%s(%d좌석 남음) - %s", seatInfo.roomName, seatInfo.currentAvailableNumberOfSeats, seatInfo.usagePercentage);
		upperTextView.setText(upperString);
		
		// 두번째 줄 총 132좌석 중 125좌석 사용
		String bottomString = String.format("총 %d좌석 중 %d좌석 사용", seatInfo.totalNumberOfSeats, seatInfo.totalNumberOfSeats - seatInfo.currentAvailableNumberOfSeats);
		bottomTextView.setText(bottomString);
		
		return convertView;
	}

}
