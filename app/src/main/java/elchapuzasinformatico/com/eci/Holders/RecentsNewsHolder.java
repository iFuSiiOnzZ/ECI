package elchapuzasinformatico.com.eci.Holders;

import android.support.v7.widget.RecyclerView;
import android.widget.TextView;
import android.view.View;

import elchapuzasinformatico.com.eci.Views.WebImageView;
import elchapuzasinformatico.com.eci.R;

/**
 * Created by AnDrEi AJ on 30/05/2015.
 */
public class RecentsNewsHolder extends RecyclerView.ViewHolder
{
    public WebImageView m_PostImage = null;

    public TextView m_PostComments = null;
    public TextView m_PostCategory = null;

    public TextView m_PostTitle = null;
    public TextView m_PostTime = null;

    public RecentsNewsHolder(View l_ItemView)
    {
        super(l_ItemView);

        m_PostImage = (WebImageView) l_ItemView.findViewById(R.id.id_post_image);

        m_PostComments = (TextView) l_ItemView.findViewById(R.id.id_post_ncomments);
        m_PostCategory = (TextView) l_ItemView.findViewById(R.id.id_post_category);

        m_PostTitle = (TextView) l_ItemView.findViewById(R.id.id_post_title);
        m_PostTime = (TextView) l_ItemView.findViewById(R.id.id_post_time);
    }
}
