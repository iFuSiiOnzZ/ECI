package eu.ifussionzz.wpReader.Interface.View.Holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import eu.ifussionzz.wpReader.Interface.View.WebImageView;
import android.widget.TextView;

import local.host.eci.R;

public class DrawerHolder extends RecyclerView.ViewHolder
{
    public TextView  m_ItemName = null;
    public WebImageView m_ItemImg  = null;

    public DrawerHolder(View ItemView)
    {
        super(ItemView);

        m_ItemName = (TextView)  ItemView.findViewById(R.id.drawer_item_name);
        m_ItemImg  = (WebImageView) ItemView.findViewById(R.id.drawer_item_img);
    }
}