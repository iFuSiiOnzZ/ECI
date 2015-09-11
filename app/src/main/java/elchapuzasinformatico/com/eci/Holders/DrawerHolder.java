package elchapuzasinformatico.com.eci.Holders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import elchapuzasinformatico.com.eci.R;


/**
 * Created by AnDrEi AJ on 10/07/2015.
 */
public class DrawerHolder extends RecyclerView.ViewHolder
{
    public TextView  m_ItemName = null;
    public ImageView m_ItemImg  = null;

    public DrawerHolder(View l_View)
    {
        super(l_View);

        m_ItemName = (TextView)  l_View.findViewById(R.id.drawer_item_name);
        m_ItemImg  = (ImageView) l_View.findViewById(R.id.drawer_item_img);
    }
}
