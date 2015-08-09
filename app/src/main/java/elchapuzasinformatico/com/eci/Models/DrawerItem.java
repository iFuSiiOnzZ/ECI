package elchapuzasinformatico.com.eci.Models;

import android.graphics.Bitmap;

/**
 * Created by andrei on 8/7/15.
 */
public class DrawerItem
{
    public String m_ItemName = null;
    public Bitmap m_ItemIcon = null;

    public DrawerItem(String l_Name, Bitmap l_Icon)
    {
        m_ItemName = l_Name;
        m_ItemIcon = l_Icon;
    }
}
