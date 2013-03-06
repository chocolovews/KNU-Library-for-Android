package com.knu_library.main.seats_info;

import java.util.ArrayList;

import android.content.Context;

/**
 * 경북대 도서관 서버에서 자리 정보를 얻어와서 파싱을 하고, 원하는 자료 구조로 정리 
 * @author Wooseong Kim
 *
 */
public class KLSeatsInfoParsingThread extends Thread {

	Context m_context = null;
	KLSeatsInfoListFragment m_SuperListFragment = null;
	
	public KLSeatsInfoParsingThread(Context context, KLSeatsInfoListFragment superListFragment) {
		m_context = context;
		
		// 처리를 끝낸 뒤 콜백을 보내기 위한 초기화
		m_SuperListFragment = superListFragment;
	}

	@Override
	public void run() {
		super.run();
		ArrayList<KLSeatInfoModel> seatsInfoModelList = new ArrayList<KLSeatInfoModel>();
		
		// 파싱 시작
		
		// 모든 처리가 완료되면 ListFragment에 콜백
		m_SuperListFragment.threadDidParsing(seatsInfoModelList);
	}
}
