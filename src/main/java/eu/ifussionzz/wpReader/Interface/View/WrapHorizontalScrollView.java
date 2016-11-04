package eu.ifussionzz.wpReader.Interface.View;

import android.widget.HorizontalScrollView;
import android.util.AttributeSet;
import android.content.Context;

/**
 *  https://code.google.com/p/android/issues/detail?id=21291
 *  HorizontalScrollView incorrectly measures its height
 */

public class WrapHorizontalScrollView extends HorizontalScrollView
{
    public WrapHorizontalScrollView(Context context)
    {
        this(context, null);
    }

    public WrapHorizontalScrollView(Context context, AttributeSet attrs)
    {
        this(context, attrs, 0);
    }

    public WrapHorizontalScrollView(Context context, AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
    }

    @Override protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec)
    {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        if (getChildCount() > 0 && isFillViewport())
        {
            int height =  getChildAt(0).getMeasuredHeight(), width = getMeasuredWidth();
            setMeasuredDimension(width, height);
        }
    }
}
