package com.mengk.viewmodellivedata.view.widget;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.Interpolator;
import android.view.animation.LinearInterpolator;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;


import com.mengk.viewmodellivedata.R;
import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 根据官方TabLayout进行修改
 * 支持自定义多种样式
 */
public class XTabLayout extends HorizontalScrollView implements ViewPager.OnPageChangeListener {

    private Context mContext;
    private ViewPager mViewPager;
    private ArrayList<String> mTitles;
    protected LinearLayout mTabsContainer;
    private int mCurrentTab;
    private float mCurrentPositionOffset;
    protected int mTabCount;
    protected Rect mIndicatorRect = new Rect();
    protected Rect mTabRect = new Rect();
    protected GradientDrawable mIndicatorDrawable = new GradientDrawable();
    protected GradientDrawable mIndicatorStrokeDrawable = new GradientDrawable();

    protected Paint mRectPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    protected Paint mDividerPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
    protected static final int MODE_NORMAL = 0;
    protected static final int MODE_BLOCK = 1;
    protected static final int MODE_EXACTLY = 2;
    protected int mIndicatorStyle = MODE_NORMAL;
    protected float mIndicatorStrokeWidth;
    protected float mIndicatorStrokeRadius;
    protected int mIndicatorStrokeColor;

    private float mTabPadding;
    private boolean mTabSpaceEqual;
    private float mTabWidth;
    private boolean mTabCenterHorizontal;

    protected boolean mIndicatorUnselected;
    protected int mIndicatorUnselectedColor;
    protected int mIndicatorColor;
    protected float mIndicatorHeight;
    protected float mIndicatorWidth;
    protected float mIndicatorCornerRadius;
    protected float mIndicatorMarginLeft;
    protected float mIndicatorMarginTop;
    protected float mIndicatorMarginRight;
    protected float mIndicatorMarginBottom;
    protected int mIndicatorGravity;
    protected boolean mIndicatorWidthEqualTitle;
    protected float mIndicatorWidthEqualTitleExtra;
    protected float mTabFirstLastMargin;

    protected int mUnderlineColor;
    protected float mUnderlineHeight;
    protected int mUnderlineGravity;

    protected int mDividerColor;
    protected float mDividerWidth;
    protected float mDividerPadding;

    private static final int TEXT_BOLD_NONE = 0;
    private static final int TEXT_BOLD_WHEN_SELECT = 1;
    private static final int TEXT_BOLD_BOTH = 2;
    private float mTextSize;
    private int mTextSelectColor;
    private int mTextUnselectColor;
    private int mTextBold;
    private float mTextScale = 1.0f;
    private boolean mTextAllCaps;

    private int mLastScrollX;
    private boolean mSnapOnTabClick;

    private static final int TEXT_SCALE_DURATION = 50;

    // 控制动画
    private Interpolator mStartInterpolator = new AccelerateInterpolator();
    private Interpolator mEndInterpolator = new DecelerateInterpolator(2.0f);
    /**
     * 未选择的RectF
     */
    protected RectF unselectedRect = new RectF();

    public XTabLayout(Context context) {
        this(context, null, 0);
    }

    public XTabLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public XTabLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setFillViewport(true);//设置滚动视图是否可以伸缩其内容以填充视口
        setWillNotDraw(false);//重写onDraw方法,需要调用这个方法来清除flag
        setClipChildren(false);
        setClipToPadding(false);

        this.mContext = context;
        mTabsContainer = new LinearLayout(context);
        addView(mTabsContainer);

        obtainAttributes(context, attrs);
    }

    private void obtainAttributes(Context context, AttributeSet attrs) {
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.XTabLayout);

        mIndicatorStyle = ta.getInt(R.styleable.XTabLayout_tl_indicator_style, MODE_NORMAL);
        mIndicatorUnselected = ta.getBoolean(R.styleable.XTabLayout_tl_indicator_unselected, false);
        mIndicatorColor = ta.getColor(R.styleable.XTabLayout_tl_indicator_color, Color.parseColor(mIndicatorStyle == MODE_BLOCK ? "#4B6A87" : "#4B6A87"));
        mIndicatorUnselectedColor = ta.getColor(R.styleable.XTabLayout_tl_indicator_unselected_color, Color.parseColor(mIndicatorStyle == MODE_BLOCK ? "#4B6A87" : "#ffffff"));
        mIndicatorHeight = ta.getDimension(R.styleable.XTabLayout_tl_indicator_height,
                dp2px(mIndicatorStyle == MODE_BLOCK ? -1 : 2));
        mIndicatorWidth = ta.getDimension(R.styleable.XTabLayout_tl_indicator_width, dp2px(-1));
        mIndicatorCornerRadius = ta.getDimension(R.styleable.XTabLayout_tl_indicator_corner_radius, dp2px(mIndicatorStyle == MODE_BLOCK ? -1 : 0));
        mIndicatorMarginLeft = ta.getDimension(R.styleable.XTabLayout_tl_indicator_margin_left, dp2px(0));
        mIndicatorMarginTop = ta.getDimension(R.styleable.XTabLayout_tl_indicator_margin_top, dp2px(mIndicatorStyle == MODE_BLOCK ? 7 : 0));
        mIndicatorMarginRight = ta.getDimension(R.styleable.XTabLayout_tl_indicator_margin_right, dp2px(0));
        mIndicatorMarginBottom = ta.getDimension(R.styleable.XTabLayout_tl_indicator_margin_bottom, dp2px(mIndicatorStyle == MODE_BLOCK ? 7 : 0));
        mIndicatorGravity = ta.getInt(R.styleable.XTabLayout_tl_indicator_gravity, Gravity.BOTTOM);
        mIndicatorWidthEqualTitle = ta.getBoolean(R.styleable.XTabLayout_tl_indicator_width_equal_title, false);
        mIndicatorWidthEqualTitleExtra=ta.getDimension(R.styleable.XTabLayout_tl_indicator_width_equal_title_extra, 0);
        mTabFirstLastMargin=ta.getDimension(R.styleable.XTabLayout_tl_tab_first_last_margin,0);
        mIndicatorStrokeWidth = ta.getDimension(R.styleable.XTabLayout_tl_indicator_stroke_width, 0);
        mIndicatorStrokeRadius = ta.getDimension(R.styleable.XTabLayout_tl_indicator_stroke_radius, 0);
        mIndicatorStrokeColor = ta.getColor(R.styleable.XTabLayout_tl_indicator_stroke_color, Color.parseColor("#ffffff"));

        mUnderlineColor = ta.getColor(R.styleable.XTabLayout_tl_underline_color, Color.parseColor("#ffffff"));
        mUnderlineHeight = ta.getDimension(R.styleable.XTabLayout_tl_underline_height, dp2px(0));
        mUnderlineGravity = ta.getInt(R.styleable.XTabLayout_tl_underline_gravity, Gravity.BOTTOM);

        mDividerColor = ta.getColor(R.styleable.XTabLayout_tl_divider_color, Color.parseColor("#ffffff"));
        mDividerWidth = ta.getDimension(R.styleable.XTabLayout_tl_divider_width, dp2px(0));
        mDividerPadding = ta.getDimension(R.styleable.XTabLayout_tl_divider_padding, dp2px(12));

        mTextSize = ta.getDimension(R.styleable.XTabLayout_tl_textsize, sp2px(14));
        mTextScale = ta.getFloat(R.styleable.XTabLayout_tl_textScale, 1f);
        mTextSelectColor = ta.getColor(R.styleable.XTabLayout_tl_textSelectColor, Color.parseColor("#FC8E3E"));
        mTextUnselectColor = ta.getColor(R.styleable.XTabLayout_tl_textUnselectColor, Color.parseColor("#AAffffff"));
        mTextBold = ta.getInt(R.styleable.XTabLayout_tl_textBold, TEXT_BOLD_NONE);
        mTextAllCaps = ta.getBoolean(R.styleable.XTabLayout_tl_textAllCaps, false);

        mTabSpaceEqual = ta.getBoolean(R.styleable.XTabLayout_tl_tab_space_equal, false);
        mTabWidth = ta.getDimension(R.styleable.XTabLayout_tl_tab_width, dp2px(-1));
        mTabCenterHorizontal = ta.getBoolean(R.styleable.XTabLayout_tl_tab_center_horizontal, false);
        mTabPadding = ta.getDimension(R.styleable.XTabLayout_tl_tab_padding, mTabSpaceEqual || mTabWidth > 0 ? 0 : dp2px(10));

        ta.recycle();
    }

    /**
     * 关联ViewPager
     */
    public void setViewPager(ViewPager vp) {
        if (vp == null || vp.getAdapter() == null) {
            throw new IllegalStateException("ViewPager or ViewPager adapter can not be NULL !");
        }

        this.mViewPager = vp;

        this.mViewPager.removeOnPageChangeListener(this);
        this.mViewPager.addOnPageChangeListener(this);
        notifyDataSetChanged();
    }

    /**
     * 关联ViewPager,用于不想在ViewPager适配器中设置titles数据的情况
     */
    public void setViewPager(ViewPager vp, String[] titles) {
        if (vp == null || vp.getAdapter() == null) {
            throw new IllegalStateException("ViewPager or ViewPager adapter can not be NULL !");
        }

        if (titles == null || titles.length == 0) {
            throw new IllegalStateException("Titles can not be EMPTY !");
        }

        if (titles.length != vp.getAdapter().getCount()) {
            throw new IllegalStateException("Titles length must be the same as the page count !");
        }

        this.mViewPager = vp;
        mTitles = new ArrayList<>();
        Collections.addAll(mTitles, titles);

        this.mViewPager.removeOnPageChangeListener(this);
        this.mViewPager.addOnPageChangeListener(this);
        notifyDataSetChanged();
    }

    /**
     * 关联ViewPager,用于连适配器都不想自己实例化的情况
     */
    public void setViewPager(ViewPager vp, String[] titles, FragmentActivity fa, ArrayList<Fragment> fragments) {
        if (vp == null) {
            throw new IllegalStateException("ViewPager can not be NULL !");
        }

        if (titles == null || titles.length == 0) {
            throw new IllegalStateException("Titles can not be EMPTY !");
        }

        this.mViewPager = vp;
        this.mViewPager.setAdapter(new InnerPagerAdapter(fa.getSupportFragmentManager(), fragments, titles));

        this.mViewPager.removeOnPageChangeListener(this);
        this.mViewPager.addOnPageChangeListener(this);
        notifyDataSetChanged();
    }

    /**
     * 更新数据
     */
    public void notifyDataSetChanged() {
        mTabsContainer.removeAllViews();
        this.mTabCount = mTitles == null ? mViewPager.getAdapter().getCount() : mTitles.size();
        View tabView;
        for (int i = 0; i < mTabCount; i++) {
            tabView = View.inflate(mContext, R.layout.layout_tab, null);
            CharSequence pageTitle = mTitles == null ? mViewPager.getAdapter().getPageTitle(i) : mTitles.get(i);
            addTab(i, pageTitle.toString(), tabView);
            if(i==0){
               LinearLayout.LayoutParams params= (LinearLayout.LayoutParams) tabView.getLayoutParams();
                params.leftMargin= (int) mTabFirstLastMargin;
            }else if(i==mTabCount-1){
                LinearLayout.LayoutParams params= (LinearLayout.LayoutParams) tabView.getLayoutParams();
                params.rightMargin= (int) mTabFirstLastMargin;
            }
        }

        updateTabStyles();
    }

    public void addNewTab(String title) {
        View tabView = View.inflate(mContext, R.layout.layout_tab, null);
        if (mTitles != null) {
            mTitles.add(title);
        }

        CharSequence pageTitle = mTitles == null ? mViewPager.getAdapter().getPageTitle(mTabCount) : mTitles.get(mTabCount);
        addTab(mTabCount, pageTitle.toString(), tabView);
        this.mTabCount = mTitles == null ? mViewPager.getAdapter().getCount() : mTitles.size();
        int postion=mTabCount-1;
        if(postion>0){
            LinearLayout.LayoutParams params= (LinearLayout.LayoutParams) mTabsContainer.getChildAt(postion-1).getLayoutParams();
            params.rightMargin= 0;
        }
        if(postion==0){
            LinearLayout.LayoutParams params= (LinearLayout.LayoutParams) tabView.getLayoutParams();
            params.leftMargin= (int) mTabFirstLastMargin;
        }else if(postion==mTabCount-1){
            LinearLayout.LayoutParams params= (LinearLayout.LayoutParams) tabView.getLayoutParams();
            params.rightMargin= (int) mTabFirstLastMargin;
        }
        updateTabStyles();
    }

    /**
     * 创建并添加tab
     */
    private void addTab(final int position, String title, View tabView) {
        TextView tv_tab_title = tabView.findViewById(R.id.tv_tab_title);
        if (tv_tab_title != null) {
            RelativeLayout.LayoutParams layoutParams = (RelativeLayout.LayoutParams) tv_tab_title.getLayoutParams();
            if (mTabCenterHorizontal) {
                layoutParams.addRule(RelativeLayout.CENTER_HORIZONTAL);
            } else {
                layoutParams.addRule(RelativeLayout.CENTER_IN_PARENT);
            }
            if (title != null) {
                tv_tab_title.setText(title);
            }
        }

        tabView.setOnClickListener(v -> {
            int position1 = mTabsContainer.indexOfChild(v);
            if (position1 != -1) {
                if (mViewPager.getCurrentItem() != position1) {
                    if (mSnapOnTabClick) {
                        mViewPager.setCurrentItem(position1, false);
                    } else {
                        mViewPager.setCurrentItem(position1);
                    }

                    if (mListener != null) {
                        mListener.onTabSelect(position1);
                    }
                } else {
                    if (mListener != null) {
                        mListener.onTabReselect(position1);
                    }
                }
            }
        });

        LinearLayout.LayoutParams lp_tab = mTabSpaceEqual ?
                new LinearLayout.LayoutParams(0, LayoutParams.MATCH_PARENT, 1.0f) :
                new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT);
        if (mTabWidth > 0) {
            lp_tab = new LinearLayout.LayoutParams((int) mTabWidth, LayoutParams.MATCH_PARENT);
        }

        mTabsContainer.addView(tabView, position, lp_tab);
    }

    private void updateTabStyles() {
        for (int i = 0; i < mTabCount; i++) {
            TextView tv_tab_title = getTabView(i);
            if (tv_tab_title != null) {
                tv_tab_title.setTextColor(i == mCurrentTab ? mTextSelectColor : mTextUnselectColor);
                tv_tab_title.setTextSize(TypedValue.COMPLEX_UNIT_PX, mTextSize);
//                tv_tab_title.setTextSize(TypedValue.COMPLEX_UNIT_PX, i == mCurrentTab ? (int) (mTextSize) : mTextSize);
                tv_tab_title.setPadding((int) mTabPadding, 0, (int) mTabPadding, 0);
                if (mTextAllCaps) {
                    tv_tab_title.setText(tv_tab_title.getText().toString().toUpperCase());
                }

                if (mTextBold == TEXT_BOLD_BOTH) {
                    tv_tab_title.getPaint().setFakeBoldText(true);
                } else if (mTextBold == TEXT_BOLD_NONE) {
                    tv_tab_title.getPaint().setFakeBoldText(false);
                } else {
                    tv_tab_title.getPaint().setFakeBoldText(i == mCurrentTab);
                }
            }
        }
    }



    /**
     * position:当前View的位置
     * mCurrentPositionOffset:当前View的偏移量比例.[0,1)
     */
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        this.mCurrentTab = position;
        this.mCurrentPositionOffset = positionOffset;
        scrollToCurrentTab();
        invalidate();
    }

    private TextView getTabView(int i) {
        View tabView = mTabsContainer.getChildAt(i);
        return tabView.findViewById(R.id.tv_tab_title);
    }

    @Override
    public void onPageSelected(int position) {
        updateTabSelection(position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {
    }

    /**
     * HorizontalScrollView滚到当前tab,并且居中显示
     */
    private void scrollToCurrentTab() {
        if (mTabCount <= 0) {
            return;
        }

        int offset = (int) (mCurrentPositionOffset * mTabsContainer.getChildAt(mCurrentTab).getWidth());
        int newScrollX = mTabsContainer.getChildAt(mCurrentTab).getLeft() + offset;


            if(mCurrentTab==0){
                newScrollX-= (int) mTabFirstLastMargin;
            }
        if (mCurrentTab > 0 || offset > 0) {
            newScrollX -= getWidth() / 2 - getPaddingLeft();
            calcIndicatorRect();
            newScrollX += ((mTabRect.right - mTabRect.left) / 2);
        }

        if (newScrollX != mLastScrollX) {
            mLastScrollX = newScrollX;
            scrollTo(newScrollX, 0);
        }
    }

    private void scaleIn(View view) {
        if (mTextScale > 0) {
            ObjectAnimator scaleInXAnimator = ObjectAnimator.ofFloat(view, "scaleX", mTextScale);
            ObjectAnimator scaleInYAnimator = ObjectAnimator.ofFloat(view, "scaleY", mTextScale);
            AnimatorSet scaleInAnimSet = new AnimatorSet();
            scaleInAnimSet.setDuration(TEXT_SCALE_DURATION);
            scaleInAnimSet.setInterpolator(new LinearInterpolator());
            scaleInAnimSet.play(scaleInXAnimator)
                    .with(scaleInYAnimator);
            scaleInAnimSet.start();
        }
    }

    private void scaleOut(View view) {
        ObjectAnimator scaleOutXAnimator = ObjectAnimator.ofFloat(view, "scaleX", 1.0f);
        ObjectAnimator scaleOutYAnimator = ObjectAnimator.ofFloat(view, "scaleY", 1.0f);
        AnimatorSet scaleOutAnimSet = new AnimatorSet();
        scaleOutAnimSet.setDuration(TEXT_SCALE_DURATION);
        scaleOutAnimSet.setInterpolator(new LinearInterpolator());
        scaleOutAnimSet.play(scaleOutXAnimator)
                .with(scaleOutYAnimator);
        scaleOutAnimSet.start();
    }

    private void updateTabSelection(int position) {
        for (int i = 0; i < mTabCount; ++i) {
            View tabView = mTabsContainer.getChildAt(i);
            final boolean isSelect = i == position;
            TextView tab_title = tabView.findViewById(R.id.tv_tab_title);
            if (tab_title != null) {
                tab_title.setTextColor(isSelect ? mTextSelectColor : mTextUnselectColor);
                if (mTextBold == TEXT_BOLD_WHEN_SELECT) {
                    tab_title.getPaint().setFakeBoldText(isSelect);
                }
                if (isSelect) {
                    scaleIn(tab_title);
                } else {
                    scaleOut(tab_title);
                }
                invalidate();
            }
        }
        invalidate();
    }

    private float margin;

    protected void calcIndicatorRect() {
        View currentTabView = mTabsContainer.getChildAt(this.mCurrentTab);
        float left = currentTabView.getLeft();
        float right = currentTabView.getRight();

        if (mIndicatorStyle == MODE_NORMAL && mIndicatorWidthEqualTitle) {
            TextView tab_title = currentTabView.findViewById(R.id.tv_tab_title);
            mTextPaint.setTextSize(mTextSize);
            float textWidth = mTextPaint.measureText(tab_title.getText().toString());
            margin = (right - left - textWidth-mIndicatorWidthEqualTitleExtra) / 2;
        }

        if (this.mCurrentTab < mTabCount - 1) {
            View nextTabView = mTabsContainer.getChildAt(this.mCurrentTab + 1);
            float nextTabLeft = nextTabView.getLeft();
            float nextTabRight = nextTabView.getRight();

            left = left + mCurrentPositionOffset * (nextTabLeft - left);
            right = right + mCurrentPositionOffset * (nextTabRight - right);

            if (mIndicatorStyle == MODE_NORMAL && mIndicatorWidthEqualTitle) {
                TextView next_tab_title = nextTabView.findViewById(R.id.tv_tab_title);
                mTextPaint.setTextSize(mTextSize);
                float nextTextWidth = mTextPaint.measureText(next_tab_title.getText().toString());
                float nextMargin = (nextTabRight - nextTabLeft - nextTextWidth-mIndicatorWidthEqualTitleExtra) / 2;
                margin = margin + mCurrentPositionOffset * (nextMargin - margin);
            }
        }

        mIndicatorRect.left = (int) left;
        mIndicatorRect.right = (int) right;
        //for mIndicatorWidthEqualTitle
        if (mIndicatorStyle == MODE_NORMAL && mIndicatorWidthEqualTitle) {
            mIndicatorRect.left = (int) (left + margin - 1);
            mIndicatorRect.right = (int) (right - margin - 1);
        }

        mTabRect.left = (int) left;
        mTabRect.right = (int) right;


        if (mIndicatorWidth > 0) {
            if (mIndicatorStyle == MODE_EXACTLY) {
                float indicatorLeft = currentTabView.getLeft() + (currentTabView.getWidth() - mIndicatorWidth) / 2;
                float indicatorRight = currentTabView.getLeft() + (currentTabView.getWidth() + mIndicatorWidth) / 2;

                if (this.mCurrentTab < mTabCount - 1) {
                    View nextTab = mTabsContainer.getChildAt(this.mCurrentTab + 1);
                    float nextLeft = nextTab.getLeft() + (nextTab.getWidth() - mIndicatorWidth) / 2;
                    float nextRight = nextTab.getLeft() + (nextTab.getWidth() + mIndicatorWidth) / 2;

                    indicatorLeft = indicatorLeft + (nextLeft - indicatorLeft) * mStartInterpolator.getInterpolation(mCurrentPositionOffset);
                    indicatorRight = indicatorRight + (nextRight - indicatorRight) * mEndInterpolator.getInterpolation(mCurrentPositionOffset);
                }
                mIndicatorRect.left = (int) indicatorLeft;
                mIndicatorRect.right = (int) indicatorRight;
            } else {
                float indicatorLeft = currentTabView.getLeft() + (currentTabView.getWidth() - mIndicatorWidth) / 2;

                if (this.mCurrentTab < mTabCount - 1) {
                    View nextTab = mTabsContainer.getChildAt(this.mCurrentTab + 1);
                    indicatorLeft = indicatorLeft + mCurrentPositionOffset * (currentTabView.getWidth() / 2 + nextTab.getWidth() / 2);
                }

                mIndicatorRect.left = (int) indicatorLeft;
                mIndicatorRect.right = (int) (mIndicatorRect.left + mIndicatorWidth);
            }

        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (isInEditMode() || mTabCount <= 0) {
            return;
        }

        int width = getWidth();
        int height = getHeight();

        if (mIndicatorStrokeWidth > 0) {//画边框
            mIndicatorStrokeDrawable.setCornerRadius(mIndicatorStrokeRadius);
            mIndicatorStrokeDrawable.setStroke((int) mIndicatorStrokeWidth, mIndicatorStrokeColor);
        }

        int paddingLeft = getPaddingLeft();
        int paddingRight = getPaddingRight();
        // draw divider
        if (mDividerWidth > 0) {
            mDividerPaint.setStrokeWidth(mDividerWidth);
            mDividerPaint.setColor(mDividerColor);
            for (int i = 0; i < mTabCount - 1; i++) {
                View tab = mTabsContainer.getChildAt(i);
                canvas.drawLine(paddingLeft + tab.getRight(), mDividerPadding, paddingLeft + tab.getRight(), height - mDividerPadding, mDividerPaint);
            }
        }

        // draw underline
        if (mUnderlineHeight > 0) {
            mRectPaint.setColor(mUnderlineColor);
            if (mUnderlineGravity == Gravity.BOTTOM) {
                canvas.drawRect(paddingLeft, height - mUnderlineHeight, mTabsContainer.getWidth() + paddingLeft, height, mRectPaint);
            } else {
                canvas.drawRect(paddingLeft, 0, mTabsContainer.getWidth() + paddingLeft, mUnderlineHeight, mRectPaint);
            }
        }
        //draw indicator line

        calcIndicatorRect();
        if (mIndicatorStyle == MODE_BLOCK) {
            if (mIndicatorHeight < 0) {
                mIndicatorHeight = height - mIndicatorMarginTop - mIndicatorMarginBottom;
            }
            if (mIndicatorHeight > 0 && mIndicatorCornerRadius < 0 || mIndicatorCornerRadius > mIndicatorHeight / 2) {
                mIndicatorCornerRadius = mIndicatorHeight / 2;
            }
            //draw unselected
            if (mIndicatorUnselected) {
                mRectPaint.setColor(mIndicatorUnselectedColor);
                for (int i = 0; i < mTabsContainer.getChildCount(); i++) {
                    View mView = mTabsContainer.getChildAt(i);
                    float indicatorLeft = mView.getLeft() + (mView.getWidth() - mIndicatorWidth) / 2;
                    float indicatorRight = mView.getLeft() + (mView.getWidth() + mIndicatorWidth) / 2;
                    unselectedRect.set(paddingLeft + (int) mIndicatorMarginLeft + indicatorLeft,
                            (int) mIndicatorMarginTop, (int) (paddingLeft + indicatorRight - mIndicatorMarginRight),
                            (int) (mIndicatorMarginTop + mIndicatorHeight));
                    canvas.drawRoundRect(unselectedRect, mIndicatorCornerRadius, mIndicatorCornerRadius, mRectPaint);
                }
            }
            if (mIndicatorHeight > 0) {
                if (!isIndicatorDrawable) {
                    mIndicatorDrawable.setColor(mIndicatorColor);
                }
                mIndicatorDrawable.setBounds(paddingLeft + (int) mIndicatorMarginLeft + mIndicatorRect.left,
                        (int) mIndicatorMarginTop, (int) (paddingLeft + mIndicatorRect.right - mIndicatorMarginRight),
                        (int) (mIndicatorMarginTop + mIndicatorHeight));
                mIndicatorStrokeDrawable.setBounds(paddingLeft + (int) mIndicatorMarginLeft,
                        (int) mIndicatorMarginTop,
                        (int) (width - mIndicatorMarginRight - paddingRight),
                        (int) (mIndicatorMarginTop + mIndicatorHeight));
                mIndicatorDrawable.setCornerRadius(mIndicatorCornerRadius);
                mIndicatorDrawable.draw(canvas);
                mIndicatorStrokeDrawable.draw(canvas);
            }
        } else {
            if (mIndicatorHeight > 0) {
                if (!isIndicatorDrawable) {
                    mIndicatorDrawable.setColor(mIndicatorColor);
            }

                if (mIndicatorGravity == Gravity.BOTTOM) {
                    mIndicatorDrawable.setBounds(paddingLeft + (int) mIndicatorMarginLeft + mIndicatorRect.left,
                            height - (int) mIndicatorHeight - (int) mIndicatorMarginBottom,
                            paddingLeft + mIndicatorRect.right - (int) mIndicatorMarginRight,
                            height - (int) mIndicatorMarginBottom);
                    mIndicatorStrokeDrawable.setBounds(paddingLeft + (int) mIndicatorMarginLeft,
                            (int) mIndicatorMarginTop,
                            (int) (width - mIndicatorMarginRight - paddingRight),
                            (int) (mIndicatorMarginTop + mIndicatorHeight));
                } else {
                    mIndicatorDrawable.setBounds(paddingLeft + (int) mIndicatorMarginLeft + mIndicatorRect.left,
                            (int) mIndicatorMarginTop,
                            paddingLeft + mIndicatorRect.right - (int) mIndicatorMarginRight,
                            (int) mIndicatorHeight + (int) mIndicatorMarginTop);
                    mIndicatorStrokeDrawable.setBounds(paddingLeft + (int) mIndicatorMarginLeft,
                            (int) mIndicatorMarginTop,
                            (int) (width - mIndicatorMarginRight - paddingRight),
                            (int) (mIndicatorMarginTop + mIndicatorHeight));
                }
                mIndicatorDrawable.setCornerRadius(mIndicatorCornerRadius);
                mIndicatorDrawable.draw(canvas);
                mIndicatorStrokeDrawable.draw(canvas);
            }
        }
    }
    public void setCurrentTabForce(int currentTab) {
        this.mCurrentTab = currentTab;
        mViewPager.setCurrentItem(currentTab);
        updateTabSelection(currentTab);
    }



    public void setTabPadding(float tabPadding) {
        this.mTabPadding = dp2px(tabPadding);
        updateTabStyles();
    }

    public void setTabSpaceEqual(boolean tabSpaceEqual) {
        this.mTabSpaceEqual = tabSpaceEqual;
        updateTabStyles();
    }

    public void setTabWidth4Px(float tabWidth) {
        this.mTabWidth = tabWidth;
        updateTabStyles();
    }

    public void setTabWidth(float tabWidth) {
        this.mTabWidth = dp2px(tabWidth);
        updateTabStyles();
    }

    public void setTabWidthNoChange(float tabWidth) {
        this.mTabWidth = tabWidth;
        updateTabStyles();
    }

    public void setIndicatorColor(int indicatorColor) {
        this.mIndicatorColor = indicatorColor;
        invalidate();
    }

    public void setIndicatorHeight(float indicatorHeight) {
        this.mIndicatorHeight = dp2px(indicatorHeight);
        invalidate();
    }

    public void setIndicatorWidth(float indicatorWidth) {
        this.mIndicatorWidth = dp2px(indicatorWidth);
        invalidate();
    }

    public void setIndicatorWidthNoChange(float indicatorWidth) {
        this.mIndicatorWidth = indicatorWidth;
        invalidate();
    }

    public void setIndicatorCornerRadius(float indicatorCornerRadius) {
        this.mIndicatorCornerRadius = dp2px(indicatorCornerRadius);
        invalidate();
    }

    public void setIndicatorGravity(int indicatorGravity) {
        this.mIndicatorGravity = indicatorGravity;
        invalidate();
    }

    public void setIndicatorMargin(float indicatorMarginLeft, float indicatorMarginTop,
                                   float indicatorMarginRight, float indicatorMarginBottom) {
        this.mIndicatorMarginLeft = dp2px(indicatorMarginLeft);
        this.mIndicatorMarginTop = dp2px(indicatorMarginTop);
        this.mIndicatorMarginRight = dp2px(indicatorMarginRight);
        this.mIndicatorMarginBottom = dp2px(indicatorMarginBottom);
        invalidate();
    }

    public void setIndicatorWidthEqualTitle(boolean indicatorWidthEqualTitle) {
        this.mIndicatorWidthEqualTitle = indicatorWidthEqualTitle;
        invalidate();
    }

    public void setUnderlineColor(int underlineColor) {
        this.mUnderlineColor = underlineColor;
        invalidate();
    }

    public void setUnderlineHeight(float underlineHeight) {
        this.mUnderlineHeight = dp2px(underlineHeight);
        invalidate();
    }

    public void setUnderlineGravity(int underlineGravity) {
        this.mUnderlineGravity = underlineGravity;
        invalidate();
    }

    public void setDividerColor(int dividerColor) {
        this.mDividerColor = dividerColor;
        invalidate();
    }

    public void setDividerWidth(float dividerWidth) {
        this.mDividerWidth = dp2px(dividerWidth);
        invalidate();
    }

    public void setDividerPadding(float dividerPadding) {
        this.mDividerPadding = dp2px(dividerPadding);
        invalidate();
    }

    public void setTextSize(float textSize) {
        this.mTextSize = sp2px(textSize);
        updateTabStyles();
    }

    public void setTextSelectColor(int textSelectColor) {
        this.mTextSelectColor = textSelectColor;
        updateTabStyles();
    }

    public void setTextUnselectColor(int textUnselectColor) {
        this.mTextUnselectColor = textUnselectColor;
        updateTabStyles();
    }

    public void setTextBold(int textBold) {
        this.mTextBold = textBold;
        updateTabStyles();
    }

    public void setTextAllCaps(boolean textAllCaps) {
        this.mTextAllCaps = textAllCaps;
        updateTabStyles();
    }

    public void setSnapOnTabClick(boolean snapOnTabClick) {
        mSnapOnTabClick = snapOnTabClick;
    }


    public int getTabCount() {
        return mTabCount;
    }

    public int getCurrentTab() {
        return mCurrentTab;
    }

    public int getIndicatorStyle() {
        return mIndicatorStyle;
    }

    public float getTabPadding() {
        return mTabPadding;
    }

    public boolean isTabSpaceEqual() {
        return mTabSpaceEqual;
    }

    public float getTabWidth() {
        return mTabWidth;
    }

    public int getIndicatorColor() {
        return mIndicatorColor;
    }

    public float getIndicatorHeight() {
        return mIndicatorHeight;
    }

    public float getIndicatorWidth() {
        return mIndicatorWidth;
    }

    public float getIndicatorCornerRadius() {
        return mIndicatorCornerRadius;
    }

    public float getIndicatorMarginLeft() {
        return mIndicatorMarginLeft;
    }

    public float getIndicatorMarginTop() {
        return mIndicatorMarginTop;
    }

    public float getIndicatorMarginRight() {
        return mIndicatorMarginRight;
    }

    public float getIndicatorMarginBottom() {
        return mIndicatorMarginBottom;
    }

    public int getUnderlineColor() {
        return mUnderlineColor;
    }

    public float getUnderlineHeight() {
        return mUnderlineHeight;
    }

    public int getDividerColor() {
        return mDividerColor;
    }

    public float getDividerWidth() {
        return mDividerWidth;
    }

    public float getDividerPadding() {
        return mDividerPadding;
    }

    public float getTextSize() {
        return mTextSize;
    }

    public int getTextSelectColor() {
        return mTextSelectColor;
    }

    public int getTextUnselectColor() {
        return mTextUnselectColor;
    }

    public int getTextBold() {
        return mTextBold;
    }

    public boolean isTextAllCaps() {
        return mTextAllCaps;
    }

    protected boolean isIndicatorDrawable = false;

    /**
     * 设置渐变
     */
    public void setGradientIndicatorDrawable() {
        setGradientIndicatorDrawableSearch(getResources().getColor(R.color.color_fffc8e02), getResources().getColor(R.color.color_ff6b00));
    }

    /**
     * 设置渐变
     */
    public void setGradientIndicatorDrawable1() {
        setGradientIndicatorDrawableSearch(getResources().getColor(R.color.color_ff6b00), getResources().getColor(R.color.color_fffc8e02));
    }

    public void setGradientIndicatorDrawableSearch() {
        setGradientIndicatorDrawableSearch(getResources().getColor(R.color.color_ff6b00), getResources().getColor(R.color.color_ff6b00));
    }

    public void setGradientIndicatorDrawableWhite() {
        setGradientIndicatorDrawableSearch(getResources().getColor(R.color.white), getResources().getColor(R.color.white));
    }

    public void setGradientIndicatorDrawableSearch(int startColor, int endColor) {
        mIndicatorDrawable.setOrientation(GradientDrawable.Orientation.RIGHT_LEFT);
        mIndicatorDrawable.setColors(new int[]{startColor, endColor});
        isIndicatorDrawable = true;
        invalidate();
    }

    public void updateText(List<String> list) {
        int count = mTabsContainer.getChildCount();
        //更新的数据长度要跟原长度一致
        if (count == list.size()) {
            for (int i = 0; i < count; i++) {
                View mView = mTabsContainer.getChildAt(i);
                TextView textView = mView.findViewById(R.id.tv_tab_title);
                textView.setText(list.get(i));
            }
        }
    }

    /**
     * 设置显示右边的图标
     */
    public void showRightIcon(int tab, boolean show) {
        if (tab < mTabsContainer.getChildCount()) {
            View currentTabView = mTabsContainer.getChildAt(tab);
            if (show) {
                currentTabView.findViewById(R.id.tv_tab_rightIcon).setVisibility(View.VISIBLE);
            } else {
                currentTabView.findViewById(R.id.tv_tab_rightIcon).setVisibility(View.GONE);
            }
        }
    }

    public TextView getTitleView(int tab) {
        View tabView = mTabsContainer.getChildAt(tab);
        return (TextView) tabView.findViewById(R.id.tv_tab_title);
    }

    private Paint mTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);

    private OnTabSelectListener mListener;

    public void setOnTabSelectListener(OnTabSelectListener listener) {
        this.mListener = listener;
    }

    class InnerPagerAdapter extends FragmentPagerAdapter {
        private ArrayList<Fragment> fragments;
        private String[] titles;

        InnerPagerAdapter(FragmentManager fm, ArrayList<Fragment> fragments, String[] titles) {
            super(fm);
            this.fragments = fragments;
            this.titles = titles;
        }

        @Override
        public int getCount() {
            return fragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return titles[position];
        }

        @Override
        public Fragment getItem(int position) {
            return fragments.get(position);
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {

        }

        @Override
        public int getItemPosition(@NotNull Object object) {
            return PagerAdapter.POSITION_NONE;
        }
    }

    @Override
    protected Parcelable onSaveInstanceState() {
        Bundle bundle = new Bundle();
        bundle.putParcelable("instanceState", super.onSaveInstanceState());
        bundle.putInt("mCurrentTab", mCurrentTab);
        return bundle;
    }

    @Override
    protected void onRestoreInstanceState(Parcelable state) {
        if (state instanceof Bundle) {
            Bundle bundle = (Bundle) state;
            mCurrentTab = bundle.getInt("mCurrentTab");
            state = bundle.getParcelable("instanceState");
            if (mCurrentTab != 0 && mTabsContainer.getChildCount() > 0) {
                updateTabSelection(mCurrentTab);
                scrollToCurrentTab();
            }
        }
        super.onRestoreInstanceState(state);
    }

    protected int dp2px(float dp) {
        final float scale = mContext.getResources().getDisplayMetrics().density;
        return (int) (dp * scale + 0.5f);
    }

    protected int sp2px(float sp) {
        final float scale = this.mContext.getResources().getDisplayMetrics().scaledDensity;
        return (int) (sp * scale + 0.5f);
    }
}
