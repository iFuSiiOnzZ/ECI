package elchapuzasinformatico.com.eci.Adapters;

import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.content.Context;
import android.os.AsyncTask;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v4.view.PagerAdapter;

import android.widget.RelativeLayout;
import android.view.ViewGroup;
import android.view.View;

import elchapuzasinformatico.com.eci.ClickListeners.onClickListenerRecycleView;
import elchapuzasinformatico.com.eci.Eci.AsynkTask.GetNews;
import elchapuzasinformatico.com.eci.R;

/**
 * Created by AnDrEi AJ on 18/06/2015.
 * Modificado: 10/06/2016 20:28     Quitado los comentarios
 *                                  Quitado el prefijo de las variables locales
 */
public class InfinitePageAdapter extends PagerAdapter implements ViewPager.OnPageChangeListener
{
    private Context     m_Context = null;
    private ActionBar   m_ActionBar = null;

    public InfinitePageAdapter(Context Context, ActionBar ActionBar)
    {
        m_Context = Context;
        m_ActionBar = ActionBar;
    }

    @Override public Object instantiateItem(ViewGroup Container, int Position)
    {
        LinearLayoutManager LayoutManager = new LinearLayoutManager(m_Context);
        LayoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        RecyclerView RecyclerView = new RecyclerView(m_Context);
        NewsAdapter PostAdapter = new NewsAdapter(null, R.layout.news_card, m_Context);
        RecyclerView.setLayoutParams(new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

        RecyclerView.setLayoutManager(LayoutManager);
        RecyclerView.setAdapter(PostAdapter);

        GetNews News = new GetNews(PostAdapter, m_Context);
        News.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, Position + 1);

        RecyclerView.addOnItemTouchListener(new onClickListenerRecycleView(m_Context, News));
        Container.addView(RecyclerView);

        return RecyclerView;
    }


    @Override public void destroyItem(ViewGroup Container, int Position, Object Object)
    {
        Container.removeView((View) Object);
    }

    @Override public int getCount()
    {
        return Integer.MAX_VALUE;
    }

    @Override public boolean isViewFromObject(View view, Object o)
    {
        return view == o;
    }

    @Override public void onPageScrolled(int Position, float PositionOffset, int PositionOffsetPixels)
    {

    }

    @Override public void onPageSelected(int Position)
    {
        if(m_ActionBar != null)
        {
            m_ActionBar.setTitle("Noticias - " + (Position + 1));
        }
    }

    @Override public void onPageScrollStateChanged(int State)
    {

    }
}
