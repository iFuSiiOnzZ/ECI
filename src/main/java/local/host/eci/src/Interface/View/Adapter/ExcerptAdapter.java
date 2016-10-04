package local.host.eci.src.Interface.View.Adapter;

import android.support.v7.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;

import java.io.Serializable;
import java.util.ArrayList;
import android.text.Html;

import local.host.eci.src.Interface.Listener.OnClickListenerInterface;
import local.host.eci.src.Interface.View.Holder.ExcerptHolder;
import local.host.eci.src.Data.NewsExcerpt;
import local.host.eci.src.Activity_03;
import local.host.eci.R;

import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.view.View;

public class ExcerptAdapter extends RecyclerView.Adapter<ExcerptHolder> implements OnClickListenerInterface, Serializable
{
    ArrayList<NewsExcerpt> m_NewsExcerpt = null;
    Context m_Context = null;

    public ExcerptAdapter(ArrayList<NewsExcerpt> ne)
    {
        m_NewsExcerpt = ne;
    }

    @Override public ExcerptHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        View ExceprtView = LayoutInflater.from(parent.getContext()).inflate(R.layout.excerpt_01, parent, false);
        m_Context = parent.getContext();

        return new ExcerptHolder(ExceprtView);
    }

    @Override public void onBindViewHolder(ExcerptHolder holder, int position)
    {
        NewsExcerpt NewsExcerpt = m_NewsExcerpt.get(position);
        holder.Image.setImageDrawable(null);

        holder.Title.setText(Html.fromHtml(NewsExcerpt.Title));
        holder.Description.setText(Html.fromHtml(NewsExcerpt.Excerpt));

        holder.Time.setText(NewsExcerpt.Date);
        holder.Category.setText(NewsExcerpt.Categories.get(0).Title);

        String ComentsText = m_Context.getString(R.string.comments_lcp);
        if(NewsExcerpt.NumComments == 1) ComentsText = m_Context.getString(R.string.comments_lcs);

        holder.Comments.setText(NewsExcerpt.NumComments + " " + ComentsText);
        if(NewsExcerpt.Thumbnail != null) holder.Image.SetURL(NewsExcerpt.Thumbnail);
    }

    @Override public int getItemCount()
    {
        return m_NewsExcerpt == null ? 0 : m_NewsExcerpt.size();
    }

    @Override public void onItemClick(View v, int p)
    {
        if(m_NewsExcerpt == null || p >= m_NewsExcerpt.size()) return;
        Intent Activity_03 = new Intent(m_Context, Activity_03.class);

        if( m_NewsExcerpt.get(p).Images.Medium != null) Activity_03.putExtra("THUMBNAIL", m_NewsExcerpt.get(p).Images.Medium.URL);
        else Activity_03.putExtra("THUMBNAIL", "");

        Activity_03.putExtra("TITLE", m_NewsExcerpt.get(p).Title);
        Activity_03.putExtra("URL", m_NewsExcerpt.get(p).Url);
        Activity_03.putExtra("ID", m_NewsExcerpt.get(p).Id);

        m_Context.startActivity(Activity_03);
    }

    @Override public void onItemLongClick(View v, int p)
    {

    }
}
