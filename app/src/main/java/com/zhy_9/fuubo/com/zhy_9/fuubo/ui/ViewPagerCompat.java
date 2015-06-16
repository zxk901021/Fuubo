package com.zhy_9.fuubo.com.zhy_9.fuubo.ui;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.view.MotionEvent;

public class ViewPagerCompat extends ViewPager {

    //mViewTouchMode��ʾViewPager�Ƿ�ȫȨ���ƻ����¼���Ĭ��Ϊfalse����������
    private boolean mViewTouchMode = false;

    public ViewPagerCompat(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public void setViewTouchMode(boolean b) {
        if (b && !isFakeDragging()) {
            //ȫȨ���ƻ����¼�
            beginFakeDrag();
        } else if (!b && isFakeDragging()) {
            //��ֹ���ƻ����¼�
            endFakeDrag();
        }
        mViewTouchMode = b;
    }

    /**
     * ��mViewTouchModeΪtrue��ʱ��ViewPager�����ص���¼�������¼�������View����
     */
    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        if (mViewTouchMode) {
            return false;
        }
        return super.onInterceptTouchEvent(event);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        try {
            return super.onTouchEvent(ev);
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * ��mViewTouchModeΪtrue���߻������������ҵ�ʱ��ViewPager���������Ƶ���¼���
     * ��������������ViewPager�м���ListView�ȿ��Ի����Ŀؼ�����������֮��Ļ��������г�ͻ
     */
    @Override
    public boolean arrowScroll(int direction) {
        if (mViewTouchMode) return false;
        if (direction != FOCUS_LEFT && direction != FOCUS_RIGHT) return false;
        return super.arrowScroll(direction);
    }

}
