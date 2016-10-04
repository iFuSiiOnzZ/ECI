package local.host.eci.src.Interface.AsyncTask;

import android.support.v7.widget.RecyclerView;
import android.graphics.Bitmap;

import android.content.Context;
import android.os.AsyncTask;

import com.google.gson.reflect.TypeToken;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.io.IOException;

import local.host.eci.src.Data.Constants;

import local.host.eci.src.Interface.Listener.OnClickListenerRecycleView;
import local.host.eci.src.Interface.View.Adapter.ExcerptAdapter;
import local.host.eci.src.Data.NewsExcerpt;

import local.host.eci.src.Utils.Cache.BitmapRAMCache;
import local.host.eci.src.Utils.Network;

import local.host.eci.src.Utils.Utils;
public class GetExcerpt extends AsyncTask<Integer, Void, ArrayList<NewsExcerpt>> implements Runnable
{
    ArrayList<NewsExcerpt> m_NewsExcerptAray = null;
    private RecyclerView m_RecyclerView = null;
    private Context m_Context = null;


    public GetExcerpt(Context c, RecyclerView rv)
    {
        m_RecyclerView = rv;
        m_Context = c;
    }

    @Override protected ArrayList<NewsExcerpt> doInBackground(Integer... PageNumber)
    {
        try
        {
            String JSonString = Network.GetText(Constants.GetPostFromPage(PageNumber[0]));
            if(JSonString == null) return null;

            Gson GSon = new Gson();
            JSONObject JSonRoot = new JSONObject(JSonString);

            if(!JSonRoot.getString("status").equalsIgnoreCase("ok")) return null;
            m_NewsExcerptAray = GSon.fromJson(JSonRoot.getJSONArray("posts").toString(), new TypeToken<ArrayList<NewsExcerpt>>(){}.getType());
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
        catch (JSONException e)
        {
            e.printStackTrace();
        }

        new Thread(this).start();
        return m_NewsExcerptAray;
    }

    @Override protected void onPostExecute(ArrayList<NewsExcerpt> Result)
    {
        ExcerptAdapter Adapter = new ExcerptAdapter(Result);
        OnClickListenerRecycleView Click = new OnClickListenerRecycleView(m_Context, Adapter);

        m_RecyclerView.setAdapter(Adapter);
        m_RecyclerView.addOnItemTouchListener(Click);
    }

    @Override public void run()
    {
        for(int i = 0; i < m_NewsExcerptAray.size(); ++i)
        {
            try
            {
                Bitmap Bmp = Network.GetImage(m_NewsExcerptAray.get(i).Thumbnail);
                BitmapRAMCache.GetInstance().put(Utils.MD5(m_NewsExcerptAray.get(i).Thumbnail), Bmp);
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
            catch (NoSuchAlgorithmException e)
            {
                e.printStackTrace();
            }

        }
    }
}