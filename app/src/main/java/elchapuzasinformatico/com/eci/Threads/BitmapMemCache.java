package elchapuzasinformatico.com.eci.Threads;

import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import elchapuzasinformatico.com.eci.Utilities.Cache.BitmapRamCache;

/**
 * Created by AnDrEi AJ on 15/09/2015.
 */
public class BitmapMemCache extends AsyncTask<Void, Void, Bitmap>
{
    /** */
    String m_ImageUrl = null;

    public  BitmapMemCache(String l_ImageUrl)
    {
        m_ImageUrl = l_ImageUrl;
    }

    @Override protected Bitmap doInBackground(Void... params)
    {
        if(m_ImageUrl == null) return null;

        Bitmap l_Bitmap = BitmapRamCache.getInstance().get(m_ImageUrl);
        if(l_Bitmap != null) return null;

        try
        {
            InputStream l_InputStream = (InputStream) new URL(m_ImageUrl).getContent();
            l_Bitmap = BitmapFactory.decodeStream(l_InputStream);
        }
        catch(IOException e)
        {
            e.printStackTrace();
        }

        return l_Bitmap;
    }

    @Override protected void onPostExecute(Bitmap l_Bitmap)
    {
        if(l_Bitmap == null) return;

        Bitmap l_BitmapCache = BitmapRamCache.getInstance().get(m_ImageUrl);
        if(l_BitmapCache == null)  BitmapRamCache.getInstance().put(m_ImageUrl, l_Bitmap);
    }
}
