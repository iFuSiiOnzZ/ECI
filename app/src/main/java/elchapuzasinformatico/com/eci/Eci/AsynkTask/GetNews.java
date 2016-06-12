package elchapuzasinformatico.com.eci.Eci.AsynkTask;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.view.View;

import com.google.gson.reflect.TypeToken;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;

import elchapuzasinformatico.com.eci.ClickListeners.onClickListenerInterface;
import elchapuzasinformatico.com.eci.Utilities.Network.DownloadUtilities;
import elchapuzasinformatico.com.eci.Eci.SharedPref.SettingsPrefs;
import elchapuzasinformatico.com.eci.Threads.BitmapMemCache;
import elchapuzasinformatico.com.eci.Adapters.NewsAdapter;
import elchapuzasinformatico.com.eci.Eci.Models.NewsInfo;
import elchapuzasinformatico.com.eci.Eci.Models.URLS;
import elchapuzasinformatico.com.eci.NewsActivity;

/**
 * Created by AnDrEi AJ on 31/05/2015.
 * Modificado: 10/06/2016 20:28     Quitado los comentarios
 *                                  Quitado el prefijo de las variables locales
 */
public class GetNews extends AsyncTask<Integer, Void, Void> implements onClickListenerInterface
{
    private ArrayList<NewsInfo> m_Data = null;
    private NewsAdapter m_PostAdapter = null;
    private Context m_Context = null;

    public GetNews(NewsAdapter PostAdapter, Context Context)
    {
        m_PostAdapter = PostAdapter;
        m_Context = Context;
    }

    @Override protected Void doInBackground(Integer... PageNumber)
    {
        String JSonString = DownloadUtilities.getStringFromNetwork(URLS.GetPostFromPage(PageNumber[0]));
        if(JSonString == null) return null;

        try
        {
            Gson l_Gson = new Gson();
            JSONObject l_Root = new JSONObject(JSonString);

            if(!l_Root.getString("status").equalsIgnoreCase("ok")) return null;
            m_Data = l_Gson.fromJson(l_Root.getJSONArray("posts").toString(), new TypeToken<ArrayList<NewsInfo>>(){}.getType());
        }
        catch(JSONException e)
        {
            e.printStackTrace();
        }

        return null;
    }

    @Override protected void onPostExecute(Void Result)
    {
        if(m_PostAdapter == null || m_Data == null) return;

        if(new SettingsPrefs(m_Context).GetPreloadImages())
        {
            for(int i = 0; i < m_Data.size(); i++)
            {
                new BitmapMemCache(m_Data.get(i).m_Thumbnail).execute();
            }
        }

        m_PostAdapter.setData(m_Data);
        m_PostAdapter.notifyDataSetChanged();
    }

    @Override public void onItemClick(View View, int Position)
    {
        if(m_Data == null || Position >= m_Data.size()) return;
        Intent ShowPost = new Intent(m_Context, NewsActivity.class);

        ShowPost.putExtra("postTitle", m_Data.get(Position).m_Title);
        ShowPost.putExtra("postId", m_Data.get(Position).m_Id);

        m_Context.startActivity(ShowPost);
    }

    @Override public void onItemLongClick(View View, int Position)
    {

    }
}
