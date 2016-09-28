package local.host.eci.src.Interface.Listener;

import android.support.v7.widget.RecyclerView;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.content.Context;
import android.view.View;

public class OnClickListenerRecycleView implements RecyclerView.OnItemTouchListener
{
    private ExtendedGestureListener m_GestureListener = null;
    private GestureDetector m_GestureDetector = null;

    public OnClickListenerRecycleView(Context c, OnClickListenerInterface l)
    {
        m_GestureListener = new ExtendedGestureListener(l);
        m_GestureDetector = new GestureDetector(c, m_GestureListener);
    }

    @Override public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e)
    {
        View View = rv.findChildViewUnder(e.getX(), e.getY());
        if(View == null) return false;

        int ViewPosition = rv.getChildAdapterPosition(View);
        m_GestureListener.setHelpers(View, ViewPosition);
        m_GestureDetector.onTouchEvent(e);

        return false;
    }

    @Override public void onTouchEvent(RecyclerView rv, MotionEvent e)
    {

    }

    @Override public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept)
    {

    }
}
