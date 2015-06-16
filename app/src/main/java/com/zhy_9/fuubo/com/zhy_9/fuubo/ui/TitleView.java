package com.zhy_9.fuubo.com.zhy_9.fuubo.ui;

import com.zhy_9.stoexpress.R;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class TitleView extends FrameLayout{
	
	private ImageView leftImage;
	
	private TextView title;
	
	private TextView rightText;
	
	private RelativeLayout titleBg;

	public TitleView(Context context, AttributeSet attrs) {
		super(context, attrs);
		LayoutInflater.from(context).inflate(R.layout.title, this);
		titleBg = (RelativeLayout) findViewById(R.id.title_bg);
		leftImage = (ImageView) findViewById(R.id.title_left);
		title = (TextView) findViewById(R.id.title_text);
		rightText = (TextView) findViewById(R.id.title_right);
		
		leftImage.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				((Activity)getContext()).finish();
			}
		});
		
		
	}
	
	public void rightTextListener(final RightBtnCallBack callBack){
		rightText.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				if (callBack != null) {
					callBack.onRightBtnClick();
				}
			}
		});
	}
	
	public void setTitle (String titleStr) {
		title.setText(titleStr);
	}
	
	public void setTitleColor (String colorRGB) {
		title.setTextColor(Color.parseColor(colorRGB));
	}
	
	public void setBackgroundColor (int color) {
		titleBg.setBackgroundColor(color);
	}
	
	public void setRightText (String rightTextStr) {
		rightText.setText(rightTextStr);
	}

	public void setRightTextDrawable(int resId){
		Drawable drawable = getResources().getDrawable(resId);
		drawable.setBounds(0, 0, drawable.getMinimumWidth(), drawable.getMinimumHeight());
		rightText.setCompoundDrawables(drawable, null, null, null);
	}
	
	public void setRightTextGone(boolean isGone){
		if (isGone) {
			rightText.setVisibility(View.GONE);
		}
	}
	
	public interface RightBtnCallBack {
		void onRightBtnClick();
	}
}
