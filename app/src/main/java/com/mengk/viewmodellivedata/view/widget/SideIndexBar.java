package com.mengk.viewmodellivedata.view.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.MotionEvent;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.Nullable;
import com.mengk.viewmodellivedata.R;
import com.mengk.viewmodellivedata.common.util.DensityUtil;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Author: mengk
 * @Date: 2019/12/25
 */
public class SideIndexBar extends View {
    private static final String[] DEFAULT_INDEX_ITEMS = {"热", "A", "B", "C", "D", "E", "F", "G", "H",
            "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};

    private List<String> mIndexItems;
    private float mItemHeight = 0;
    private int mTextSize;
    private int mTextColor;
    private int mTextTouchedColor;
    private int mCurrentIndex = -1;

    private Paint mPaint;
    private Paint mTouchedPaint;

    private int mWidth;
    private int mHeight;
    //居中绘制，文字绘制起点和控件顶部的间隔
    private float mTopMargin = 0;
    // 记录选中的字母
    private String mChooseItem = "";
    private TextView mOverlayTextView;
    private OnIndexTouchedChangedListener mOnIndexChangedListener;
    private int mPosition;

    private int navigationBarHeight;

    private Paint paintItem = null;
    private Paint mPaintBg = new Paint(Paint.ANTI_ALIAS_FLAG);

    public SideIndexBar(Context context) {
        this(context, null);
    }

    public SideIndexBar(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SideIndexBar(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    public void setNavigationBarHeight(int height) {
        this.navigationBarHeight = height;
    }

    public void setIndexItems(List<String> items) {
        if (items == null || items.size() == 0) {
            return;
        }
        mIndexItems.clear();
        mIndexItems.addAll(items);
        mChooseItem = mIndexItems.get(0);// 获取第一个字母

    }

    private void init(Context context) {
        mIndexItems = new ArrayList<>();
        mIndexItems.addAll(Arrays.asList(DEFAULT_INDEX_ITEMS));
        TypedValue typedValue = new TypedValue();
        context.getTheme().resolveAttribute(R.attr.cpIndexBarTextSize, typedValue, true);
//        mTextSize = context.getResources().getDimensionPixelSize(typedValue.resourceId);

        context.getTheme().resolveAttribute(R.attr.cpIndexBarNormalTextColor, typedValue, true);
        mTextColor = context.getResources().getColor(R.color.color_FF999999);

        context.getTheme().resolveAttribute(R.attr.cpIndexBarSelectedTextColor, typedValue, true);
        mTextTouchedColor = context.getResources().getColor(R.color.color_ff7801);

        paintItem = new Paint();

        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPaint.setTextSize(DensityUtil.dp2px(10f));
        mPaint.setColor(mTextColor);

        mTouchedPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mTouchedPaint.setTextSize(DensityUtil.dp2px(10f));
        mTouchedPaint.setColor(mTextTouchedColor);
//        mPaintBg.setColor(Color.WHITE);
        mPaintBg.setColor(getResources().getColor(R.color.color_f8f8f8));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        String index;
        int t = (int) (getMeasuredHeight() - mItemHeight * mIndexItems.size())/2;
        Paint.FontMetrics fm = mPaint.getFontMetrics();
        int base = (int) ((mItemHeight - fm.bottom - fm.top) / 2);
        int top = (int) (t + base/2);
        canvas.drawCircle(getMeasuredWidth()/2,top,getMeasuredWidth()/2,mPaintBg);
        canvas.drawCircle(getMeasuredWidth()/2,getMeasuredHeight() - top,getMeasuredWidth()/2,mPaintBg);
        canvas.drawRect(0,top,getMeasuredWidth(),getMeasuredHeight() - top,mPaintBg);
        for (int i = 0; i < mIndexItems.size(); i++) {
            index = mIndexItems.get(i);
            //计算出在每格index区域，竖直居中的baseLine值
            int baseline = (int) ((mItemHeight - fm.bottom - fm.top) / 2);
            if (index.equals(mChooseItem)) {
                // Paint paint = new Paint();
                paintItem.setTextSize(DensityUtil.dp2px(10f));
                paintItem.setColor(getResources().getColor(R.color.color_ff7801));
                paintItem.setFlags(Paint.ANTI_ALIAS_FLAG);
                canvas.drawText(index, (mWidth - paintItem.measureText(index)) / 2, t + mItemHeight * i + baseline, i == mCurrentIndex ? mTouchedPaint : paintItem);
            } else {
                canvas.drawText(index, (mWidth - mPaint.measureText(index)) / 2, t + mItemHeight * i + baseline, i == mCurrentIndex ? mTouchedPaint : mPaint);
            }
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = getWidth();
        if (Math.abs(h - oldh) == navigationBarHeight) {
            //底部导航栏隐藏或显示
            mHeight = h;
        } else {
            //避免软键盘弹出时挤压
            mHeight = Math.max(getHeight(), oldh);
        }
        if (mIndexItems.size() > 0) {
            mItemHeight = mHeight / mIndexItems.size();
            mTopMargin = (mHeight - mItemHeight * mIndexItems.size()) / 2;
        }
    }

    @Override
    public boolean performClick() {
        return super.performClick();
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int t = (int) (getMeasuredHeight() - mItemHeight * mIndexItems.size())/2;
        if(event.getY() < t || event.getY() > getMeasuredHeight() - t){
            return false;
        }
        performClick();
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
                float y = event.getY();
                int indexSize = mIndexItems.size();
                int touchedIndex = (int) ((y - t) / mItemHeight);
                if (touchedIndex < 0) {
                    touchedIndex = 0;
                } else if (touchedIndex >= indexSize) {
                    touchedIndex = indexSize - 1;
                }
                if (mOnIndexChangedListener != null && touchedIndex >= 0 && touchedIndex < indexSize) {
                    if (touchedIndex != mCurrentIndex) {
                        mCurrentIndex = touchedIndex;
                        if (mOverlayTextView != null) {
                            mOverlayTextView.setVisibility(VISIBLE);
                            mOverlayTextView.setText(mIndexItems.get(touchedIndex));
                        }
                        mOnIndexChangedListener.onIndexChanged(mIndexItems.get(touchedIndex), touchedIndex);
                        invalidate();
                    }
                }
                mChooseItem = mIndexItems.get(touchedIndex);
                break;
            case MotionEvent.ACTION_UP:
            case MotionEvent.ACTION_CANCEL:
                mCurrentIndex = -1;
                if (mOverlayTextView != null) {
                    mOverlayTextView.setVisibility(GONE);
                }
                invalidate();
                break;
                default:
        }
        return true;
    }

    public SideIndexBar setOverlayTextView(TextView overlay) {
        this.mOverlayTextView = overlay;
        return this;
    }

    public SideIndexBar setOnIndexChangedListener(OnIndexTouchedChangedListener listener) {
        this.mOnIndexChangedListener = listener;
        return this;
    }

    public interface OnIndexTouchedChangedListener {
        void onIndexChanged(String index, int position);
    }


    public void setScrollPosition(int position) {
        this.mPosition = position;
        String s = mIndexItems.get(position);
        mChooseItem = s;
        invalidate();
    }
}
