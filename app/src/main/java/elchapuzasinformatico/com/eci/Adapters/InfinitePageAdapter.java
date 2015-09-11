package elchapuzasinformatico.com.eci.Adapters;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v4.view.PagerAdapter;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;

import elchapuzasinformatico.com.eci.ClickListeners.onClickListenerRecycleView;
import elchapuzasinformatico.com.eci.Eci.AsynkTask.GetNews;
import elchapuzasinformatico.com.eci.R;

/**
 * Created by AnDrEi AJ on 18/06/2015.
 */
public class InfinitePageAdapter extends PagerAdapter
{
    private Context m_Context = null;

    /**
     * Constructor.
     * @param l_Context contexto de ejecucion.
     */
    public InfinitePageAdapter(Context l_Context)
    {
        m_Context = l_Context;
    }

    /**
     *
     * @param l_Container contiene la vista padre.
     * @param l_Position numero de pagina que vamos a cargar.
     * @return una  nueva vista (RecyclerView) generada.
     */
    @Override public Object instantiateItem(ViewGroup l_Container, int l_Position)
    {
        LinearLayoutManager layoutManager = new LinearLayoutManager(m_Context);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);

        RecyclerView l_RecyclerView = new RecyclerView(m_Context);
        NewsAdapter l_PostAdapter = new NewsAdapter(null, R.layout.news_card, m_Context);
        l_RecyclerView.setLayoutParams(new RelativeLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

        l_RecyclerView.setLayoutManager(layoutManager);
        l_RecyclerView.setAdapter(l_PostAdapter);

        GetNews l_News = new GetNews(l_PostAdapter, m_Context);
        l_News.executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, l_Position + 1);

        l_RecyclerView.addOnItemTouchListener(new onClickListenerRecycleView(m_Context, l_News));
        l_Container.addView(l_RecyclerView);

        return l_RecyclerView;
    }

    /**
     *
     * @param container contiene la vista padre.
     * @param position posicion en la lista de vistas (no se usa).
     * @param object objeto a quitar de la lista de vistas.
     */
    @Override public void destroyItem(ViewGroup container, int position, Object object)
    {
        container.removeView((View) object);
    }

    /**
     * Devuelve el numero total de paginas. Al ser "infinito", devuelve el maximo de un int.
     * @return numero de paginas disponibles.
     */
    @Override public int getCount()
    {
        return Integer.MAX_VALUE;
    }

    /**
     * Compara si una vista es instancia de un objeto.
     * @param view vista a compara.
     * @param o objeto a comprar.
     * @return verdadero si son la misma cosa.
     */
    @Override public boolean isViewFromObject(View view, Object o)
    {
        return view == o;
    }
}
