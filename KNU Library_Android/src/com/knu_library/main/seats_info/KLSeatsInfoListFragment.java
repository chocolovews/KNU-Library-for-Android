package com.knu_library.main.seats_info;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.util.Log;
import android.widget.ListAdapter;

public class KLSeatsInfoListFragment extends ListFragment {

	private static final String TAG = "KLSeatsInfoListFragment";
	
	public Context m_context;
	
	@Override 
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		
		init();
	}
	
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		
		m_context = activity;
	}
	
	public void init()
	{
		// 쓰레드에 파싱을 위임. 파싱이 끝나면 threadDidParsing 으로 콜백.
		KLSeatsInfoParsingThread seatsInfoParsingThread = new KLSeatsInfoParsingThread(getActivity(), this);
		seatsInfoParsingThread.start();
	}
	
	public void threadDidParsing(ArrayList<KLSeatInfoModel> seatInfoModelList) {
		// 필요한 정보를 받아서 파싱을 완료하고 어댑터를 만들어서 보여줌. 그동안은 로딩 화면을 보여줄 수 있으면 좋을 듯.
		((Activity)m_context).runOnUiThread(new Runnable() {
			
			@Override
			public void run() {
				ListAdapter roomsListAdapter = new KLSeatsInfoListAdapter(getActivity());
				setListAdapter(roomsListAdapter);	
			}
		});
	}
	
	public void threadFailedParsing() {
		Log.i(TAG, "threadFailedParsing");
	}
}
