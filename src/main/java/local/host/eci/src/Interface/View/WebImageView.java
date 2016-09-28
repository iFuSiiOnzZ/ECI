package local.host.eci.src.Interface.View;

import android.graphics.BitmapFactory;
import android.graphics.Bitmap;

import android.content.Context;

import android.graphics.drawable.Drawable;
import android.os.AsyncTask;

import android.util.AttributeSet;
import android.widget.ImageView;

import java.security.NoSuchAlgorithmException;
import java.net.URL;

import java.io.IOException;
import java.io.InputStream;

import local.host.eci.src.Utils.Cache.BitmapRAMCache;
import local.host.eci.src.Utils.Utils;
import local.host.eci.R;


public class WebImageView extends ImageView
{
    public WebImageView(Context context)
    {
        super(context);
    }

    public WebImageView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
    }

    public WebImageView(Context context, AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
    }

    public void SetURL(String l_URL)
    {
        new LoadImageFromInternet().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, l_URL);
    }

    private class LoadImageFromInternet extends AsyncTask<String, Void, Bitmap>
    {
        protected Bitmap doInBackground(String... Urls)
        {
            try
            {
                String MD5Key = Utils.MD5(Urls[0]);
                Bitmap Img = BitmapRAMCache.GetInstance().get(MD5Key);

                if(Img != null) return Img;
                Img = BitmapFactory.decodeStream((InputStream) new URL(Urls[0]).getContent());

                BitmapRAMCache.GetInstance().put(MD5Key, Img);
                return Img;
            }
            catch (IOException e)
            {
                e.printStackTrace();
                return null;
            }
            catch (NoSuchAlgorithmException e)
            {
                e.printStackTrace();
                return null;
            }
        }

        protected void onPostExecute(Bitmap BitmapImage)
        {
            setImageBitmap(BitmapImage);
            invalidate();
        }
    }
}
