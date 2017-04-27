package eu.ifussionzz.wpReader.Interface.AsyncTask;

import android.os.AsyncTask;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import eu.ifussionzz.wpReader.Data.Constants;
import eu.ifussionzz.wpReader.Data.Excerpt.NewsExcerpt;
import eu.ifussionzz.wpReader.Data.Media.Media;
import eu.ifussionzz.wpReader.Interface.View.WebImageView;
import eu.ifussionzz.wpReader.Utils.Network;

public class LoadImageAsync extends AsyncTask<Integer, Void, String>
{
    WebImageView m_WebImageView = null;
    NewsExcerpt m_NewsExcerpt = null;

    public LoadImageAsync(WebImageView v, NewsExcerpt n)
    {
        m_WebImageView = v;
        m_NewsExcerpt = n;
    }

    @Override protected String doInBackground(Integer... params)
    {
        if(m_NewsExcerpt.thumbnail != null)
        {
            return m_NewsExcerpt.thumbnail;
        }

        try
        {
            String JSonString = Network.GetText(Constants.GetMediaData(m_NewsExcerpt.featured_media));
            if (JSonString == null) return null;

            Media media = new Gson().fromJson(new JSONObject(JSonString).get("media_details").toString(), new TypeToken<Media>(){ }.getType());
            if (media == null || media.images == null || media.images.medium == null) return null;

            String MediaURL = media.images.medium.source_url;
            if(MediaURL == null) MediaURL = media.images.thumbnail.source_url;

            m_NewsExcerpt.thumbnail = MediaURL;
            return MediaURL;
        }
        catch (IOException e)
        {
            e.printStackTrace();
            return null;
        }
        catch (JSONException e)
        {
            e.printStackTrace();
            return null;
        }
    }

    @Override protected void onPostExecute(String Result)
    {
        if(Result != null)
        {
            m_WebImageView.SetURL(Result);
        }
    }
}
