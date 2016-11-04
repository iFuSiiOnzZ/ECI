package eu.ifussionzz.wpReader.Interface.View.Adapter;

import eu.ifussionzz.wpReader.Interface.View.Holder.DrawerHolder;
import eu.ifussionzz.wpReader.Data.DrawerItem;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.View;

import android.support.v7.widget.RecyclerView;
import java.util.ArrayList;
import local.host.eci.R;

public class DrawerAdapter extends RecyclerView.Adapter<DrawerHolder>
{
    private static final int TYPE_HEADER = 0;
    private static final int TYPE_ITEM = 1;

    private ArrayList<DrawerItem> m_Data = null;
    private int m_ItemType = 0;

    public DrawerAdapter(ArrayList<DrawerItem> Data)
    {
        m_Data = Data;
    }

    @Override public DrawerHolder onCreateViewHolder(ViewGroup ViewGroup, int ViewType)
    {
        if(ViewType == TYPE_HEADER)
        {
            View View  = LayoutInflater.from(ViewGroup.getContext()).inflate(R.layout.drawer_row_01, ViewGroup, false);
            View.setTag(m_ItemType);

            m_ItemType = TYPE_HEADER;
            return new DrawerHolder(View);
        }

        View View = LayoutInflater.from(ViewGroup.getContext()).inflate(R.layout.drawer_row_0n, ViewGroup, false);
        View.setSelected(ViewType == 1);
        View.setTag(TYPE_ITEM);

        m_ItemType = TYPE_ITEM;
        return new DrawerHolder(View );
    }

    @Override public void onBindViewHolder(DrawerHolder Holder, int Position)
    {
        if(m_ItemType == TYPE_HEADER) return;

        Holder.m_ItemName.setText(m_Data.get(Position).Name);
        Holder.m_ItemImg.SetURL(m_Data.get(Position).Icon);
    }

    @Override public int getItemCount()
    {
        return m_Data.size();
    }

    @Override public int getItemViewType(int position)
    {
        return position;
    }
}