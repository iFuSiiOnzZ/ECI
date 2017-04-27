package eu.ifussionzz.wpReader.Interface.View.Adapter;

import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;

import java.io.Serializable;
import java.util.ArrayList;
import android.text.Html;

import eu.ifussionzz.wpReader.Interface.AsyncTask.LoadImageAsync;
import eu.ifussionzz.wpReader.Interface.Listener.OnClickListenerInterface;
import eu.ifussionzz.wpReader.Interface.View.Holder.ExcerptHolder;
import eu.ifussionzz.wpReader.Data.Excerpt.NewsExcerpt;
import eu.ifussionzz.wpReader.Activity_03;
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

        holder.Title.setText(Html.fromHtml(NewsExcerpt.title.rendered));
        holder.Description.setText(Html.fromHtml(NewsExcerpt.excerpt.rendered));

        holder.Time.setText(NewsExcerpt.date);
        //holder.Category.setText(NewsExcerpt.Categories.get(0).Title);

        /*String ComentsText = m_Context.getString(R.string.comments_lcp);
        if(NewsExcerpt.NumComments == 1) ComentsText = m_Context.getString(R.string.comments_lcs);
        holder.Comments.setText(NewsExcerpt.NumComments + " " + ComentsText);*/

        new LoadImageAsync(holder.Image, NewsExcerpt).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    @Override public int getItemCount()
    {
        return m_NewsExcerpt == null ? 0 : m_NewsExcerpt.size();
    }

    @Override public void onItemClick(View v, int p)
    {
        if(m_NewsExcerpt == null || p >= m_NewsExcerpt.size()) return;
        Intent Activity_03 = new Intent(m_Context, Activity_03.class);

        if( m_NewsExcerpt.get(p).thumbnail != null) Activity_03.putExtra("THUMBNAIL", m_NewsExcerpt.get(p).thumbnail);
        else Activity_03.putExtra("THUMBNAIL", "");

        Activity_03.putExtra("CONTENT", m_NewsExcerpt.get(p).content.rendered);
        Activity_03.putExtra("TITLE", m_NewsExcerpt.get(p).title.rendered);

        Activity_03.putExtra("URL", m_NewsExcerpt.get(p).link);
        Activity_03.putExtra("ID", m_NewsExcerpt.get(p).id);

        m_Context.startActivity(Activity_03);
    }

    @Override public void onItemLongClick(View v, int p)
    {

    }
}
