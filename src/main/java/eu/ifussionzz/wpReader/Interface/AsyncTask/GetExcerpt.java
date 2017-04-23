package eu.ifussionzz.wpReader.Interface.AsyncTask;

import android.app.Activity;
import android.support.v7.widget.RecyclerView;
import android.graphics.Bitmap;

import android.content.Context;
import android.os.AsyncTask;

import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.google.gson.Gson;

import org.json.JSONException;
import org.json.JSONObject;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.io.IOException;

import eu.ifussionzz.wpReader.Data.Constants;

import eu.ifussionzz.wpReader.Data.Media.Guid;
import eu.ifussionzz.wpReader.Data.Media.Media;
import eu.ifussionzz.wpReader.Interface.Listener.OnClickListenerRecycleView;
import eu.ifussionzz.wpReader.Interface.View.Adapter.ExcerptAdapter;
import eu.ifussionzz.wpReader.Data.Excerpt.NewsExcerpt;

import eu.ifussionzz.wpReader.Utils.Cache.BitmapRAMCache;
import eu.ifussionzz.wpReader.Utils.Network;

import eu.ifussionzz.wpReader.Utils.Utils;

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

            Gson gson = new Gson();
            m_NewsExcerptAray = gson.fromJson(JSonString, new TypeToken<ArrayList<NewsExcerpt>>(){}.getType());
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        for(int i = 0; i < m_NewsExcerptAray.size(); ++i)
        {
            try
            {
                String JSonString = Network.GetText(Constants.GetMediaData(m_NewsExcerptAray.get(i).featured_media));
                if (JSonString == null) continue;

                Media media = new Gson().fromJson(new JSONObject(JSonString).get("media_details").toString(), new TypeToken<Media>(){ }.getType());
                if (media == null) continue;

                if(media.images.post_thumbnail != null) m_NewsExcerptAray.get(i).thumbnail = media.images.post_thumbnail.source_url;
                else m_NewsExcerptAray.get(i).thumbnail = media.images.thumbnail.source_url;
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
            catch (JSONException e)
            {
                e.printStackTrace();
            }
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
                if(m_NewsExcerptAray.get(i).thumbnail !=  null)
                {
                    Bitmap Bmp = Network.GetImage(m_NewsExcerptAray.get(i).thumbnail);
                    BitmapRAMCache.GetInstance().put(Utils.MD5(m_NewsExcerptAray.get(i).thumbnail), Bmp);
                }
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