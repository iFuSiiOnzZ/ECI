package local.host.eci.src.Interface.View.Holder;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import local.host.eci.R;
import local.host.eci.src.Interface.View.WebImageView;

public class SearchHolder extends RecyclerView.ViewHolder
{
    public WebImageView Image = null;
    public TextView Title = null;

    public SearchHolder(View ItemView)
    {
        super(ItemView);

        Image = (WebImageView) ItemView.findViewById(R.id.excerpt_image);
        Title = (TextView) ItemView.findViewById(R.id.excerpt_title);
    }
}