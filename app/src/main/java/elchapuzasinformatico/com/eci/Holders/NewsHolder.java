package elchapuzasinformatico.com.eci.Holders;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import elchapuzasinformatico.com.eci.R;
import elchapuzasinformatico.com.eci.Views.WebImageView;

/**
 * Created by AnDrEi AJ on 30/05/2015.
 */
public class NewsHolder extends RecyclerView.ViewHolder
{
    public WebImageView m_PostImage = null;

    public TextView m_PostComments = null;
    public TextView m_PostCategory = null;
    public TextView m_PostTime = null;

    public TextView m_PostDescription = null;
    public TextView m_PostTitle = null;

    public NewsHolder(View l_ItemView)
    {
        super(l_ItemView);

        m_PostImage = (WebImageView) l_ItemView.findViewById(R.id.id_post_image);

        m_PostComments = (TextView) l_ItemView.findViewById(R.id.id_post_ncomments);
        m_PostCategory = (TextView) l_ItemView.findViewById(R.id.id_post_category);
        m_PostTime = (TextView) l_ItemView.findViewById(R.id.id_post_time);

        m_PostDescription = (TextView) l_ItemView.findViewById(R.id.id_post_description);
        m_PostTitle = (TextView) l_ItemView.findViewById(R.id.id_post_title);
    }
}
