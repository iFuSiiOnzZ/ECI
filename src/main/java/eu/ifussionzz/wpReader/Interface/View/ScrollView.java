package eu.ifussionzz.wpReader.Interface.View;

import android.content.Context;
import android.util.AttributeSet;

import eu.ifussionzz.wpReader.Interface.Listener.OnScrollViewListener;

public class ScrollView extends android.widget.ScrollView
{
    private OnScrollViewListener m_OnScrollViewListener = null;

    public ScrollView(Context context)
    {
        super(context);
    }

    public ScrollView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
    }

    public ScrollView(Context context, AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
    }

    public ScrollView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes)
    {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    public void setOnScrollViewListener(OnScrollViewListener l)
    {
        m_OnScrollViewListener = l;
    }

    protected void onScrollChanged(int l, int t, int oldl, int oldt)
    {
        if(m_OnScrollViewListener != null) m_OnScrollViewListener.onScrollChanged( this, l, t, oldl, oldt );
        super.onScrollChanged( l, t, oldl, oldt );
    }
}
