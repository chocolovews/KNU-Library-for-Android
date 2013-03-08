package com.knu_library.main.seats_info;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

import android.content.Context;
import android.util.Log;

/**
 * 경북대 도서관 서버에서 자리 정보를 얻어와서 파싱을 하고, 원하는 자료 구조로 정리 
 * @author Wooseong Kim
 *
 */
public class KLSeatsInfoParsingThread extends Thread {

	private static final String TAG = "KLSeatsInfoParsingThread";
	
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
		try {
			URL url = new URL("http://libseat.knu.ac.kr/domian5.asp");
			HttpURLConnection connection = (HttpURLConnection)url.openConnection();
			// DoInput 은 GET 방식 DoOutput 은 POST 방식
			connection.setDoInput(true);
			
			StringBuilder htmlString = new StringBuilder();
			if (connection != null) {
				connection.setConnectTimeout(10000);
				connection.setUseCaches(false);
				if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
					
					BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), "EUC-KR"));
					for(;;) {
						String line = br.readLine();
						if (line == null) {
							break;
						}
						htmlString.append(line + "\n");
					}
					br.close();
				}
				connection.disconnect();
			}

			// 모든 처리가 완료되면 ListFragment에 콜백
			Log.i(TAG, htmlString.toString());
			m_SuperListFragment.threadDidParsing(seatsInfoModelList);
			
		} catch (Exception e) {
			e.printStackTrace();
			// 실패 했을 경우를 대비하기
			m_SuperListFragment.threadFailedParsing();
		}
	}
}
