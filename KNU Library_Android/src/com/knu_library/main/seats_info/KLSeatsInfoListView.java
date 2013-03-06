package com.knu_library.main.seats_info;


// 최초에 사용할 줄 알고 만들었지만 ListFragments가 자리를 대신하게 됨
/*
public class KLSeatsInfoListView extends ListView {

	public KLSeatsInfoListView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}
	
	public KLSeatsInfoListView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	public KLSeatsInfoListView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init();
	}
	
	public void init()
	{
//		this.setBackgroundColor( GeneralSetting.getBackwardBackgroundColor() );
////		this.setSelector(new PaintDrawable(0xffff0000));
//		this.setDivider(new ColorDrawable( GeneralSetting.getBackwardBackgroundColor() ));
//		this.setDividerHeight( DipToPixel.getPixel(getContext(), 1) );
//		this.setCacheColorHint(0x00000000);
//		this.setOverScrollMode(OVER_SCROLL_NEVER);
//		this.setVerticalScrollBarEnabled(false);
//		
//		int padding = DipToPixel.getPixel(getContext(), 3);
//		this.setPadding(padding, padding, padding, padding);
//		
//		infoListAdaptor = new MNInfoListAdaptor(getContext(), (OnClickListener)this);
//		setAdapter(infoListAdaptor);
		
		// 필요한 정보를 받아서 파싱을 완료하고 어댑터를 만들어서 보여줌. 그동안은 로딩 화면을 보여줄 수 있으면 좋을 듯.
		ListAdapter roomsListAdapter = new KLSeatsInfoListAdapter(getContext());
		setAdapter(roomsListAdapter);
	}

}
*/
