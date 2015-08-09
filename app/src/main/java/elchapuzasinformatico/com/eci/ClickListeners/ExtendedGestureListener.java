package elchapuzasinformatico.com.eci.ClickListeners;

import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by AnDrEi AJ on 31/05/2015.
 */
public class ExtendedGestureListener extends GestureDetector.SimpleOnGestureListener
{
    private int m_Position = 0;
    private View m_View = null;
    onClickListenerInterface m_Listener = null;

    /**
     * Constructor.
     * @param l_Listener la clase callback que recibira el aviso de touch.
     */
    public ExtendedGestureListener(onClickListenerInterface l_Listener)
    {
        m_Listener = l_Listener;
    }

    /**
     * Guarda la vista y la posicion de la vista que se ha pulsado.
     * @param l_View vista que se ha pulsado.
     * @param l_Position posicion que ocupa en la lista de vistas.
     */
    public void setHelpers(View l_View, int l_Position)
    {
        m_View = l_View;
        m_Position = l_Position;
    }

    /**
     * Se notifica que ha habido un toque en la pantalla.
     * @param l_Input entrada del usuario, cuando hace algun gesto en la pantalla.
     * @return siempre verdadero ya que procesamos en input.
     */
    @Override public boolean onSingleTapUp(MotionEvent l_Input)
    {
        m_Listener.onItemClick(m_View, m_Position);
        return true;
    }

    /**
     * Se notifica de que la pantalla ha estado pulsada durante un rato.
     * @param l_Input entrada del usuario, cuando hace algun gesto en la pantalla.
     */
    @Override public void onLongPress(MotionEvent l_Input)
    {
        m_Listener.onItemLongClick(m_View, m_Position);
    }
}

