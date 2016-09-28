package local.host.eci.src.Interface.Listener;

import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

public class ExtendedGestureListener extends GestureDetector.SimpleOnGestureListener
{
    private int m_Position = -1; private View m_View = null;
    private OnClickListenerInterface m_Listener = null;

    public ExtendedGestureListener(OnClickListenerInterface l)
    {
        m_Listener = l;
    }

    public void setHelpers(View v, int p)
    {
        m_View = v;
        m_Position = p;
    }


    @Override public boolean onSingleTapUp(MotionEvent l_Input)
    {
        m_Listener.onItemClick(m_View, m_Position);
        return true;
    }

    @Override public void onLongPress(MotionEvent l_Input)
    {
        m_Listener.onItemLongClick(m_View, m_Position);
    }
}
