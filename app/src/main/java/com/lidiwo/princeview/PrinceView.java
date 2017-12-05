package com.lidiwo.princeview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.sql.Array;
import java.util.Arrays;

/**
 * *****************************************************
 *
 * @author：lidi
 * @date：2017/11/30 15:12
 * @Company：智能程序员
 * @Description： *****************************************************
 */
public class PrinceView extends View {
    private Paint linPaint = new Paint();
    private Paint leftTextPaint = new Paint();
    private Paint rightTextPaint = new Paint();

    private String pv_left_text;//左侧文字
    private float pv_left_textSize;//左侧文字大小
    private int pv_left_textColor;//左侧文字颜色
    private Drawable pv_leftDrawable; //左侧图片
    private int pv_drawablePadding; //左侧图片与文件间距
    private String pv_rigth_text;//右侧文字
    private Drawable pv_rigthText_background;//右侧文字背景
    private int pv_rigthText_paddingHorizontal;//左侧文字的左右padding
    private int pv_rigthText_paddingVertical; //左侧文字的上下padding
    private float pv_rigth_textSize;//右侧文字大小
    private int pv_rigth_textColor;//右侧文字颜色
    private int pv_arrowPadding;//箭头文字/箭头图片间距
    private Drawable pv_arrowDrawable;//箭头图片
    private Drawable pv_rightDrawable;//右侧图片
    private int pv_lineColor;//线颜色
    private int pv_aboveLineLeftMargin;//上线距离左边间距
    private int pv_aboveLineRightMargin;//上线距离右边间距
    private int pv_belowLineLeftMargin;//下线距离左边间距
    private int pv_belowLineRightMargin;//下线距离右边间距
    private int pv_lineHeight;//线高度
    private int pv_aboveLine_visibility;//上线是否显示
    private int pv_belowLine_visibility;//下线是否显示

    private int paddingLeft;
    private int paddingRight;
    private int paddingTop;
    private int paddingBottom;
    private int leftDrawableWidth;
    private int arrowDrawableWidth;
    private int maxValue;
    private int leftDrawableHeight;
    private int arrowDrawableHeight;
    private int aboveLineHeight;
    private int belowLineHeight;
    private int rightTextHeight;
    private int rightTextWidth;
    private int rightDrawableWidth;
    private int rightDrawableHeight;


    public PrinceView(Context context) {
        super(context);
    }

    public PrinceView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initAttributes(context, attrs);
        initPaint();
    }

    private void initAttributes(Context context, AttributeSet attrs) {
        TypedArray attributes = context.obtainStyledAttributes(attrs, R.styleable.PrinceView);
        if (attributes != null) {
            pv_left_text = attributes.getString(R.styleable.PrinceView_pv_left_text);
            pv_left_textSize = attributes.getDimensionPixelSize(R.styleable.PrinceView_pv_left_textSize, 24);
            pv_left_textColor = attributes.getColor(R.styleable.PrinceView_pv_left_textColor, Color.BLACK);
            pv_leftDrawable = attributes.getDrawable(R.styleable.PrinceView_pv_leftDrawable);
            pv_drawablePadding = attributes.getDimensionPixelSize(R.styleable.PrinceView_pv_drawablePadding, dip2px(5));
            pv_rigth_text = attributes.getString(R.styleable.PrinceView_pv_rigth_text);
            pv_rigth_textSize = attributes.getDimensionPixelSize(R.styleable.PrinceView_pv_rigth_textSize, 24);
            pv_rigth_textColor = attributes.getColor(R.styleable.PrinceView_pv_rigth_textColor, Color.BLACK);
            pv_rigthText_paddingHorizontal = attributes.getDimensionPixelSize(R.styleable.PrinceView_pv_rigthText_paddingHorizontal, 0);
            pv_rigthText_paddingVertical = attributes.getDimensionPixelSize(R.styleable.PrinceView_pv_rigthText_paddingVertical, 0);
            pv_rigthText_background = attributes.getDrawable(R.styleable.PrinceView_pv_rigthText_background);
            pv_arrowPadding = attributes.getDimensionPixelSize(R.styleable.PrinceView_pv_arrowPadding, dip2px(5));
            pv_arrowDrawable = attributes.getDrawable(R.styleable.PrinceView_pv_arrowDrawable);
            pv_rightDrawable = attributes.getDrawable(R.styleable.PrinceView_pv_rightDrawable);
            pv_lineColor = attributes.getColor(R.styleable.PrinceView_pv_lineColor, Color.BLACK);
            pv_aboveLineLeftMargin = (int) attributes.getDimension(R.styleable.PrinceView_pv_aboveLineLeftMargin, 0);
            pv_aboveLineRightMargin = (int) attributes.getDimension(R.styleable.PrinceView_pv_aboveLineRightMargin, 0);
            pv_belowLineLeftMargin = (int) attributes.getDimension(R.styleable.PrinceView_pv_belowLineLeftMargin, 0);
            pv_belowLineRightMargin = (int) attributes.getDimension(R.styleable.PrinceView_pv_belowLineRightMargin, 0);
            pv_lineHeight = (int) attributes.getDimension(R.styleable.PrinceView_pv_lineHeight, 0);
            pv_aboveLine_visibility = attributes.getInt(R.styleable.PrinceView_pv_aboveLine_visibility, 0);
            pv_belowLine_visibility = attributes.getInt(R.styleable.PrinceView_pv_belowLine_visibility, 0);

        }
        attributes.recycle();
    }

    private void initPaint() {
        //初始左侧文字画笔
        leftTextPaint.setColor(pv_left_textColor);
        leftTextPaint.setTextSize(pv_left_textSize);
        leftTextPaint.setTextAlign(Paint.Align.LEFT);
        leftTextPaint.setAntiAlias(true);

        //初始右侧文字画笔
        rightTextPaint.setColor(pv_rigth_textColor);
        rightTextPaint.setTextSize(pv_rigth_textSize);
        rightTextPaint.setTextAlign(Paint.Align.RIGHT);
        rightTextPaint.setAntiAlias(true);

        //初始上下线的画笔
        linPaint.setColor(pv_lineColor);
        linPaint.setAntiAlias(true);

    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        initMeasure();

        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSize = MeasureSpec.getSize(heightMeasureSpec);

        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);

        int measuredWidth;
        int measuredHeight;

        if (widthMode == MeasureSpec.EXACTLY) {
            measuredWidth = widthSize;
        } else {
            measuredWidth = getContext().getResources().getDisplayMetrics().widthPixels;
            if (widthMode == MeasureSpec.AT_MOST) {
                measuredWidth = Math.min(measuredWidth, widthSize);
            }
        }

        if (heightMode == MeasureSpec.EXACTLY) {
            measuredHeight = heightSize;
        } else {
            measuredHeight = paddingBottom + paddingTop + aboveLineHeight + belowLineHeight + maxValue;
            if (heightMode == MeasureSpec.AT_MOST) {
                measuredHeight = Math.min(measuredHeight, heightSize);
            }
        }
        setMeasuredDimension(measuredWidth, measuredHeight);
    }

    private void initMeasure() {
        paddingLeft = getPaddingLeft();
        paddingRight = getPaddingRight();

        paddingTop = getPaddingTop();
        paddingBottom = getPaddingBottom();

        aboveLineHeight = pv_aboveLine_visibility == View.GONE ? 0 : pv_lineHeight;
        belowLineHeight = pv_belowLine_visibility == View.GONE ? 0 : pv_lineHeight;

        //获取左侧图片的尺寸
        leftDrawableWidth = pv_leftDrawable == null ? 0 : pv_leftDrawable.getIntrinsicWidth();
        leftDrawableHeight = pv_leftDrawable == null ? 0 : pv_leftDrawable.getIntrinsicHeight();

        //获取左侧文字高度
        int leftTextHeight;
        if (TextUtils.isEmpty(pv_left_text)) {
            leftTextHeight = 0;
        } else {
            Rect leftTextRect = new Rect();
            leftTextPaint.getTextBounds(pv_left_text, 0, pv_left_text.length(), leftTextRect);
            leftTextHeight = leftTextRect.height();
        }

        //获取箭头尺寸
        arrowDrawableWidth = pv_arrowDrawable == null ? 0 : pv_arrowDrawable.getIntrinsicWidth();
        arrowDrawableHeight = pv_arrowDrawable == null ? 0 : pv_arrowDrawable.getIntrinsicWidth();

        //获取右侧文字尺寸
        if (TextUtils.isEmpty(pv_rigth_text)) {
            rightTextHeight = 0;
            rightTextWidth = 0;
        } else {
            Rect rightTextRect = new Rect();
            rightTextPaint.getTextBounds(pv_rigth_text, 0, pv_rigth_text.length(), rightTextRect);
            rightTextHeight = rightTextRect.height();
            rightTextWidth = rightTextRect.width();
        }

        //获取右侧图片尺寸
        rightDrawableWidth = pv_rightDrawable == null ? 0 : pv_rightDrawable.getIntrinsicWidth();
        rightDrawableHeight = pv_rightDrawable == null ? 0 : pv_rightDrawable.getIntrinsicHeight();

        maxValue = getMaxValue(new int[]{leftDrawableHeight, leftTextHeight, arrowDrawableHeight, rightTextHeight, rightDrawableHeight});
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //绘制左边图片
        float leftDrawableDx = paddingLeft;
        float leftDrawableDy = (maxValue - leftDrawableHeight) / 2 + paddingTop + aboveLineHeight;
        if (pv_leftDrawable != null) {
            canvas.save();
            canvas.translate(leftDrawableDx, leftDrawableDy);
            pv_leftDrawable.setBounds(0, 0, leftDrawableWidth, leftDrawableHeight);
            pv_leftDrawable.draw(canvas);
            canvas.restore();
        }

        //绘制左边文字
        if (!TextUtils.isEmpty(pv_left_text)) {
            canvas.save();
            Paint.FontMetrics leftTextFontMetrics = leftTextPaint.getFontMetrics();
            float leftTextTop = leftTextFontMetrics.top;//为基线到字体上边框的距离,即上图中的top
            float leftTextBottom = leftTextFontMetrics.bottom;//为基线到字体下边框的距离,即上图中的bottom
            float leftTextBaseLineX = paddingLeft + leftDrawableWidth + pv_drawablePadding;
            float leftTextBaseLineY;//基线中间点的y轴计算公式
            if (pv_aboveLine_visibility != View.GONE && pv_belowLine_visibility != View.GONE) {
                leftTextBaseLineY = (paddingBottom + belowLineHeight + paddingTop + aboveLineHeight + maxValue) / 2 - leftTextTop / 2 - leftTextBottom / 2;//基线中间点的y轴计算公式
            } else if (pv_aboveLine_visibility != View.GONE && pv_belowLine_visibility == View.GONE) {
                leftTextBaseLineY = (paddingBottom + paddingTop + maxValue) / 2 + aboveLineHeight - leftTextTop / 2 - leftTextBottom / 2;//基线中间点的y轴计算公式
            } else {
                leftTextBaseLineY = (paddingBottom + paddingTop + maxValue) / 2 - leftTextTop / 2 - leftTextBottom / 2;//基线中间点的y轴计算公式
            }
            canvas.drawText(pv_left_text, leftTextBaseLineX, leftTextBaseLineY, leftTextPaint);
            canvas.restore();
        }

        //绘制箭头
        if (pv_arrowDrawable != null) {
            canvas.save();
            float arrowDrawableDx = getWidth() - arrowDrawableWidth - paddingRight;
            float arrowDrawableDy = (maxValue - arrowDrawableHeight) / 2 + paddingTop + aboveLineHeight;
            canvas.translate(arrowDrawableDx, arrowDrawableDy);
            pv_arrowDrawable.setBounds(0, 0, arrowDrawableWidth, arrowDrawableHeight);
            pv_arrowDrawable.draw(canvas);
            canvas.restore();
        }

        //绘制右侧文字背景
        if (pv_rigthText_background != null) {
            canvas.save();
            float arrowDrawableDx = getWidth() - arrowDrawableWidth - paddingRight - pv_arrowPadding - rightTextWidth;
            float arrowDrawableDy = (maxValue - rightTextHeight) / 2 + paddingTop + aboveLineHeight;
            canvas.translate(arrowDrawableDx, arrowDrawableDy);
            pv_rigthText_background.setBounds(-pv_rigthText_paddingHorizontal, -pv_rigthText_paddingVertical, rightTextWidth + pv_rigthText_paddingHorizontal, rightTextHeight + pv_rigthText_paddingVertical);
            pv_rigthText_background.draw(canvas);
            canvas.restore();
        }

        //绘制右侧文字
        if (!TextUtils.isEmpty(pv_rigth_text)) {
            canvas.save();
            Paint.FontMetrics rightTextFontMetrics = rightTextPaint.getFontMetrics();
            float rightTextTop = rightTextFontMetrics.top;//为基线到字体上边框的距离,即上图中的top
            float rightTextBottom = rightTextFontMetrics.bottom;//为基线到字体下边框的距离,即上图中的bottom
            float rightTextBaseLineX = getWidth() - paddingRight - arrowDrawableWidth - pv_arrowPadding;
            float rightTextBaseLineY;//基线中间点的y轴计算公式
            if (pv_aboveLine_visibility != View.GONE && pv_belowLine_visibility != View.GONE) {
                rightTextBaseLineY = (paddingBottom + belowLineHeight + paddingTop + aboveLineHeight + maxValue) / 2 - rightTextTop / 2 - rightTextBottom / 2;//基线中间点的y轴计算公式
            } else if (pv_aboveLine_visibility != View.GONE && pv_belowLine_visibility == View.GONE) {
                rightTextBaseLineY = (paddingBottom + paddingTop + maxValue) / 2 + aboveLineHeight - rightTextTop / 2 - rightTextBottom / 2;//基线中间点的y轴计算公式
            } else {
                rightTextBaseLineY = (paddingBottom + paddingTop + maxValue) / 2 - rightTextTop / 2 - rightTextBottom / 2;//基线中间点的y轴计算公式
            }

            canvas.drawText(pv_rigth_text, rightTextBaseLineX, rightTextBaseLineY, rightTextPaint);
            canvas.restore();
        }

        //绘制右侧图片
        if (pv_rightDrawable != null) {
            canvas.save();
            float arrowDrawableDx = getWidth() - arrowDrawableWidth -pv_arrowPadding-rightDrawableWidth- paddingRight-rightTextWidth-pv_rigthText_paddingHorizontal*2;
            float arrowDrawableDy = (maxValue - rightDrawableHeight) / 2 + paddingTop + aboveLineHeight;
            canvas.translate(arrowDrawableDx, arrowDrawableDy);
            pv_rightDrawable.setBounds(0, 0, rightDrawableWidth, rightDrawableHeight);
            pv_rightDrawable.draw(canvas);
            canvas.restore();
        }


//        private int rightDrawableWidth;
//        private int rightDrawableHeight;
//


        //绘制上下线
        canvas.save();
        RectF aboveRectF = new RectF(pv_aboveLineLeftMargin, 0, getWidth() - pv_aboveLineRightMargin, aboveLineHeight);
        canvas.drawRect(aboveRectF, linPaint);

        RectF belowRectF = new RectF(pv_belowLineLeftMargin, paddingTop + aboveLineHeight + maxValue + paddingBottom, getWidth() - pv_belowLineRightMargin, paddingTop + aboveLineHeight + maxValue + paddingBottom + belowLineHeight);
        canvas.drawRect(belowRectF, linPaint);
        canvas.restore();
    }

    private int dip2px(float dpValue) {
        float scale = getContext().getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    private int getMaxValue(int[] arr) {
        int max = arr[0];
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] > max) {
                max = arr[i];
            }
        }
        return max;
    }
}
