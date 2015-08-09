package elchapuzasinformatico.com.eci.Views;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.util.AttributeSet;
import android.widget.ImageView;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;

import elchapuzasinformatico.com.eci.Utilities.Cache.BitmapRamCache;

/**
 * Created by AnDrEi AJ on 27/05/2015.
 */
public class WebImageView extends ImageView
{
    /**
     *
     * @param context
     */
    public WebImageView(Context context)
    {
        super(context);
    }

    /**
     *
     * @param context
     * @param attrs
     */
    public WebImageView(Context context, AttributeSet attrs)
    {
        super(context, attrs);
    }

    /**
     *
     * @param context
     * @param attrs
     * @param defStyleAttr
     */
    public WebImageView(Context context, AttributeSet attrs, int defStyleAttr)
    {
        super(context, attrs, defStyleAttr);
    }

    /**
     *
     * @param l_URL
     */
    public void setURL(String l_URL)
    {
        new loadImageFromInternet().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, l_URL);
    }

    /**
     *
     */
    private class loadImageFromInternet extends AsyncTask<String, Void, Bitmap>
    {
        /**
         *
         * @param l_Urls
         * @return
         */
        protected Bitmap doInBackground(String... l_Urls)
        {
            if(l_Urls[0] == null) return null;

            Bitmap l_Bitmap = BitmapRamCache.getInstance().get(l_Urls[0]);
            if(l_Bitmap != null) return l_Bitmap;

            try
            {
                InputStream l_InputStream = (InputStream) new URL(l_Urls[0]).getContent();
                l_Bitmap = BitmapFactory.decodeStream(l_InputStream);

                BitmapRamCache.getInstance().put(l_Urls[0], l_Bitmap);
                return l_Bitmap;
            }
            catch(IOException e)
            {
                e.printStackTrace();
            }

            return null;
        }

        /**
         *
         * @param l_Drawable
         */
        protected void onPostExecute(Bitmap l_Drawable)
        {
            setImageBitmap(l_Drawable);
            invalidate();
        }
    }
}
