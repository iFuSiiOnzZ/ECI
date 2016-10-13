package eu.ifussionzz.eci.Interface.View.Holder;

import android.support.v7.widget.RecyclerView;
import android.widget.TextView;
import android.view.View;

import local.host.eci.R;
import eu.ifussionzz.eci.Interface.View.WebImageView;

public class ExcerptHolder extends RecyclerView.ViewHolder
{
    public WebImageView Image = null;
    public TextView Time = null;

    public TextView Comments = null;
    public TextView Category = null;

    public TextView Description = null;
    public TextView Title = null;

    public ExcerptHolder(View ItemView)
    {
        super(ItemView);

        Image = (WebImageView) ItemView.findViewById(R.id.excerpt_image);
        Time = (TextView) ItemView.findViewById(R.id.excerpt_time);

        Comments = (TextView) ItemView.findViewById(R.id.excerpt_ncomments);
        Category = (TextView) ItemView.findViewById(R.id.excerpt_category);

        Description = (TextView) ItemView.findViewById(R.id.excerpt_description);
        Title = (TextView) ItemView.findViewById(R.id.excerpt_title);
    }
}