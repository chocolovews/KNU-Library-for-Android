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
	ArrayList<KLSeatInfo> seatInfoList = null;
	
	public KLSeatsInfoParsingThread(Context context, KLSeatsInfoListFragment superListFragment) {
		m_context = context;
		
		// 처리를 끝낸 뒤 콜백을 보내기 위한 초기화
		m_SuperListFragment = superListFragment;
	}

	@Override
	public void run() {
		super.run();
		
		// 파싱 시작
		try {
			URL url = new URL("http://libseat.knu.ac.kr/domian5.asp");
			HttpURLConnection connection = (HttpURLConnection)url.openConnection();
			// DoInput 은 GET 방식 DoOutput 은 POST 방식
			connection.setDoInput(true);
			
			StringBuilder htmlString = new StringBuilder();
			if (connection != null) {
				connection.setConnectTimeout(10000);
//				connection.setUseCaches(false);
				if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
					
					BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream(), "EUC-KR"), connection.getContentLength());
					String lineString = null;
					while ((lineString = br.readLine()) != null) {
						htmlString.append(lineString + "\n");
					}
					br.close();
				}
				connection.disconnect();
			}

			// 모든 처리가 완료되면 ListFragment에 콜백
//			Log.i(TAG, htmlString.toString());
			seatInfoList = new ArrayList<KLSeatInfo>();
			parseHtmlString(htmlString.toString());
			
		} catch (Exception e) {
			e.printStackTrace();
			// 실패 했을 경우를 대비하기
			m_SuperListFragment.threadFailedParsing();
		}
	}
	
	private void parseHtmlString(String htmlString) {
		
		// 각 열람실 별 String으로 분리
		String[] seatInfoArray1 = htmlString.split("<a href=\"JavaScript:ExcelSave();\"><img src=\"./images/ExcelSave.gif\" border=0></a>&nbsp;");
		String[] seatInfoArray2 = seatInfoArray1[0].split("이용율");
		
		// 각 층별로 나누어진 Array
		String[] seatInfoSeperatedArray = seatInfoArray2[1].split("</FONT></TD></TR><TR ALIGN=\"CENTER\" style=\"background-color:");
		
		// 각 열람실 별 정보 파싱하기
		for (int i = 0; i < seatInfoSeperatedArray.length; i++) {
			KLSeatInfo seatInfo = new KLSeatInfo();
			
			String splitTokenString = "room_no=";
			String indexString = String.format("%d", i+1);
			splitTokenString += indexString;
			splitTokenString += "\">&nbsp;";
			
			// RoomName
			String[] parsedArray1 = seatInfoSeperatedArray[i].split(splitTokenString);
			String[] parsedArray2 = parsedArray1[1].split("</A></FONT></TD><TD ALIGN=\"CENTER\"><FONT SIZE=-1>&nbsp;");			
			seatInfo.roomName = parsedArray2[0];
			
			// TotalSeat
			String[] parsedArray3 = parsedArray2[1].split("</FONT></TD><TD ALIGN=\"CENTER\"><FONT SIZE=-1>&nbsp;");			
			seatInfo.totalNumberOfSeats = Integer.valueOf(parsedArray3[0]);
			
			// CurrentUsedSeat
			String[] parsedArray4 = parsedArray3[1].split("</FONT></TD><TD ALIGN=\"CENTER\"><FONT COLOR=\"blue\" SIZE=-1>&nbsp;");
			seatInfo.currentAvailableNumberOfSeats = Integer.valueOf(parsedArray4[0]);
			
			// CurrentAvailableSeat
			String[] parsedArray5 = parsedArray4[1].split("</FONT></TD><TD ALIGN=\"LEFT\">");
			seatInfo.currentAvailableNumberOfSeats = Integer.valueOf(parsedArray5[0]);
			
			// UsagePercent
			String[] parsedArray6 = parsedArray5[1].split("border=0 alt=\"");
			String[] parsedArray7 = parsedArray6[1].split("\">");
			String[] parsedArray8 = parsedArray7[0].split(" %");
			
			seatInfo.usagePercentage = parsedArray8[0];
			seatInfo.usagePercentage += "%";
			
			// seatInfo Container 테스트
			// seatInfo Container 에 추가
			seatInfoList.add(seatInfo);
		}
		
		m_SuperListFragment.threadDidParsing(seatInfoList);
	}
}
