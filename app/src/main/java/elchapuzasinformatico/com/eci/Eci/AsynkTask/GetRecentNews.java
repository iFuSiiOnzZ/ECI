package elchapuzasinformatico.com.eci.Eci.AsynkTask;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


import elchapuzasinformatico.com.eci.ClickListeners.onClickListenerRecycleView;
import elchapuzasinformatico.com.eci.ClickListeners.onClickListenerInterface;
import elchapuzasinformatico.com.eci.Utilities.Network.DownloadUtilities;
import elchapuzasinformatico.com.eci.Adapters.RecentNewsAdapter;
import elchapuzasinformatico.com.eci.Eci.Models.NewsInfo;
import elchapuzasinformatico.com.eci.Eci.Models.URLS;
import elchapuzasinformatico.com.eci.NewsActivity;
import elchapuzasinformatico.com.eci.R;

/**
 * Created by AnDrEi AJ on 19/07/2015.
 */
public class GetRecentNews extends AsyncTask<Integer, Void, Void> implements onClickListenerInterface
{
    private ArrayList<NewsInfo> m_Data = null;
    private RecyclerView m_RecycleView = null;
    private Context m_Context = null;

    /**
     * Constructor.
     * @param l_RecycleView la vista donde se cargan los datos.
     * @param l_Context contexto de ejecucion.
     */
    public GetRecentNews(RecyclerView l_RecycleView, Context l_Context)
    {
        m_RecycleView = l_RecycleView;
        m_Context = l_Context;
    }

    /**
     * Carga las noticias de forma asincrona.
     * @param l_NumNews numero de noticias.
     * @return nada, no se usa.
     */
    @Override protected Void doInBackground(Integer... l_NumNews)
    {
        String l_JSonString = DownloadUtilities.getStringFromNetwork(URLS.getRecentPosts(l_NumNews[0]));
        if(l_JSonString == null) return null;

        try
        {
            Gson l_Gson = new Gson();
            JSONObject l_Root = new JSONObject(l_JSonString);

            if(!l_Root.getString("status").equalsIgnoreCase("ok")) return null;
            m_Data = l_Gson.fromJson(l_Root.getJSONArray("posts").toString(), new TypeToken<ArrayList<NewsInfo>>(){}.getType());
        }
        catch(JSONException e)
        {
            e.printStackTrace();
        }

        return null;
    }

    /**
     * Carga las noticias en la vista y habilita el click.
     * @param l_Result nada (no se usa)
     */
    @Override protected void onPostExecute(Void l_Result)
    {
        m_RecycleView.setAdapter(new RecentNewsAdapter(m_Data, R.layout.recents_card, m_Context));
        m_RecycleView.addOnItemTouchListener(new onClickListenerRecycleView(m_Context, this));
    }

    /**
     * Cuando un elemento ha sido pulsado.
     *
     * @param l_View vista que se ha pulsado.
     * @param l_Position posicion que ocupa en lista de vistas.
     */
    @Override public void onItemClick(View l_View, int l_Position)
    {
        if(m_Data == null || l_Position >= m_Data.size()) return;

        Intent l_ShowPost = new Intent(m_Context, NewsActivity.class);
        l_ShowPost.putExtra("postId", m_Data.get(l_Position).m_Id);
        m_Context.startActivity(l_ShowPost);
    }

    /**
     * Cuando un elemento es mantido pulsado.
     *
     * @param l_View vista que ha pulsado.
     * @param l_Position posicion que ocupa en la lista de vistas.
     */
    @Override public void onItemLongClick(View l_View, int l_Position)
    {

    }
}
