package elchapuzasinformatico.com.eci.ClickListeners;

import android.support.v7.widget.RecyclerView;
import android.content.Context;

import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by AnDrEi AJ on 10/07/2015.
 */
public class onClickListenerRecycleView implements RecyclerView.OnItemTouchListener
{
    private ExtendedGestureListener m_GestureListener = null;
    private GestureDetector m_GestureDetector = null;

    /**
     * Constructor.
     * @param l_Context contexto en el cual se ejecuta.
     * @param l_ClickListener la clase callback que recibira el aviso de click.
     */
    public onClickListenerRecycleView(Context l_Context, onClickListenerInterface l_ClickListener)
    {
        m_GestureListener = new ExtendedGestureListener(l_ClickListener);
        m_GestureDetector = new GestureDetector(l_Context, m_GestureListener);
    }

    /**
     * Cuando se ha detectado algun evento en la pantalla.
     * @param l_RecycleView la RecyclerView sobre la cual se ha pulsado.
     * @param e eventos hechos sobre la pantalla, velocidad, distancia, accion, etc.
     * @return
     */
    @Override public boolean onInterceptTouchEvent(RecyclerView l_RecycleView, MotionEvent e)
    {
        View l_View = l_RecycleView.findChildViewUnder(e.getX(), e.getY());
        if(l_View == null) return false;

        int l_ViewPosition = l_RecycleView.getChildAdapterPosition(l_View);
        m_GestureListener.setHelpers(l_View, l_ViewPosition);
        m_GestureDetector.onTouchEvent(e);

        return false;
    }

    /**
     * No se usa.
     * @param rv
     * @param e
     */
    @Override public void onTouchEvent(RecyclerView rv, MotionEvent e)
    {

    }
}