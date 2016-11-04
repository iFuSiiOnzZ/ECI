package eu.ifussionzz.wpReader.Data;

import android.graphics.Bitmap;

public class DrawerItem
{
    public String Name = null;
    public String Icon = null;
    public String URL = null;

    public DrawerItem(String Name, String URL, String Icon)
    {
        this.Name = Name;
        this.Icon = Icon;
        this.URL = URL;
    }
}
