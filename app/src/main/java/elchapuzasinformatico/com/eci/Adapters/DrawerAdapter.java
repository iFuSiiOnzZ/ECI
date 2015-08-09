package elchapuzasinformatico.com.eci.Adapters;

import android.support.v7.widget.RecyclerView;
import android.graphics.BitmapFactory;
import android.graphics.Bitmap;

import android.view.LayoutInflater;
import android.widget.ImageView;
import android.view.ViewGroup;
import android.view.View;

import java.util.ArrayList;

import elchapuzasinformatico.com.eci.Holders.DrawerHolder;
import elchapuzasinformatico.com.eci.Utilities.ImageUtils;
import elchapuzasinformatico.com.eci.Models.DrawerItem;
import elchapuzasinformatico.com.eci.R;

/**
 * Created by andrei on 8/7/15.
 */
public class DrawerAdapter extends RecyclerView.Adapter<DrawerHolder>
{
    private static final int TYPE_HEADER = 0;
    private static final int TYPE_ITEM = 1;

    private ArrayList<DrawerItem> m_Data = null;
    private int m_ItemType = 0;

    /**
     * Constructor.
     * @param l_Data
     */
    public DrawerAdapter(ArrayList<DrawerItem> l_Data)
    {
        m_Data = l_Data;
    }

    /**
     *
     * @param l_ViewGroup contiene la vista padre.
     * @param l_ViewType tipo de vista que vamos a generar (header o menu).
     * @return estructura que contiene todas las vistas del layout.
     */
    @Override public DrawerHolder onCreateViewHolder(ViewGroup l_ViewGroup, int l_ViewType)
    {
        if(l_ViewType == TYPE_HEADER)
        {
            View l_View  = LayoutInflater.from(l_ViewGroup.getContext()).inflate(R.layout.drawer_header, l_ViewGroup, false);
            l_View.setTag(m_ItemType); m_ItemType = TYPE_HEADER;

            Bitmap l_Bitmap = BitmapFactory.decodeResource(l_ViewGroup.getContext().getResources(), R.drawable.ic_launcher);
            ImageView l_HeaderImage = (ImageView) l_View.findViewById(R.id.id_drawer_header_img);

            l_Bitmap = new ImageUtils().roundImage(true).StrokeImage(l_Bitmap);
            l_HeaderImage.setImageBitmap(l_Bitmap);

            return new DrawerHolder(l_View);
        }

        View l_View = LayoutInflater.from(l_ViewGroup.getContext()).inflate(R.layout.drawer_row, l_ViewGroup, false);
        l_View.setTag(TYPE_ITEM); m_ItemType = TYPE_ITEM;
        l_View.setSelected(l_ViewType == 1);

        return new DrawerHolder(l_View);
    }

    /**
     *
     * @param l_Holder estructura que contiene todas las vistas del layout.
     * @param l_Position posicion en la array de menus con la cual llenaremos la vista.
     */
    @Override public void onBindViewHolder(DrawerHolder l_Holder, int l_Position)
    {
        if(m_ItemType == TYPE_HEADER) return;

        l_Holder.m_ItemName.setText(m_Data.get(l_Position).m_ItemName);
        l_Holder.m_ItemImg.setImageBitmap(m_Data.get(l_Position).m_ItemIcon);
    }

    /**
     * Devuelve el numero de elementos disponibles.
     * @return numero de noticias.
     */
    @Override public int getItemCount()
    {
        return m_Data.size();
    }

    /**
     * Esta funcion nos dice que tipo vista vamos a generar (l_ViewType de onCreateViewHolder)
     * @param position  posicion en la array de menus.
     * @return la misma posicion.
     */
    @Override public int getItemViewType(int position)
    {
       return position;
    }
}
