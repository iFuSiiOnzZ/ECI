package elchapuzasinformatico.com.eci.Utilities.Html;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.AsyncTask;
import android.text.Html.ImageGetter;
import android.view.Display;
import android.view.WindowManager;
import android.widget.TextView;

import java.io.InputStream;

import elchapuzasinformatico.com.eci.Utilities.Network.DownloadUtilities;

/**
 * http://stackoverflow.com/questions/7424512/android-html-imagegetter-as-asynctask
 */
public class HtmlImageGetter implements ImageGetter
{
    Context m_Context = null;
    TextView m_View = null;

    /**
     *
     * @param l_View
     * @param l_Context
     */
    public HtmlImageGetter(TextView l_View, Context l_Context)
    {
        m_Context = l_Context;
        m_View = l_View;
    }

    /**
     *
     * @param source
     * @return
     */
    public Drawable getDrawable(String source)
    {
        UrlDrawable urlDrawable = new UrlDrawable();

        ImageGetterAsyncTask asyncTask = new ImageGetterAsyncTask( urlDrawable);
        asyncTask.execute(source);

        return urlDrawable;
    }

    public class ImageGetterAsyncTask extends AsyncTask<String, Void, Drawable>
    {
        UrlDrawable m_UrlDrawer = null;

        /**
         *
         * @param l_Drawable
         */
        public ImageGetterAsyncTask(UrlDrawable l_Drawable)
        {
            m_UrlDrawer = l_Drawable;
        }

        /**
         *
         * @param l_Url
         * @return
         */
        @Override protected Drawable doInBackground(String... l_Url)
        {
            try
            {
                InputStream l_InputStream = DownloadUtilities.getInputStreamFromNetwork(l_Url[0]);
                Bitmap l_Bitmap = BitmapFactory.decodeStream(l_InputStream);

                WindowManager l_WindowManager = (WindowManager) m_Context.getSystemService(Context.WINDOW_SERVICE);
                Display l_Display = l_WindowManager.getDefaultDisplay();

                Point l_WindowsSize = new Point(); l_Display.getSize(l_WindowsSize);
                float l_ScaleFactor = ((float)l_WindowsSize.x / (float)l_Bitmap.getWidth());

                Drawable l_Drawable = new BitmapDrawable(m_Context.getResources(), Bitmap.createScaledBitmap(l_Bitmap, l_WindowsSize.x, Math.round(l_Bitmap.getHeight() * l_ScaleFactor), true));
                l_Drawable.setBounds(0, 0, l_Drawable.getIntrinsicWidth(), l_Drawable.getIntrinsicHeight());

                return l_Drawable;
            }
            catch (Exception e)
            {
                e.printStackTrace();
                return null;
            }
        }

        /**
         *
         * @param l_Drawable
         */
        @Override protected void onPostExecute(Drawable l_Drawable)
        {
            if(l_Drawable == null) return;

            m_UrlDrawer.setBounds(0, 0, l_Drawable.getIntrinsicWidth(), l_Drawable.getIntrinsicHeight());
            m_UrlDrawer.setDrawable(l_Drawable);

            m_View.setMinimumHeight(m_View.getHeight() + l_Drawable.getIntrinsicHeight());
            m_View.invalidate();

            m_View.setText(m_View.getText());
        }
    }

    public class UrlDrawable extends BitmapDrawable
    {
        protected Drawable m_Drawable = null;

        /**
         *
         * @param l_Drawable
         */
        public void setDrawable(Drawable l_Drawable)
        {
            m_Drawable = l_Drawable;
        }

        /**
         *
         * @param canvas
         */
        @Override public void draw(Canvas canvas)
        {
            if(m_Drawable != null) m_Drawable.draw(canvas);
        }
    }
}
