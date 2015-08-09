package elchapuzasinformatico.com.eci.ClickListeners;

import android.view.View;

/**
 * Created by AnDrEi AJ on 31/05/2015.
 */
public interface onClickListenerInterface
{
    /**
     * Cuando se ha pulsado una vez (toque).
     * @param l_View vista que se ha pulsado.
     * @param l_Position posicion que ocupa en la lista de vistas.
     */
    public void onItemClick(View l_View, int l_Position);

    /**
     * Cuando se mantiene pulsado.
     * @param l_View vista que se esta pulsando.
     * @param l_Position posicion que ocupa en la lista de vistas.
     */
    public void onItemLongClick(View l_View, int l_Position);
}
