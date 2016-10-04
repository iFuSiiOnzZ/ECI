package local.host.eci.src.Interface.View.Adapter;

import android.os.AsyncTask;
import android.support.v7.widget.LinearLayoutManager;

import android.support.v4.view.PagerAdapter;

import android.support.v7.widget.RecyclerView;
import android.view.ViewGroup;
import android.view.View;

import android.content.Context;

import local.host.eci.src.Interface.AsyncTask.GetExcerpt;

public class InfinitePagerAdapter extends PagerAdapter
{
    private Context m_Context = null;

    public InfinitePagerAdapter(Context c)
    {
        m_Context = c;
    }

    @Override public int getCount()
    {
        return Integer.MAX_VALUE;
    }

    @Override public boolean isViewFromObject(View view, Object object)
    {
        return view == object;
    }

    @Override public void destroyItem(ViewGroup container, int position, Object object)
    {
        container.removeView((View)object);
    }

    @Override public Object instantiateItem(ViewGroup container, int position)
    {
        RecyclerView RecyclerView = new RecyclerView(m_Context);
        RecyclerView.setLayoutManager(new LinearLayoutManager(m_Context));

        RecyclerView.setAdapter(new ExcerptAdapter(null));
        new GetExcerpt(m_Context, RecyclerView).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, position + 1);

        container.addView(RecyclerView);
        return RecyclerView;
    }
}
